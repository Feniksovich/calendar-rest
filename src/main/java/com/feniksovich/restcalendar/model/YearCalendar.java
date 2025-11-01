package com.feniksovich.restcalendar.model;

import java.time.LocalDate;
import java.time.Month;
import java.time.Year;
import java.time.YearMonth;
import java.util.stream.Stream;

/**
 * Доменная модель одного года ISO‑8601.
 * Предоставляет упорядоченные конечные потоки месяцев и дат.
 * Интерфейс описан для разделения контракта от реализации.
 *
 * @see java.time.Year
 * @see java.time.YearMonth
 * @see java.time.LocalDate
 */
public interface YearCalendar {

    /**
     * Возвращает год ISO‑8601.
     *
     * @return ненулевой объект {@link Year}, например {@code Year.of(2025)}
     */
    Year year();

    /**
     * Возвращает 12 значений {@link YearMonth} для данного года
     * в естественном порядке от января до декабря.
     * Новый независимый поток создаётся при каждом вызове.
     *
     * @return упорядоченный поток из 12 элементов {@link YearMonth}
     */
    Stream<YearMonth> months();

    /**
     * Возвращает все даты указанного месяца данного года по возрастанию —
     * с первого по последний день месяца (включительно).
     * Новый независимый поток создаётся при каждом вызове.
     *
     * @param month месяц, для которого требуются даты (не {@code null})
     * @return конечный упорядоченный поток дат указанного месяца
     * @throws NullPointerException если {@code month} равен {@code null}
     */
    Stream<LocalDate> datesOf(Month month);

    /**
     * Возвращает все даты данного года по возрастанию —
     * с 1 января (включительно) по 1 января следующего года (не включительно).
     * Размер потока — 365 или 366 элементов в зависимости от високосности года.
     * Новый независимый поток создаётся при каждом вызове.
     *
     * @return конечный упорядоченный поток всех дат года
     */
    Stream<LocalDate> allDates();
}