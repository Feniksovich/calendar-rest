package com.feniksovich.restcalendar.service;

import com.feniksovich.restcalendar.dto.calendar.CalendarData;

public interface CalendarService {
    CalendarData getCalendar(int yearNumber);
}
