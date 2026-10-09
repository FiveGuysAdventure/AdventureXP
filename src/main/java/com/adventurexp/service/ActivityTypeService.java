package com.adventurexp.service;

import com.adventurexp.model.ActivityType;
import com.adventurexp.repository.ActivityTypeRepo;
import com.adventurexp.repository.BookingRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;


@Service
public class ActivityTypeService {

@Autowired
private ActivityTypeRepo activityTypeRepo;

@Autowired
    private BookingRepo bookingRepo;

    public List<ActivityType> getAvailableActivities() {
        return activityTypeRepo.findAll();
    }

    public ActivityType getActivityById(Long activityId) {
        return activityTypeRepo.findById(activityId)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "ActivityID " + activityId));
    }

        // Pris på aktiviteten (Gokart 450 for 30 min)
        // Antallet af gæster
        // aktivitetpris * gæster * antal sessioner af 30 min
}


