package com.emarsys.homework.duedateapp.service;

import com.emarsys.homework.duedateapp.validator.TurnaroundTimeValidator;
import com.emarsys.homework.duedateapp.validator.WorkingHourValidator;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.time.LocalDateTime;

import static java.time.LocalDateTime.parse;
import static java.time.format.DateTimeFormatter.ofPattern;
import static org.junit.Assert.assertEquals;

@RunWith(MockitoJUnitRunner.class)
public class DueDateCalculatorTest {

    private static final String DATE_TIME_PATTERN = "yyyy-MM-dd HH:mm";

    @Mock
    private WorkingHourValidator workingHourValidator;
    @Mock
    private TurnaroundTimeValidator turnaroundTimeValidator;

    private DueDateCalculator dueDateCalculator;

    @Before
    public void setUp() {
        dueDateCalculator = new DueDateCalculator(workingHourValidator, turnaroundTimeValidator);
    }

    @Test
    public void calculateDueDateShouldReturnTheNextDaySameHourIfTurnaroundTimeIs8() {
        // Given
        LocalDateTime submitDateTime = parse("2020-11-24 14:12", ofPattern(DATE_TIME_PATTERN));
        LocalDateTime expected = parse("2020-11-25 14:12", ofPattern(DATE_TIME_PATTERN));

        // When
        LocalDateTime actual = dueDateCalculator.calculateDueDate(submitDateTime, 8);

        // Then
        assertEquals(expected, actual);
    }

    @Test
    public void calculateDueDateShouldOverflowToTheNextDayIfHourReachesEndOfTheWorkday() {
        // Given
        LocalDateTime submitDateTime = parse("2020-11-24 14:12", ofPattern(DATE_TIME_PATTERN));
        LocalDateTime expected = parse("2020-11-25 11:12", ofPattern(DATE_TIME_PATTERN));

        // When
        LocalDateTime actual = dueDateCalculator.calculateDueDate(submitDateTime, 5);

        // Then
        assertEquals(expected, actual);
    }

    @Test
    public void calculateDueDateShouldOverflowToTheNextWeekIfDayReachesWeekend() {
        // Given
        LocalDateTime submitDateTime = parse("2020-11-27 14:12", ofPattern(DATE_TIME_PATTERN));
        LocalDateTime expected = parse("2020-11-30 11:12", ofPattern(DATE_TIME_PATTERN));

        // When
        LocalDateTime actual = dueDateCalculator.calculateDueDate(submitDateTime, 5);

        // Then
        assertEquals(expected, actual);
    }

}