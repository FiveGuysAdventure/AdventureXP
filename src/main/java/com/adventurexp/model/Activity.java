package com.adventurexp.model;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

    @Entity
    @Table(name = "activity_type")
    public class Activity {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "activity_id")
        private int activityId;

        @Column(name = "activity_name", nullable = false, length = 60)
        private String activityName;

        @Column(name = "duration_seconds")
        private int durationSeconds;

        @Column(name = "price_per_person")
        private int pricePerPerson;

        @Column(name = "tag_id")
        private int tagId;

        @OneToMany(mappedBy = "activity")
        private List<Booking> bookings = new ArrayList<>();




        public Activity() {
    }

    public Activity(String activityName,
                    int durationSeconds,
                    int pricePerPerson,
                    int tagId) {

        this.activityName = activityName;
        this.durationSeconds = durationSeconds;
        this.pricePerPerson = pricePerPerson;
        this.tagId = tagId;
    }

    public int getActivityId() {
        return activityId;
    }

    public void setActivityId(int activityId) {
        this.activityId = activityId;
    }

    public String getActivityName() {
        return activityName;
    }

    public void setActivityName(String activityName) {
        this.activityName = activityName;
    }

    public int getDurationSeconds() {
        return durationSeconds;
    }

    public void setDurationSeconds(int durationSeconds) {
        this.durationSeconds = durationSeconds;
    }

    public int getPricePerPerson() {
        return pricePerPerson;
    }

    public void setPricePerPerson(int pricePerPerson) {
        this.pricePerPerson = pricePerPerson;
    }


    public int getTagId() {
        return tagId;
    }

    public void setTagId(int tagId) {
        this.tagId = tagId;
    }

    public List<Booking> getBookings() {
        return bookings;
    }

    public void setBookings(List<Booking> bookings) {
        this.bookings = bookings;
    }
}




