package com.spring.airag.common.utils;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Enumeration;

/**
 * 网络工具类
 */
public class NetworkUtil {

    /**
     * 获取本机IP地址
     *
     * @return 本机IP地址
     */
    public static String getLocalIpAddress() {
        try {
            Enumeration<NetworkInterface> interfaces = NetworkInterface.getNetworkInterfaces();
            while (interfaces.hasMoreElements()) {
                NetworkInterface networkInterface = interfaces.nextElement();
                if (networkInterface.isLoopback() || networkInterface.isVirtual() || !networkInterface.isUp()) {
                    continue;
                }
                Enumeration<InetAddress> addresses = networkInterface.getInetAddresses();
                while (addresses.hasMoreElements()) {
                    InetAddress address = addresses.nextElement();
                    if (!address.isLoopbackAddress() && !address.isAnyLocalAddress() && 
                        !address.isLinkLocalAddress() && address.getHostAddress().indexOf(":") == -1) {
                        return address.getHostAddress();
                    }
                }
            }
        } catch (SocketException e) {
            // 如果获取失败，返回localhost
            return "127.0.0.1";
        }
        return "127.0.0.1";
    }

    /**
     * 检查IP地址是否为内网地址
     *
     * @param ipAddress IP地址
     * @return 是否为内网地址
     */
    public static boolean isPrivateIpAddress(String ipAddress) {
        if (ipAddress == null) {
            return false;
        }
        try {
            InetAddress addr = InetAddress.getByName(ipAddress);
            return addr.isSiteLocalAddress() || addr.isLoopbackAddress() || addr.isLinkLocalAddress();
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * 验证IP地址格式
     *
     * @param ipAddress IP地址
     * @return 是否为有效IP地址
     */
    public static boolean isValidIpAddress(String ipAddress) {
        if (ipAddress == null || ipAddress.isEmpty()) {
            return false;
        }

        // IPv4格式验证
        String ipv4Pattern = "^([0-9]{1,3}\\.){3}[0-9]{1,3}$";
        if (ipAddress.matches(ipv4Pattern)) {
            String[] parts = ipAddress.split("\\.");
            for (String part : parts) {
                int num = Integer.parseInt(part);
                if (num < 0 || num > 255) {
                    return false;
                }
            }
            return true;
        }

        // IPv6格式验证（简化版）
        return ipAddress.contains(":") && ipAddress.length() >= 2 && ipAddress.length() <= 39;
    }
}