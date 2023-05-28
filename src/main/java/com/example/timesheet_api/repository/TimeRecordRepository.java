package com.example.timesheet_api.repository;

import com.example.timesheet_api.model.TimeRecord;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TimeRecordRepository extends JpaRepository<TimeRecord, String> {
    List<TimeRecord> findByEmployeeId(String employeeId);

    List<TimeRecord> findActiveEntriesByEmployeeId(String employeeId);

    TimeRecord findActiveEntryByEmployeeId(String employeeId);
}
