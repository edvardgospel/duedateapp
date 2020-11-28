package com.emarsys.homework.duedateapp.validator;

import org.springframework.stereotype.Service;

@Service
public class TurnaroundTimeValidator {

    private static final String NEGATIVE_TURNAROUND_TIME_MESSAGE = "Turnaround time can't be negative number.";

    public void validateTurnaroundTime(long turnaroundTime) {
        if (turnaroundTime < 0) {
            throw new IllegalArgumentException(NEGATIVE_TURNAROUND_TIME_MESSAGE);
        }
    }
}
