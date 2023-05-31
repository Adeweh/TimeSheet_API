package com.example.timesheet_api.config;

import com.example.timesheet_api.dto.response.TokenResponse;
import org.springframework.security.core.Authentication;

public interface TokenGeneratorService {
    TokenResponse createToken(Authentication authentication);
}
