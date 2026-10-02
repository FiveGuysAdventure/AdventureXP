package com.adventurexp.service;

import com.adventurexp.model.Booking;
import com.adventurexp.repository.BookingRepo;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class BookingService {

    private BookingRepo bookingRepo;

    public Booking getBooking(Long id) {
       Optional<Booking> booking = bookingRepo.findById(id);

       if (booking.isEmpty()) {
           throw new EntityNotFoundException();
       }

       return booking.get();
    }
}
