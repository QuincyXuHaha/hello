package com.quincy.netty.protocol.resp;

import com.quincy.netty.command.Command;
import com.quincy.netty.protocol.AbstractPacket;
import lombok.Data;

/**
 * @author quincy
 * @date 2019/4/12 星期五
 */
@Data
public class QuitGroupResponsePacket extends AbstractPacket {

    private String groupId;
    private String name;
    private String reason;
    private boolean success;

    @Override
    public Byte getCommand() {
        return Command.QUIT_GROUP_RESPONSE;
    }
}
