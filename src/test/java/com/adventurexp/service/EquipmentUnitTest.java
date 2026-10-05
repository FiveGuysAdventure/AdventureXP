package com.adventurexp.service;


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
}
