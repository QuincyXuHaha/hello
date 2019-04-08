package com.quincy.multithread.collection;

import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;

/**
 * @author quincy
 * @date 2018/2/6 星期二
 */
public class CopyOnWriteArrayListDemo {

    private static Set<String> concurrentList = new CopyOnWriteArraySet<>();

    public static void main(String[] args) {
        Thread a = new ThreadA("aa");
        Thread b = new ThreadA("bb");
        a.start();
        b.start();

    }

    static void printAll() {
        for (String str : concurrentList) {
            System.out.print(str + ", ");
        }
        System.out.println();

//        Iterator<String> it = concurrentList.iterator();
//        while (it.hasNext()) {
//            System.out.print(it.next() + ", ");
//        }
//        System.out.println();
    }

    static class ThreadA extends Thread {
        ThreadA(String name) {
            super(name);
        }

        @Override
        public void run() {
            for (int i = 0; i < 6; i++) {
                concurrentList.add(Thread.currentThread().getName() + "--" + i % 2);
                printAll();
            }
        }
    }

}
