package com.example.timesheet_api.security;

import com.example.timesheet_api.dto.request.AuthenticationRequest;
import com.example.timesheet_api.dto.response.AuthenticationResponse;
import com.example.timesheet_api.model.Employee;
import com.example.timesheet_api.repository.EmployeeRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class SecurityServiceImpl implements SecurityService{
    private final AuthenticationManager authenticationManager;
    private final EmployeeRepository employeeRepository;
    private final JwtService jwtService;
    @Override
    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(request.getEmail().toLowerCase(), request.getPassword())
            );
            SecurityContextHolder.getContext().setAuthentication(authentication);
        } catch (BadCredentialsException e) {
            throw new RuntimeException(e.getLocalizedMessage());
        }
        Employee employee = employeeRepository.findEmployeeByEmail(request.getEmail().toLowerCase()).orElseThrow();
        SecuredUser user = new SecuredUser(employee);
        String jwtToken = jwtService.generateToken(user);
        return AuthenticationResponse.of(jwtToken, user.getId());
    }
}
