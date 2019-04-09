package com.quincy.netty.protocol.resp;

import com.quincy.netty.protocol.AbstractPacket;
import com.quincy.netty.protocol.Command;
import lombok.Data;

/**
 * 消息报文响应对象
 *
 * @author quincy
 * @date 2019/4/9 星期二
 */
@Data
public class MsgResponsePacket extends AbstractPacket {

    private String msg;

    @Override
    public Byte getCommand() {
        return Command.MESSAGE_RESPONSE;
    }
}
