package com.feniksovich.restcalendar.controller;

import com.feniksovich.restcalendar.dto.calendar.CalendarData;
import com.feniksovich.restcalendar.service.CalendarService;
import jakarta.validation.constraints.Positive;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.time.Year;

@Validated
@RestController
@RequestMapping("/calendar")
public class CalendarController {

    private final CalendarService calendarService;

    public CalendarController(CalendarService calendarService) {
        this.calendarService = calendarService;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public CalendarData getCurrentCalendar() {
        return calendarService.getCalendar(Year.now().getValue());
    }

    @GetMapping("/{year}")
    @ResponseStatus(HttpStatus.OK)
    public CalendarData getCalendarByYear(
            @Positive(message = "must be positive number")
            @PathVariable int year
    ) {
        return calendarService.getCalendar(year);
    }
}
