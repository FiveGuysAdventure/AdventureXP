package com.adventurexp.service;


import com.adventurexp.model.Equipment;
import com.adventurexp.repository.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.ActiveProfiles;

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

//    @Test
//    void getEquipmentOverview_shouldReturnWithNameAndLastChecked() {
//        Equipment equipment = new Equipment()
//    }
}
