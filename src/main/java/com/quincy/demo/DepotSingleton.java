package com.quincy.demo;

/**
 * @author quincy
 * @date 2018/2/27 星期二
 */
public class DepotSingleton {

    private static Depot depot = new Depot();

    private DepotSingleton(){}

    public static Depot getInstance() {
        return depot;
    }

}
