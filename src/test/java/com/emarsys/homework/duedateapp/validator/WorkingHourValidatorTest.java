package com.emarsys.homework.duedateapp.validator;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import java.time.LocalDateTime;

import static java.time.LocalDateTime.parse;
import static java.time.format.DateTimeFormatter.ofPattern;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;

@RunWith(MockitoJUnitRunner.class)
public class WorkingHourValidatorTest {

    private static final String DATE_TIME_PATTERN = "yyyy-MM-dd HH:mm";

    private WorkingHourValidator workingHourValidator;

    @Before
    public void setUp() {
        workingHourValidator = new WorkingHourValidator();
    }

    @Test
    public void validateWorkingHourShouldThrowExceptionWhenItsWeekend() {
        // Given
        LocalDateTime submitDateTime = parse("2020-11-28 14:12", ofPattern(DATE_TIME_PATTERN));

        // When
        Throwable thrown = catchThrowable(() -> workingHourValidator.validateWorkingHour(submitDateTime));

        // Then
        assertThat(thrown).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    public void validateWorkingHourShouldThrowExceptionWhenItsBeforeWorkHour() {
        // Given
        LocalDateTime submitDateTime = parse("2020-11-27 08:59", ofPattern(DATE_TIME_PATTERN));

        // When
        Throwable thrown = catchThrowable(() -> workingHourValidator.validateWorkingHour(submitDateTime));

        // Then
        assertThat(thrown).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    public void validateWorkingHourShouldThrowExceptionWhenItsAfterWorkHour() {
        // Given
        LocalDateTime submitDateTime = parse("2020-11-27 17:00", ofPattern(DATE_TIME_PATTERN));

        // When
        Throwable thrown = catchThrowable(() -> workingHourValidator.validateWorkingHour(submitDateTime));

        // Then
        assertThat(thrown).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    public void validateWorkingHourShouldNotThrowExceptionWhenDateTimeIsValid() {
        // Given
        LocalDateTime submitDateTime = parse("2020-11-27 16:59", ofPattern(DATE_TIME_PATTERN));

        // When
        workingHourValidator.validateWorkingHour(submitDateTime);

        // Then
    }
}