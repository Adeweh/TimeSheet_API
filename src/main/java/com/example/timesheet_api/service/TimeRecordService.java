package com.example.timesheet_api.service;

import com.example.timesheet_api.model.TimeRecord;

public interface TimeRecordService {
    TimeRecord clockIn(TimeRecord timeRecord);

    TimeRecord clockOut(TimeRecord timeRecord);

    TimeRecord startBreak(TimeRecord timeRecord);

    TimeRecord endBreak(TimeRecord timeRecord);
}
