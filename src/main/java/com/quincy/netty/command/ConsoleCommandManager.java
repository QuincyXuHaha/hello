package com.quincy.netty.command;

import io.netty.channel.Channel;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * 控制台命令管理
 *
 * @author quincy
 * @date 2019/4/12 星期五
 */
public class ConsoleCommandManager implements ConsoleCommand {

    private static Map<String, ConsoleCommand> consoleCommandMap = new HashMap<>();

    static {
        consoleCommandMap.put("createGroup", new CreateGroupConsoleCommand());
        consoleCommandMap.put("loginOut", new LoginOutConsoleCommand());
        consoleCommandMap.put("sendToUser", new SendToUserConsoleCommand());
    }


    @Override
    public void exec(Scanner scanner, Channel channel) {
        String command = scanner.next();
        ConsoleCommand consoleCommand = consoleCommandMap.get(command);
        if (consoleCommand != null) {
            consoleCommand.exec(scanner, channel);
        } else {
            System.err.println(String.format("【不存在的控制台命令：%s】", command));
        }

    }
}
