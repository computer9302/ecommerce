package com.zerobase.ecommerce.config.filter;

import com.zerobase.domain.config.JwtAuthenticationProvider;
import com.zerobase.domain.domain.common.UserVo;
import com.zerobase.ecommerce.service.CustomerService;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;

import java.io.IOException;

@WebFilter(urlPatterns = "/customer/*")
@RequiredArgsConstructor
public class CustomerFilter implements Filter {

    private final JwtAuthenticationProvider jwtAuthenticationProvider;
    private final CustomerService customerService;


    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        String token = req.getHeader("X-AUTH-TOKEN");
        if (!jwtAuthenticationProvider.validateToken(token)){
            throw new ServletException("Invalid Access");
        }
        UserVo vo = jwtAuthenticationProvider.getUserVo(token);
        customerService.findByIdAndEmail(vo.getId(), vo.getEmail()).orElseThrow(
                ()->new ServletException("Invalid access")
        );

    }
}
