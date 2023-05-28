package com.example.timesheet_api.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class Employee {
    private int id;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String email;
}
