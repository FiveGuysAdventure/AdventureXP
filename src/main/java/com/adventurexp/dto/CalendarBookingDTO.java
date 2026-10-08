package com.adventurexp.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;

public record CalendarBookingDTO(
        Long bookingId,
        LocalDate bookingDate,
        LocalDateTime startTime,
        LocalDateTime endTime,
        String activityName,
        Long employeeId,
        String employeeName
) { }