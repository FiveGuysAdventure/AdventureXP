package com.adventurexp.service;

import com.adventurexp.model.ActivityType;
import com.adventurexp.repository.ActivityTypeRepo;
import com.adventurexp.repository.BookingRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class ActivityTypeService {

@Autowired
private ActivityTypeRepo activityTypeRepo;

@Autowired
    private BookingRepo bookingRepo;

    public List<ActivityType> getAvailableActivities() {
        return activityTypeRepo.findAll();
    }

        // Pris på aktiviteten (Gokart 450 for 30 min)
        // Antallet af gæster
        // aktivitetpris * gæster * antal sessioner af 30 min
}


