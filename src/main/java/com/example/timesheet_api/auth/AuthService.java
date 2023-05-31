package com.example.timesheet_api.auth;

import com.example.timesheet_api.dto.request.AuthenticationRequest;
import com.example.timesheet_api.dto.request.LoginRequest;
import com.example.timesheet_api.dto.request.RegisterRequest;
import com.example.timesheet_api.dto.response.AuthenticationResponse;
import com.example.timesheet_api.dto.response.TokenResponse;

public interface AuthService {
    AuthenticationResponse register(RegisterRequest request);

    AuthenticationResponse authenticate(AuthenticationRequest request);

    TokenResponse login(LoginRequest loginRequest);
}
