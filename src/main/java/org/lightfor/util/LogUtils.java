package org.lightfor.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Light
 * @date 2018-01-11
 */
public enum LogUtils {

    INSTANCE;

    private static final Logger logger = LoggerFactory.getLogger(LogUtils.class);

    public static void error(String msg, Throwable t){
        logger.error(msg, t);
    }

    public static void info(Object msg){
        logger.info(String.valueOf(msg));
    }
}
