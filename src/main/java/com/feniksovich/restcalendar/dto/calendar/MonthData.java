package com.feniksovich.restcalendar.dto.calendar;

import java.time.Month;
import java.util.List;

public record MonthData(int number, Month month, List<DayData> days) {
}
