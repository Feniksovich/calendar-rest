package com.feniksovich.restcalendar.model;

import java.time.LocalDate;
import java.time.Month;
import java.time.Year;
import java.time.YearMonth;
import java.util.Objects;
import java.util.stream.Stream;

public record GregorianYearCalendar(Year year) implements YearCalendar {
    @Override
    public Stream<YearMonth> months() {
        return Stream.of(Month.values())
                .map(month -> YearMonth.of(year.getValue(), month));
    }

    @Override
    public Stream<LocalDate> datesOf(Month month) {
        final YearMonth yearMonth = YearMonth.of(year.getValue(), month);
        final LocalDate untilExclusive = yearMonth.plusMonths(1).atDay(1);
        return yearMonth.atDay(1).datesUntil(untilExclusive);
    }

    @Override
    public Stream<LocalDate> allDates() {
        final LocalDate start = year.atDay(1);
        final LocalDate endExclusive = year.plusYears(1).atDay(1);
        return start.datesUntil(endExclusive);
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof GregorianYearCalendar(Year inputYear))) return false;
        return Objects.equals(year, inputYear);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(year);
    }

    @Override
    public String toString() {
        return "GregorianYearCalendar{" +
                "year=" + year +
                '}';
    }
}
