package com.adventurexp.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "employee")
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "employee_id")
    private Long employeeId;

    @Column(name = "emp_name", nullable = false, length = 60)
    private String employeeName;

    @Column(name = "emp_phone_nr", nullable = false , unique = true,length = 60)
    private String employeePhoneNumber;

    @Column(name = "emp_email", nullable = false, unique = true ,length = 100)
    private String employeeEmail;

    @ManyToOne
    @JoinColumn(name = "role_id", nullable = false)
    private Role roleId;

    @JsonIgnore
    @OneToMany(mappedBy = "employee")
    private List<Booking> bookings = new ArrayList<>();


    public Employee() {
    }

    public Employee(String employeeName,
                    String employeePhoneNumber,
                    String employeeEmail,
                    Role roleId) {

        this.employeeName = employeeName;
        this.employeePhoneNumber = employeePhoneNumber;
        this.employeeEmail = employeeEmail;
        this.roleId = roleId;
    }

    public Long getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Long employeeId) {
        this.employeeId = employeeId;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    public String getEmployeePhoneNumber() {
        return employeePhoneNumber;
    }

    public void setEmployeePhoneNumber(String employeePhoneNumber) {
        this.employeePhoneNumber = employeePhoneNumber;
    }

    public String getEmployeeEmail() {
        return employeeEmail;
    }

    public void setEmployeeEmail(String employeeEmail) {
        this.employeeEmail = employeeEmail;
    }

    public Role getRoleId() {
        return roleId;
    }

    public void setRoleId(Role roleId) {
        this.roleId = roleId;
    }

    public List<Booking> getBookings() {
        return bookings;
    }

    public void setBookings(List<Booking> bookings) {
        this.bookings = bookings;
    }
}