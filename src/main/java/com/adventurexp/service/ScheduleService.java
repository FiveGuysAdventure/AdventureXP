package com.adventurexp.service;

import com.adventurexp.model.Employee;
import com.adventurexp.model.EmployeeSchedule;
import com.adventurexp.records.CalendarEvent;
import com.adventurexp.records.TimeSlot;
import com.adventurexp.repository.EmployeeScheduleRepo;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.time.Duration;
import java.time.LocalTime;
import java.util.*;

@Service
public class ScheduleService {

    private final EmployeeScheduleRepo employeeScheduleRepo;

    public ScheduleService(EmployeeScheduleRepo employeeScheduleRepo) {
        this.employeeScheduleRepo = employeeScheduleRepo;
    }

    public List<EmployeeSchedule> getEmployeeSchedules(Employee employee) {
        List<EmployeeSchedule> employeeSchedules = new ArrayList<>();

        for (EmployeeSchedule employeeSchedule : employeeScheduleRepo.findAll()) {

            if (employee.getEmployeeId().equals(employeeSchedule.getEmployee().getEmployeeId())) {

                employeeSchedules.add(employeeSchedule);
            }
        }


        return employeeSchedules;
    }



    public SequencedCollection<TimeSlot> getEmployeeTimeSlots(Employee employee) {
        SequencedCollection<TimeSlot> timeslots = new ArrayList<>();


        for (EmployeeSchedule employeeSchedule : getEmployeeSchedules(employee)) {
            TimeSlot timeslot = new TimeSlot(employeeSchedule.getStartTime().getDayOfWeek(),
                    employeeSchedule.getStartTime().toLocalTime(),
                    Duration.ofMinutes(employeeSchedule.getActivity().getDurationMinutes()));
            timeslots.add(timeslot);
        }

        return timeslots;
    }

    public Map<DayOfWeek, List<CalendarEvent>> toCalendar(SequencedCollection<TimeSlot> slots) {
        LocalTime dayStart = LocalTime.of(7, 0);
        Map<DayOfWeek, List<CalendarEvent>> byDay = new EnumMap<>(DayOfWeek.class);
        for (DayOfWeek d : DayOfWeek.values()) byDay.put(d, new ArrayList<>());

        for (TimeSlot s : slots) {
            int top = (int) Duration.between(dayStart, s.startTime()).toMinutes();
            int height = (int) s.duration().toMinutes();
            String label = s.startTime() + " – " + s.startTime().plus(s.duration());
            byDay.get(s.dayOfWeek()).add(new CalendarEvent(label, top, height));
        }
        return byDay;
    }

}
