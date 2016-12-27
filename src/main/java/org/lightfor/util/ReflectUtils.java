package org.lightfor.util;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;

/**
 * f反射工具类
 * Created by Light on 2016/1/29.
 */
public enum ReflectUtils {
    INSTANCE;

    /**
     * 利用反射获取指定对象的指定属性
     * @param obj 目标对象
     * @param fieldName 目标属性
     * @return 目标属性的值
     */
    public static Object getFieldValue(Object obj, String fieldName) {
        Object result = null;
        Field field = getField(obj, fieldName);
        if (field != null) {
            field.setAccessible(true);
            try {
                result = field.get(obj);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return result;
    }

    /**
     * 利用反射获取指定对象里面的指定属性
     * @param obj 目标对象
     * @param fieldName 目标属性
     * @return 目标字段
     */
    private static Field getField(Object obj, String fieldName) {
        Field field = null;
        for (Class<?> clazz = obj.getClass(); clazz != Object.class; clazz = clazz
                .getSuperclass()) {
            try {
                field = clazz.getDeclaredField(fieldName);
                break;
            } catch (NoSuchFieldException e) {
                //ignore exception
            }
        }
        return field;
    }

    /**
     * 利用反射设置指定对象的指定属性为指定的值
     * @param obj 目标对象
     * @param fieldName 目标属性
     * @param fieldValue 目标值
     */
    public static void setFieldValue(Object obj, String fieldName,
                                     Object fieldValue) {
        Field field = getField(obj, fieldName);
        if (field != null) {
            try {
                field.setAccessible(true);
                field.set(obj, fieldValue);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 根据指定的对象、方法、参数反射调用，并返回调用结果
     * @param method 方法
     * @param target 对象
     * @param args 参数数组
     * @return 方法调用的返回数据
     */
    public static Object invoke(Method method, Object target, Object[] args) {
        if (method == null) {
            throw new RuntimeException("方法不能为空");
        }
        try {
            if (!method.isAccessible()) {
                method.setAccessible(true);
            }
            return method.invoke(target, args);
        } catch (InvocationTargetException e) {
            Throwable targetException = e.getTargetException();
            throw new RuntimeException("不能调用 '" + method.getName() + "' with " + Arrays.toString(args) + " on " + target + ": " + targetException.getMessage());
        } catch (Exception e) {
            throw new RuntimeException("不能调用 '" + method.getName() + "' with " + Arrays.toString(args) + " on " + target + ": " + e.getMessage());
        }
    }

    /**
     * 根据class类型、methodName方法名称，返回Method对象。
     * 注意：这里不检查参数类型，所以自定义的java类应该避免使用重载方法
     * @param clazz 类
     * @param methodName 方法名
     * @return 方法
     */
    public static Method findMethod(Class<?> clazz, String methodName) {
        Method[] candidates = clazz.getDeclaredMethods();
        for (Method candidate : candidates) {
            if (candidate.getName().equals(methodName)) {
                return candidate;
            }
        }
        if (clazz.getSuperclass() != null) {
            return findMethod(clazz.getSuperclass(), methodName);
        }
        return null;
    }
}
