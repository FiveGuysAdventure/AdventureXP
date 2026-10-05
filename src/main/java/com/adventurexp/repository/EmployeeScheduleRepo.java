package com.adventurexp.repository;

import com.adventurexp.model.EmployeeSchedule;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeScheduleRepo extends JpaRepository<EmployeeSchedule, Long> {
}
