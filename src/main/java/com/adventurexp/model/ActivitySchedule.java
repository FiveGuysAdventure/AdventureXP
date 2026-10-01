package com.adventurexp.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "activity_schedule")
public class ActivitySchedule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "activity_schedule_id")
    private Integer activityScheduleId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "activity_id", nullable = false)
    private ActivityType ActivityType;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "employee_id", nullable = false)
    private Employee employee;

    @Column(name = "start_time", nullable = false)
    private LocalDateTime startTime;

    @Column(name = "end_time", nullable = false)
    private LocalDateTime endTime;

    @Column(name = "active", nullable = false)
    private boolean active;


    // Required by JPA
    protected ActivitySchedule() {
    }

    public ActivitySchedule(ActivityType ActivityType,
                            Employee employee,
                            LocalDateTime startTime,
                            LocalDateTime endTime,
                            boolean active) {

        this.ActivityType = ActivityType;
        this.employee = employee;
        this.startTime = startTime;
        this.endTime = endTime;
        this.active = active;
    }


    public Integer getActivityScheduleId() {
        return activityScheduleId;
    }

    public void setActivityScheduleId(Integer activityScheduleId) {
        this.activityScheduleId = activityScheduleId;
    }

    public ActivityType getActivity() {
        return ActivityType;
    }

    public void setActivity(ActivityType ActivityType) {
        this.ActivityType = ActivityType;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
}