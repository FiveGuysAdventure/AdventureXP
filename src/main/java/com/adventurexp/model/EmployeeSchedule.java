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

    @Column(name = "start_time", nullable = false)
    private LocalDateTime startTime;

    @Column(name = "end_time", nullable = false)
    private LocalDateTime endTime;

    @Column(name = "available", nullable = false)
    private boolean available;

    protected EmployeeSchedule() {
    }

    public EmployeeSchedule(
            Employee employee,
            LocalDateTime startTime,
            LocalDateTime endTime,
            boolean available) {

        this.employee = employee;
        this.startTime = startTime;
        this.endTime = endTime;
        this.available = available;
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

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }
}