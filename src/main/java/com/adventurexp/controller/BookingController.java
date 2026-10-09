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
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.awt.print.Book;
import java.net.URI;
import java.time.LocalTime;
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
    public ResponseEntity<BookingFormData> showBookingFormData() {
        BookingFormData data = new BookingFormData(
                activityTypeService.getAvailableActivities(),
                employeeService.getListOfEmployees()
        );

        return ResponseEntity.ok(data);
    }

    @GetMapping("/api/booking/time-intervals")
    public ResponseEntity<List<LocalTime>> getTimeIntervalsForBookings(@RequestParam Long activityId) {
        return ResponseEntity.ok(bookingService.getStartTimeIntervalList(activityId));
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

    @GetMapping("/api/booking-overview")
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




