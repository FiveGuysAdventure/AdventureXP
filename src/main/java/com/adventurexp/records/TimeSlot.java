package com.adventurexp.records;

import org.jspecify.annotations.NonNull;

import java.time.DayOfWeek;
import java.time.Duration;
import java.time.LocalTime;
import java.util.Comparator;

public record TimeSlot(
        DayOfWeek dayOfWeek,
        LocalTime startTime,
        Duration duration
)
implements Comparable<TimeSlot>
{
    private static final Comparator<TimeSlot> COMPARATOR =
            Comparator.comparing(TimeSlot :: dayOfWeek)
                    .thenComparing(TimeSlot :: startTime)
                    .thenComparing(TimeSlot :: duration);

    @Override
    public int compareTo(final TimeSlot that) {
        return TimeSlot.COMPARATOR.compare(this, that);
    }

}
