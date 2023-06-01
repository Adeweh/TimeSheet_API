package com.example.timesheet_api.auth;

import com.example.timesheet_api.dto.request.AuthenticationRequest;
import com.example.timesheet_api.dto.request.LoginRequest;
import com.example.timesheet_api.dto.response.AuthenticationResponse;
import com.example.timesheet_api.dto.response.TokenResponse;

public interface AuthService {
    String logout(String token);

    AuthenticationResponse authenticate(AuthenticationRequest request);

    TokenResponse login(LoginRequest loginRequest);
}
