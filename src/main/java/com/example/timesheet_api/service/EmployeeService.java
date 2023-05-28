package com.example.timesheet_api.service;

import com.example.timesheet_api.model.Employee;

public interface EmployeeService {
    Employee addEmployee(Employee employee);

    Employee modifyEmployee(String employeeId, Employee employee);

    String generatePayslip(String employeeId);
}
