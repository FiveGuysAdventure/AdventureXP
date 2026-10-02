package com.adventurexp.service;

import com.adventurexp.model.ActivityType;
import com.adventurexp.model.Booking;
import com.adventurexp.model.Equipment;
import com.adventurexp.repository.EquipmentRepo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EquipmentService {

    private final EquipmentRepo equipmentRepo;

    public EquipmentService(EquipmentRepo equipmentRepo) {
        this.equipmentRepo = equipmentRepo;
    }

    public List<Equipment> getEquipmentOverview() {
        return equipmentRepo.findAll();
    }
//
//    public boolean availabilityCheck(Booking booking, Equipment equipment, ActivityType activityType) {
//
//    }
}
