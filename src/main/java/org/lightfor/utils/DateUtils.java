package org.lightfor.utils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

/**
 * 时间工具类
 * Created by Light on 2016/1/29.
 */
public class DateUtils {
    private static final String DATE_FORMAT_DEFAULT = "yyyy-MM-dd HH:mm:ss";

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
}
