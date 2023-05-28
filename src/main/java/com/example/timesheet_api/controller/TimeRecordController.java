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
public class TimeRecordController {
    private final TimeRecordService timeRecordService;

    @PostMapping("/clock-in")
    public ResponseEntity<TimeRecord> clockIn(@RequestBody TimeRecord timeRecord){
        TimeRecord clockInRecord = timeRecordService.clockIn(timeRecord);
        return ResponseEntity.status(HttpStatus.CREATED).body(clockInRecord);
    }

    @PostMapping("/clock-out")
    public ResponseEntity<TimeRecord> clockOut(@RequestBody TimeRecord timeRecord){
        TimeRecord clockOutRecord = timeRecordService.clockOut(timeRecord);
        return ResponseEntity.status(HttpStatus.CREATED).body(clockOutRecord);
    }

    @PostMapping("/start-break")
    public ResponseEntity<TimeRecord> startBreak(@RequestBody TimeRecord timeRecord){
        TimeRecord startBreakRecord = timeRecordService.startBreak(timeRecord);
        return ResponseEntity.status(HttpStatus.CREATED).body(startBreakRecord);
    }

    @PostMapping("/end-break")
    public ResponseEntity<TimeRecord> endBreak(@RequestBody TimeRecord timeRecord){
        TimeRecord endBreakRecord = timeRecordService.endBreak(timeRecord);
        return ResponseEntity.status(HttpStatus.CREATED).body(endBreakRecord);
    }
}
