package com.example.timesheet_api.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@Entity
public class Employee {
    @Id
    private String id;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String email;
    private String password;
    private boolean isEnabled;
    private Role role;

    public Employee() {
    }
}
