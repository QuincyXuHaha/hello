package com.quincy.netty.protocol.req;

import com.quincy.netty.protocol.AbstractPacket;
import lombok.Data;

import static com.quincy.netty.command.Command.JOIN_GROUP_REQUEST;

/**
 * @author quincy
 * @date 2019/4/12 星期五
 */
@Data
public class JoinGroupRequestPacket extends AbstractPacket {

    private String groupId;

    @Override
    public Byte getCommand() {
        return JOIN_GROUP_REQUEST;
    }
}
