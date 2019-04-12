package com.quincy.netty.protocol.req;

import com.quincy.netty.command.Command;
import com.quincy.netty.protocol.AbstractPacket;
import lombok.Data;

import java.util.List;

/**
 * 创建群聊请求消息
 *
 * @author quincy
 * @date 2019/4/12 星期五
 */
@Data
public class CreateGroupRequestPacket extends AbstractPacket {

    private List<String> userIdList;

    @Override
    public Byte getCommand() {
        return Command.CREATE_GROUP_REQUEST;
    }
}
