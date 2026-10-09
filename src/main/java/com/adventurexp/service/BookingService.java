package com.adventurexp.service;

import com.adventurexp.dto.BookingResponseDTO;
import com.adventurexp.exceptions.BookingConflictException;
import com.adventurexp.model.ActivityType;
import com.adventurexp.model.Booking;
import com.adventurexp.model.Employee;
import com.adventurexp.repository.ActivityTypeRepo;
import com.adventurexp.repository.BookingRepo;
import com.adventurexp.repository.EmployeeRepo;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.IntStream;

@Service
public class BookingService {

    @Autowired
    private BookingRepo bookingRepo;

    @Autowired
    private EquipmentService equipmentService;

    @Autowired
    private ActivityTypeRepo activityTypeRepo;

    @Autowired
    private EmployeeRepo employeeRepo;

    @Autowired
    private ActivityTypeService activityTypeService;

    public Booking getBooking(Long id) {
        Optional<Booking> booking = bookingRepo.findById(id);
        if (booking.isEmpty()) {
            throw new EntityNotFoundException("No booking with given id was found: " + id);
        }
        return booking.get();
    }

    public Booking createBookingForm(BookingResponseDTO requestDTO) {
       ActivityType activityType = activityTypeRepo.findById(requestDTO.getActivityTypeId())
               .orElseThrow(()-> new BookingConflictException("No activity found: " + requestDTO.getActivityTypeId()));

       Employee employee = employeeRepo.findById(requestDTO.getEmployeeId())
               .orElseThrow(() -> new BookingConflictException("No employee found: " + requestDTO.getEmployeeId()));

        Booking bookingData = new Booking();
        bookingData.setContactEmail(requestDTO.getContactEmail());
        bookingData.setContactNumber(requestDTO.getContactNumber());
        bookingData.setNumOfGuests(requestDTO.getNumOfGuests());
        bookingData.setPrice(requestDTO.getPrice());
        bookingData.setStartTime(requestDTO.getStartTime());
        bookingData.setBookingDate(requestDTO.getStartTime().toLocalDate());
        bookingData.setEndTime(requestDTO.getStartTime().plusMinutes(activityType.getDurationMinutes()));
        bookingData.setActivityType(activityType);
        bookingData.setEmployee(employee);

        return createBooking(bookingData);
    }

    public Booking createBooking(Booking booking) {

        if (booking == null) {
            throw new EntityNotFoundException("No booking was found");
        }

        LocalTime OPENING = LocalTime.of(8, 0);
        LocalTime CLOSING = LocalTime.of(20, 0);
        LocalDate bookingDate = booking.getBookingDate();

        if (booking.getStartTime().isBefore(OPENING.atDate(bookingDate)) || booking.getEndTime().isAfter(CLOSING.atDate(bookingDate))) {
            throw new BookingConflictException("Booking is outside opening hours 8:00-20:00");
        }

        if (checkBookingOverlapV2(booking)){
            throw new BookingConflictException ("Time slot is taken");
        }

        if (!equipmentService.availabilityCheckForBooking(booking.getActivityType(), booking)) {
            throw new BookingConflictException ("Group size is too big.");
        }

        booking.setPrice(calculateBookingPrice(booking));

        return bookingRepo.save(booking);
    }

    public double calculateBookingPrice(Booking booking) {
        ActivityType activity = booking.getActivityType();

        long bookingDuration = Duration.between(booking.getStartTime(), booking.getEndTime()).toMinutes();
        int interval = activity.getDurationMinutes();

        long intervals = bookingDuration / interval;
        return activity.getPricePerPerson() * booking.getNumOfGuests() * intervals;
    }

    public List<LocalTime> getStartTimeIntervalList(Long activityId) {
        List<LocalTime> startTimeIntervalList = new ArrayList<>();

        ActivityType activity = activityTypeService.getActivityById(activityId);
        Duration activityTimeInterval = Duration.ofMinutes(activity.getDurationMinutes());

        LocalTime OPENING = LocalTime.of(8,0);
        LocalTime CLOSING = LocalTime.of(20,0);

        for (LocalTime startTime = OPENING; !startTime.plus(activityTimeInterval).isAfter(CLOSING); startTime = startTime.plus(activityTimeInterval)) {
            startTimeIntervalList.add(startTime);
        }

        return startTimeIntervalList;
    }

    public List<Booking> getAllBookings() {
        List<Booking> bookings = bookingRepo.findAll();

        if (bookings.isEmpty()) {
            throw new IllegalArgumentException("Could not load bookings...");
        }

        return bookings;
    }
    public List<Booking> getBookingsForAvailability() {
        return bookingRepo.findAll();
    }

    public void setBookingDate(Booking booking, LocalDate date) {
        booking.setBookingDate(date);
        bookingRepo.save(booking);
    }

    public void setContactEmail(Booking booking, String contactEmail) {
        booking.setContactEmail(contactEmail);
        bookingRepo.save(booking);
    }

    public void setContactNumber(Booking booking, String contactNumber) {
        booking.setContactNumber(contactNumber);
        bookingRepo.save(booking);
    }

    public void setNumOfParticipants(Booking booking, int numOfGuests) {
        booking.setNumOfGuests(numOfGuests);
        bookingRepo.save(booking);
    }

    public void setPrice(Booking booking, double price) {
        booking.setPrice(price);
        bookingRepo.save(booking);
    }

    public void setStartTime(Booking booking, LocalDateTime startTime) {
        booking.setStartTime(startTime);
        bookingRepo.save(booking);
    }

    public void setEndTime(Booking booking,  LocalDateTime endTime) {
        booking.setEndTime(endTime);
        bookingRepo.save(booking);
    }

    public void setActivityType(Booking booking, ActivityType activityType) {
        booking.setActivityType(activityType);
        bookingRepo.save(booking);
    }

    public void setEmployee(Booking booking, Employee employee) {
        booking.setEmployee(employee);
        bookingRepo.save(booking);
    }

    public boolean checkBookingOverlapV2(Booking newBooking) {

        List<Booking> allActivityBookings = new ArrayList<>();

        for (Booking booking : getBookingsForAvailability()) {
            if (booking.getActivityType().getActivityId()
                    .equals(newBooking.getActivityType().getActivityId())) {

                allActivityBookings.add(booking);
            }
        }

        for (Booking existing : allActivityBookings) {
            LocalDateTime existingStart = existing.getStartTime();
            LocalDateTime existingEnd = existing.getEndTime();

            if(newBooking.getStartTime().isBefore(existingEnd) && newBooking.getEndTime().isAfter(existingStart)){
                return true;
            }
        }
        return false;
    }
}
