package org.lightfor.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * 时间工具类
 * Created by Light on 2016/1/29.
 */
public class DateUtils {
    private static final String DATE_FORMAT_DEFAULT = "yyyy-MM-dd HH:mm:ss";

    private static Long time;

    /**
     * 返回标准格式的当前时间
     * @return 默认
     */
    public static String getTime() {
        return new SimpleDateFormat(DATE_FORMAT_DEFAULT).format(new Date());
    }

    /**
     * 解析日期时间对象
     * @param date 时间
     * @return 时间字符
     */
    public static String parseTime(Object date) {
        if(date == null) return null;
        if(date instanceof Date) {
            return new SimpleDateFormat(DATE_FORMAT_DEFAULT).format(date);
        } else if(date instanceof String) {
            return String.valueOf(date);
        }
        return null;
    }

    public static int getWeekday() {
        return getWeekday(getCalendar());
    }

    public static int getWeekday(Date date) {
        return getWeekday(getCalendar(date));
    }

    public static int getWeekday(Calendar calendar) {
        int weekday = calendar.get(Calendar.DAY_OF_WEEK);
        if (weekday == Calendar.SUNDAY) {
            weekday = 7;
        } else {
            weekday -= 1;
        }
        return weekday;
    }

    public static Calendar getCalendar(){
        return Calendar.getInstance();
    }

    public static Calendar getCalendar(Date date){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar;
    }

    public static int getYear(){
        return getYear(getCalendar());
    }

    public static int getYear(Date date){
        return getYear(getCalendar(date));
    }

    public static int getYear(Calendar calendar){
        return calendar.get(Calendar.YEAR);
    }

    public static int getMonth(){
        return getMonth(getCalendar());
    }

    public static int getMonth(Date date){
        return getMonth(getCalendar(date));
    }

    public static int getMonth(Calendar calendar){
        return calendar.get(Calendar.MONTH) + 1;
    }

    public static int getDate(){
        return getDate(getCalendar());
    }

    public static int getDate(Date date){
        return getDate(getCalendar(date));
    }

    public static int getDate(Calendar calendar){
        return calendar.get(Calendar.DATE);
    }

    public static int getHour(){
        return getHour(getCalendar());
    }

    public static int getHour(Date date){
        return getHour(getCalendar(date));
    }

    public static int getHour(Calendar calendar){
        return calendar.get(Calendar.HOUR_OF_DAY);
    }

    public static int getMinute(){
        return getMinute(getCalendar());
    }

    public static int getMinute(Date date){
        return getMinute(getCalendar(date));
    }

    public static int getMinute(Calendar calendar){
        return calendar.get(Calendar.MINUTE);
    }

    public static int getSecond(){
        return getSecond(getCalendar());
    }

    public static int getSecond(Date date){
        return getSecond(getCalendar(date));
    }

    public static int getSecond(Calendar calendar){
        return calendar.get(Calendar.SECOND);
    }

    public static int getMillisecond(){
        return getMillisecond(getCalendar());
    }

    public static int getMillisecond(Date date){
        return getMillisecond(getCalendar(date));
    }

    public static int getMillisecond(Calendar calendar){
        return calendar.get(Calendar.MILLISECOND);
    }

    public static void start(){
        time = System.currentTimeMillis();
    }

    public static Long count(){
        Long now = System.currentTimeMillis();
        Long count = now - time;
        time = now;
        return count;
    }

}
