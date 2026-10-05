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

        List<Equipment> outOfServiceList = equipmentRepo.outOfService();

        for (Equipment equipmentInService : equipmentRepo.findAll()) {

            if (!equipmentInService.isCurrentlyInUse()) {
                outOfServiceList.add(equipmentInService);
            }
        }
        return outOfServiceList;
    }
}
