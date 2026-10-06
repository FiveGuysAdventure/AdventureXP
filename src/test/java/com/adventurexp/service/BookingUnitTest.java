package com.adventurexp.service;

import com.adventurexp.exceptions.BookingConflictException;
import com.adventurexp.model.ActivityType;
import com.adventurexp.model.Booking;
import com.adventurexp.model.Employee;
import com.adventurexp.model.Equipment;
import com.adventurexp.repository.*;
import jakarta.persistence.EntityNotFoundException;
import net.bytebuddy.asm.Advice;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.*;

@ActiveProfiles("test")
@ExtendWith(MockitoExtension.class)
public class BookingUnitTest {

    @Mock private BookingRepo bookingRepo;

    @Mock private EquipmentService equipmentService;

    @InjectMocks
    private BookingService service;

    @Test
    void getBooking_shouldReturnBookingWhenFound() {
        Booking booking = new Booking();
        when(bookingRepo.findById(1L)).thenReturn(Optional.of(booking));

        Booking result = service.getBooking(1L);
        assertThat(result).isSameAs(booking);
    }

        @Test
        void getBooking_throwErrorWhenBookingNotFound() {
            when(bookingRepo.findById(1L)).thenReturn(Optional.empty());

            assertThatThrownBy(() -> service.getBooking(1L)).isInstanceOf(EntityNotFoundException.class);
        }

    @Test
    void getAllBookings_shouldReturnBookings() {
        List<Booking> allBookings = new ArrayList<>();
        Booking booking = new Booking();
        allBookings.add(booking);

        when(bookingRepo.findAll()).thenReturn(allBookings);

        assertThat(service.getAllBookings()).isSameAs(allBookings);
    }

        @Test
        void getAllBookings_shouldReturnBookingsCouldNotBeLoaded() {
            List<Booking> bookings = new ArrayList<>();
            when(bookingRepo.findAll()).thenReturn(bookings);

            assertThatThrownBy(() -> service.getAllBookings()).isInstanceOf(IllegalArgumentException.class);
        }

    @Test
    void createBookingDate_shouldReturnBookingDateWhenFound() {
        Booking booking = new Booking();
        service.setBookingDate(booking, LocalDate.of(2026,10,2));

        when(bookingRepo.findById(1L)).thenReturn(Optional.of(booking));
        Booking result = service.getBooking(1L);
        assertThat(result.getBookingDate()).isSameAs(booking.getBookingDate());
    }

    @Test
    void createBookingContactEmail_shouldReturnBookingContactEmailWhenFound() {
        Booking booking = new Booking();
        service.setContactEmail(booking, "testemail@gmail.com");

        when(bookingRepo.findById(1L)).thenReturn(Optional.of(booking));
        Booking result = service.getBooking(1L);
        assertThat(result.getContactEmail()).isSameAs(booking.getContactEmail());
    }

    @Test
    void createBookingContactNumber_shouldReturnBookingContactNumberWhenFound() {
        Booking booking = new Booking();
        service.setContactNumber(booking, "60614475");

        when(bookingRepo.findById(1L)).thenReturn(Optional.of(booking));
        Booking result = service.getBooking(1L);
        assertThat(result.getContactNumber()).isSameAs(booking.getContactNumber());
    }

    @Test
    void createBookingNumOfParticipants_shouldReturnBookingNumOfParticipantsWhenFound() {
        Booking booking = new Booking();
        service.setNumOfParticipants(booking, 2);

        when(bookingRepo.findById(1L)).thenReturn(Optional.of(booking));
        Booking result = service.getBooking(1L);
        assertThat(result.getNumOfGuests()).isEqualTo(2);
    }

    @Test
    void createBookingPrice_shouldReturnBookingPriceWhenFound() {
        Booking booking = new Booking();
        service.setPrice(booking, 999.0);

        when(bookingRepo.findById(1L)).thenReturn(Optional.of(booking));
        Booking result = service.getBooking(1L);
        assertThat(result.getPrice()).isEqualTo(999.0);
    }

    @Test
    void createBookingStartTime_shouldReturnBookingStartTimeWhenFound() {
        Booking booking = new Booking();
        service.setStartTime(booking, LocalDateTime.of(2026,10,2,12,30));

        when(bookingRepo.findById(1L)).thenReturn(Optional.of(booking));
        Booking result = service.getBooking(1L);
        assertThat(result.getStartTime()).isEqualTo(LocalDateTime.of(2026,10,2,12,30));
    }

    @Test
    void createBookingEndTime_shouldReturnBookingEndTimeWhenFound() {
        Booking booking = new Booking();
        service.setEndTime(booking, LocalDateTime.of(2026,10,2,14,30));

        when(bookingRepo.findById(1L)).thenReturn(Optional.of(booking));
        Booking result = service.getBooking(1L);
        assertThat(result.getEndTime()).isEqualTo(LocalDateTime.of(2026,10,2,14,30));
    }

    @Test
    void createBookingActivityType_shouldReturnBookingActivityTypeWhenFound() {
        Booking booking = new Booking();
        ActivityType activityType = new ActivityType();
        service.setActivityType(booking, activityType);
        when(bookingRepo.findById(1L)).thenReturn(Optional.of(booking));
        Booking result = service.getBooking(1L);
        assertThat(result).isSameAs(booking);
    }

    @Test
    void getBookings_shouldReturnBookingsCouldNotBeLoaded() {
        List<Booking> bookings = new ArrayList<>();
        when(bookingRepo.findAll()).thenReturn(bookings);

        assertThatThrownBy(() -> service.getAllBookings()).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void createBooking_shouldReturnCreatedBookingWhenFound() {
        ActivityType activityType = new ActivityType();
        activityType.setActivityId(1L);
        activityType.setDurationMinutes(120);

        Employee employee = new Employee();


        Booking booking = new Booking(
                LocalDate.of(2026,10,2),
                "testemail@gmail.com",
                "60614475",
                12,
                1200 ,
                LocalDateTime.of(2026, 10, 2, 14, 0),
                activityType,
                employee);

        Booking existingBooking = new Booking(
                LocalDate.of(2026, 10, 2),
                "other@gmail.com",
                "12345678",
                5,
                500,
                LocalDateTime.of(2026, 10, 2, 10, 0),
                activityType,
                employee
        );

        when(equipmentService.availabilityCheckForBooking(activityType, booking)).thenReturn(true);
        when(bookingRepo.findAll()).thenReturn(List.of(existingBooking));
        when(bookingRepo.save(booking)).thenReturn(booking);

        Booking result = service.createBooking(booking);

        assertThat(result).isSameAs(booking);
        verify(bookingRepo).save(booking);
    }

    @Test
    void createBooking_ShouldThrowExceptionWhenOverlap(){
        ActivityType activityType = new ActivityType();
        activityType.setActivityId(1L);
        activityType.setDurationMinutes(120);
        Employee employee = new Employee();
        Booking existingBooking = new Booking(
                LocalDate.of(2026, 10, 2),
                "other@gmail.com",
                "12345678",
                5,
                500,
                LocalDateTime.of(2026, 10, 2, 10, 0),
                activityType,
                employee
        );

        Booking booking = new Booking(
                LocalDate.of(2026, 10, 2),
                "testemail@gmail.com",
                "60614475",
                12,
                1200,
                LocalDateTime.of(2026, 10, 2, 11, 0),
                activityType,
                employee
        );

        when(bookingRepo.findAll()).thenReturn(List.of(existingBooking));

        assertThatThrownBy(() -> service.createBooking(booking))
                .isInstanceOf(BookingConflictException.class)
                .hasMessage("Time slot is taken");

        verify(bookingRepo, never()).save(booking);
    }


    @Test
    void createBookingActivityType_shouldReturnBookingsCouldNotBeLoaded() {
        Booking booking = new Booking();
        ActivityType activityType = new ActivityType();

        service.setActivityType(booking, activityType);
        when(bookingRepo.findById(1L)).thenReturn(Optional.of(booking));
        Booking result = service.getBooking(1L);
        assertThat(result.getActivityType()).isEqualTo(activityType);
    }

    @Test
    void createBookingEmployee_shouldReturnBookingEmployeeWhenFound() {
        Booking booking = new Booking();
        Employee employee = new Employee();
        service.setEmployee(booking, employee);

        when(bookingRepo.findById(1L)).thenReturn(Optional.of(booking));
        Booking result = service.getBooking(1L);
        assertThat(result.getEmployee()).isEqualTo(employee);
    }





}
