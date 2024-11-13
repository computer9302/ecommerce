package com.zerobase.domain.util;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

public class Aes256Util {
    public static String alg = "AES/CBC/PKCS5Padding"; // 암호화에 사용되는 알고리즘 정의 : AES 알고리즘, CBC모드와 PKCS5Padding 적용
    private static final String KEY = "ZEROBASEKEYISZEROBASEKEY"; // 암호화에 사용할 Secret Key
    private static final String IV = KEY.substring(0, 16);  // 초기화 벡터(Initialization Vector), KEY에서 처음~16자리를 잘라 사용

    public static String encrypt(String text){
        try {
            Cipher cipher = Cipher.getInstance(alg);
            SecretKeySpec keySpec = new SecretKeySpec(KEY.getBytes(), "AES");
            IvParameterSpec ivParameterSpec = new IvParameterSpec(IV.getBytes(StandardCharsets.UTF_8));
            cipher.init(Cipher.ENCRYPT_MODE, keySpec, ivParameterSpec);
            byte[] encrypted = cipher.doFinal(text.getBytes(StandardCharsets.UTF_8));
            return java.util.Base64.getEncoder().encodeToString(encrypted);
        }catch (Exception e){
            return null;
        }
    }

    public static String decrypt(String cipherText) throws IllegalAccessException {
        if (cipherText == null){
            throw new IllegalAccessException("cipherText cannot be null");
        }
        try {
            Cipher cipher = Cipher.getInstance(alg);
            SecretKeySpec keySpec = new SecretKeySpec(KEY.getBytes(), "AES");
            IvParameterSpec ivParameterSpec = new IvParameterSpec(IV.getBytes(StandardCharsets.UTF_8));
            cipher.init(Cipher.DECRYPT_MODE, keySpec, ivParameterSpec);

            byte[] decodedBytes = Base64.getDecoder().decode(cipherText);
            byte[] decrypted = cipher.doFinal(decodedBytes);
            return new String(decrypted, StandardCharsets.UTF_8);

        }catch (Exception e){
            System.out.println("Decryption error: " + e.getMessage());
            e.printStackTrace();
            return null;
        }
    }
}
