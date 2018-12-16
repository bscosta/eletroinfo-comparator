package com.eletroinfo.eletroinfo.comparator.formatter;

import org.springframework.format.Formatter;
import org.springframework.util.StringUtils;

import java.text.ParseException;
import java.time.LocalDateTime;
import java.util.Locale;

/**
 * @author Bruno Costa
 */

public class LocalDateFormatter implements Formatter<LocalDateTime> {
    @Override
    public LocalDateTime parse(String date, Locale locale) throws ParseException {
        if (!StringUtils.isEmpty(date)) {
            return LocalDateTime.parse(date);
        }
        return null;
    }

    @Override
    public String print(LocalDateTime localDateTime, Locale locale) {
        if (localDateTime != null) {
            return String.valueOf(localDateTime);
        }
        return null;
    }
}
