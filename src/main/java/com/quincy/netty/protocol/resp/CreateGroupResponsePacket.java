package com.quincy.netty.protocol.resp;

import com.quincy.netty.command.Command;
import com.quincy.netty.protocol.AbstractPacket;
import lombok.Data;

import java.util.List;

/**
 * 创建群聊请求消息响应
 *
 * @author quincy
 * @date 2019/4/12 星期五
 */
@Data
public class CreateGroupResponsePacket extends AbstractPacket {

    private List<String> userNameList;
    private boolean success;
    private String groupId;

    @Override
    public Byte getCommand() {
        return Command.CREATE_GROUP_RESPONSE;
    }
}
