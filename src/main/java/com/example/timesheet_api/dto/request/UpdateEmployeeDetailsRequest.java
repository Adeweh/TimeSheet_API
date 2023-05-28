package com.example.timesheet_api.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@Builder
public class UpdateEmployeeDetailsRequest {
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String email;
}
