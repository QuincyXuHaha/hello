package com.quincy.netty.command;

import io.netty.channel.Channel;

import java.util.Scanner;

/**
 * 控制台命令
 *
 * @author quincy
 * @date 2019/4/12 星期五
 */
public interface ConsoleCommand {


    /**
     * 处理控制台输入的信息
     *
     * @param scanner 输入信息
     * @param channel 通道连接
     */
    void exec(Scanner scanner, Channel channel);


}
