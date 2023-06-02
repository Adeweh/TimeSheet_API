package com.example.timesheet_api.controller;

import com.example.timesheet_api.model.Employee;
import com.example.timesheet_api.model.TimeRecord;
import com.example.timesheet_api.service.TimeRecordService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/api/v1/timerecord")
@AllArgsConstructor
public class NonAdminTaskController {
    private final TimeRecordService timeRecordService;

    @PostMapping("/clock-in")
    public ResponseEntity<TimeRecord> clockIn(@AuthenticationPrincipal Employee employee){
        TimeRecord clockInRecord = timeRecordService.clockIn(employee.getId());
        return ResponseEntity.status(HttpStatus.CREATED).body(clockInRecord);
    }

    @PostMapping("/clock-out")
    public ResponseEntity<TimeRecord> clockOut(@AuthenticationPrincipal Employee employee){
        TimeRecord clockOutRecord = timeRecordService.clockOut(employee.getId());
        return ResponseEntity.status(HttpStatus.CREATED).body(clockOutRecord);
    }

    @PostMapping("/start-break")
    public ResponseEntity<TimeRecord> startBreak(@AuthenticationPrincipal Employee employee){
        TimeRecord startBreakRecord = timeRecordService.startBreak(employee.getId());
        return ResponseEntity.status(HttpStatus.CREATED).body(startBreakRecord);
    }

    @PostMapping("/end-break")
    public ResponseEntity<TimeRecord> endBreak(@AuthenticationPrincipal Employee employee){
        TimeRecord endBreakRecord = timeRecordService.endBreak(employee.getId());
        return ResponseEntity.status(HttpStatus.CREATED).body(endBreakRecord);
    }
}
