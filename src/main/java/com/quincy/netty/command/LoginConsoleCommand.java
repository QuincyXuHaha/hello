package com.quincy.netty.command;

import com.quincy.netty.protocol.req.LoginRequestPacket;
import io.netty.channel.Channel;

import java.util.Scanner;

/**
 * 登陆指令
 *
 * @author quincy
 * @date 2019/4/12 星期五
 */
public class LoginConsoleCommand implements ConsoleCommand {
    @Override
    public void exec(Scanner scanner, Channel channel) {
        System.out.print("输入用户名登录: ");
        LoginRequestPacket loginRequestPacket = new LoginRequestPacket();
        String username = scanner.nextLine();
        loginRequestPacket.setName(username);
        // 密码使用默认的
        loginRequestPacket.setPwd("pwd");
        // 发送登录数据包
        channel.writeAndFlush(loginRequestPacket);
        waitForLoginResponse();
    }

    private void waitForLoginResponse() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException ignored) {
        }
    }
}
