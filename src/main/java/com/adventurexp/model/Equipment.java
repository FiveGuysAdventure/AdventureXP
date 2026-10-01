package com.adventurexp.model;


import jakarta.persistence.*;

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
}
