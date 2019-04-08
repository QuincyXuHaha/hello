package com.quincy.multithread;

/**
 * @author quincy
 * @date 2018/2/2 星期五
 */
public class NotifyWaitDemo {

    public static void main(String[] args) {
        MyThread4 t1 = new MyThread4();

        synchronized (t1) {
            try {
                // 启动“线程t1”
                System.out.println(Thread.currentThread().getName() + " start t1");
//                new Thread(t1).start();
                t1.start();
//                Thread.sleep(1000);
                // 主线程等待t1通过notify()唤醒。
                System.out.println(Thread.currentThread().getName() + " wait()");
                t1.wait();

                System.out.println(Thread.currentThread().getName() + " continue");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}


class MyThread4 extends Thread {

    @Override
    public void run() {
//        synchronized (this) {
        System.out.println(Thread.currentThread().getName() + " is running.");
//            while (true){}
//            notify();
//        }
    }
}