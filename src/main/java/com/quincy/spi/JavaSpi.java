package com.quincy.spi;

import java.util.ServiceLoader;

/**
 * @author quincy
 * @date 2019/4/16 星期二
 */
public class JavaSpi {

    public static void main(String[] args) {
        ServiceLoader<Demo> load = ServiceLoader.load(Demo.class);
        load.forEach(Demo::sayHello);
    }

}





