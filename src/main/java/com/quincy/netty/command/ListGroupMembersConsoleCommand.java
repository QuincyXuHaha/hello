package com.quincy.netty.command;

import com.quincy.netty.protocol.req.QueryGroupMemberRequestPacket;
import io.netty.channel.Channel;

import java.util.Scanner;

/**
 * @author quincy
 * @date 2019/4/12 星期五
 */
public class ListGroupMembersConsoleCommand implements ConsoleCommand {
    @Override
    public void exec(Scanner scanner, Channel channel) {
        System.out.print("输入 groupId，获取群成员列表：");
        String groupId = scanner.next();
        QueryGroupMemberRequestPacket packet = new QueryGroupMemberRequestPacket();
        packet.setGroupId(groupId);
        channel.writeAndFlush(packet);
    }
}
