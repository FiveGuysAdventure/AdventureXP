package com.adventurexp.service;

import com.adventurexp.model.Booking;
import com.adventurexp.enums.RoleName;
import com.adventurexp.model.Employee;
import com.adventurexp.model.Role;
import com.adventurexp.repository.BookingRepo;
import com.adventurexp.repository.EmployeeRepo;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class EmployeeUnitTest {

    @Mock
    private EmployeeRepo employeeRepo;

    @Mock
    private BookingRepo bookingRepo;

    @InjectMocks
    private EmployeeService service;

    // Arrange - Act - Assert:

    @Test
    void getAvailableInstructors_returnsBoth_whenNoBookingsExist() {

        Role instructorRole = new Role(RoleName.EMPLOYEE);

        Employee anna = new Employee(
                "Anna", "11111111", "anna@example.com", instructorRole
        );
        anna.setEmployeeId(101L);

        Employee emil = new Employee(
                "Emil", "22222222", "emil@example.com", instructorRole
        );
        emil.setEmployeeId(102L);

        LocalDateTime start =
                LocalDateTime.of(2026, 10, 5, 10, 0);
        LocalDateTime end =
                LocalDateTime.of(2026, 10, 5, 11, 0);

        when(employeeRepo.findAll()).thenReturn(List.of(anna, emil));
        when(bookingRepo.findAll()).thenReturn(List.of());

        List<Employee> available =
                service.getAvailableInstructors(start, end);

        assertThat(available).containsExactlyInAnyOrder(anna, emil);
    }
    @Test
    void getAvailableInstructors_returnsEmil_WhenAnnaIsBooked() {

        Role EmployeeRole = new Role(RoleName.EMPLOYEE);

        Employee anna = new Employee(
                "Anna", "11111111", "anna@example.com", EmployeeRole
        );
        anna.setEmployeeId(101L);

        Employee emil = new Employee(
                "Emil", "22222222", "emil@example.com", EmployeeRole
        );
        emil.setEmployeeId(102L);


        LocalDateTime start =
                LocalDateTime.of (2026, 10, 5, 10,0);

        LocalDateTime end =
                LocalDateTime.of (2026, 10, 5, 10,30);

        Booking annasBooking = new Booking();
        annasBooking.setEmployee(anna);
        annasBooking.setStartTime(start);
        annasBooking.setEndTime(end);

        when(employeeRepo.findAll()).thenReturn(List.of(anna, emil));
        when(bookingRepo.findAll()).thenReturn(List.of(annasBooking));

        List<Employee> available =
                service.getAvailableInstructors(start, end);

        assertThat(available).containsExactly(emil);
    }
    }