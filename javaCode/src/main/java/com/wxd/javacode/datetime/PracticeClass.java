package com.wxd.javacode.datetime;

import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

import javax.xml.crypto.Data;

public class PracticeClass {
    public static void main(String[] args) {
        // Date -> Instant:
        Instant instant = new Date().toInstant();
        // Calendar -> Instant -> ZonedDateTime:
        Calendar calendar = Calendar.getInstance();
        Instant instant1 = calendar.toInstant();
        ZonedDateTime zdt = instant1.atZone(calendar.getTimeZone().toZoneId());
        // ZonedDateTime -> long:
        long ts = zdt.toEpochSecond()*1000;
        // long -> Date:
        Date date = new Date(ts);
        // long -> Calendar:
        Calendar calendar1 = Calendar.getInstance();
        calendar1.clear();
        calendar1.setTimeZone(TimeZone.getTimeZone(zdt.getZone().getId()));
        calendar1.setTimeInMillis(ts);

        long ts1 = 1574208900000L;
        System.out.println(timestampToString(ts1, Locale.CHINA, "Asia/Shanghai"));
        System.out.println(timestampToString(ts1, Locale.US, "America/New_York"));
    }

    static String timestampToString(long epochMilli, Locale lo, String zoneId) {
        Instant ins = Instant.ofEpochMilli(epochMilli);
        DateTimeFormatter f = DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM, FormatStyle.SHORT);
        return f.withLocale(lo).format(ZonedDateTime.ofInstant(ins, ZoneId.of(zoneId)));
    }
}
