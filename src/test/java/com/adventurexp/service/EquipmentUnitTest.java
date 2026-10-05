package com.adventurexp.service;


import com.adventurexp.model.ActivityType;
import com.adventurexp.model.Booking;
import com.adventurexp.model.Equipment;
import com.adventurexp.repository.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import org.springframework.data.domain.Sort;
import org.springframework.test.context.ActiveProfiles;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import org.springframework.dao.DataAccessResourceFailureException;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalDate;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.doAnswer;

import static org.mockito.Mockito.when;

@ActiveProfiles("test")
@ExtendWith(MockitoExtension.class)
public class EquipmentUnitTest {

    @Mock private EquipmentRepo equipmentRepo;

    @InjectMocks
    private EquipmentService service;

    @Test
    void getEquipmentOverview_shouldReturnWithNameAndLastChecked() {
        Equipment equipment = new Equipment("Gokarts");
        equipment.setLastChecked(LocalDate.of(2026, 10, 1));
        when(equipmentRepo.findAll()).thenReturn(List.of(equipment));

        when(equipmentRepo.outOfService()).thenReturn(outOfServiceList);

        assertThat(service.getEquipmentOutOfService()).isSameAs(outOfServiceList);
    }


    //SAD PATH: Medarbejderen åbner udstyrsoversigten - Der printes en fejlbesked og siden crasher ikke.
    @Test
    void getEquipmentOverview_throwErrorIfDatabaseFails() {
        when(equipmentRepo.findAll()).thenThrow(new DataAccessResourceFailureException("Database utilgængelig"));

        assertThatThrownBy(() -> service.getEquipmentOverview())
                .isInstanceOf(DataAccessResourceFailureException.class);

    }

    @Test
    void checkEquipmentAvailabilityForBooking_shouldReturnTrue() {
        Equipment equipment = new Equipment("Gokarts");
        ActivityType activity = new ActivityType();
        Booking booking = new Booking();
        booking.setNumOfGuests(1);
        equipment.setActivityId(1L);
        activity.setActivityId(1L);

        when(equipmentRepo.findAll()).thenReturn(List.of(equipment));

        assertThat(service.availabilityCheckForBooking(activity, booking)).isEqualTo(true);
    }

    @Test
    void checkEquipmentAvailabilityForBooking_throwErrorIfDatabaseFails() {
        ActivityType activity = new ActivityType();
        Booking booking = new Booking();
        when(equipmentRepo.findAll()).thenThrow(new DataAccessResourceFailureException("Database utilgængelig"));

        assertThatThrownBy(() -> service.availabilityCheckForBooking(activity, booking))
                .isInstanceOf(DataAccessResourceFailureException.class);
    }

    @Test
    void checkEquipmentAvailabilityForActivityType_shouldReturnListOfEquipment() {
        ActivityType activity = new ActivityType();
        Equipment equipment = new Equipment("Gokarts");
        equipment.setActivityId(1L);
        activity.setActivityId(1L);

        when(equipmentRepo.findAll()).thenReturn(List.of(equipment));
        assertThat((service.getEquipmentForActivity(activity))).isEqualTo(List.of(equipment));
    }




}
