package com.spring.airag.common.utils;

import java.util.concurrent.*;

/**
 * 线程工具类
 */
public class ThreadUtil {

    /**
     * 系统处理器数量
     */
    private static final int PROCESSOR_COUNT = Runtime.getRuntime().availableProcessors();

    /**
     * 默认线程池
     */
    private static final ThreadPoolExecutor DEFAULT_THREAD_POOL = new ThreadPoolExecutor(
            PROCESSOR_COUNT,
            PROCESSOR_COUNT * 2,
            60L,
            TimeUnit.SECONDS,
            new LinkedBlockingQueue<>(100),
            new ThreadFactory() {
                private int counter = 0;

                @Override
                public Thread newThread(Runnable r) {
                    Thread t = new Thread(r, "Common-Pool-" + counter++);
                    t.setDaemon(false);
                    return t;
                }
            },
            new ThreadPoolExecutor.CallerRunsPolicy()
    );

    /**
     * 执行异步任务
     *
     * @param runnable 任务
     */
    public static void execute(Runnable runnable) {
        DEFAULT_THREAD_POOL.execute(runnable);
    }

    /**
     * 提交异步任务并获取Future
     *
     * @param callable 任务
     * @param <T>      返回值类型
     * @return Future
     */
    public static <T> Future<T> submit(Callable<T> callable) {
        return DEFAULT_THREAD_POOL.submit(callable);
    }

    /**
     * 提交异步任务并获取Future
     *
     * @param runnable 任务
     * @return Future
     */
    public static Future<?> submit(Runnable runnable) {
        return DEFAULT_THREAD_POOL.submit(runnable);
    }

    /**
     * 提交异步任务并获取Future
     *
     * @param runnable 任务
     * @param result   默认返回值
     * @param <T>      返回值类型
     * @return Future
     */
    public static <T> Future<T> submit(Runnable runnable, T result) {
        return DEFAULT_THREAD_POOL.submit(runnable, result);
    }

    /**
     * 睡眠指定时间（毫秒）
     *
     * @param millis 睡眠时间（毫秒）
     */
    public static void sleep(long millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    /**
     * 睡眠指定时间（秒）
     *
     * @param seconds 睡眠时间（秒）
     */
    public static void sleepSeconds(int seconds) {
        sleep(seconds * 1000L);
    }

    /**
     * 获取当前线程名称
     *
     * @return 线程名称
     */
    public static String getCurrentThreadName() {
        return Thread.currentThread().getName();
    }

    /**
     * 获取当前线程ID
     *
     * @return 线程ID
     */
    public static long getCurrentThreadId() {
        return Thread.currentThread().getId();
    }

    /**
     * 等待线程执行完成
     *
     * @param future Future
     * @param <T>    返回值类型
     * @return 执行结果
     */
    public static <T> T waitForCompletion(Future<T> future) {
        try {
            return future.get();
        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 等待线程执行完成，带超时
     *
     * @param future Future
     * @param timeout 超时时间
     * @param unit    时间单位
     * @param <T>    返回值类型
     * @return 执行结果，如果超时则返回null
     */
    public static <T> T waitForCompletion(Future<T> future, long timeout, TimeUnit unit) {
        try {
            return future.get(timeout, unit);
        } catch (InterruptedException | ExecutionException | TimeoutException e) {
            return null;
        }
    }

    /**
     * 关闭默认线程池
     */
    public static void shutdown() {
        DEFAULT_THREAD_POOL.shutdown();
        try {
            if (!DEFAULT_THREAD_POOL.awaitTermination(60, TimeUnit.SECONDS)) {
                DEFAULT_THREAD_POOL.shutdownNow();
            }
        } catch (InterruptedException e) {
            DEFAULT_THREAD_POOL.shutdownNow();
            Thread.currentThread().interrupt();
        }
    }
}