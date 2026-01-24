package com.spring.airag.common.utils;

import java.lang.management.ManagementFactory;
import java.lang.management.MemoryMXBean;
import java.lang.management.OperatingSystemMXBean;
import java.lang.management.RuntimeMXBean;

/**
 * 系统信息工具类
 */
public class SystemUtil {

    /**
     * 获取JVM内存信息
     *
     * @return JVM内存信息
     */
    public static String getJvmMemoryInfo() {
        MemoryMXBean memoryBean = ManagementFactory.getMemoryMXBean();
        long heapUsed = memoryBean.getHeapMemoryUsage().getUsed();
        long heapMax = memoryBean.getHeapMemoryUsage().getMax();
        return String.format("堆内存使用: %d MB / %d MB", heapUsed / 1024 / 1024, heapMax / 1024 / 1024);
    }

    /**
     * 获取系统信息
     *
     * @return 系统信息
     */
    public static String getSystemInfo() {
        OperatingSystemMXBean osBean = ManagementFactory.getOperatingSystemMXBean();
        return String.format("操作系统: %s %s (%s)", 
            osBean.getName(), osBean.getVersion(), osBean.getArch());
    }

    /**
     * 获取JVM信息
     *
     * @return JVM信息
     */
    public static String getJvmInfo() {
        RuntimeMXBean runtimeBean = ManagementFactory.getRuntimeMXBean();
        return String.format("JVM: %s %s", 
            runtimeBean.getVmName(), runtimeBean.getSpecVersion());
    }

    /**
     * 获取CPU核心数
     *
     * @return CPU核心数
     */
    public static int getCpuCores() {
        return Runtime.getRuntime().availableProcessors();
    }

    /**
     * 获取JVM运行时间（毫秒）
     *
     * @return JVM运行时间
     */
    public static long getUptime() {
        RuntimeMXBean runtimeBean = ManagementFactory.getRuntimeMXBean();
        return runtimeBean.getUptime();
    }

    /**
     * 获取JVM运行时间（格式化）
     *
     * @return 格式化的JVM运行时间
     */
    public static String getFormattedUptime() {
        long uptime = getUptime();
        long seconds = uptime / 1000;
        long minutes = seconds / 60;
        long hours = minutes / 60;
        long days = hours / 24;

        seconds %= 60;
        minutes %= 60;
        hours %= 24;

        return String.format("%d天 %d小时 %d分钟 %d秒", days, hours, minutes, seconds);
    }

    /**
     * 获取系统负载平均值
     *
     * @return 系统负载平均值
     */
    public static double getSystemLoadAverage() {
        OperatingSystemMXBean osBean = ManagementFactory.getOperatingSystemMXBean();
        return osBean.getSystemLoadAverage();
    }

    /**
     * 获取已使用内存百分比
     *
     * @return 已使用内存百分比
     */
    public static double getUsedMemoryPercentage() {
        MemoryMXBean memoryBean = ManagementFactory.getMemoryMXBean();
        long heapUsed = memoryBean.getHeapMemoryUsage().getUsed();
        long heapMax = memoryBean.getHeapMemoryUsage().getMax();
        if (heapMax == -1) {
            return -1; // 无法获取最大堆内存
        }
        return (double) heapUsed / heapMax * 100;
    }
}