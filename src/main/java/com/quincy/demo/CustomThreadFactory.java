package com.quincy.demo;

import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author quincy
 * @date 2018/2/8 星期四
 */
public class CustomThreadFactory implements ThreadFactory {
    /**
     * 线程名的前缀
     */
    private String threadNamePrefix;
    /**
     * 统计由该线程工厂创建的线程的总个数
     */
    private final AtomicInteger totalThreadCount = new AtomicInteger(0);

    private ThreadGroup threadGroup;

    public CustomThreadFactory() {
        this.threadNamePrefix = getDefaultThreadNamePrefix();
    }

    public CustomThreadFactory(String threadNamePrefix) {
        this.threadNamePrefix = threadNamePrefix;
        this.threadGroup = new ThreadGroup(getClass().getName());
    }

    public CustomThreadFactory(String threadNamePrefix, String threadGroupName) {
        this.threadNamePrefix = threadNamePrefix;
        this.threadGroup = new ThreadGroup(threadGroupName);
    }

    public String getThreadNamePrefix() {
        return threadNamePrefix;
    }

    public void setThreadNamePrefix(String threadNamePrefix) {
        this.threadNamePrefix = threadNamePrefix;
    }

    /**
     * 获取当前线程工厂创建的线程总数
     */
    public int getTotalThreadCount() {
        return totalThreadCount.get();
    }

    /**
     * 获取默认的线程名前缀
     */
    protected String getDefaultThreadNamePrefix() {
        return getClass().getSimpleName() + "-";
    }

    /**
     * 获取下一个线程名
     */
    protected String getNextThreadName() {
        return getDefaultThreadNamePrefix() + totalThreadCount.incrementAndGet();
    }

    @Override
    public Thread newThread(Runnable target) {
        Thread t = new Thread(threadGroup, target, getNextThreadName());
        t.setPriority(Thread.NORM_PRIORITY);
        t.setDaemon(false);
        return t;
    }

}
