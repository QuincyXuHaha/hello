package com.quincy.netty.command;

import com.quincy.netty.protocol.req.CreateGroupRequestPacket;
import io.netty.channel.Channel;

import java.util.Arrays;
import java.util.Scanner;

/**
 * 创建群聊指令
 *
 * @author quincy
 * @date 2019/4/12 星期五
 */
public class CreateGroupConsoleCommand implements ConsoleCommand {


    @Override
    public void exec(Scanner scanner, Channel channel) {
        System.out.print("【拉人群聊】输入 userId 列表，userId 之间英文逗号隔开：");
        String line = scanner.next();
        CreateGroupRequestPacket packet = new CreateGroupRequestPacket();
        packet.setUserIdList(Arrays.asList(line.split(",")));
        channel.writeAndFlush(packet);
    }
}
