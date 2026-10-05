package com.adventurexp.service;

import com.adventurexp.exceptions.BookingConflictException;
import com.adventurexp.model.ActivityType;
import com.adventurexp.model.Booking;
import com.adventurexp.model.Employee;
import com.adventurexp.repository.BookingRepo;
import com.adventurexp.repository.EquipmentRepo;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class BookingService {

    private BookingRepo bookingRepo;
    private ActivityType activityType;
    private EquipmentService equipmentService;

    public Booking getBooking(Long id) {
        Optional<Booking> booking = bookingRepo.findById(id);
        if (booking.isEmpty()) {
            throw new EntityNotFoundException("No booking with given id was found: " + id);
        }
        return booking.get();
    }

    public Booking createBooking(Booking booking) {

        if (booking == null) {
            throw new EntityNotFoundException("No booking object was found");
        }

        if (checkBookingOverlapV2(booking)){
            throw new BookingConflictException ("Time slot is taken");
        }

        if (!equipmentService.availabilityCheckForBooking(booking.getActivityType(), booking)) {
            throw new BookingConflictException ("Group size is too big.");
        }
        return bookingRepo.save(booking);
    }


    public List<Booking> getAllBookings() {
        List<Booking> bookings = bookingRepo.findAll();

        if (bookings.isEmpty()) {
            throw new IllegalArgumentException("Could not load bookings...");
        }

        return bookings;
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

    // Tjek for booking overlap
    // Start time + Duration = end time

    // booking kl 15 newstart
    // existing 15-1530
    // endtime = newstart(15) + 30 min

//    public boolean checkBookingOverlap(LocalDateTime newStart, ActivityType activityType) {
//        LocalDateTime endTime = newStart.plusMinutes(activityType.getDurationSeconds());
//
//        for (Booking existing : getAllBookings()) {
//            LocalDateTime existingStart = existing.getStartTime();
//            LocalDateTime existingEnd = existing.getEndTime();
//
//            return newStart.isBefore(existingEnd) && endTime.isAfter(existingStart);
//        }
//        return false;
//    }

    public boolean checkBookingOverlapV2(Booking newBooking) {
//        LocalDateTime newStart = newBooking.getStartTime();
//        LocalDateTime endTime = newBooking.getEndTime();

        List<Booking> allActivityBookings = new ArrayList<>();

        for (Booking booking : getAllBookings()) {
            if (booking.getActivityType().equals(newBooking.getActivityType())) {
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
