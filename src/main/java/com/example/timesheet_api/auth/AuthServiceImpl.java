package com.example.timesheet_api.auth;

import com.example.timesheet_api.config.JwtService;
import com.example.timesheet_api.config.TokenGeneratorService;
import com.example.timesheet_api.dto.request.AuthenticationRequest;
import com.example.timesheet_api.dto.request.LoginRequest;
import com.example.timesheet_api.dto.response.AuthenticationResponse;
import com.example.timesheet_api.dto.response.TokenResponse;
import com.example.timesheet_api.model.Employee;
import com.example.timesheet_api.repository.EmployeeRepository;
import com.example.timesheet_api.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService{
    private final EmployeeRepository repository;
    private final EmployeeService employeeService;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authManager;
    private final AuthenticationProvider authenticationProvider;
    private final TokenGeneratorService tokenGeneratorService;
    private final TokenBlacklistService tokenBlacklistService;
    @Override
    public String logout(String token) {
        tokenBlacklistService.blacklist(token);
        return "Logout Successful";
    }
    @Override
    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        authManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );
        Employee employee = repository.findByEmail(request.getEmail()).orElseThrow();
        var jwtToken = jwtService.generateToken(employee);
        return AuthenticationResponse.builder().accessToken(jwtToken).build();

    }
    @Override
    public TokenResponse login(LoginRequest loginRequest) {
        employeeService.ensureValidLoginDetails(loginRequest);
        Authentication authentication = authenticationProvider
                .authenticate(UsernamePasswordAuthenticationToken.unauthenticated(loginRequest.getEmail(), loginRequest.getPassword()));
        return tokenGeneratorService.createToken(authentication);
    }
}

