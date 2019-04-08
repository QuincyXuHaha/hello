package com.quincy.multithread;

import java.util.concurrent.locks.LockSupport;

/**
 * @author quincy
 * @date 2018/2/6 星期二
 */
public class LockSupportDemo {

    static Thread main;

    public static void main(String[] args) throws InterruptedException {
        main = Thread.currentThread();
        LockSupportThread th = new LockSupportThread("th");
        th.start();
        Thread.sleep(100);
        System.out.println(Thread.currentThread().getName() + " block");
        // 主线程阻塞
        LockSupport.park(main);
        System.out.println("continue");

    }

    static class LockSupportThread extends Thread {

        LockSupportThread(String name) {
            super(name);
        }

        @Override
        public void run() {
            System.out.println(Thread.currentThread().getName() + " is running.");
            // 主线程唤醒
            LockSupport.unpark(main);
            System.out.println(Thread.currentThread().getName() + " is wake up main.");
        }
    }
}


