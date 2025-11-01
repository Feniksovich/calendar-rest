package com.feniksovich.restcalendar.mapper;

import com.feniksovich.restcalendar.dto.CalendarDto;
import com.feniksovich.restcalendar.dto.CalendarDto.DayEntry;
import com.feniksovich.restcalendar.dto.CalendarDto.MonthEntry;
import com.feniksovich.restcalendar.model.YearCalendar;
import com.feniksovich.restcalendar.utils.YearCalendarFactory;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.ReportingPolicy;

import java.time.Year;
import java.time.YearMonth;
import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.ERROR)
public interface CalendarMapper {

    @Mapping(target = "year", expression = "java(calendar.year().getValue())")
    @Mapping(target = "months", source = "calendar", qualifiedByName = "toMonths")
    CalendarDto toDto(YearCalendar calendar);

    default YearCalendar toModel(CalendarDto dto) {
        return YearCalendarFactory.gregorian(dto.year());
    }

    @Named("toMonths")
    default List<MonthEntry> toMonths(YearCalendar calendar) {
        return calendar.months()
                .map(ym -> new MonthEntry(
                        ym.getMonthValue(),
                        ym.getMonth(),
                        mapDays(calendar, ym)
                ))
                .toList();
    }

    default List<DayEntry> mapDays(YearCalendar calendar, YearMonth ym) {
        return calendar.datesOf(ym.getMonth())
                .map(date -> new DayEntry(
                        date.getDayOfMonth(),
                        date.getDayOfWeek()
                ))
                .toList();
    }
}
