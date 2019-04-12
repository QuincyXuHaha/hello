package com.quincy.netty.protocol.req;

import com.quincy.netty.command.Command;
import com.quincy.netty.protocol.AbstractPacket;
import lombok.Data;

/**
 * @author quincy
 * @date 2019/4/12 星期五
 */
@Data
public class QuitGroupRequestPacket extends AbstractPacket {

    private String groupId;

    @Override
    public Byte getCommand() {
        return Command.QUIT_GROUP_REQUEST;
    }
}
