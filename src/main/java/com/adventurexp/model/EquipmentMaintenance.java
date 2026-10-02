package com.adventurexp.model;


import jakarta.persistence.*;


import java.time.LocalDate;

@Entity
@Table(name = "equipment_maintenance")
public class EquipmentMaintenance {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "maintenance_id")
    private Integer maintenanceId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "equipment_id", nullable = false)
    private Equipment equipment;

    @Column(name = "maintenance_date", nullable = false)
    private LocalDate date;

    @Column(name = "status", nullable = false)
    private boolean status;

    @Column(name = "description")
    private String description;

    @Column(name = "num_equipment", nullable = false)
    private int numEquipment;



    protected EquipmentMaintenance() {
    }

    public EquipmentMaintenance(
            Equipment equipment,
            LocalDate date,
            boolean status,
            String description,
            int numEquipment) {

        this.equipment = equipment;
        this.date = date;
        this.status = status;
        this.description = description;
        this.numEquipment = numEquipment;
    }


    public Integer getMaintenanceId() {
        return maintenanceId;
    }

    public void setMaintenanceId(Integer maintenanceId) {
        this.maintenanceId = maintenanceId;
    }

    public Equipment getEquipment() {
        return equipment;
    }

    public void setEquipment(Equipment equipment) {
        this.equipment = equipment;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getNumEquipment() {
        return numEquipment;
    }

    public void setNumEquipment(int numEquipment) {
        this.numEquipment = numEquipment;
    }

}

