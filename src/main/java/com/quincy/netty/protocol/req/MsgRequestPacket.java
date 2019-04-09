package com.quincy.netty.protocol.req;

import com.quincy.netty.protocol.AbstractPacket;
import com.quincy.netty.protocol.Command;
import lombok.Data;

/**
 * 消息报文请求对象
 *
 * @author quincy
 * @date 2019/4/9 星期二
 */
@Data
public class MsgRequestPacket extends AbstractPacket {

    private String msg;

    @Override
    public Byte getCommand() {
        return Command.MESSAGE_REQUEST;
    }
}
