package org.lightfor.utils;

/**
 * IP转换工具
 * Created by Light on 2016/10/12.
 */
public class IpTransUtils {

    /**
     * @param ip IPv4地址
     * @return 可以比较的12位纯数字
     */
    public static String getIpCode(String ip) {
        if (null == ip || "".equals(ip.trim())) {
            return null;
        }
        StringBuilder sb = new StringBuilder(15);
        String[] ipArr = ip.split("\\.");
        for (String ipChild : ipArr) {
            sb.append(add(ipChild));
        }
        return sb.toString();
    }

    private static String add(String c) {
        if (c.length() == 1) {
            return "00" + c;
        } else if (c.length() == 2) {
            return "0" + c;
        } else {
            return c;
        }
    }
}
