package com.example.timesheet_api.security;

import com.example.timesheet_api.dto.request.AuthenticationRequest;
import com.example.timesheet_api.dto.response.AuthenticationResponse;

public interface SecurityService {
    AuthenticationResponse authenticate(AuthenticationRequest request);
}
