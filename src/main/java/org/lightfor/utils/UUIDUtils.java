package org.lightfor.utils;

import java.util.UUID;

/**
 * UUID工具类
 * Created by Light on 2016/7/8.
 */
public class UUIDUtils {
    public static String getUUID(){
        String s = UUID.randomUUID().toString();
        return s.substring(0,8)+s.substring(9,13)+s.substring(14,18)+s.substring(19,23)+s.substring(24);        //去掉“-”符号
    }
}
