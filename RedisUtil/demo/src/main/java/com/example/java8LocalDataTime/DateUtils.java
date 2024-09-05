package com.example.java8LocalDataTime;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class DateUtils {

    //LocalDate -> Date
    public static Date asDate(LocalDate localDate) {
        return Date.from(localDate.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant());
    }

    //LocalDateTime -> Date
    public static Date asDate(LocalDateTime localDateTime) {
        return Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
    }

    //Date -> LocalDate
    public static LocalDate asLocalDate(Date date) {
        return Instant.ofEpochMilli(date.getTime()).atZone(ZoneId.systemDefault()).toLocalDate();
    }

    //Date -> LocalDateTime
    public static LocalDateTime asLocalDateTime(Date date) {
        return Instant.ofEpochMilli(date.getTime()).atZone(ZoneId.systemDefault()).toLocalDateTime();
    }

    //LocalDateTime -> TimeStamp
    public static long LdtToTs(LocalDateTime localDateTime) {
        LocalDateTime now = LocalDateTime.now();
        long timestamp = now.toInstant(ZoneOffset.of("+8")).toEpochMilli();
        return timestamp;
    }

    //TimeStamp -> LocalDateTime
    public static LocalDateTime TsToLdt(long timeStamp) {
        LocalDateTime localDateTime = LocalDateTime.ofEpochSecond(timeStamp / 1000, 0, ZoneOffset.ofHours(8));
        return localDateTime;
    }

    public static void main(String[] args) {

//        System.out.println(asLocalDate(new Date()).getDayOfMonth());
        System.out.println(LdtToTs(LocalDateTime.now()));
        LocalDateTime localDateTime = TsToLdt(1626930331561L);
        System.out.println(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS").format(localDateTime));
    }
}
