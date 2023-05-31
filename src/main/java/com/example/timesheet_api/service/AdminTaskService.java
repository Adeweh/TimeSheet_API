package com.example.timesheet_api.service;

import com.example.timesheet_api.dto.request.LoginRequest;
import com.example.timesheet_api.dto.request.UpdateEmployeeDetailsRequest;
import com.example.timesheet_api.dto.response.UpdateEmployeeDetailsResponse;
import com.example.timesheet_api.model.Employee;

public interface AdminTaskService {
    Employee addEmployee(Employee employee);
   UpdateEmployeeDetailsResponse modifyEmployee(String employeeId, UpdateEmployeeDetailsRequest employee);

    String generatePayslip(String employeeId);

    void ensureValidLoginDetails(LoginRequest loginRequest);
}
