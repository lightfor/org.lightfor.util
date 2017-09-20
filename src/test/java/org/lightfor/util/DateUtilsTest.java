package org.lightfor.util;

import org.junit.Test;

public class DateUtilsTest {

    @Test
    public void test(){
        System.out.println(DateUtils.getWeekday());
        System.out.println(DateUtils.getYear());
        System.out.println(DateUtils.getMonth());
        System.out.println(DateUtils.getDate());
        System.out.println(DateUtils.getHour());
        System.out.println(DateUtils.getMinute());
        System.out.println(DateUtils.getSecond());
        System.out.println(DateUtils.getMillisecond());
    }
}
