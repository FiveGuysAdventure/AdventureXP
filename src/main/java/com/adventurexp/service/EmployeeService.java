package com.adventurexp.service;

import org.springframework.stereotype.Service;

import com.adventurexp.model.Booking;
import com.adventurexp.model.Employee;
import com.adventurexp.repository.BookingRepo;
import com.adventurexp.repository.EmployeeRepo;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class EmployeeService {

    private final EmployeeRepo employeeRepo;
    private final BookingRepo bookingRepo;

    public EmployeeService(
            EmployeeRepo employeeRepo,
            BookingRepo bookingRepo
    ) {
        this.employeeRepo = employeeRepo;
        this.bookingRepo = bookingRepo;
    }

    public List<Employee> getAvailableInstructors(
            LocalDateTime start,
            LocalDateTime end
    ) {
        if (bookingRepo.findAll().isEmpty()){
return employeeRepo.findAll();
    }
        return List.of();
}}
