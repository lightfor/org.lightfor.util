package org.lightfor.util;

/**
 * URL Utils
 * Created by Light on 2017/6/7.
 */
public enum URLUtils {
    INSTANCE;

    public static String FixedLength(String url){
        if(url == null || url.length() <= 55){
            return url;
        }
        return url.substring(0, 39) + " ... " + url.substring(url.length() - 10);
    }
}
