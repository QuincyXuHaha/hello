package com.quincy.demo;

import java.util.concurrent.ExecutorService;

/**
 * @author quincy
 * @date 2018/2/27 星期二
 */
public class Main {

    public static void main(String[] args) {
        ExecutorService pool = CustomThreadPool.getPool();
        pool.execute(new Producer());
        pool.execute(new Consumer());
    }
}
