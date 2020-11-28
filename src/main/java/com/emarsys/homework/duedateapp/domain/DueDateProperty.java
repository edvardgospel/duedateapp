package com.emarsys.homework.duedateapp.domain;

import java.time.DayOfWeek;
import java.util.List;

import static java.time.DayOfWeek.*;
import static java.util.Arrays.asList;

public class DueDateProperty {

    public static final int START_WORK_HOUR = 9;
    public static final int END_WORK_HOUR = 17;
    public static final List<DayOfWeek> WORKDAYS = asList(MONDAY, TUESDAY, WEDNESDAY, THURSDAY, FRIDAY);

}
