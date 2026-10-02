package com.adventurexp.service;

import com.adventurexp.model.ActivityType;
import com.adventurexp.model.Booking;
import com.adventurexp.model.Employee;
import com.adventurexp.repository.BookingRepo;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;

import java.util.List;
import java.util.Optional;

@Service
public class BookingService {

    private BookingRepo bookingRepo;

    public Booking getBooking(Long id) {
       Optional<Booking> booking = bookingRepo.findById(id);

       if (booking.isEmpty()) {
           throw new EntityNotFoundException("No booking with given id was found: " + id);
       }

       return booking.get();
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

    public void setNumOfParticipants(Booking booking, int numOfParticipants) {
        booking.setNumOfParticipants(numOfParticipants);
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
    public List<Booking> getBookings() {
        List<Booking> bookings = bookingRepo.findAll();

        if (bookings.isEmpty()) {
            throw new IllegalArgumentException("Could not load bookings...");
        }

        return bookings;
    }
}
