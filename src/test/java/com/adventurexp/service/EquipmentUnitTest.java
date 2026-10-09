package com.adventurexp.service;

import com.adventurexp.model.ActivityType;
import com.adventurexp.model.Booking;
import com.adventurexp.model.Equipment;
import com.adventurexp.repository.EquipmentRepo;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.dao.DataAccessResourceFailureException;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ActiveProfiles("test")
@ExtendWith(MockitoExtension.class)
public class EquipmentUnitTest {

    @Mock private EquipmentRepo equipmentRepo;

    @InjectMocks
    private EquipmentService service;

    @Test
    void getEquipmentOverview_shouldReturnAllEquipment() {
        List<Equipment> equipmentList = new ArrayList<>();
        Equipment equipment = new Equipment();
        equipmentList.add(equipment);

        when(equipmentRepo.findAll()).thenReturn(equipmentList);

        assertThat(service.getEquipmentOverview()).isSameAs(equipmentList);
    }

    @Test
    void getEquipmentOutOfService_shouldReturnEquipmentFlaggedOutOfService() {
        List<Equipment> outOfServiceList = new ArrayList<>();
        Equipment equipment = new Equipment();
        equipment.setOutOfService(true);
        outOfServiceList.add(equipment);

        when(equipmentRepo.findAllByOutOfServiceTrue()).thenReturn(outOfServiceList);

        assertThat(service.getEquipmentOutOfService()).isSameAs(outOfServiceList);
    }

    //SAD PATH: Medarbejderen åbner udstyrsoversigten - Der printes en fejlbesked og siden crasher ikke.
    @Test
    void getEquipmentOverview_throwErrorIfDatabaseFails() {
        when(equipmentRepo.findAll()).thenThrow(new DataAccessResourceFailureException("Database utilgængelig"));

        assertThatThrownBy(() -> service.getEquipmentOverview())
                .isInstanceOf(DataAccessResourceFailureException.class);
    }

    //HAPPY PATH: Medarbejderen sorterer ud fra dato - Udstyr vises sorteret enten fra ældste eller nyeste tjek
   @Test
   void getEquipmentSortedByLastChecked_shouldReturnOldestFirst() {
        Equipment oldCheck = new Equipment("Gokarts");
        oldCheck.setLastChecked(LocalDate.of(2026, 1, 1));
        Equipment newCheck = new Equipment("Sumo Suits");
        newCheck.setLastChecked(LocalDate.of(2026, 12, 12));
        when(equipmentRepo.findAllByOrderByLastCheckedAsc()).thenReturn(List.of(oldCheck, newCheck));

        List<Equipment> result = service.getEquipmentSortedByLastChecked(true);

        assertThat(result).containsExactly(oldCheck, newCheck);
   }

    //HAPPY PATH: Medarbejderen sorterer ud fra status check - Udstyr som aldrig er tjekket vises
    @Test
    void getNeverCheckedEquipment_shouldReturnUncheckedEquipment() {
        List<Equipment> neverChecked = List.of(
                new Equipment("Gokarts"),
                new Equipment("Sumo Suits")
        );
        when(equipmentRepo.findAllByLastCheckedIsNull()).thenReturn(neverChecked);

        List<Equipment> result = service.getNeverCheckedEquipment();

        assertThat(result).isEqualTo(neverChecked);
        assertThat(result).allMatch(equipment -> equipment.getLastChecked() == null);
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

    //HAPPY PATH: Medarbejderen sætter udstyr ude af drift - udstyret markeres og gemmes
    @Test
    void markOutOfService_shouldSetOutOfServiceToTrue() {
        Equipment equipment = new Equipment("Gokart 1");
        when(equipmentRepo.findById(1)).thenReturn(Optional.of(equipment));
        when(equipmentRepo.save(equipment)).thenReturn(equipment);

        Equipment result = service.markOutOfService(1);

        assertThat(result.isOutOfService()).isTrue();
        verify(equipmentRepo).save(equipment);
    }

    //SAD PATH: Medarbejderen sætter udstyr ude af drift der ikke findes - der vises en fejl og intet gemmes
    @Test
    void markOutOfService_throwErrorIfEquipmentNotFound() {
        when(equipmentRepo.findById(99)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> service.markOutOfService(99))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Equipment with id 99 not found");

        verify(equipmentRepo, never()).save(any());
    }

    //HAPPY PATH: Medarbejderen sætter udstyr i drift igen - udstyret markeres og gemmes
    @Test
    void markInService_shouldSetOutOfServiceToFalse() {
        Equipment equipment = new Equipment("Gokart 1");
        equipment.setOutOfService(true);
        when(equipmentRepo.findById(1)).thenReturn(Optional.of(equipment));
        when(equipmentRepo.save(equipment)).thenReturn(equipment);

        Equipment result = service.markInService(1);

        assertThat(result.isOutOfService()).isFalse();
        verify(equipmentRepo).save(equipment);
    }

    //SAD PATH: Medarbejderen sætter udstyr i drift der ikke findes - der vises en fejl og intet gemmes
    @Test
    void markInService_throwErrorIfEquipmentNotFound() {
        when(equipmentRepo.findById(99)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> service.markInService(99))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Equipment with id 99 not found");

        verify(equipmentRepo, never()).save(any());
    }

}
