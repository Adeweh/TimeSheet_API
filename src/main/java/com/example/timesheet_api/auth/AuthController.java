package com.example.timesheet_api.auth;


import com.example.timesheet_api.dto.request.AuthenticationRequest;
import com.example.timesheet_api.dto.request.LoginRequest;
import com.example.timesheet_api.dto.response.AuthenticationResponse;
import com.example.timesheet_api.dto.response.TokenResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;

    @PostMapping("/logout")
    public ResponseEntity<String> logout(@RequestHeader("Authorization") String token){
        return ResponseEntity.ok(authService.logout(token));

    }

    @PostMapping("/login")
    public ResponseEntity<TokenResponse> login(@RequestBody LoginRequest request){
        return ResponseEntity.ok(authService.login(request));

    }

}
