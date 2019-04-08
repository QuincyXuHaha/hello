package com.quincy.multithread;

/**
 * @author quincy
 * @date 2018/2/2 星期五
 */
public class TwoWaysImplementMultiThreadDemo {

    public static void main(String[] args) {
        MyThread2 o1 = new MyThread2();
        MyThread2 o2 = new MyThread2();
        MyThread2 o3 = new MyThread2();
        new Thread(o1).start();
        new Thread(o2).start();
        new Thread(o3).start();

//        MyThread3 my = new MyThread3();
//        new Thread(my).start();
//        new Thread(my).start();
//        new Thread(my).start();

    }

}

class MyThread2 extends Thread {
    private int totalTicket = 10;

    @Override
    public void run() {
        for (int i = 0; i < 20; i++) {
            if (totalTicket > 0) {
                System.out.println(Thread.currentThread().getName() + "买票：ticket--" + totalTicket--);
            }
        }
    }
}

class MyThread3 implements Runnable {
    private int totalTicket = 10;

    @Override
    public void run() {
        for (int i = 0; i < 20; i++) {
            if (totalTicket > 0) {
                System.out.println(Thread.currentThread().getName() + "买票：ticket--" + totalTicket--);
            }
        }
    }
}


