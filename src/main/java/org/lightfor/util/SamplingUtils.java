package org.lightfor.util;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * 抽样工具类
 * Created by Light on 2017/4/19.
 */
public enum SamplingUtils {
    INSTANCE;

    private static final double ratio = 1.0 / 11;

    private static AtomicInteger count = new AtomicInteger(0);

    public static boolean isSelected(){
        return count.addAndGet(1) % (int)(1 / ratio) == 0;
    }
}
