package com.adventurexp.controller;


import com.adventurexp.model.Equipment;
import com.adventurexp.service.EquipmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class EquipmentController {

    @Autowired
    private EquipmentService equipmentService;

    @GetMapping("/api/equipment")
    public List<Equipment> getEquipmentInService() {
        return equipmentService.getEquipmentInService();
    }

    @GetMapping("/api/equipment/out-of-service")
    public List<Equipment> getEquipmentOutOfService() {
        return equipmentService.getEquipmentOutOfService();
    }

    @PutMapping("/api/equipment/{id}/out-of-service")
    public ResponseEntity<Equipment> markOutOfService(@PathVariable Integer id) {
        try {
            return ResponseEntity.ok(equipmentService.markOutOfService(id));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("/api/equipment/{id}/in-service")
    public ResponseEntity<Equipment> markInService(@PathVariable Integer id) {
        try {
            return ResponseEntity.ok(equipmentService.markInService(id));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        }
    }
}
