package org.lightfor.utils;

import java.util.Calendar;

/**
 * 日期工具类
 * Created by Light on 2016/6/7.
 */
public class CalendarUtils {
    /**
     * @param calendar Calendar实例
     * @return 返回一周之中的星期几，星期一返回1，星期天返回7
     */
    public static int getWeekday(Calendar calendar) {
        int weekday = calendar.get(Calendar.DAY_OF_WEEK);
        if (weekday == Calendar.SUNDAY) {
            weekday = 7;
        } else {
            weekday -= 1;
        }
        return weekday;
    }
}
