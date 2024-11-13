package com.zerobase.domain.config;

import com.zerobase.domain.domain.common.UserType;
import com.zerobase.domain.domain.common.UserVo;
import com.zerobase.domain.util.Aes256Util;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.Objects;

public class JwtAuthenticationProvider {
    private SecretKey secretKey = Keys.secretKeyFor(SignatureAlgorithm.HS256);

    private long tokenValidTime = 1000L * 60 *60 *24;

    public String createToken(String userPk, Long id, UserType userType){
        Claims claims = Jwts.claims().setSubject(Aes256Util.encrypt(userPk)).setId(Aes256Util.encrypt(id.toString()));
        claims.put("roles", userType);
        Date now = new Date();
        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(now)
                .setExpiration(new Date(now.getTime()+tokenValidTime))
                .signWith(secretKey)
                .compact();
    }
    public boolean validateToken(String jwtToken){
        try {
            Jws<Claims> claimsJws = Jwts.parserBuilder().setSigningKey(secretKey).build().parseClaimsJws(jwtToken);
            return !claimsJws.getBody().getExpiration().before(new Date());
        }catch (Exception e){
            return false;
        }
    }
    public UserVo getUserVo(String token) throws IllegalAccessException {
        Claims c = Jwts.parserBuilder().setSigningKey(secretKey).build().parseClaimsJws(token).getBody();
        String decryptedId = Aes256Util.decrypt(c.getId());
        if (decryptedId == null){
            throw new IllegalAccessException("Decrypted ID is null");
        }


        return new UserVo(Long.valueOf(Objects.requireNonNull(Aes256Util.decrypt(c.getId()))), Aes256Util.decrypt(c.getSubject()));
    }
}
