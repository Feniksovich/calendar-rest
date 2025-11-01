package com.feniksovich.restcalendar.utils;

import com.feniksovich.restcalendar.model.GregorianYearCalendar;
import com.feniksovich.restcalendar.model.YearCalendar;

import java.time.Year;

/**
 * Статическая фабрика для создания экземпляров {@link YearCalendar}.
 *
 * @see YearCalendar
 * @see Year
 */
public final class YearCalendarFactory {

    /**
     * Создаёт григорианский календарь для указанного года ISO‑8601.
     *
     * @param year числовое значение года (например, {@code 2025})
     * @return новый экземпляр {@link YearCalendar} для указанного года
     * @throws java.time.DateTimeException если {@link Year#of(int)} не принимает указанный год
     */
    public static YearCalendar gregorian(int year) {
        return new GregorianYearCalendar(Year.of(year));
    }
}
