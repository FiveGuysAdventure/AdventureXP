package com.adventurexp.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "employee_schedule")
public class EmployeeSchedule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "employee_schedule_id")
    private Integer employeeScheduleId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "employee_id", nullable = false)
    private Employee employee;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "activity_id", nullable = false)
    private ActivityType activity;


    @Column(name = "start_time", nullable = false)
    private LocalDateTime startTime;

    protected EmployeeSchedule() {
    }

    public EmployeeSchedule(
            Employee employee,
            LocalDateTime startTime,
            ActivityType activity
            ) {

        this.employee = employee;
        this.startTime = startTime;
        this.activity = activity;
    }

    public Integer getEmployeeScheduleId() {
        return employeeScheduleId;
    }

    public void setEmployeeScheduleId(Integer employeeScheduleId) {
        this.employeeScheduleId = employeeScheduleId;
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

    public ActivityType getActivity() {
        return activity;
    }

    public void setActivity(ActivityType activity) {
        this.activity = activity;
    }
}