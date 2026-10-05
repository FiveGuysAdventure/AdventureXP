package com.adventurexp.model;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

    @Entity
    @Table(name = "activity_type")
    public class ActivityType {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "activity_id")
        private Long activityId;

        @Column(name = "activity_name", nullable = false, length = 60)
        private String activityName;

        @Column(name = "duration_seconds")
        private int durationMinutes;

        @Column(name = "price_per_person")
        private int pricePerPerson;


        @ManyToOne
        @JoinColumn(name = "tag_id")
        private ActivityTag tagId;

        @OneToMany(mappedBy = "activityType")
        private List<Booking> bookings = new ArrayList<>();


        public ActivityType() {
    }

    public ActivityType(String activityName,
                        int durationMinutes,
                        int pricePerPerson,
                        ActivityTag tagId) {

        this.activityName = activityName;
        this.durationMinutes = durationMinutes;
        this.pricePerPerson = pricePerPerson;
        this.tagId = tagId;
    }

    public Long getActivityId() {
        return activityId;
    }

    public void setActivityId(Long activityId) {
        this.activityId = activityId;
    }

    public String getActivityName() {
        return activityName;
    }

    public void setActivityName(String activityName) {
        this.activityName = activityName;
    }

    public int getDurationMinutes() {
        return durationMinutes;
    }

    public void setDurationMinutes(int durationMinutes) {
        this.durationMinutes = durationMinutes;
    }

    public int getPricePerPerson() {
        return pricePerPerson;
    }

    public void setPricePerPerson(int pricePerPerson) {
        this.pricePerPerson = pricePerPerson;
    }


    public ActivityTag getTagId() {
        return tagId;
    }

    public void setTagId(ActivityTag tagId) {
        this.tagId = tagId;
    }

    public List<Booking> getBookings() {
        return bookings;
    }

    public void setBookings(List<Booking> bookings) {
        this.bookings = bookings;
    }
}