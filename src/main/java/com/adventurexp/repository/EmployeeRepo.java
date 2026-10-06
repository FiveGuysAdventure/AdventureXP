package com.adventurexp.repository;
import java.util.Optional;

import com.adventurexp.model.Employee;

import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepo extends JpaRepository<Employee, Long> {
    Optional<Employee> findEmployeeByEmployeeEmailAndEmployeePassword(String email, String password);
}
