package com.example.java8LocalDataTime;

import org.springframework.format.annotation.DateTimeFormat;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjusters;

import static org.junit.Assert.assertEquals;

/**
 * @author Sky
 * create 2020/06/18
 * email sky.li@ixiaoshuidi.com
 **/
public class LocalDateTimeTest {

    public static void main(String[] args) {
//        method01();
        method02();
    }


    private static void method02() {
        System.out.println(Instant.now());
        System.out.println(LocalDateTime.now());

        Period fromUnits = Period.of(3, 10, 10);
        assertEquals(10, fromUnits.getDays());
        assertEquals(10, fromUnits.getMonths());
        assertEquals(3,fromUnits.getYears());
        Period fromDays = Period.ofDays(50);
        Period fromMonths = Period.ofMonths(5);
        Period fromYears = Period.ofYears(10);
        Period fromWeeks = Period.ofWeeks(40);
        assertEquals(280, fromWeeks.getDays());


        LocalDateTime dateTime =LocalDateTime.ofEpochSecond(System.currentTimeMillis()/1000,0, ZoneOffset.ofHours(8));
        String format = dateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        System.out.println(format);

    }

    private static void method01() {
        DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime startTime = LocalDateTime.now();
        LocalDateTime endTime = startTime.plusDays(7);

        System.out.println("开始时间：" + startTime.format(df) + "，结束时间：" + endTime.format(df));

        LocalDateTime dateTest = LocalDateTime.parse("2018-02-30 12:12:12", df);
        System.out.println("时间自动转化：" + dateTest.toString());

        int daysNum = Period.between(startTime.toLocalDate(), endTime.toLocalDate()).getDays();
        int monthNum = Period.between(startTime.toLocalDate(), endTime.toLocalDate()).getMonths();
        System.out.println("相差天数：" + daysNum);
        System.out.println("相差月数：" + monthNum);

        System.out.println("当前时间向前推6天：" + LocalDateTime.now().minusDays(6));
        System.out.println("当前时间向前推6小时：" + LocalDateTime.now().minusHours(6));


        //本月的第一天
        LocalDate today = LocalDate.now();
        LocalDate firstday = LocalDate.of(today.getYear(), today.getMonth(), 1);
        LocalDate firstday01 = today.with(TemporalAdjusters.firstDayOfMonth());
        //本月的最后一天
        LocalDate lastDay = today.with(TemporalAdjusters.lastDayOfMonth());
        System.out.println("本月的第一天" + firstday);
        System.out.println("本月的第一天" + firstday01);
        System.out.println("本月的最后一天" + lastDay);
    }
}
