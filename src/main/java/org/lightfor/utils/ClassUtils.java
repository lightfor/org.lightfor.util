package org.lightfor.utils;

import java.math.BigDecimal;
import java.math.BigInteger;

/**
 * 类工具类
 * Created by Light on 2016/1/29.
 */
public class ClassUtils {
    /**
     * 查询结果总记录数的类型转换
     * @param count 总记录数（Object类型）
     * @return 总记录数（long）
     */
    public static long castLong(Object count) {
        if(count == null) return -1L;
        if(count instanceof Long) {
            return (Long)count;
        } else if(count instanceof BigDecimal) {
            return ((BigDecimal)count).longValue();
        } else if(count instanceof Integer) {
            return ((Integer)count).longValue();
        } else if(count instanceof BigInteger) {
            return ((BigInteger)count).longValue();
        } else if(count instanceof Byte) {
            return ((Byte)count).longValue();
        } else if(count instanceof Short) {
            return ((Short)count).longValue();
        } else {
            return -1L;
        }
    }

    /**
     * 实例化指定的类名称（全路径）
     * @param clazzStr 类全路径
     * @return 对应的对象
     */
    public static Object newInstance(String clazzStr) {
        try {
            Class<?> clazz = loadClass(clazzStr);
            return instantiate(clazz);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 根据指定的类名称加载类
     * @param className 类全路径
     * @return 类
     * @throws ClassNotFoundException 异常
     */
    private static Class<?> loadClass(String className) throws ClassNotFoundException {
        try {
            return Thread.currentThread().getContextClassLoader().loadClass(className);
        } catch (ClassNotFoundException e) {
            try {
                return Class.forName(className);
            } catch (ClassNotFoundException ex) {
                return ClassLoader.class.getClassLoader().loadClass(className);
            }
        }
    }

    /**
     * 根据类的class实例化对象
     * @param clazz 类
     * @return 实例
     */
    private static <T> T instantiate(Class<T> clazz) {
        if (clazz.isInterface()) {
            return null;
        }
        try {
            return clazz.newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
