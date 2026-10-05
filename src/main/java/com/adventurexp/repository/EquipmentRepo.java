package com.adventurexp.repository;

import com.adventurexp.model.Equipment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface EquipmentRepo extends JpaRepository<Equipment, Integer> {

    //ASC = GAMMEL til NY
    List<Equipment> findAllByOrderByLastCheckedAsc();

    //DESC = NY til GAMMEL
    List<Equipment> findAllByOrderByLastCheckedDesc();

    //UDSTYR aldrig tjekket
    List<Equipment> findAllByLastCheckedIsNull();

    List<Equipment> findAllByOutOfServiceTrue();

}
