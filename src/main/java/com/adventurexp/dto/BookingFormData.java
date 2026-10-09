package com.adventurexp.dto;

import com.adventurexp.model.ActivityType;
import com.adventurexp.model.Employee;

import java.time.LocalTime;
import java.util.List;

public class BookingFormData {
    private List<ActivityType> activityTypeList;
    private List<Employee> employeeList;


    public BookingFormData(List<ActivityType> activityTypeList, List<Employee> employeeList) {
        this.activityTypeList = activityTypeList;
        this.employeeList = employeeList;
    }

    public List<ActivityType> getActivityTypeList() {return activityTypeList;}

    public List<Employee> getEmployeeList() {return employeeList;}
}
