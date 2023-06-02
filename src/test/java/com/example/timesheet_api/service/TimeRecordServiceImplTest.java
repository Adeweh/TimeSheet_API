package com.example.timesheet_api.service;

import com.example.timesheet_api.model.Employee;
import com.example.timesheet_api.model.Role;
import com.example.timesheet_api.model.TimeRecord;
import com.example.timesheet_api.repository.EmployeeRepository;
import com.example.timesheet_api.repository.TimeRecordRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class TimeRecordServiceImplTest {
    @Mock
    private EmployeeRepository employeeRepository;

    @Mock
    private TimeRecordRepository timeRecordRepository;

    @InjectMocks
    private TimeRecordServiceImpl timeRecordService;

    @Test
    void clockIn() {

        Employee employee = new Employee();
        employee.setId("111");
        employee.setFirstName("Joe");
        employee.setLastName("John");
        employee.setEmail("joe@mail.com");
        employee.setRole(Role.NON_ADMIN);
        employee.setPassword("Joe1234");

        when(employeeRepository.findById(employee.getId())).thenReturn(Optional.of(employee));
        when(timeRecordRepository.findActiveEntriesByEmployeeId(employee.getId())).thenReturn(new ArrayList<>());

        TimeRecord timeRecord = new TimeRecord();
        timeRecord.setClockInTime(LocalDateTime.parse("2023-06-01T09:00:00"));

        when(timeRecordRepository.save(any(TimeRecord.class))).thenReturn(timeRecord);
            timeRecord.setId(employee.getId());

        TimeRecord result = timeRecordService.clockIn(employee.getId());

        assertNotNull(result);
        verify(employeeRepository, times(1)).findById(employee.getId());
        verify(timeRecordRepository, times(1)).findActiveEntriesByEmployeeId(employee.getId());
        verify(timeRecordRepository, times(1)).save(any(TimeRecord.class));
    }

    @Test
    void clockOut() {
        Employee employee = new Employee();
        employee.setId("111");
        employee.setFirstName("Joe");
        employee.setLastName("John");
        employee.setEmail("joe@mail.com");
        employee.setRole(Role.NON_ADMIN);
        employee.setPassword("Joe1234");

        when(employeeRepository.findById(employee.getId())).thenReturn(Optional.of(employee));
        when(timeRecordRepository.findActiveEntriesByEmployeeId(employee.getId())).thenReturn(new ArrayList<>());

        TimeRecord timeRecord = new TimeRecord();
        timeRecord.setClockOutTime(LocalDateTime.parse("2023-06-01T17:00:00"));

        when(timeRecordRepository.save(any(TimeRecord.class))).thenReturn(timeRecord);
        timeRecord.setId(employee.getId());

        TimeRecord result = timeRecordService.clockIn(employee.getId());

        assertNotNull(result);
        verify(employeeRepository, times(1)).findById(employee.getId());
        verify(timeRecordRepository, times(1)).findActiveEntriesByEmployeeId(employee.getId());
        verify(timeRecordRepository, times(1)).save(any(TimeRecord.class));
    }

    @Test
    void startBreak() {
        Employee employee = new Employee();
        employee.setId("111");
        employee.setFirstName("Joe");
        employee.setLastName("John");
        employee.setEmail("joe@mail.com");
        employee.setRole(Role.NON_ADMIN);
        employee.setPassword("Joe1234");

        when(employeeRepository.findById(employee.getId())).thenReturn(Optional.of(employee));
        when(timeRecordRepository.findActiveEntriesByEmployeeId(employee.getId())).thenReturn(new ArrayList<>());

        TimeRecord timeRecord = new TimeRecord();
        timeRecord.setStartBreak(LocalDateTime.parse("2023-06-01T09:00:00"));

        when(timeRecordRepository.save(any(TimeRecord.class))).thenReturn(timeRecord);
        timeRecord.setId(employee.getId());

        TimeRecord result = timeRecordService.clockIn(employee.getId());

        assertNotNull(result);
        verify(employeeRepository, times(1)).findById(employee.getId());
        verify(timeRecordRepository, times(1)).findActiveEntriesByEmployeeId(employee.getId());
        verify(timeRecordRepository, times(1)).save(any(TimeRecord.class));
    }

    @Test
    void endBreak() {
        Employee employee = new Employee();
        employee.setId("111");
        employee.setFirstName("Joe");
        employee.setLastName("John");
        employee.setEmail("joe@mail.com");
        employee.setRole(Role.NON_ADMIN);
        employee.setPassword("Joe1234");

        when(employeeRepository.findById(employee.getId())).thenReturn(Optional.of(employee));
        when(timeRecordRepository.findActiveEntriesByEmployeeId(employee.getId())).thenReturn(new ArrayList<>());

        TimeRecord timeRecord = new TimeRecord();
        timeRecord.setEndBreak(LocalDateTime.parse("2023-06-01T17:00:00"));

        when(timeRecordRepository.save(any(TimeRecord.class))).thenReturn(timeRecord);
        timeRecord.setId(employee.getId());

        TimeRecord result = timeRecordService.clockIn(employee.getId());

        assertNotNull(result);
        verify(employeeRepository, times(1)).findById(employee.getId());
        verify(timeRecordRepository, times(1)).findActiveEntriesByEmployeeId(employee.getId());
        verify(timeRecordRepository, times(1)).save(any(TimeRecord.class));
    }
}