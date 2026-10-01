package com.adventurexp.repository;

import com.adventurexp.model.ActivityType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ActivityTypeRepo extends JpaRepository<ActivityType, Long> {
}
