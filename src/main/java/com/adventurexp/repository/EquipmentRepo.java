package com.adventurexp.repository;

import com.adventurexp.model.Equipment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EquipmentRepo extends JpaRepository<Equipment, Integer> {

    List<Equipment> outOfService();
}
