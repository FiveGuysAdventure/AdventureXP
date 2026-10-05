package com.adventurexp.model;


import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "equipment")
public class Equipment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "equipment_id")
    private Integer equipmentId;

    @Column(name = "equipment_name", nullable = false, length = 60)
    private String equipmentName;

    @Column(name = "currently_in_use")
    private boolean currentlyInUse;

    @Column(name = "out_of_service", nullable = false)
    private boolean outOfService;

    @Column(name = "last_checked")
    private LocalDate lastChecked;



    public Equipment() {
    }

    public Equipment(String equipmentName) {

        this.equipmentName = equipmentName;
    }

    public Integer getEquipmentId() {
        return equipmentId;
    }

    public void setEquipmentId(Integer equipmentId) {
        this.equipmentId = equipmentId;
    }

    public String getEquipmentName() {
        return equipmentName;
    }

    public void setEquipmentName(String equipmentName) {
        this.equipmentName = equipmentName;
    }

    public boolean isCurrentlyInUse() {
        return currentlyInUse;
    }

    public void setCurrentlyInUse(boolean currentlyInUse) {
        this.currentlyInUse = currentlyInUse;
    }

    public boolean getOutOfService() {
        return outOfService;
    }

    public void setOutOfService(boolean outOfService) {
        this.outOfService = outOfService;
    }

    public LocalDate getLastChecked() {
        return lastChecked;
    }

    public void setLastChecked(LocalDate lastChecked) {
        this.lastChecked = lastChecked;
    }
}
