package org.lightfor.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 正则表达式工具类
 * Created by Light on 2016/8/9.
 */
public class RegexUtils {

    /**
     * @param input 输入串
     * @param regex 正则表达式
     * @return 返回第一个符合结果
     */
    public static String returnFirstMatch(String input, String regex){
        Pattern pattern =  Pattern.compile(regex);
        Matcher matcher = pattern.matcher(input);
        if (matcher.find()) {
            return matcher.group(1);
        } else {
            return null;
        }
    }
}
