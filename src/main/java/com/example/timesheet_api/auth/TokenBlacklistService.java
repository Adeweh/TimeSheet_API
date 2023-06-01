package com.example.timesheet_api.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TokenBlacklistService {
    @Autowired
    TokenBlacklistRepository tokenBlacklistRepository;

    public void blacklist(String token) {
        tokenBlacklistRepository.save(new BlackListedToken(token));
    }

    public boolean isTokenBlacklisted(String token) {
        return tokenBlacklistRepository.existsByToken(token);
    }
}
