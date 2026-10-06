package com.adventurexp.controller;

import com.adventurexp.model.Employee;
import com.adventurexp.service.EmployeeService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin("http://localhost:63342")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @GetMapping("/employees")
    public List<Employee> getAllEmployees() {
        return employeeService.getListOfEmployees();
    }

    @PostMapping("/login")
    public ResponseEntity<Employee> login(@RequestBody Employee employee) {
        Optional<Employee> employeeData = employeeService.login(employee.getEmployeeEmail(), employee.getEmployeePassword());

        if (employeeData.isPresent()) {
            Employee found = employeeData.get();
            found.setEmployeePassword(null);
        return ResponseEntity.ok(found);
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }
}
