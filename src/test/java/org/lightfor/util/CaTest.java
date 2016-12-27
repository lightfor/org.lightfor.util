package org.lightfor.util;

import org.junit.Test;

import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * 测试Calendar
 * Created by Light on 2016/11/14.
 */
public class CaTest {

    @Test
    public void test(){
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.DAY_OF_MONTH, 24);
        calendar.set(Calendar.MONTH, Calendar.OCTOBER);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        System.out.println(sdf.format(calendar.getTime()));
        calendar.add(Calendar.DAY_OF_MONTH, 99);
        System.out.println(sdf.format(calendar.getTime()));

    }
}
