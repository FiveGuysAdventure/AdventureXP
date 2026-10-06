package com.adventurexp.controller;


import com.adventurexp.exceptions.BookingConflictException;
import com.adventurexp.model.*;
import com.adventurexp.repository.ActivityTypeRepo;
import com.adventurexp.service.EquipmentService;
import com.adventurexp.service.EmployeeService;
import com.adventurexp.repository.EmployeeRepo;
import com.adventurexp.service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.CacheControl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("/adventureexperience/bookings")
public class BookingController {

    private final BookingService bookingService;
    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private EquipmentService equipmentService;

    @Autowired
    private ActivityTypeRepo activityTypeRepo;
    @Autowired
    private EmployeeRepo employeeRepo;

    private static final LocalTime OPENING = LocalTime.of(8, 0);
    private static final LocalTime CLOSING = LocalTime.of(20, 0);
    private static final int START_INTERVAL = 15;

    public BookingController(BookingService bookingService) {
        this.bookingService = bookingService;


    }
    @GetMapping("/available-slots")
    public ResponseEntity<?> getAvailableSlots(

            @RequestParam("activityId") Long activityId,
            @RequestParam("date")
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
            LocalDate date,

            @RequestParam("numOfGuests") int numOfGuests
    ) {
        ActivityType activity = activityTypeRepo.findById(input.getActivityType().getActivityId()
        ).orElseThrow();

        LocalDateTime opening = date.atTime(OPENING);
        LocalDateTime closing = date.atTime(CLOSING);

        double price =
                (double) activity.getPricePerPerson() * numOfGuests;

        Booking sample = new Booking(
                date, null, null, numOfGuests, price,
                opening, activity, null
        );

        boolean groupFits =
                equipmentService.availabilityCheckForBooking(
                        activity, sample
                );

        List<Map<String, Object>> slots = new ArrayList<>();

        if (groupFits) {
            for (
                    LocalDateTime start = opening;
                    !start.plusMinutes(activity.getDurationMinutes())
                            .isAfter(closing);
                    start = start.plusMinutes(START_INTERVAL)
            ) {
                Booking possibleBooking = new Booking(
                        date, null, null, numOfGuests, price,
                        start, activity, null
                );

                if (bookingService.checkBookingOverlapV2(possibleBooking)) {
                    continue;
                }

                List<Employee> employees =
                        employeeService.getAvailableEmployees(
                                possibleBooking.getStartTime(),
                                possibleBooking.getEndTime()
                        );

                if (employees.isEmpty()) {
                    continue;
                }

                var employeeOptions = employees.stream()
                        .map(employee -> Map.<String, Object>of(
                                "employeeId", employee.getEmployeeId(),
                                "name", employee.getEmployeeName()
                        ))
                        .toList();

                slots.add(Map.of(
                        "startTime", possibleBooking.getStartTime(),
                        "endTime", possibleBooking.getEndTime(),
                        "availableEmployees", employeeOptions
                ));
            }
        }

        return ResponseEntity.ok()
                .cacheControl(CacheControl.noStore())
                .body(Map.of(
                        "slots", slots,
                        "price", price,
                        "capacityAvailable", groupFits
                ));
    }

    @GetMapping("/activities")
    public List<Map<String, Object>> getActivities() {
        return activityTypeRepo.findAll().stream()
                .map(activity -> Map.<String, Object>of(
                        "activityId", activity.getActivityId(),
                        "activityName", activity.getActivityName(),
                        "durationMinutes", activity.getDurationMinutes(),
                        "pricePerPerson", activity.getPricePerPerson()
                ))
                .toList();
    }

    @PostMapping
    public synchronized ResponseEntity<?> createBooking(
            @RequestBody Booking input
    ) {
        ActivityType activity = activityTypeRepo.findById(
                input.getActivityType().getActivityId()
        ).orElseThrow();

        Employee employee = employeeRepo.findById(
                input.getEmployee().getEmployeeId()
        ).orElseThrow();

        Booking booking = new Booking(
                input.getStartTime().toLocalDate(),
                input.getContactEmail(),
                input.getContactNumber(),
                input.getNumOfGuests(),
                (double) activity.getPricePerPerson()
                        * input.getNumOfGuests(),
                input.getStartTime(),
                activity,
                employee
        );

        if (!isOfferedTime(booking)) {
            return error(HttpStatus.BAD_REQUEST,
                    "Booking must fit opening hours and start "
                            + "on an offered time interval");
        }

        boolean employeeAvailable =
                employeeService.getAvailableEmployees(
                        booking.getStartTime(),
                        booking.getEndTime()
                ).stream().anyMatch(available ->
                        available.getEmployeeId()
                                .equals(employee.getEmployeeId())
                );

        if (!employeeAvailable) {
            return error(HttpStatus.CONFLICT,
                    "Selected employee is no longer available");
        }

        try {
            Booking saved = bookingService.createBooking(booking);

            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(Map.of(
                            "bookingId", saved.getBookingId(),
                            "startTime", saved.getStartTime(),
                            "endTime", saved.getEndTime(),
                            "price", saved.getPrice(),
                            "employeeName", employee.getEmployeeName()
                    ));
        } catch (BookingConflictException exception) {
            return error(HttpStatus.CONFLICT, exception.getMessage());
        }
    }
    private boolean isOfferedTime(Booking booking) {
        LocalDateTime start = booking.getStartTime();
        LocalDateTime opening = start.toLocalDate().atTime(OPENING);
        LocalDateTime closing = start.toLocalDate().atTime(CLOSING);

        long minutesFromOpening =
                Duration.between(opening, start).toMinutes();

        return !start.isBefore(opening)
                && !booking.getEndTime().isAfter(closing)
                && start.getSecond() == 0
                && start.getNano() == 0
                && minutesFromOpening % START_INTERVAL == 0;
    }

    private ResponseEntity<?> error(HttpStatus status, String message) {
        return ResponseEntity.status(status)
                .body(Map.of("error", message));
    }



}




