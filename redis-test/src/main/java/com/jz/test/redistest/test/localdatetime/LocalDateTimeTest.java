package com.jz.test.redistest.test.localdatetime;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.codec.Base64;
import com.google.common.collect.Lists;
import org.junit.Test;
import org.springframework.util.Base64Utils;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAccessor;
import java.util.ArrayList;
import java.util.Base64.Decoder;
import java.util.List;

/**
 * @author liqi
 * create  2022/1/16 9:46 上午
 */
public class LocalDateTimeTest {

    @Test
    public void test01() {
        LocalDateTime now = LocalDateTime.now();
        System.out.println("计算两个时间的差：");
        LocalDateTime end = LocalDateTime.now().plusHours(47).plusMinutes(20).plusSeconds(15);
        Duration duration = Duration.between(now, end);
        long days = duration.toDays(); //相差的天数

        LocalDateTime end1 = end.plusDays(-days);
        long hours = Duration.between(now, end1).toHours();//相差的小时数

        LocalDateTime end2 = end1.plusHours(-hours);
        long minutes = Duration.between(now, end2).toMinutes();//相差的分钟数

        LocalDateTime end3 = end2.plusMinutes(-minutes);
        long millis = Duration.between(now, end3).toMillis();//相差毫秒数
        long second = Long.valueOf(new DecimalFormat("##").format(Double.valueOf(millis / 1000)));//相差秒数

//        long nanos = duration.toNanos();//相差的纳秒数
        System.out.println(now);
        System.out.println(end);

//        System.out.println("时间差【 "+days+"天："+hours+" 小时："+minutes+" 分钟："+millis+" 毫秒："+nanos+" 纳秒】");
        System.out.println("时间差【" + days + "天" + hours + "小时" + minutes + "分钟" + second + "秒】");
    }


    @Test
    public void test02() {
        LocalDateTime now1 = LocalDateTime.now();
        LocalDateTime localDateTime = now1.plusDays(1).plusMinutes(30);
        LocalDateTime now2 = localDateTime;

        long l = Duration.between(now2, now1).toHours();
        System.out.println(l);

        LocalDateTime localDateTime1 = localDateTime.now().minusHours(5);
        System.out.println(localDateTime1);

        String[] strings = new String[5];
        System.out.println(strings.length);


    }


    @Test
    public void test03() {
        BigDecimal bigDecimal = BigDecimal.valueOf(0.1).setScale(0, BigDecimal.ROUND_UP);
        System.out.println(bigDecimal);
    }

    @Test
    public void test04() {
        String str="0001-01-01 00:00:00";
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime parse = LocalDateTime.parse(str, dateTimeFormatter);
        System.out.println(parse);
        String format = parse.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        System.out.println(format);
    }


    @Test
    public void test05() {
        LocalDateTime now = LocalDateTime.now();
        System.out.println(now);
        LocalDateTime localDateTime = now.plusDays(-1L);
        System.out.println(localDateTime);
    }

    @Test
    public void test06(){
        LocalDateTime now = LocalDateTime.now();
        System.out.println(now);

        LocalDateTime today_start = LocalDateTime.of(LocalDate.now(), LocalTime.MIN);//当天零点

        LocalDateTime today_end = LocalDateTime.of(LocalDate.now(), LocalTime.MAX);//当天零点

        System.out.println(today_start);
        System.out.println(today_end);

    }


    @Test
    public void test07(){
        String a = null;
        //5Lym5a625piv5LiA5Liq6Z2e5bi46ZW/55qE5a2X56ym5Liy

        // 还原为a
        String decodeStr = Base64.decodeStr("eyJ1c2VySW5mbyI6eyJ1c2VyTmFtZSI6Ijg4ODgiLCJtYWNoTm8iOm51bGwsIm1hY2hUeXBlIjoiUlRHIiwidXNlclJvbGUiOiJDWVJQUyJ9LCJleHAiOjE2ODkyNDMwOTAsInVzZXJfbmFtZSI6IntcIm1hY2hUeXBlXCI6XCJSVEdcIixcInVzZXJOYW1lXCI6XCI4ODg4XCIsXCJ1c2VyUm9sZVwiOlwiQ1lSUFNcIn0iLCJqdGkiOiJkNDk1NDMxZC1hMWU0LTQ2NWUtOWRmNS1jYjY0ZmY5YWJlZjYiLCJjbGllbnRfaWQiOiJpVlFoWmkwZlI4MEVMSmV0Nm1qcURiZ1Z3bzB5RWlwaSIsInNjb3BlIjpbImFsbCJdfQ");

        System.out.println(decodeStr);

        List<String> strings = Lists.<String>newArrayList("a", "b", "c");
        List<Object> objects = BeanUtil.copyToList(strings, Object.class);
        System.out.println(objects);

    }


}
