package com.feniksovich.restcalendar.service;

import com.feniksovich.restcalendar.dto.calendar.CalendarData;
import com.feniksovich.restcalendar.dto.calendar.DayData;
import com.feniksovich.restcalendar.dto.calendar.MonthData;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.Month;
import java.time.Year;
import java.util.ArrayList;
import java.util.List;

@Service
@CacheConfig(cacheNames = "calendars")
public class CalendarServiceImpl implements CalendarService {
    
    @Override
    @Cacheable(key = "#yearNumber", sync = true)
    public CalendarData getCalendar(int yearNumber) {
        return buildCalendar(yearNumber);
    }

    private CalendarData buildCalendar(int yearNumber) {
        final List<MonthData> months = new ArrayList<>(12);
        final Year year = Year.of(yearNumber);

        for (final Month month : Month.values()) {
            final List<DayData> days = generateDaysForMonth(year, month);
            months.add(new MonthData(month.getValue(), month, days));
        }

        return new CalendarData(yearNumber, List.copyOf(months));
    }

    private List<DayData> generateDaysForMonth(Year year, Month month) {
        final List<DayData> daysData = new ArrayList<>();
        final int days = month.length(year.isLeap());

        for (int day = 1; day <= days; day++) {
            final LocalDate date = LocalDate.of(year.getValue(), month, day);
            final DayOfWeek dayOfWeek = date.getDayOfWeek();
            daysData.add(new DayData(day, dayOfWeek));
        }
        
        return daysData;
    }
}
