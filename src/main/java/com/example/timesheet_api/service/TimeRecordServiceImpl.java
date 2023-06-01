package com.example.timesheet_api.service;

import com.example.timesheet_api.exceptions.EmployeeNotFoundException;
import com.example.timesheet_api.model.Employee;
import com.example.timesheet_api.model.TimeRecord;
import com.example.timesheet_api.repository.EmployeeRepository;
import com.example.timesheet_api.repository.TimeRecordRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;

@Service
@AllArgsConstructor
public class TimeRecordServiceImpl implements TimeRecordService {
    private final TimeRecordRepository timeRecordRepository;
    private final EmployeeRepository employeeRepository;
    @Override
    public TimeRecord clockIn(String employeeId) {
        Employee findEmployee = employeeRepository.findById(employeeId).orElseThrow(()-> new EmployeeNotFoundException("Employee not found"));

        List<TimeRecord> activeEntries = timeRecordRepository.findActiveEntriesByEmployeeId(employeeId);
        if(!activeEntries.isEmpty()){
            throw  new IllegalStateException("Employee already clocked in");
        }
        TimeRecord timeRecord = new TimeRecord();
        timeRecord.setEmployee(findEmployee);
        timeRecord.setClockInTime(LocalDateTime.now());

        return timeRecordRepository.save(timeRecord);
    }

    @Override
    public TimeRecord clockOut(String employeeId) {
        Employee findEmployee = employeeRepository.findById(employeeId).orElseThrow(()-> new EmployeeNotFoundException("Employee not found"));

        TimeRecord activeEntry = timeRecordRepository.findActiveEntryByEmployeeId(employeeId);

        activeEntry.setClockOutTime(LocalDateTime.now());

        return timeRecordRepository.save(activeEntry);
    }

    @Override
    public TimeRecord startBreak(String employeeId) {
        Employee findEmployee = employeeRepository.findById(employeeId).orElseThrow(()-> new EmployeeNotFoundException("Employee not found"));

        TimeRecord activeEntry = timeRecordRepository.findActiveEntryByEmployeeId(employeeId);

        activeEntry.setStartBreak(LocalDateTime.now());

        return timeRecordRepository.save(activeEntry);
    }

    @Override
    public TimeRecord endBreak(String employeeId) {
        Employee findEmployee = employeeRepository.findById(employeeId).orElseThrow(()-> new EmployeeNotFoundException("Employee not found"));

        TimeRecord activeEntry = timeRecordRepository.findActiveEntryByEmployeeId(employeeId);

        activeEntry.setEndBreak(LocalDateTime.now());

        return timeRecordRepository.save(activeEntry);
    }


}
