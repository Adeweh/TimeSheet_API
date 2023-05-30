package com.example.timesheet_api.security;

import com.example.timesheet_api.exceptions.EmployeeNotFoundException;
import com.example.timesheet_api.model.Employee;
import com.example.timesheet_api.repository.EmployeeRepository;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@AllArgsConstructor
@Configuration
public class AuthBeanConfig {
    private final PasswordEncoder passwordEncoder;
    private final EmployeeRepository employeeRepository;
    @Bean
    public UserDetailsService userDetailsService() {
        return email -> {
            Employee employee = employeeRepository.findEmployeeByEmail(email).orElseThrow(
                    () -> new EmployeeNotFoundException("Employee not found")
            );
            return (UserDetails) employee;
        };
    }
    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService());
        authProvider.setPasswordEncoder(passwordEncoder);
        return authProvider;
    }
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}
