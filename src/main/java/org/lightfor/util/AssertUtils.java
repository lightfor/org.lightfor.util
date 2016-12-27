package org.lightfor.util;

/**
 * 断言Utils类
 * Created by Light on 2016/1/29.
 */
public enum  AssertUtils {
    INSTANCE;

    /**
     * 断言表达式为true
     * @param expression 表达式
     * @param message 异常打印信息
     */
    private static void isTrue(boolean expression, String message) {
        if (!expression) {
            throw new IllegalArgumentException(message);
        }
    }

    /**
     * 断言表达式为true
     * @param expression 表达式
     */
    public static void isTrue(boolean expression) {
        isTrue(expression, "[Assertion failed] - this expression must be true");
    }

    /**
     * 断言给定的object对象为空
     * @param object 对象
     * @param message 异常打印信息
     */
    private static void isNull(Object object, String message) {
        if (object != null) {
            throw new IllegalArgumentException(message);
        }
    }

    /**
     * 断言给定的object对象为空
     * @param object 对象
     */
    public static void isNull(Object object) {
        isNull(object, "[Assertion failed] - the object argument must be null");
    }

    /**
     * 断言给定的object对象为非空
     * @param object 对象
     * @param message 异常打印信息
     */
    private static void notNull(Object object, String message) {
        if (object == null) {
            throw new IllegalArgumentException(message);
        }
    }

    /**
     * 断言给定的object对象为非空
     * @param object 对象
     */
    public static void notNull(Object object) {
        notNull(object, "[Assertion failed] - this argument is required; it must not be null");
    }

    /**
     * 断言给定的字符串为非空
     * @param str 字符串
     */
    public static void notEmpty(String str) {
        notEmpty(str, "[Assertion failed] - this argument is required; it must not be null or empty");
    }

    /**
     * 断言给定的字符串为非空
     * @param str 字符串
     * @param message 消息
     */
    private static void notEmpty(String str, String message) {
        if (str == null || str.length() == 0) {
            throw new IllegalArgumentException(message);
        }
    }
}
