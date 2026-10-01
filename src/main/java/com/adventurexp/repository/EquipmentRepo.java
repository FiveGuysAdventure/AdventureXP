package com.adventurexp.repository;

import com.adventurexp.model.Equipment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EquipmentRepo extends JpaRepository<Equipment, Long> {

}
