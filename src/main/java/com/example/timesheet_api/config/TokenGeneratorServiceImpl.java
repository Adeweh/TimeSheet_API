package com.example.timesheet_api.config;

import com.example.timesheet_api.dto.response.TokenResponse;
import com.example.timesheet_api.model.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.text.MessageFormat;

@Service
public class TokenGeneratorServiceImpl implements TokenGeneratorService{
    @Autowired
    private JwtService jwtService;
    @Override
    public TokenResponse createToken(Authentication authentication) {

        if (!(authentication.getPrincipal() instanceof Employee employee)) {
            throw new BadCredentialsException(
                    MessageFormat.format("principal {0} is not of User type", authentication.getPrincipal().getClass())
            );
        }

        TokenResponse tokenResponse = new TokenResponse();
        tokenResponse.setEmployeeId(employee.getId());
        tokenResponse.setAccess(jwtService.generateToken(employee));


        return tokenResponse;
    }


}
