package com.example.timesheet_api.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class TimeRecord {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;

    @ManyToOne
    @JoinColumn(name = "employee_id")
    private Employee employee;
    private LocalDateTime clockInTime;
    private LocalDateTime clockOutTime;
    private LocalDateTime startBreak;
    private LocalDateTime endBreak;

}
