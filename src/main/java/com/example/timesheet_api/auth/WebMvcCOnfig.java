package com.example.timesheet_api.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcCOnfig implements WebMvcConfigurer {
    @Autowired
    private BlackListFilter filter;

    public static String[] patterns = {
            "/api/v1/auth/login",
            "/api/v1/employee"
    };

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(filter).excludePathPatterns(patterns);
    }
}
