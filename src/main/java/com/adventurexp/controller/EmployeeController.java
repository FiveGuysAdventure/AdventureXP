package com.adventurexp.controller;

import com.adventurexp.model.Employee;
import com.adventurexp.service.EmployeeService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class EmployeeController {

    private EmployeeService employeeService;

    @GetMapping("/employees")
    public List<Employee> getAllEmployees() {
        return employeeService.getListOfEmployees();
    }





//    @PostMapping("/login")
//    public ResponseEntity<LoginResponse> login(@RequestBody Employee employee) {
//        Employee employee = employeeService.employeeLogin(employee.name(), employee.password());
//
//        if (employee == null) {
//            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
//                    .body(new LoginResponse("FAILED", null, null));
//        }
//
//        return ResponseEntity.ok(
//                new LoginResponse("SUCCESS", employee.getEmployeeId(), employee.getEmployeeName())
//        );
//    }





}
