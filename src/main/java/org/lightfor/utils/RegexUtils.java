package org.lightfor.utils;

import java.util.ArrayList;
import java.util.List;
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

    public static Integer countMatch(String input, String regex){
        Pattern pattern =  Pattern.compile(regex);
        Matcher matcher = pattern.matcher(input);
        Integer count = 0;
        while(matcher.find()){
            count ++;
        }
        return count;
    }

    public static List<String> returnAllMatch(String input, String regex){
        Pattern pattern =  Pattern.compile(regex);
        Matcher matcher = pattern.matcher(input);
        List<String> result = new ArrayList<>();
        while(matcher.find()){
            result.add(matcher.group(1));
        }
        return result;
    }

}
