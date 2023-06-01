package com.example.timesheet_api.service;

import com.example.timesheet_api.model.TimeRecord;

public interface TimeRecordService {
    TimeRecord clockIn(String employeeId);

    TimeRecord clockOut(String employeeId);

    TimeRecord startBreak(String employeeId);

    TimeRecord endBreak(String employeeId);
}
