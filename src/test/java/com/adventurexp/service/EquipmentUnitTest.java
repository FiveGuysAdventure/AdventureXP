package com.adventurexp.service;

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
        Equipment equipment = new Equipment("Gokarts", 12, false);
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

    //HAPPY PATH: Medarbejderen sorterer ud fra dato - Udstyr vises sorteret enten fra ældste eller nyeste tjek
   @Test
   void getEquipmentSortedByLastedChecked_shouldReturnOldestFirst() {
        Equipment oldCheck = new Equipment("Gokarts", 12, false);
        oldCheck.setLastChecked(LocalDate.of(2026, 10, 1));
        Equipment newCheck = new Equipment("Sumo Suits", 4, false);
        newCheck.setLastChecked(LocalDate.of(2026, 9, 12));
        when(equipmentRepo.findAllByOrderByLastCheckedAsc()).thenReturn(List.of(oldCheck, newCheck));

        List<Equipment> result = service.getEquipmentSortedByLastChecked(true);

        assertThat(result).containsExactly(oldCheck, newCheck);
   }

    //HAPPY PATH: Medarbejderen sorterer ud fra status check - Udstyr som aldrig er tjekket vises
    @Test
    void getNeverCheckedEquipment_shouldReturnUncheckedEquipment() {
        List<Equipment> neverChecked = List.of(
                new Equipment("Gokarts", 12, false),
                new Equipment("Sumo Suits", 4, false)
        );
        when(equipmentRepo.findAllByLastCheckedIsNull()).thenReturn(neverChecked);

        List<Equipment> result = service.getNeverCheckedEquipment();

        assertThat(result).isEqualTo(neverChecked);
        assertThat(result).allMatch(equipment -> equipment.getLastChecked() == null);
    }
}
