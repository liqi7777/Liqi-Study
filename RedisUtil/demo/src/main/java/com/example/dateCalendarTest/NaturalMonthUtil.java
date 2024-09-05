package com.example.dateCalendarTest;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * @author Sky
 * create 2020/04/22
 * email sky.li@ixiaoshuidi.com
 **/
public class NaturalMonthUtil {

    public static int getDay(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(Calendar.DATE);
    }

    /**
     * 返回日期的月份，1-12,即yyyy-MM-dd中的MM
     *
     * @param date
     * @return
     */
    public static int getMonth(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(Calendar.MONTH) + 1;
    }

    /**
     * 返回日期的年,即yyyy-MM-dd中的yyyy
     *
     * @param date Date
     * @return int
     */
    public static int getYear(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(Calendar.YEAR);
    }

    public static int getDaysOfMonth(int year, int month) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(year, month - 1, 1);
        return calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
    }


    public static int calDiffMonth(String startDate, String endDate) throws ParseException {
        int result = 0;
        try {
            SimpleDateFormat sfd = new SimpleDateFormat("yyyy-MM-dd");
            Date start = sfd.parse(startDate);
            Date end = sfd.parse(endDate);
            int startYear = getYear(start);
            int startMonth = getMonth(start);
            int startDay = getDay(start);
            int endYear = getYear(end);
            int endMonth = getMonth(end);
            int endDay = getDay(end);
            if (startDay > endDay) { //1月17  大于 2月28
                if (endDay == getDaysOfMonth(getYear(new Date()), 2)) {   //也满足一月
                    result = (endYear - startYear) * 12 + endMonth - startMonth;
                } else {
                    result = (endYear - startYear) * 12 + endMonth - startMonth - 1;
                }
            } else {
                result = (endYear - startYear) * 12 + endMonth - startMonth;
            }

        } catch (ParseException e) {
            e.printStackTrace();
        }

        return result;
    }


    public static void main(String[] args) throws ParseException {
        int i = calDiffMonth("2020-02-01", "2020-05-13");
        System.out.println(i);

        System.out.println(getDaysOfMonth(2020,7));
    }

}
