package org.lightfor.util;

/**
 * 开关工具类
 * Created by Light on 2017/5/16.
 */
public enum SwitchUtils {
    INSTANCE;

    private static final String ON = "on";

    private static final String TRACE_SWITCH_KEY = "traceSwitch";

    private static final String traceSwitch = getProp(TRACE_SWITCH_KEY, ON);

    private static String getProp(String name, String def) {
        String str = System.getProperty(name);
        if (str != null) {
            return str;
        }
        str = System.getenv(name);
        if (str != null) {
            return str;
        }
        return def;
    }

    public static boolean traceSwitchOff(){
        return !ON.equals(traceSwitch);
    }
}
