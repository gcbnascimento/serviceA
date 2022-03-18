package com.microservicea.utils;

import com.microservicea.exception.BusinessException;
import org.apache.http.HttpStatus;

import javax.swing.text.StyledEditorKit;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashSet;
import java.util.Set;

public final class DateUtils {

    public static LocalDate getLocalDate(String date) {
        return LocalDate.parse(date, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
    }

    public static String getFormattedDateForApi(LocalDate date) {
        return "'" + date.format(DateTimeFormatter.ofPattern("MM-dd-yyyy")) + "'";
    }

    public static String getVerifyAndFormattedDateLastDayForApi(LocalDate date) {
        while (!businessDayCheck(date))
            date = date.plusDays(-1L);
        return getFormattedDateForApi(date);
    }

    public static Boolean businessDayCheck(LocalDate date) throws BusinessException {
        final Set<LocalDate> holidays = getFixedHolidays(date.getYear());
        return date.getDayOfWeek() != DayOfWeek.SATURDAY && date.getDayOfWeek() != DayOfWeek.SUNDAY && !holidays.contains(date);
    }

    private static Set<LocalDate> getFixedHolidays(int year) {
        Set<LocalDate> dates = new HashSet<>();

        dates.add(LocalDate.of(year, 9, 7));
        dates.add(LocalDate.of(year, 11, 15));
        dates.add(LocalDate.of(year, 12, 25));
        dates.add(LocalDate.of(year, 01, 01));

        return dates;
    }
}
