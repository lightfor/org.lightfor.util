package org.lightfor.utils;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;

/**
 * bean工具类
 * Created by Light on 2016/7/21.
 */
public enum  BeanUtils {
    INSTANCE;

    public static Method getWriteMethod(Object beanObj, String name){
        Method method = null;
        try {
            BeanInfo info = Introspector.getBeanInfo(beanObj.getClass());
            PropertyDescriptor[] pds = info.getPropertyDescriptors();
            if(pds != null){
                for (PropertyDescriptor pd : pds) {
                    String pName = pd.getName();
                    if(name.equals(pName)){
                        method = pd.getWriteMethod();
                    }
                }
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return method;
    }
}
