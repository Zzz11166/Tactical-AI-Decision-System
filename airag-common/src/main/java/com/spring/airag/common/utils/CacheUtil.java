package com.spring.airag.common.utils;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;

/**
 * 本地缓存工具类
 * 注意：在分布式环境下，建议使用Redis等外部缓存
 */
public class CacheUtil {

    // 使用ConcurrentHashMap作为本地缓存存储
    private static final ConcurrentHashMap<String, CacheEntry<Object>> cache = new ConcurrentHashMap<>();

    /**
     * 缓存条目内部类
     */
    private static class CacheEntry<T> {
        private final T value;
        private final long expireTime;

        public CacheEntry(T value, long expireTime) {
            this.value = value;
            this.expireTime = expireTime;
        }

        public T getValue() {
            return value;
        }

        public boolean isExpired() {
            return expireTime > 0 && System.currentTimeMillis() > expireTime;
        }
    }

    /**
     * 存储数据到缓存
     *
     * @param key   键
     * @param value 值
     */
    public static void put(String key, Object value) {
        cache.put(key, new CacheEntry<>(value, 0)); // 0表示永不过期
    }

    /**
     * 存储数据到缓存并设置过期时间
     *
     * @param key     键
     * @param value   值
     * @param timeout 过期时间
     * @param unit    时间单位
     */
    public static void put(String key, Object value, long timeout, TimeUnit unit) {
        long expireTime = System.currentTimeMillis() + unit.toMillis(timeout);
        cache.put(key, new CacheEntry<>(value, expireTime));
    }

    /**
     * 从缓存获取数据
     *
     * @param key 键
     * @return 值，如果不存在或已过期则返回null
     */
    public static Object get(String key) {
        CacheEntry<Object> entry = cache.get(key);
        if (entry == null || entry.isExpired()) {
            cache.remove(key); // 清除过期数据
            return null;
        }
        return entry.getValue();
    }

    /**
     * 检查缓存中是否存在指定键
     *
     * @param key 键
     * @return 是否存在
     */
    public static boolean containsKey(String key) {
        CacheEntry<Object> entry = cache.get(key);
        if (entry == null || entry.isExpired()) {
            cache.remove(key); // 清除过期数据
            return false;
        }
        return true;
    }

    /**
     * 从缓存中移除指定键
     *
     * @param key 键
     * @return 被移除的值
     */
    public static Object remove(String key) {
        CacheEntry<Object> entry = cache.remove(key);
        if (entry != null && !entry.isExpired()) {
            return entry.getValue();
        }
        return null;
    }

    /**
     * 清空所有缓存
     */
    public static void clear() {
        cache.clear();
    }

    /**
     * 获取缓存大小
     *
     * @return 缓存大小
     */
    public static int size() {
        // 清理过期数据后再返回大小
        cleanupExpired();
        return cache.size();
    }

    /**
     * 清理过期数据
     */
    private static void cleanupExpired() {
        cache.entrySet().removeIf(entry -> entry.getValue().isExpired());
    }
}