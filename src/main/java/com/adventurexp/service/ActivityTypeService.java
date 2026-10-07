package com.adventurexp.service;

import com.adventurexp.model.ActivityType;
import com.adventurexp.repository.ActivityTypeRepo;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class ActivityTypeService {


private ActivityTypeRepo activityTypeRepo;

    public List<ActivityType> getAvailableActivities() {
        return activityTypeRepo.findAll();
    }
}

