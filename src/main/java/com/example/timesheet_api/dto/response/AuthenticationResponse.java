package com.example.timesheet_api.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AuthenticationResponse {
    private String accessToken;
    private String id;

    public static AuthenticationResponse of(String jwtToken, String id) {
        return new AuthenticationResponse(jwtToken, id);
    }
}
