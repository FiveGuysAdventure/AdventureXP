package com.adventurexp.model;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "booking")
public class Booking {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long bookingId;

    private LocalDate bookingDate;
    private String contactEmail;
    private String contactNumber;
    private int numOfParticipants;
    private double price;
    private LocalDateTime startTime;
    private LocalDateTime endTime;

    @ManyToOne
    @JoinColumn(name = "activity_id")
    private ActivityType activityType;

    @ManyToOne
    @JoinColumn(name = "employee_id")
    private Employee employee;

    public Booking(){};

    public Booking(LocalDate bookingDate, String contactEmail,
        String contactNumber, int numOfParticipants, double price, LocalDateTime startTime,
        LocalDateTime endTime, ActivityType activityType, Employee employee) {
        this.bookingDate = bookingDate;
        this.contactEmail = contactEmail;
        this.contactNumber = contactNumber;
        this.numOfParticipants = numOfParticipants;
        this.price = price;
        this.startTime = startTime;
        this.endTime = endTime;
        this.activityType = activityType;
        this.employee = employee;
    }

    //Getters
    public LocalDate getBookingDate() {
        return bookingDate;
    }

    public String getContactEmail() {
        return contactEmail;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public int getNumOfParticipants() {
        return numOfParticipants;
    }

    public double getPrice() {
        return price;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public ActivityType getActivityType() {
        return activityType;
    }

    public Employee getEmployee() {
        return employee;
    }

    //Setters


    public void setBookingDate(LocalDate bookingDate) {
        this.bookingDate = bookingDate;
    }

    public void setContactEmail(String contactEmail) {
        this.contactEmail = contactEmail;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }

    public void setNumOfParticipants(int numOfParticipants) {
        this.numOfParticipants = numOfParticipants;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }


    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
    }

    public void setActivityType(ActivityType activityType) {
        this.activityType = activityType;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

}
