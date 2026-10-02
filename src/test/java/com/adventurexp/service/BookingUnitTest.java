package com.adventurexp.service;

import com.adventurexp.repository.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.ActiveProfiles;

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
    void shouldGetBooking() {

    }








}
