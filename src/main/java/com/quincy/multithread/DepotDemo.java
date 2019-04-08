package com.quincy.multithread;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 仓库
 *
 * @author quincy
 * @date 2018/2/5 星期一
 */
public class DepotDemo {
    /**
     * 仓库容量
     */
    private int capacity;
    /**
     * 当前仓库数量
     */
    private int size;
    /**
     * 锁
     */
    private Lock lock;

    /**
     * 生产条件
     */
    private Condition fullCondition;
    /**
     * 消费条件
     */
    private Condition emptyCondition;

    public DepotDemo(int capacity) {
        this.capacity = capacity;
        // 设置该可重入锁为非公平锁
        this.lock = new ReentrantLock();
        this.size = 0;
        this.fullCondition = lock.newCondition();
        this.emptyCondition = lock.newCondition();
        printStatus(0, "init");
    }

    /**
     * 生产指定数量的物品
     *
     * @param val 数量
     */
    public void produce(int val) {
        lock.lock();
        try {
            // 实际剩余生产的量，可能一次性超过了容量，所以会出现多次生产，需要这个变量进行分割
            int left = val;
            while (left > 0) {
                // 当前仓库已满，通知消费者进行消费
                while (size >= capacity) {
                    fullCondition.await();
                }
                // 表示每一次生产的数量
                int inc = (size + left) > capacity ? capacity - size : left;
                size += inc;
                left -= inc;
                printStatus(inc, "product");
                // 最后还需要通知消费者进行消费
                emptyCondition.signal();

            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }

    }

    /**
     * 消费指定数量的物品
     *
     * @param val 数量
     */
    public void consume(int val) {
        lock.lock();
        try {
            // 实际剩余消费的量
            int left = val;
            while (left > 0) {
                // 当前仓库已空,消费者应该等待
                while (size <= 0) {
                    emptyCondition.await();
                }
                // 此次循环实际消费的数量
                int dec = size < left ? size : left;
                size -= dec;
                left -= dec;
                printStatus(val, "consume");
                // 消费一定数量扣可以唤醒生产者进行生产
                fullCondition.signal();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }

    }

    /**
     * 打印仓库变动后仓库状态信息
     *
     * @param val         变动的数量
     * @param description 调用的行为
     */
    private void printStatus(int val, String description) {
        System.out.printf("%s %s (%3d) --> size=%3d\n", Thread.currentThread().getName(), description, val, size);
    }

    /**
     * 测试
     *
     * @param args
     */
    public static void main(String[] args) {
        DepotDemo depot = new DepotDemo(100);

        Producer producer = new Producer(depot);
        Consumer consumer = new Consumer(depot);

        producer.product(100);
        consumer.consume(200);
        producer.product(100);
        consumer.consume(200);
        producer.product(100);
    }

}

/**
 * 生产者
 */
class Producer {
    private DepotDemo depot;

    public Producer(DepotDemo depot) {
        this.depot = depot;
    }

    public void product(final int val) {
        new Thread(() -> depot.produce(val)).start();
    }
}

/**
 * 消费者
 */
class Consumer {
    private DepotDemo depot;

    public Consumer(DepotDemo depot) {
        this.depot = depot;
    }

    public void consume(final int val) {
        new Thread(() -> depot.consume(val)).start();
    }
}
