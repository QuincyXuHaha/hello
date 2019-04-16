package com.quincy.spi;

import com.alibaba.dubbo.common.extension.SPI;

/**
 * @author quincy
 * @date 2019/4/16 星期二
 */
@SPI
public interface Demo {

    void sayHello();

}