package com.feniksovich.restcalendar.dto.calendar;

import java.util.List;

public record CalendarData(int year, List<MonthData> months) {
}
