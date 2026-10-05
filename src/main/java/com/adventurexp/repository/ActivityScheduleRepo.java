package com.adventurexp.repository;

import com.adventurexp.model.ActivitySchedule;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ActivityScheduleRepo extends JpaRepository<ActivitySchedule, Long> {
}
