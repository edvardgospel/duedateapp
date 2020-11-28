package com.emarsys.homework.duedateapp.validator;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.time.LocalDateTime;

import static com.emarsys.homework.duedateapp.domain.DueDateProperty.*;

@Service
@RequiredArgsConstructor
public class WorkingHourValidator {

    private static final String NOT_WORKDAY_MESSAGE = "SubmitDateTime is not a workday.";
    private static final String NOT_WORK_HOUR_MESSAGE = "SubmitDateTime is not a  working hour.";

    public void validateWorkingHour(LocalDateTime submitDateTime) {
        validateDayOfWeek(submitDateTime.getDayOfWeek());
        validateHour(submitDateTime.getHour());
    }

    private void validateDayOfWeek(DayOfWeek dayOfWeek) {
        if (!WORKDAYS.contains(dayOfWeek)) {
            throw new IllegalArgumentException(NOT_WORKDAY_MESSAGE);
        }
    }

    private void validateHour(int hour) {
        if (hour < START_WORK_HOUR || hour >= END_WORK_HOUR) {
            throw new IllegalArgumentException(NOT_WORK_HOUR_MESSAGE);
        }
    }

}
