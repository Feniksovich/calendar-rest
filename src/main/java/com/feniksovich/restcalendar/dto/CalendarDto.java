package com.feniksovich.restcalendar.dto;

import java.time.DayOfWeek;
import java.time.Month;
import java.util.List;

public record CalendarDto(int year, List<MonthEntry> months) {

    /**
     * Месяц календаря.
     */
    public record MonthEntry(int number, Month month, List<DayEntry> days) {}

    /**
     * День календаря.
     */
    public record DayEntry(int number, DayOfWeek dow) {}

}
