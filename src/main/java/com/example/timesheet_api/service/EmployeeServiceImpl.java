package com.example.timesheet_api.service;

import com.example.timesheet_api.dto.request.LoginRequest;
import com.example.timesheet_api.dto.request.UpdateEmployeeDetailsRequest;
import com.example.timesheet_api.dto.response.UpdateEmployeeDetailsResponse;
import com.example.timesheet_api.exceptions.EmployeeNotFoundException;
import com.example.timesheet_api.model.Employee;
import com.example.timesheet_api.model.Role;
import com.example.timesheet_api.model.TimeRecord;
import com.example.timesheet_api.repository.EmployeeRepository;
import com.example.timesheet_api.repository.TimeRecordRepository;
import jakarta.annotation.PostConstruct;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.List;

@Service
@AllArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;

    private final TimeRecordRepository timeRecordRepository;

    private final PasswordEncoder passwordEncoder;
    private final ModelMapper mapper = new ModelMapper();


    @Override
    public Employee addEmployee(Employee employee) {
        if(employeeRepository.existsByEmail(employee.getEmail())) throw new RuntimeException("Email Exists");
        employee.setPassword(passwordEncoder.encode(employee.getPassword()));
        return employeeRepository.save(employee);
    }

    @Override
    public UpdateEmployeeDetailsResponse modifyEmployee(String employeeId, UpdateEmployeeDetailsRequest employee) {
        Employee findEmployee = employeeRepository.findById(employeeId).orElseThrow(()-> new EmployeeNotFoundException("Employee not found"));
        mapper.map(employee, findEmployee);
        employeeRepository.save(findEmployee);

        return new UpdateEmployeeDetailsResponse("Employee updated");
    }

    @Override
    public String generatePayslip(String employeeId) {
        Employee employee = employeeRepository.findById(employeeId).orElseThrow(()-> new EmployeeNotFoundException("Employee not found"));

        List<TimeRecord> timeRecordList = timeRecordRepository.findByEmployeeId(employeeId);
        Duration totalWorkHours = Duration.ZERO;
        for (TimeRecord record: timeRecordList){
            if(record.getClockOutTime() != null && record.getClockInTime() != null){
                totalWorkHours = totalWorkHours.plus(Duration.between(record.getClockInTime(), record.getClockOutTime()));
            }

        }
        return  "Payslip for employee: " + employee.getFirstName() + "\n" +
                "Total working hours: " + totalWorkHours.toHours() + "hours" + totalWorkHours.toMinutesPart() + "minutes";
    }

    @Override
    public void ensureValidLoginDetails(LoginRequest loginRequest) {
        Employee employee = employeeRepository.findByEmail(loginRequest.getEmail()).orElseThrow(()-> new EmployeeNotFoundException("Employee not found"));

        if (!passwordEncoder.matches(loginRequest.getPassword(), employee.getPassword()))
            throw new EmployeeNotFoundException("Invalid email or password");
    }


    @PostConstruct
    void createAdmin(){
        if (!employeeRepository.existsByEmail("admin@mail.com")){
            Employee employee = Employee.builder()
                    .email("admin@mail.com")
                    .password(passwordEncoder.encode("Admin123"))
                    .role(Role.ADMIN)
                    .build();
            employeeRepository.save(employee);
        }
    }
}



