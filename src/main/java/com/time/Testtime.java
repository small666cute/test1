package com.time;

import org.apache.commons.lang3.StringUtils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class Testtime {
    /**
     * yyyyMMdd
     */
    public static final String SHORT_FORMAT = "yyyyMMdd";
    public static void main(String[] args) {
        String currentTime=formatCurrent(SHORT_FORMAT);
        System.out.println(currentTime);
    }
    public static String formatCurrent(String format) {
        if (StringUtils.isBlank(format)) {
            return StringUtils.EMPTY;
        }

        return format(new Date(), format);
    }
    public static String format(Date date, String format) {
        if (date == null || StringUtils.isBlank(format)) {
            return StringUtils.EMPTY;
        }

        return new SimpleDateFormat(format, Locale.SIMPLIFIED_CHINESE).format(date);
    }
}
