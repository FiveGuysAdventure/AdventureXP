package com.adventurexp.controller;

import com.adventurexp.dto.BookingFormData;
import com.adventurexp.dto.BookingResponseDTO;
import com.adventurexp.model.Booking;
import com.adventurexp.service.ActivityTypeService;
import com.adventurexp.service.BookingService;
import com.adventurexp.service.EmployeeService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Map;


@RestController
public class BookingController {

    @Autowired
    private BookingService bookingService;

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private ActivityTypeService activityTypeService;

    public BookingController(BookingService bookingService) {
        this.bookingService = bookingService;
    }

    @GetMapping("/api/booking")
    public ResponseEntity<BookingFormData> showBookingForm() {
        BookingFormData data = new BookingFormData(
                activityTypeService.getAvailableActivities(),
                employeeService.getListOfEmployees()
        );

        return ResponseEntity.ok(data);
    }

    @PostMapping("/api/booking")
    public ResponseEntity<Booking> bookingCompletion(@Valid @RequestBody BookingResponseDTO bookingRequestDTO) {
        Booking savedBookingData = bookingService.createBookingForm(bookingRequestDTO);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(savedBookingData.getBookingId())
                .toUri();

        return ResponseEntity.created(location).body(savedBookingData);
    }

    @GetMapping
    public ResponseEntity<List<Map<String, Object>>> getBookingsForCalendar() {
        List<Map<String, Object>> bookings =
                bookingService.getBookingsForAvailability()
                        .stream()
                        .map(booking -> Map.<String, Object>of(
                                "bookingId", booking.getBookingId(),
                                "bookingDate",
                                booking.getStartTime().toLocalDate().toString(),
                                "startTime", booking.getStartTime().toString(),
                                "endTime", booking.getEndTime().toString(),
                                "activityName",
                                booking.getActivityType().getActivityName(),
                                "employeeName",
                                booking.getEmployee() == null
                                        ? "Ikke tildelt"
                                        : booking.getEmployee().getEmployeeName()
                        ))
                        .toList();
        return ResponseEntity.ok(bookings);
    }

}




