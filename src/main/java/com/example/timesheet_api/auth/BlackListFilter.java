package com.example.timesheet_api.auth;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.servlet.HandlerInterceptor;

import java.io.IOException;
@Component
@Slf4j
public class BlackListFilter implements HandlerInterceptor {
    @Autowired
    private TokenBlacklistService tokenBlacklistService;
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws ServletException, IOException {
        String token = request.getHeader("Authorization").substring(7);
        log.info("Blacklist Filter:  token: {}", token);
        if(tokenBlacklistService.isTokenBlacklisted(token)){
            throw new RuntimeException("Session Expired");
        }
        return true;
    }
}
