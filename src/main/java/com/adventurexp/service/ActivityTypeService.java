package com.adventurexp.service;

import com.adventurexp.model.ActivityType;
import com.adventurexp.model.Booking;
import com.adventurexp.repository.ActivityTypeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.List;


@Service
public class ActivityTypeService {

@Autowired
private ActivityTypeRepo activityTypeRepo;

private Booking booking;

    public List<ActivityType> getAvailableActivities() {
        return activityTypeRepo.findAll();
    }

    public double calculateTimeIntervals() {
        List<ActivityType> activities = activityTypeRepo.findAll();

        int gokartInterval = 30;
        int minigolfInterval = 60;
        int sumoInterval = 60;
        int activityIntervalTotal;

        double activityPriceTotal = 0;

        for (ActivityType a : activities) {

            Long bookingDuration = Duration.between(booking.getStartTime(), booking.getEndTime()).toMinutes();
            a.setDurationMinutes(bookingDuration.intValue());

            if (a.getActivityName().equalsIgnoreCase("Gokart")) {
                activityIntervalTotal = Math.toIntExact(bookingDuration / gokartInterval);
                activityPriceTotal = a.getPricePerPerson() * booking.getNumOfGuests() * activityIntervalTotal;
            }

            if (a.getActivityName().equalsIgnoreCase("Minigolf")) {
                activityIntervalTotal = Math.toIntExact(bookingDuration / minigolfInterval);
                activityPriceTotal = a.getPricePerPerson() * booking.getNumOfGuests() * activityIntervalTotal;
            }

            if (a.getActivityName().equalsIgnoreCase("Sumo wrestling")) {
                activityIntervalTotal = Math.toIntExact(bookingDuration / sumoInterval);
                activityPriceTotal = a.getPricePerPerson() * booking.getNumOfGuests() * activityIntervalTotal;
            }

        }
        return activityPriceTotal;
    }



        // Pris på aktiviteten (Gokart 450 for 30 min)
        // Antallet af gæster
        // aktivitetpris * gæster * antal sessioner af 30 min

}


