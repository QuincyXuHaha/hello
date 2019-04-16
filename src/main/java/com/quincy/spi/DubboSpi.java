package com.quincy.spi;

import com.alibaba.dubbo.common.extension.ExtensionLoader;

/**
 * @author quincy
 * @date 2019/4/16 星期二
 */
public class DubboSpi {

    public static void main(String[] args) {
        ExtensionLoader<Demo> extensionLoader = ExtensionLoader.getExtensionLoader(Demo.class);
        Demo quincy = extensionLoader.getExtension("quincy");
        quincy.sayHello();
        Demo tina = extensionLoader.getExtension("tina");
        tina.sayHello();
    }

}
