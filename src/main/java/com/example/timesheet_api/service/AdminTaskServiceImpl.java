package com.example.timesheet_api.service;

import com.example.timesheet_api.dto.request.UpdateEmployeeDetailsRequest;
import com.example.timesheet_api.dto.response.UpdateEmployeeDetailsResponse;
import com.example.timesheet_api.exceptions.EmployeeNotFoundException;
import com.example.timesheet_api.model.Employee;
import com.example.timesheet_api.model.TimeRecord;
import com.example.timesheet_api.repository.EmployeeRepository;
import com.example.timesheet_api.repository.TimeRecordRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.List;

@Service
@AllArgsConstructor
public class AdminTaskServiceImpl implements AdminTaskService {

    private final EmployeeRepository employeeRepository;

    private final TimeRecordRepository timeRecordRepository;
    private final ModelMapper mapper = new ModelMapper();


    @Override
    public Employee addEmployee(Employee employee) {
        return employeeRepository.save(employee);
    }

    @Override
    public UpdateEmployeeDetailsResponse modifyEmployee(String employeeId, UpdateEmployeeDetailsRequest employee) {
        Employee findEmployee = employeeRepository.findById(employeeId).orElseThrow(()-> new EmployeeNotFoundException("Employee not found"));
        mapper.map(employee, findEmployee);

        employeeRepository.save(findEmployee);

        return UpdateEmployeeDetailsResponse
                .builder()
                .message("Employee updates")
                .build();
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


}
