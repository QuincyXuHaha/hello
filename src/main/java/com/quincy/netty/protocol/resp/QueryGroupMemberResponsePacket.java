package com.quincy.netty.protocol.resp;

import com.quincy.netty.command.Command;
import com.quincy.netty.protocol.AbstractPacket;
import lombok.Data;

import java.util.List;

/**
 * @author quincy
 * @date 2019/4/12 星期五
 */
@Data
public class QueryGroupMemberResponsePacket extends AbstractPacket {
    private String groupId;
    private List<String> nameList;

    @Override
    public Byte getCommand() {
        return Command.QUERY_GROUP_MEMBER_RESPONSE;
    }
}
