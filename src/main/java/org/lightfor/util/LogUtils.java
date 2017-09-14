package org.lightfor.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public enum LogUtils {

    INSTANCE;

    private static final Logger logger = LoggerFactory.getLogger(LogUtils.class);

    public static void error(String msg, Throwable t){
        logger.error(msg, t);
    }
}
