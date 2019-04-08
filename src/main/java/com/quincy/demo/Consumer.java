package com.quincy.demo;

/**
 * 消费者
 *
 * @author quincy
 * @date 2018/2/8 星期四
 */
public class Consumer implements Runnable {

    @Override
    public void run() {
        DepotSingleton.getInstance().consume();
    }
}
