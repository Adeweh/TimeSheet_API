package com.example.timesheet_api.dto.response;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AuthenticationResponse {
    private String accessToken;
    private String id;

}
