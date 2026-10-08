package com.adventurexp.dto;

import com.adventurexp.model.ActivityType;
import com.adventurexp.service.ActivityTypeService;
import jakarta.validation.constraints.NotBlank;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class BookingResponseDTO {

    ActivityTypeService activityTypeService;

    @NotBlank(message = "Email is required")
    private String contactEmail;

    @NotBlank(message = "Phone number is required")
    private String contactNumber;

    private int numOfGuests;

    private double price;

    private LocalDateTime startTime;

    private LocalDate bookingDate;

    private LocalDateTime endTime;

    private Long activityTypeId;

    private Long employeeId;

    public BookingResponseDTO() {}

    public String getContactEmail() {return contactEmail;}
    public void setContactEmail(String contactEmail) {this.contactEmail = contactEmail;}

    public String getContactNumber() {return contactNumber;}
    public void setContactNumber(String contactNumber) {this.contactNumber = contactNumber;}

    public int getNumOfGuests() {return numOfGuests;}
    public void setNumOfGuests(int numOfGuests) {this.numOfGuests = numOfGuests;}

    public double getPrice() {return price;}
    public void setPrice(double price) {this.price = price;}

    public LocalDateTime getStartTime() {return startTime;}
    public void setStartTime(LocalDateTime startTime) {this.startTime = startTime;}

    public LocalDate getBookingDate() {return bookingDate;}
    public void setBookingDate(LocalDate bookingDate) {this.bookingDate = bookingDate;}

    public LocalDateTime getEndTime() {return endTime;}
    public void setEndTime(LocalDateTime endTime) {this.endTime = endTime;}

    public Long getActivityTypeId() {return activityTypeId;}
    public void setActivityTypeId(Long activityTypeId) {this.activityTypeId = activityTypeId;}

    public Long getEmployeeId() {return employeeId;}
    public void setEmployeeId(Long employeeId) {this.employeeId = employeeId;}
}