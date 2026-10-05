package com.adventurexp.controller;

import com.adventurexp.model.Employee;
import com.adventurexp.service.EmployeeService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class EmployeeController {

    private EmployeeService employeeService;

//    @GetMapping("/employees")
//    public List<Employee> getAllEmployees(@RequestBody Employee employee) {
//
//    }


}
