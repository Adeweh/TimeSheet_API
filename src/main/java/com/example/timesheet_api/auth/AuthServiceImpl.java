package com.example.timesheet_api.auth;

import com.example.timesheet_api.config.JwtService;
import com.example.timesheet_api.dto.request.AuthenticationRequest;
import com.example.timesheet_api.dto.request.RegisterRequest;
import com.example.timesheet_api.dto.response.AuthenticationResponse;
import com.example.timesheet_api.model.Employee;
import com.example.timesheet_api.model.Role;
import com.example.timesheet_api.repository.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService{
    private final EmployeeRepository repository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authManager;
    @Override
    public AuthenticationResponse register(RegisterRequest request) {
        Employee employee = Employee.builder()
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(Role.NON_ADMIN)
                .build();
        repository.save(employee);
        var jwtToken = jwtService.generateToken(employee);

        return AuthenticationResponse.builder()
                .accessToken(jwtToken)
                .build();
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
}

