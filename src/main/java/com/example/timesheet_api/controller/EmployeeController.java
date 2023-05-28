package com.example.timesheet_api.controller;

import com.example.timesheet_api.model.Employee;
import com.example.timesheet_api.service.EmployeeService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/employee")
@AllArgsConstructor
public class EmployeeController {
    private  final EmployeeService employeeService;

    @PostMapping
    public ResponseEntity<Employee> addEmployee (@RequestBody Employee employee){
        Employee addEmployee = employeeService.addEmployee(employee);
        return ResponseEntity.status(HttpStatus.CREATED).body(addEmployee);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Employee> modifyEmployee(@PathVariable String employeeId, @RequestBody Employee employee){
        Employee modifyEmployee = employeeService.modifyEmployee(employeeId, employee);
        return  ResponseEntity.ok(modifyEmployee);
    }

    @GetMapping("/{employeeId}/payslip")
    public ResponseEntity<String> generatePayslip(@PathVariable String employeeId){
        String paySlip = employeeService.generatePayslip(employeeId);
        return ResponseEntity.ok(paySlip);
    }
}
