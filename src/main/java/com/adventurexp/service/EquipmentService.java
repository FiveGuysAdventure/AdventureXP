package com.adventurexp.service;

import com.adventurexp.model.ActivityType;
import com.adventurexp.model.Booking;
import com.adventurexp.model.Equipment;
import com.adventurexp.repository.EquipmentRepo;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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

    //ASC = GAMMEL til NY - DESC = NY til GAMMEL
    public List<Equipment> getEquipmentSortedByLastChecked(boolean oldestFirst) {
        if (oldestFirst) {
            return equipmentRepo.findAllByOrderByLastCheckedAsc();
        }
        return equipmentRepo.findAllByOrderByLastCheckedDesc();
    }

    //UDSTYR aldrig tjekket
    public List<Equipment> getNeverCheckedEquipment() {
        return equipmentRepo.findAllByLastCheckedIsNull();
    }

    public List<Equipment> getEquipmentOutOfService() {
        return equipmentRepo.findAllByOutOfServiceTrue();
    }

    public List<Equipment> getEquipmentInService() {
        return equipmentRepo.findAllByOutOfServiceFalse();
    }

    public Equipment markOutOfService(Integer equipmentId) {
        Equipment equipment = equipmentRepo.findById(equipmentId)
                .orElseThrow(() -> new IllegalArgumentException("Equipment with id " + equipmentId + " not found"));

        equipment.setOutOfService(true);
        return equipmentRepo.save(equipment);
    }

    public Equipment markInService(Integer equipmentId) {
        Equipment equipment = equipmentRepo.findById(equipmentId)
                .orElseThrow(() -> new IllegalArgumentException("Equipment with id " + equipmentId + " not found"));

        equipment.setOutOfService(false);
        return equipmentRepo.save(equipment);
    }

    public boolean availabilityCheckForBooking(ActivityType activity, Booking booking) {
        List<Equipment> allEquipment = getEquipmentOverview();
        List<Equipment> activityEquipment = new ArrayList<>();

        for (Equipment equipment : allEquipment) {
            if (equipment.getActivityId().equals(activity.getActivityId())) {
                activityEquipment.add(equipment);
            }
        }
        return activityEquipment.size() >= booking.getNumOfGuests();
    }

    public List<Equipment> getEquipmentForActivity(ActivityType activity) {
        List<Equipment> allEquipment = getEquipmentOverview();
        List<Equipment> activityEquipment = new ArrayList<>();

        for (Equipment equipment : allEquipment) {
            if (equipment.getActivityId().equals(activity.getActivityId())) {
                activityEquipment.add(equipment);
            }
        }
        return activityEquipment;
    }
}
