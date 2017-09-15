package org.lightfor.util;

import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.util.Enumeration;

/**
 * IP工具类
 * Created by Light on 2016/11/10.
 */
public enum IpUtils {
    INSTANCE;

    public static long ipToLong(String ip) {
        long result = 0;
        String[] ipArr = ip.split("\\.");
        result += Long.parseLong(ipArr[0]) << 24;
        result += Long.parseLong(ipArr[1]) << 16;
        result += Long.parseLong(ipArr[2]) << 8;
        result += Long.parseLong(ipArr[3]);
        return result;
    }

    public static String longToIp(long ipLong) {
        return (ipLong >>> 24) + "." + ((ipLong & 0x00FFFFFF) >>> 16) + "." + ((ipLong & 0x0000FFFF) >>> 8) + "." + (ipLong & 0x000000FF);
    }

    public static String getIpCode(String ip) {
        if (null == ip || "".equals(ip.trim())) {
            return null;
        }
        StringBuilder sb = new StringBuilder(12);
        String[] ipArr = ip.split("\\.");
        for (String ipChild : ipArr) {
            sb.append(add(ipChild));
        }
        return sb.toString();
    }

    public static String getIpv4() {
        try {
            Enumeration<NetworkInterface> interfaces = NetworkInterface.getNetworkInterfaces();
            while (interfaces.hasMoreElements()) {
                NetworkInterface current = interfaces.nextElement();
                if (!current.isUp() || current.isLoopback() || current.isVirtual()) {
                    continue;
                }
                Enumeration<InetAddress> addresses = current.getInetAddresses();
                while (addresses.hasMoreElements()) {
                    InetAddress inetAddress = addresses.nextElement();
                    if (inetAddress.isLoopbackAddress()) {
                        continue;
                    }
                    if (inetAddress instanceof Inet4Address) {
                        return inetAddress.getHostAddress();
                    }
                }
            }
        } catch (Exception e) {
            LogUtils.error("获取Ipv4地址异常", e);
        }
        return null;
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
