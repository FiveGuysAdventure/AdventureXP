package com.adventurexp.model;


import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "equipment")
public class Equipment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "equipment_id")
    private int equipmentId;

    @Column(name = "equipment_name", nullable = false, length = 60)
    private String equipmentName;

    @Column(name = "total_quantity")
    private int totalQuantity;

    @Column(name = "currently_in_use")
    private boolean currentlyInUse;

    @Column(name = "out_of_service", nullable = false)
    private int outOfService = 0;

    @Column(name = "last_checked")
    private LocalDate lastChecked;



    public Equipment() {
    }

    public Equipment(String equipmentName,
                     int totalQuantity,
                     boolean currentlyInUse) {

        this.equipmentName = equipmentName;
        this.totalQuantity = totalQuantity;
        this.currentlyInUse = currentlyInUse;
    }

    public int getEquipmentId() {
        return equipmentId;
    }

    public void setEquipmentId(int equipmentId) {
        this.equipmentId = equipmentId;
    }

    public String getEquipmentName() {
        return equipmentName;
    }

    public void setEquipmentName(String equipmentName) {
        this.equipmentName = equipmentName;
    }

    public int getTotalQuantity() {
        return totalQuantity;
    }

    public void setTotalQuantity(int totalQuantity) {
        this.totalQuantity = totalQuantity;
    }

    public boolean isCurrentlyInUse() {
        return currentlyInUse;
    }

    public void setCurrentlyInUse(boolean currentlyInUse) {
        this.currentlyInUse = currentlyInUse;
    }

    public int getOutOfService() {
        return outOfService;
    }

    public void setOutOfService(int outOfService) {
        this.outOfService = outOfService;
    }

    public LocalDate getLastChecked() {
        return lastChecked;
    }

    public void setLastChecked(LocalDate lastChecked) {
        this.lastChecked = lastChecked;
    }

    //Hvis vi skal bruge det i selve bookingen til at tjekke availability
    public int getAvailableQuantity() {
        return totalQuantity - outOfService;
    }
}
