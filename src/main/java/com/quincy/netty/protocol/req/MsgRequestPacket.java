package com.quincy.netty.protocol.req;

import com.quincy.netty.protocol.AbstractPacket;
import com.quincy.netty.protocol.Command;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 消息报文请求对象
 *
 * @author quincy
 * @date 2019/4/9 星期二
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MsgRequestPacket extends AbstractPacket {
    private Long toUserId;
    private String msg;

    @Override
    public Byte getCommand() {
        return Command.MESSAGE_REQUEST;
    }
}
