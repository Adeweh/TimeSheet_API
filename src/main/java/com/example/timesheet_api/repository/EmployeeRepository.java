package com.example.timesheet_api.repository;

import com.example.timesheet_api.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;


public interface EmployeeRepository extends JpaRepository<Employee, String> {
}
