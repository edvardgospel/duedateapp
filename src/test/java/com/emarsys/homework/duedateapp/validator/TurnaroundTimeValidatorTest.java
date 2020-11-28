package com.emarsys.homework.duedateapp.validator;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;

@RunWith(MockitoJUnitRunner.class)
public class TurnaroundTimeValidatorTest {
    private TurnaroundTimeValidator turnaroundTimeValidator;

    @Before
    public void setUp() {
        turnaroundTimeValidator = new TurnaroundTimeValidator();
    }

    @Test
    public void validateTurnaroundTimeShouldNotThrowExceptionWhenTurnaroundTimeIsZeroOrPositive() {
        // When
        turnaroundTimeValidator.validateTurnaroundTime(2);

        // Then

    }

    @Test
    public void validateTurnaroundTimeShouldThrowExceptionWhenTurnaroundTimeIsNegative() {
        // When
        Throwable thrown = catchThrowable(() -> turnaroundTimeValidator.validateTurnaroundTime(-2));

        // Then
        assertThat(thrown).isInstanceOf(IllegalArgumentException.class);
    }
}