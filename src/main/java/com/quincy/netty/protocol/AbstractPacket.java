package com.quincy.netty.protocol;

import lombok.Data;

/**
 * 通信的对象
 *
 * @author quincy
 * @date 2019/4/9 星期二
 */
@Data
public abstract class AbstractPacket {

    private Byte version = 1;


    /**
     * 获取指令
     *
     * @return 指令
     */
    public abstract Byte getCommand();


}
