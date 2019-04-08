package com.quincy.multithread;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * condition 和 lock 可以做到精细的控制
 * @author quincy
 * @date 2018/2/6 星期二
 */
public class ConditionLockDemo {
    private final Lock lock = new ReentrantLock();
    private final Condition fullCondition = lock.newCondition();
    private final Condition emptyCondition = lock.newCondition();



}
