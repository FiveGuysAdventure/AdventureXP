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
        activityTypeService.calculateTimeIntervals();

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(savedBookingData.getBookingId())
                .toUri();

        return ResponseEntity.created(location).body(savedBookingData);
    }


//    @GetMapping("/available-slots")
//    public ResponseEntity<?> getAvailableSlots(
//
//            @RequestParam("activityId") Long activityId,
//            @RequestParam("date")
//            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
//            LocalDate date,
//
//            @RequestParam("numOfGuests") int numOfGuests
//    ) {
//        ActivityType activity = activityTypeRepo.findById(activityId).orElseThrow();
//
//        LocalDateTime opening = date.atTime(OPENING);
//        LocalDateTime closing = date.atTime(CLOSING);
//
//        double price =
//                (double) activity.getPricePerPerson() * numOfGuests;
//
//        Booking sample = new Booking(
//                date, null, null, numOfGuests, price,
//                opening, activity, null
//        );
//
//        boolean groupFits =
//                equipmentService.availabilityCheckForBooking(
//                        activity, sample
//                );
//
//        List<Map<String, Object>> slots = new ArrayList<>();
//
//        if (groupFits) {
//            for (
//                    LocalDateTime start = opening;
//                    !start.plusMinutes(activity.getDurationMinutes())
//                            .isAfter(closing);
//                    start = start.plusMinutes(START_INTERVAL)
//            ) {
//                Booking possibleBooking = new Booking(
//                        date, null, null, numOfGuests, price,
//                        start, activity, null
//                );
//
//                if (bookingService.checkBookingOverlapV2(possibleBooking)) {
//                    continue;
//                }
//
//                List<Employee> employees =
//                        employeeService.getAvailableEmployees(
//                                possibleBooking.getStartTime(),
//                                possibleBooking.getEndTime()
//                        );
//
//                if (employees.isEmpty()) {
//                    continue;
//                }
//
//                var employeeOptions = employees.stream()
//                        .map(employee -> Map.<String, Object>of(
//                                "employeeId", employee.getEmployeeId(),
//                                "name", employee.getEmployeeName()
//                        ))
//                        .toList();
//
//                slots.add(Map.of(
//                        "startTime", possibleBooking.getStartTime(),
//                        "endTime", possibleBooking.getEndTime(),
//                        "availableEmployees", employeeOptions
//                ));
//            }
//        }
//
//        return ResponseEntity.ok()
//                .cacheControl(CacheControl.noStore())
//                .body(Map.of(
//                        "slots", slots,
//                        "price", price,
//                        "capacityAvailable", groupFits
//                ));
//    }
//
//    @GetMapping("/activities")
//    public List<Map<String, Object>> getActivities() {
//        return activityTypeRepo.findAll().stream()
//                .map(activity -> Map.<String, Object>of(
//                        "activityId", activity.getActivityId(),
//                        "activityName", activity.getActivityName(),
//                        "durationMinutes", activity.getDurationMinutes(),
//                        "pricePerPerson", activity.getPricePerPerson()
//                ))
//                .toList();
//    }
//
//    @PostMapping
//    public synchronized ResponseEntity<?> createBooking(
//            @RequestBody Booking input
//    ) {
//        ActivityType activity = activityTypeRepo.findById(
//                input.getActivityType().getActivityId()
//        ).orElseThrow();
//
//        Employee employee = employeeRepo.findById(
//                input.getEmployee().getEmployeeId()
//        ).orElseThrow();
//
//        Booking booking = new Booking(
//                input.getStartTime().toLocalDate(),
//                input.getContactEmail(),
//                input.getContactNumber(),
//                input.getNumOfGuests(),
//                (double) activity.getPricePerPerson()
//                        * input.getNumOfGuests(),
//                input.getStartTime(),
//                activity,
//                employee
//        );
//
//        if (!isOfferedTime(booking)) {
//            return error(HttpStatus.BAD_REQUEST,
//                    "Booking must fit opening hours and start "
//                            + "on an offered time interval");
//        }
//
//        boolean employeeAvailable =
//                employeeService.getAvailableEmployees(
//                        booking.getStartTime(),
//                        booking.getEndTime()
//                ).stream().anyMatch(available ->
//                        available.getEmployeeId()
//                                .equals(employee.getEmployeeId())
//                );
//
//        if (!employeeAvailable) {
//            return error(HttpStatus.CONFLICT,
//                    "Selected employee is no longer available");
//        }
//
//        try {
//            Booking saved = bookingService.createBooking(booking);
//
//            return ResponseEntity.status(HttpStatus.CREATED)
//                    .body(Map.of(
//                            "bookingId", saved.getBookingId(),
//                            "startTime", saved.getStartTime(),
//                            "endTime", saved.getEndTime(),
//                            "price", saved.getPrice(),
//                            "employeeName", employee.getEmployeeName()
//                    ));
//        } catch (BookingConflictException exception) {
//            return error(HttpStatus.CONFLICT, exception.getMessage());
//        }
//    }
//    private boolean isOfferedTime(Booking booking) {
//        LocalDateTime start = booking.getStartTime();
//        LocalDateTime opening = start.toLocalDate().atTime(OPENING);
//        LocalDateTime closing = start.toLocalDate().atTime(CLOSING);
//
//        long minutesFromOpening =
//                Duration.between(opening, start).toMinutes();
//
//        return !start.isBefore(opening)
//                && !booking.getEndTime().isAfter(closing)
//                && start.getSecond() == 0
//                && start.getNano() == 0
//                && minutesFromOpening % START_INTERVAL == 0;
//    }
//
//    private ResponseEntity<?> error(HttpStatus status, String message) {
//        return ResponseEntity.status(status)
//                .body(Map.of("error", message));
//    }



}




