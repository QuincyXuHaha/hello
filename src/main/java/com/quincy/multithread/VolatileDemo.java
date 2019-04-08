package com.quincy.multithread;

/**
 * 测试volatile关键字的可见性
 *
 * @author quincy
 * @date 2018/2/5 星期一
 */
public class VolatileDemo {

    public static void main(String[] args) throws InterruptedException {
        MyThread6 th = new MyThread6();
        Thread th2 = new Thread(th);
        th2.start();
        Thread.sleep(1000);
        th.stopTask();
    }


}

class MyThread6 implements Runnable {

    private volatile boolean flag = true;

    public void stopTask() {
        flag = false;
    }

    @Override
    public void run() {
        while (flag) {
            System.out.println(Thread.currentThread().getName() + " is running.");
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}