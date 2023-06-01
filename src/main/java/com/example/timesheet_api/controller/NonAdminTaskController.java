package com.example.timesheet_api.controller;

import com.example.timesheet_api.model.TimeRecord;
import com.example.timesheet_api.service.TimeRecordService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/api/v1/timerecord")
@AllArgsConstructor
public class NonAdminTaskController {
    private final TimeRecordService timeRecordService;

    @PostMapping("/clock-in")
    public ResponseEntity<TimeRecord> clockIn(@RequestBody String employeeId){
        TimeRecord clockInRecord = timeRecordService.clockIn(employeeId);
        return ResponseEntity.status(HttpStatus.CREATED).body(clockInRecord);
    }

    @PostMapping("/clock-out")
    public ResponseEntity<TimeRecord> clockOut(@RequestBody String employeeId){
        TimeRecord clockOutRecord = timeRecordService.clockOut(employeeId);
        return ResponseEntity.status(HttpStatus.CREATED).body(clockOutRecord);
    }

    @PostMapping("/start-break")
    public ResponseEntity<TimeRecord> startBreak(@RequestBody String employeeId){
        TimeRecord startBreakRecord = timeRecordService.startBreak(employeeId);
        return ResponseEntity.status(HttpStatus.CREATED).body(startBreakRecord);
    }

    @PostMapping("/end-break")
    public ResponseEntity<TimeRecord> endBreak(@RequestBody String employeeId){
        TimeRecord endBreakRecord = timeRecordService.endBreak(employeeId);
        return ResponseEntity.status(HttpStatus.CREATED).body(endBreakRecord);
    }
}
