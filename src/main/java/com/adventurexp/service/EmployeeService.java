package com.adventurexp.service;

import org.springframework.stereotype.Service;

import com.adventurexp.model.Booking;
import com.adventurexp.model.Employee;
import com.adventurexp.repository.BookingRepo;
import com.adventurexp.repository.EmployeeRepo;
import org.springframework.web.client.HttpStatusCodeException;

import java.time.LocalDateTime;
import java.util.ArrayList;
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

    public List<Employee> getListOfEmployees() {
        return employeeRepo.findAll();
    }


    // Finder alle tilgængelige medarbejdere som ikke allerede er Booket til den tidsperiode.
    public List<Employee> getAvailableEmployees(
            LocalDateTime start,
            LocalDateTime end
    ) {
        List<Employee> employees = employeeRepo.findAll();
        List<Booking> bookings = bookingRepo.findAll();

        List<Employee> availableEmployees = new ArrayList<>();

        for (Employee employee : employees) {
            boolean hasOverlap = false;

            for (Booking booking : bookings) {
                Employee assignedEmployee = booking.getEmployee();

                if (assignedEmployee == null) {
                    continue;
                }

                boolean sameEmployee = employee.getEmployeeId()
                        .equals(assignedEmployee.getEmployeeId());

                if (sameEmployee
                        && start.isBefore(booking.getEndTime())
                        && end.isAfter(booking.getStartTime())) {

                    hasOverlap = true;
                    break;
                }
            }
            if (!hasOverlap) {
                availableEmployees.add(employee);
            }
        }
        return availableEmployees;
    }}
