package com.feniksovich.restcalendar.service;

import com.feniksovich.restcalendar.dto.CalendarDto;
import com.feniksovich.restcalendar.mapper.CalendarMapper;
import com.feniksovich.restcalendar.model.YearCalendar;
import com.feniksovich.restcalendar.utils.YearCalendarFactory;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
@CacheConfig(cacheNames = "calendars")
public class CalendarServiceImpl implements CalendarService {

    private final CalendarMapper calendarMapper;

    public CalendarServiceImpl(CalendarMapper calendarMapper) {
        this.calendarMapper = calendarMapper;
    }

    @Override
    @Cacheable(key = "#yearNumber", sync = true)
    public CalendarDto getCalendar(int yearNumber) {
        final YearCalendar calendar = YearCalendarFactory.gregorian(yearNumber);
        return calendarMapper.toDto(calendar);
    }
}
