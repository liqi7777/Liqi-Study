package com.example.dateCalendarTest;

import cn.hutool.core.date.DateUnit;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * @author Sky
 * create 2020/05/20
 * email sky.li@ixiaoshuidi.com
 **/
public class CalcDateCompleteMonthUtil {

    public static void main(String[] args) throws ParseException {
        String format = "yyyy-MM-dd";
        SimpleDateFormat dateFormat = new SimpleDateFormat(format);
        Date preTime = dateFormat.parse("2020-01-01");
        Date nextTime = dateFormat.parse("2020-01-01");
//        calcDateCompleteMonth(preTime, nextTime);
        System.out.println(compareDate(preTime, nextTime));
        test(preTime,nextTime);
        System.out.println(preTime.getTime());
    }

    public static void calcDateCompleteMonth(Date start01, Date end01) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月");
        Calendar start = Calendar.getInstance();
        start.setTime(start01);
        Calendar end = Calendar.getInstance();
        end.setTime(end01);
        //合同结束时间戳
        long endTime = end01.getTime();
//        end.add(Calendar.DATE, 1);
        while (start.before(end) || start.equals(end)) {
            Date time = start.getTime();
            long monthTime = time.getTime();
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(time);
            //获取一个月的开始时间
            calendar.set(Calendar.DAY_OF_MONTH, 1);
            long monthStart = calendar.getTime().getTime();
            //获取一个月的结束时间
            calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
            long monthEnd = calendar.getTime().getTime();
            if (monthTime > monthStart) {
                if (monthEnd <= endTime) {
                    System.out.println(sdf.format(time) + "非整月,之间相差天数:" + ((monthEnd - monthTime) / DateUnit.DAY.getMillis() + 1));
                } else {
                    System.out.println(sdf.format(time) + "非整月,之间相差天数:" + ((endTime - monthTime) / DateUnit.DAY.getMillis() + 1));
                }
                //合同开始时间和当前月份的第一天时间相同时
            } else {
                if (monthEnd <= endTime) {
                    System.out.println(sdf.format(time) + "是整月");
                } else {
                    System.out.println(sdf.format(time) + "非整月,之间相差天数:" + ((endTime - monthTime) / DateUnit.DAY.getMillis() + 1));
                }
            }

            start.set(Calendar.DAY_OF_MONTH, 1);
            start.add(Calendar.MONTH, 1);
        }
    }

    public static int compareDate(Date start, Date end) {
        return start.compareTo(end);
    }

    public static void test(Date start01, Date end01) {
        start01 = new Date();
    }
}
