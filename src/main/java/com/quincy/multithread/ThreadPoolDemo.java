package com.quincy.multithread;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

/**
 * @author quincy
 * @date 2018/1/31 星期三
 */
public class ThreadPoolDemo {

    public static void main(String[] args) {
//        ExecutorService pool = Executors.newFixedThreadPool(2);
//        pool.execute(new MyThread());
//        pool.execute(new MyThread());
//        pool.execute(new MyThread());
//        pool.execute(new MyThread());
//        pool.execute(new MyThread());
////        pool.shutdown();
//        System.out.println(-1 << 29);
//        int sum = 0;
//        int bit = 5;
//        String result = null;
//        while (true) {
//            sum++;
//            if (sum == 10) {
//                result = Integer.toString(sum);
//                if (result.length() < bit) {
//                    int sub = bit - result.length();
//                    StringBuilder zero = new StringBuilder();
//                    while (true) {
//                        zero.append("0");
//                        sub--;
//                        if (sub == 0) {
//                            break;
//                        }
//                    }
//                    result = zero + result;
//                    System.out.println(result);
//                }
//                break;
//            }
//        }

        increaseBarcodeNo("00001");
        Map<DayOfWeek, String> weekStringMap = new HashMap<>(16);
        weekStringMap.put(DayOfWeek.MONDAY, "周一");
        weekStringMap.put(DayOfWeek.TUESDAY, "周二");
        weekStringMap.put(DayOfWeek.WEDNESDAY, "周三");
        weekStringMap.put(DayOfWeek.THURSDAY, "周四");
        weekStringMap.put(DayOfWeek.FRIDAY, "周五");
        weekStringMap.put(DayOfWeek.SATURDAY, "周六");
        weekStringMap.put(DayOfWeek.SUNDAY, "周日");

        DayOfWeek dayOfWeek = LocalDateTime.now().getDayOfWeek();
        DayOfWeek dayOfWeek1 = LocalDateTime.now().plusDays(3).getDayOfWeek();
        String week = DateTimeFormatter.ofPattern("EEEE").format(dayOfWeek);
        String week2 = DateTimeFormatter.ofPattern("EEEE").format(dayOfWeek1);
        System.out.println(week);
        System.out.println(week2);


    }

    //barcodeNo自增
    private static String increaseBarcodeNo(String barcodeNo) {
        int result = Integer.parseInt(barcodeNo);
        result++;
        String temp = String.format("%5d", result);
        String staticBarcodeNo = temp.replace(" ", "0");
        return staticBarcodeNo;
    }
}

class MyThread implements Runnable {

    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName() + " is running.");
    }
}
