package com.feniksovich.restcalendar.service;

import com.feniksovich.restcalendar.dto.CalendarDto;

public interface CalendarService {
    CalendarDto getCalendar(int yearNumber);
}
