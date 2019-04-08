package com.quincy.pool;


import java.util.concurrent.*;

/**
 * @author quincy
 * @date 2018/2/7 星期三
 */
public class PoolDemo {

    public static void main(String[] args) {

        ThreadFactory factory = Executors.defaultThreadFactory();
        BlockingQueue<Runnable> queue = new LinkedBlockingQueue<>(5);
        RejectedExecutionHandler reject = new ThreadPoolExecutor.AbortPolicy();

        ThreadPoolExecutor pool = new ThreadPoolExecutor(
                5,
                7,
                5000,
                TimeUnit.MILLISECONDS,
                queue,
                factory,
                reject);

        try {
            int i = 0;
            while (i < 15) {
                MyTask myTask = new MyTask(i);
                pool.execute(myTask);
                System.out.println("线程池中线程数目：" + pool.getPoolSize() + "，队列中等待执行的任务数目：" +
                        pool.getQueue().size() + "，已执行完别的任务数目：" + pool.getCompletedTaskCount());
                i++;
            }
        } finally {
            pool.shutdown();
        }
    }

}

class MyTask implements Runnable {
    private int taskNum;

    public MyTask(int num) {
        this.taskNum = num;
    }

    @Override
    public void run() {
        System.out.println("正在执行task " + taskNum);
        try {
            Thread.sleep(4000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("task " + taskNum + "执行完毕");
    }
}
