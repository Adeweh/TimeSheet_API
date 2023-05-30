package com.example.timesheet_api.controller;

import com.example.timesheet_api.dto.request.UpdateEmployeeDetailsRequest;
import com.example.timesheet_api.dto.response.UpdateEmployeeDetailsResponse;
import com.example.timesheet_api.model.Employee;
import com.example.timesheet_api.service.AdminTaskService;
import com.example.timesheet_api.service.AdminTaskServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/employee")
@AllArgsConstructor
public class AdminTaskController {
    private  final AdminTaskServiceImpl adminTaskService;

    @PostMapping
    public ResponseEntity<Employee> addEmployee (@RequestBody Employee employee){
        Employee addEmployee = adminTaskService.addEmployee(employee);
        return ResponseEntity.status(HttpStatus.CREATED).body(addEmployee);
    }

    @PutMapping("/{employeeId}")
    public ResponseEntity<UpdateEmployeeDetailsResponse> modifyEmployee(@PathVariable String employeeId, @RequestBody UpdateEmployeeDetailsRequest employee){
        UpdateEmployeeDetailsResponse modifyEmployee = adminTaskService.modifyEmployee(employeeId, employee);
        return  ResponseEntity.ok(modifyEmployee);
    }

    @GetMapping("/{employeeId}/payslip")
    public ResponseEntity<String> generatePayslip(@PathVariable String employeeId){
        String paySlip = adminTaskService.generatePayslip(employeeId);
        return ResponseEntity.ok(paySlip);
    }
}
