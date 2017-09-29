package org.lightfor.util;

import java.util.HashMap;
import java.util.Map;

public enum MapUtils {
    INSTANCE;

    public static Map<String, String> getMap(String... string) {
        if (string == null) {
            return null;
        }
        if (string.length % 2 != 0) {
            return null;
        }
        Map<String, String> map = new HashMap<>();
        for (int i = 0; i < string.length; i += 2) {
            map.put(string[i], string[i + 1]);
        }
        return map;
    }
}
