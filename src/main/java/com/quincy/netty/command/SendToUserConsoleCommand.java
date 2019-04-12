package com.quincy.netty.command;

import io.netty.channel.Channel;

import java.util.Scanner;

/**
 * @author quincy
 * @date 2019/4/12 星期五
 */
public class SendToUserConsoleCommand implements ConsoleCommand {


    @Override
    public void exec(Scanner scanner, Channel channel) {
        String groupId = scanner.next();
        String msg = scanner.next();

    }
}
