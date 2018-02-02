package org.lightfor.util;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 正则表达式工具类
 * @author Light
 * @date 2016/8/9.
 */
public enum RegexUtils {

    /**
     * 实例
     */
    INSTANCE;

    public static final String IP = "^([1-9]|[1-9]\\d|1\\d{2}|2[0-4]\\d|25[0-5])\\.(\\d|[1-9]\\d|1\\d{2}|2[0-4]\\d|25[0-5])\\.(\\d|[1-9]\\d|1\\d{2}|2[0-4]\\d|25[0-5])\\.(\\d|[1-9]\\d|1\\d{2}|2[0-4]\\d|25[0-5])$";

    public static boolean isIp(String str) {
        return str.matches(IP);
    }

    public static String returnFirstMatch(String input, String regex){
        return returnFirstMatch(input, Pattern.compile(regex));
    }

    public static String returnFirstMatch(String input, Pattern pattern) {
        Matcher matcher = pattern.matcher(input);
        if (matcher.find()) {
            return matcher.group(1);
        } else {
            return null;
        }
    }

    public static Integer countMatch(String input, String regex){
        return countMatch(input, Pattern.compile(regex));
    }

    public static Integer countMatch(String input, Pattern pattern) {
        Matcher matcher = pattern.matcher(input);
        Integer count = 0;
        while(matcher.find()){
            count ++;
        }
        return count;
    }

    public static List<String> returnAllMatch(String input, String regex){
        return returnAllMatch(input, Pattern.compile(regex));
    }

    public static List<String> returnAllMatch(String input, Pattern pattern){
        Matcher matcher = pattern.matcher(input);
        List<String> result = new ArrayList<>();
        while(matcher.find()){
            result.add(matcher.group(1));
        }
        return result;
    }

}
