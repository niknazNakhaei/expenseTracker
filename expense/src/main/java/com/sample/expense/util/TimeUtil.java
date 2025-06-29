package com.sample.expense.util;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.TemporalAdjusters;
import java.util.Objects;

public class TimeUtil {

    public static LocalDateTime generateFirstDayOfMonth(LocalDateTime date) {
        date = Objects.nonNull(date) ? date : LocalDateTime.now();
        return date.withDayOfMonth(1).withHour(0).withMinute(0).withSecond(0).withNano(0);
    }

    public static LocalDateTime generateLastDayOfMonth(LocalDateTime date) {
        date = Objects.nonNull(date) ? LocalDateTime.of(date.toLocalDate(), LocalTime.MAX) : LocalDateTime.of(LocalDate.now(), LocalTime.MAX);
        return date.with(TemporalAdjusters.lastDayOfMonth());
    }
}
