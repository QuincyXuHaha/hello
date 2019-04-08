package com.quincy.demo;

import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * 自定义线程池满了之后继续添加线程后的处理策略
 *
 * @author quincy
 * @date 2018/2/7 星期三
 */
public class CustomRejectedPolicy implements RejectedExecutionHandler {
    @Override
    public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
        System.out.printf("warning:-------%s 线程被拒绝------", r.toString());
        System.out.println();
    }
}
