package com.example.timesheet_api.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
public class TimeRecord {
    private String id;
    private Employee employee;
    private LocalDateTime clockInTime;
    private LocalDateTime clockOutTime;

    private LocalDateTime startBreak;
    private LocalDateTime endBreak;

}
