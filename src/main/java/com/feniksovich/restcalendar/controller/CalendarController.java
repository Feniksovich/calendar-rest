package com.feniksovich.restcalendar.controller;

import com.feniksovich.restcalendar.dto.CalendarDto;
import com.feniksovich.restcalendar.service.CalendarService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.constraints.Positive;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.time.Year;

@Validated
@RestController
@RequestMapping("/calendar")
@Tag(name = "Календарь")
public class CalendarController {

    private final CalendarService calendarService;

    public CalendarController(CalendarService calendarService) {
        this.calendarService = calendarService;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    @Operation(
            summary = "Получить календарь текущего года",
            description = "Возвращает календарь текущего года по серверному времени"
    )
    @ApiResponse(description = "Операция выполнена успешно")
    public CalendarDto getCurrentCalendar() {
        return calendarService.getCalendar(Year.now().getValue());
    }

    @GetMapping("/{year}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(
            summary = "Получить календарь по номеру года",
            description = "Возвращает календарь указанного года"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Операция выполнена успешно"),
            @ApiResponse(responseCode = "400", description = "Номер года имеет неверный формат")
    })
    public CalendarDto getCalendarByYear(
            @Positive(message = "must be positive number")
            @PathVariable int year
    ) {
        return calendarService.getCalendar(year);
    }
}
