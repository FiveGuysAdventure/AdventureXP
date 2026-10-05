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

    @Mock
    private BookingRepo bookingRepo;
    @Mock private ActivityTypeRepo activityTypeRepo;
    @Mock private EmployeeRepo employeeRepo;
    @Mock private EquipmentRepo equipmentRepo;
    @Mock private RoleRepo roleRepo;

    @InjectMocks
    private EquipmentService service;

    //Acceptance test
    //HAPPY PATH: Medarbejderen åbner udstyr oversigten - Alt udstyr vises med navn og dato tjek.
    @Test
    void getEquipmentOverview_shouldReturnWithNameAndLastChecked() {
        Equipment equipment = new Equipment("Gokarts");
        equipment.setLastChecked(LocalDate.of(2026, 10, 1));
        when(equipmentRepo.findAll()).thenReturn(List.of(equipment));

        List<Equipment> result = service.getEquipmentOverview();

        assertThat(result).containsExactly(equipment);
        assertThat(result.get(0).getEquipmentName()).isEqualTo("Gokarts");
        assertThat(result.get(0).getLastChecked()).isEqualTo(LocalDate.of(2026, 10, 1));
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
