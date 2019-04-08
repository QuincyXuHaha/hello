package com.quincy.demo;

import java.util.Random;

/**
 * 生产者
 *
 * @author quincy
 * @date 2018/2/8 星期四
 */
public class Producer implements Runnable {

    @Override
    public void run() {
        Long num = new Random().nextLong();
        DepotSingleton.getInstance().product(new Good(num, "Producer-" + num));
    }
}
