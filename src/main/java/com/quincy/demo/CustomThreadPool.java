package com.quincy.demo;

import org.springframework.scheduling.concurrent.CustomizableThreadFactory;

import java.util.concurrent.*;

/**
 * 自定义一个线程池
 *
 * @author quincy
 * @date 2018/2/8 星期四
 */
public class CustomThreadPool {

//    private static final long DEFAULT_KEEP_ALIVE_TIME = 60;
//    private static final TimeUnit DEFAULT_TIME_UNIT = TimeUnit.SECONDS;
//    private static final BlockingQueue<Runnable> DEFAULT_BLOCKING_QUEUE = new LinkedBlockingQueue<>(1024);
//    private static final ThreadFactory DEFAULT_THREAD_FACTORY = new CustomThreadFactory();
//    private static final RejectedExecutionHandler DEFAULT_REJECTED_HANDLER = new CustomRejectedPolicy();
//
//    public CustomThreadPool(int coolSize, int maxSize) {
//        this(coolSize, maxSize, DEFAULT_KEEP_ALIVE_TIME);
//    }
//
//    public CustomThreadPool(int coolSize, int maxSize, long keepAliveTime) {
//        this(coolSize, maxSize, keepAliveTime, DEFAULT_BLOCKING_QUEUE);
//    }
//
//    public CustomThreadPool(int coolSize, int maxSize, long keepAliveTime, BlockingQueue<Runnable> queue) {
//        this(coolSize, maxSize, keepAliveTime, queue, DEFAULT_THREAD_FACTORY);
//    }
//
//    public CustomThreadPool(int coolSize, int maxSize, long keepAliveTime, BlockingQueue<Runnable> queue, ThreadFactory factory) {
//        this(coolSize, maxSize, keepAliveTime, queue, factory, DEFAULT_REJECTED_HANDLER);
//    }
//
//    public CustomThreadPool(int coolSize, int maxSize, long keepAliveTime, BlockingQueue<Runnable> queue, ThreadFactory factory, RejectedExecutionHandler handler) {
//        super(coolSize, maxSize, keepAliveTime, DEFAULT_TIME_UNIT, queue, factory, handler);
//    }

    /**
     *
     * @return 线程池
     */
    public static ExecutorService getPool() {
        return new ThreadPoolExecutor(10,
                100,
                10L,
                TimeUnit.SECONDS,
                new LinkedBlockingQueue<>(16),
                new CustomizableThreadFactory(""),
                new CustomRejectedPolicy());
    }


}
