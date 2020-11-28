package com.emarsys.homework.duedateapp.service;

import com.emarsys.homework.duedateapp.validator.TurnaroundTimeValidator;
import com.emarsys.homework.duedateapp.validator.WorkingHourValidator;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

import static com.emarsys.homework.duedateapp.domain.DueDateProperty.*;

@Service
@AllArgsConstructor
public class DueDateCalculator {

    private static final int WORK_HOURS = END_WORK_HOUR - START_WORK_HOUR;
    private final WorkingHourValidator workingHourValidator;
    private final TurnaroundTimeValidator turnaroundTimeValidator;

    public LocalDateTime calculateDueDate(LocalDateTime submitDateTime, int turnaroundTime) {
        workingHourValidator.validateWorkingHour(submitDateTime);
        turnaroundTimeValidator.validateTurnaroundTime(turnaroundTime);
        int daysToAdd = turnaroundTime / WORK_HOURS;
        int hoursToAdd = turnaroundTime % WORK_HOURS;
        LocalDateTime submitDateTimeWithAddedWorkdays = addWorkdays(submitDateTime, daysToAdd);
        return addWorkHours(submitDateTimeWithAddedWorkdays, hoursToAdd);
    }

    private LocalDateTime addWorkdays(LocalDateTime dateTime, int daysToAdd) {
        while (daysToAdd > 0) {
            dateTime = dateTime.plusDays(1);
            if (isWorkday(dateTime)) {
                daysToAdd--;
            }
        }
        return dateTime;
    }

    private LocalDateTime addWorkHours(LocalDateTime dateTime, int hoursToAdd) {
        int hour = dateTime.getHour();
        if (isHourOverflowingToNextWorkday(hoursToAdd, hour)) {
            dateTime = addWorkdays(dateTime, 1);
            dateTime = dateTime.withHour(START_WORK_HOUR + hour + hoursToAdd - END_WORK_HOUR);
        } else {
            dateTime = dateTime.plusHours(hoursToAdd);
        }
        return dateTime;
    }

    private boolean isHourOverflowingToNextWorkday(int hoursToAdd, int hour) {
        return hour + hoursToAdd >= END_WORK_HOUR;
    }

    private boolean isWorkday(LocalDateTime dateTime) {
        return WORKDAYS.contains(dateTime.getDayOfWeek());
    }

}
