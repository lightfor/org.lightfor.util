package org.lightfor.utils;

import javax.servlet.http.HttpServletRequest;

/**
 * Request工具类
 * Created by Light on 2016/6/8.
 */
public enum  RequestUtils {
    INSTANCE;

    /**
     * 获取客户端真实IP地址
     *
     * @param request 请求对象
     * @return IP地址
     */
    public static String getRemoteRealIpAddr(HttpServletRequest request) {

        String ip = request.getHeader("x-forwarded-for");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }

        // 当通过代理访问时，x-forwarded-for获取的IP地址格式：clientIP, proxy1IP, proxy2IP, ……
        // 添加过滤处理，只获取客户真实IP，过滤掉代理服务器IP
        if (ip == null) {
            ip = "";
        }
        String ips[] = ip.split(",");
        for (String ele : ips) {
            String realIP = ele == null ? "" : ele.trim();
            if (!"unknown".equals(realIP) && !"".equals(realIP)) {
                ip = realIP;
                break;
            }
        }
        return ip;
    }
}
