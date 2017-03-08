package org.lightfor.util;

import java.util.ArrayList;
import java.util.List;

/**
 * 字符串工具类
 * Created by Light on 2016/1/29.
 */
public enum StringUtils {

    INSTANCE;
    /**
     * 判断字符串是否为穿
     * @param str 字符丿
     * @return 是否为空标识
     */
    public static boolean isEmpty(String str) {
        return str == null || str.length() == 0;
    }

    /**
     * 判断字符串是否为非空
     * @param str 字符丿
     * @return 是否为非空标诿
     */
    public static boolean isNotEmpty(String str) {
        return !isEmpty(str);
    }

    /**
     * 根据字符串数组返回?号分隔的字符串倿
     * @param strArray 字符串数绿
     * @return 逗号分隔的字符串
     */
    public static String getStringByArray(String... strArray) {
        if(strArray == null) return "";
        StringBuilder buffer = new StringBuilder(strArray.length * 10);
        for(String str : strArray) {
            buffer.append(str).append(",");
        }
        buffer.deleteCharAt(buffer.length() - 1);
        return buffer.toString();
    }

    /**
     * xml内容特殊符号替换
     * @param xml xml字符丿
     * @return 替换后的xml
     */
    public static String textXML(String xml) {
        if(xml == null) return "";
        String content = xml;
        content = content.replaceAll("<", "&lt;");
        content = content.replaceAll(">", "&gt;");
        content = content.replaceAll("\"", "&quot;");
        content = content.replaceAll("\n", "</br>");
        return content;
    }

    /**
     * 构?排序条仿
     * @param order 排序方向
     * @param orderBy 排序字段
     * @return 组装好的排序sql
     */
    public static String buildPageOrder(String order, String orderBy) {
        if(isEmpty(orderBy) || isEmpty(order)) return "";
        String[] orderByArray = split(orderBy, ',');
        String[] orderArray = split(order, ',');
        if(orderArray.length != orderByArray.length){
            throw new RuntimeException();
        }
        StringBuilder orderStr = new StringBuilder(30);
        orderStr.append(" order by ");

        for (int i = 0; i < orderByArray.length; i++) {
            orderStr.append(orderByArray[i]).append(" ").append(orderArray[i]).append(" ,");
        }
        orderStr.deleteCharAt(orderStr.length() - 1);
        return orderStr.toString();
    }

    public static boolean isBlank(final CharSequence cs) {
        int strLen;
        if (cs == null || (strLen = cs.length()) == 0) {
            return true;
        }
        for (int i = 0; i < strLen; i++) {
            if (!Character.isWhitespace(cs.charAt(i))) {
                return false;
            }
        }
        return true;
    }

    public static boolean isNotBlank(final CharSequence cs){
        return !isBlank(cs);
    }

    public static String[] split(String str, char separatorChar) {
        if(str == null) {
            return null;
        } else {
            int len = str.length();
            if(len == 0) {
                return new String[0];
            } else {
                List<String> list = new ArrayList<>();
                int i = 0;
                int start = 0;
                boolean match = false;

                while(i < len) {
                    if(str.charAt(i) == separatorChar) {
                        if(match) {
                            list.add(str.substring(start, i));
                            match = false;
                        }

                        ++i;
                        start = i;
                    } else {
                        match = true;
                        ++i;
                    }
                }

                if(match) {
                    list.add(str.substring(start, i));
                }

                return list.toArray(new String[list.size()]);
            }
        }
    }

    /**
     * 箿单字符串匹配方法，支持匹配类型为＿
     * *what *what* what*
     * @param pattern 匹配模式
     * @param str 字符丿
     * @return 是否匹配
     */
    public static boolean simpleMatch(String pattern, String str) {
        if (pattern == null || str == null) {
            return false;
        }
        int firstIndex = pattern.indexOf('*');
        if (firstIndex == -1) {
            return pattern.equals(str);
        }
        if (firstIndex == 0) {
            if (pattern.length() == 1) {
                return true;
            }
            int nextIndex = pattern.indexOf('*', firstIndex + 1);
            if (nextIndex == -1) {
                return str.endsWith(pattern.substring(1));
            }
            String part = pattern.substring(1, nextIndex);
            int partIndex = str.indexOf(part);
            while (partIndex != -1) {
                if (simpleMatch(pattern.substring(nextIndex), str.substring(partIndex + part.length()))) {
                    return true;
                }
                partIndex = str.indexOf(part, partIndex + 1);
            }
            return false;
        }
        return (str.length() >= firstIndex &&
                pattern.substring(0, firstIndex).equals(str.substring(0, firstIndex)) &&
                simpleMatch(pattern.substring(firstIndex), str.substring(firstIndex)));
    }
}
