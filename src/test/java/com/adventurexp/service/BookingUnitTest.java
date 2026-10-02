package com.adventurexp.service;

import com.adventurexp.model.Booking;
import com.adventurexp.repository.*;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.ActiveProfiles;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.when;

@ActiveProfiles("test")
@ExtendWith(MockitoExtension.class)
public class BookingUnitTest {

    @Mock private BookingRepo bookingRepo;
    @Mock private ActivityTypeRepo activityTypeRepo;
    @Mock private EmployeeRepo employeeRepo;
    @Mock private EquipmentRepo equipmentRepo;
    @Mock private RoleRepo roleRepo;

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








}
