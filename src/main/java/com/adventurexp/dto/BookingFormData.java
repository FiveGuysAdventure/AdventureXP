package com.adventurexp.dto;

import com.adventurexp.model.ActivityType;
import com.adventurexp.model.Employee;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

public class BookingFormData {
    private List<ActivityType> activityTypeList;
    private List<Employee> employeeList;
    private List<LocalTime> startTimeIntervalList;


    public BookingFormData(List<ActivityType> activityTypeList, List<Employee> employeeList, List<LocalTime> startTimeIntervalList) {
        this.activityTypeList = activityTypeList;
        this.employeeList = employeeList;
        this.startTimeIntervalList = startTimeIntervalList;
    }

    public List<LocalTime> getStartTimeList() {
        return startTimeIntervalList;
    }

    public List<ActivityType> getActivityTypeList() {return activityTypeList;}

    public List<Employee> getEmployeeList() {return employeeList;}
}
