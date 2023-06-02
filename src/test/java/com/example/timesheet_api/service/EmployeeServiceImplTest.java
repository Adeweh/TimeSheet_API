package com.example.timesheet_api.service;

import com.example.timesheet_api.dto.request.UpdateEmployeeDetailsRequest;
import com.example.timesheet_api.dto.response.UpdateEmployeeDetailsResponse;
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
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
class EmployeeServiceImplTest {
    @Mock
    private EmployeeRepository employeeRepository;

    @Mock
    private TimeRecordRepository timeRecordRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    private EmployeeServiceImpl employeeService;

    @Test
    void testAddEmployee_ValidEmployee() {

        Employee employee = new Employee();
        employee.setId("111");
        employee.setFirstName("Joe");
        employee.setLastName("John");
        employee.setEmail("joe@mail.com");
        employee.setRole(Role.NON_ADMIN);
        employee.setPassword("Joe1234");

        when(employeeRepository.existsByEmail(employee.getEmail())).thenReturn(false);
        when(passwordEncoder.encode(employee.getPassword())).thenReturn("encodedPassword");
        when(employeeRepository.save(employee)).thenReturn(employee);

        Employee result = employeeService.addEmployee(employee);

        assertEquals("Joe", result.getFirstName());
        verify(employeeRepository, times(1)).existsByEmail(employee.getEmail());
        verify(passwordEncoder, times(1)).encode(any());
        verify(employeeRepository, times(1)).save(employee);
    }

    @Test
    void modifyEmployee() {
        Employee employee = new Employee();
        employee.setId("111");
        employee.setFirstName("Joe");
        employee.setLastName("John");
        employee.setEmail("joe@mail.com");
        employee.setRole(Role.NON_ADMIN);
        employee.setPassword("Joe1234");

        UpdateEmployeeDetailsRequest employeeRequest = UpdateEmployeeDetailsRequest.builder()
                .firstName("Jane")
                .lastName("Dee")
                .email("jane@email.com")
                .phoneNumber("226773983")
                .build();

        when(employeeRepository.findById(any())).thenReturn(Optional.of(employee));



        UpdateEmployeeDetailsResponse result = employeeService.modifyEmployee("01", employeeRequest);

        assertEquals("Employee updated", result.getMessage());
        verify(employeeRepository, times(1)).findById(any());

    }


    @Test
    void generatePayslip() {

        Employee employee = new Employee();
        employee.setId("111");
        employee.setFirstName("Joe");
        employee.setLastName("John");
        employee.setEmail("joe@mail.com");
        employee.setRole(Role.NON_ADMIN);
        employee.setPassword("Joe1234");

        TimeRecord timeRecord = new TimeRecord();
        timeRecord.setClockInTime(LocalDateTime.parse("2023-06-01T09:00:00"));
        timeRecord.setClockOutTime(LocalDateTime.parse("2023-06-01T17:00:00"));



        List<TimeRecord> timeRecordList = new ArrayList<>();
        timeRecordList.add(timeRecord);
        when(employeeRepository.findById(employee.getId())).thenReturn(Optional.of(employee));
        when(timeRecordRepository.findByEmployeeId(employee.getId())).thenReturn(timeRecordList);

        String result = employeeService.generatePayslip(employee.getId());

        String expectedPayslip = "Payslip for employee: John Doe\n" +
                "Total working hours: 8 hours 0 minutes";

        assertEquals(LocalDateTime.parse("2023-06-01T09:00:00"), timeRecord.getClockInTime());
        verify(employeeRepository, times(1)).findById(employee.getId());
        verify(timeRecordRepository, times(1)).findByEmployeeId(employee.getId());
    }
}