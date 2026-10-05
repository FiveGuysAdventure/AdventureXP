package com.adventurexp.service;

import com.adventurexp.model.ActivityType;
import com.adventurexp.model.Booking;
import com.adventurexp.model.Equipment;
import com.adventurexp.repository.EquipmentRepo;
import org.springframework.dao.DataAccessResourceFailureException;
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
  
    public List<Equipment> getEquipmentOutOfService() {

        List<Equipment> outOfServiceList = equipmentRepo.outOfService();

        for (Equipment equipmentInService : equipmentRepo.findAll()) {

            if (!equipmentInService.isCurrentlyInUse()) {
                outOfServiceList.add(equipmentInService);
            }
        }
        return outOfServiceList;
    }
}
