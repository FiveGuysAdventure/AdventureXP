package com.adventurexp.service;

import com.adventurexp.model.Booking;
import com.adventurexp.repository.BookingRepo;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

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

    public List<Booking> getBookings() {
        List<Booking> bookings = bookingRepo.findAll();

        if (bookings.isEmpty()) {
            throw new EntityNotFoundException("Could not load bookings...");
        }

        return bookings;
    }
}
