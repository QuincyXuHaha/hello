package com.quincy.netty.protocol.resp;

import com.quincy.netty.protocol.AbstractPacket;

/**
 * @author quincy
 * @date 2019/4/15 星期一
 */
public class HeartBeatResponsePacket extends AbstractPacket {
    @Override
    public Byte getCommand() {
        return null;
    }
}
