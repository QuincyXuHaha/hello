package com.quincy.netty.server;

import com.quincy.netty.protocol.req.LoginRequestPacket;
import com.quincy.netty.protocol.resp.LoginResponsePacket;
import com.quincy.netty.util.DateUtils;
import com.quincy.netty.util.LoginUtils;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * 登录请求处理器
 *
 * @author quincy
 * @date 2019/4/10 星期三
 */
public class LoginRequestHandler extends SimpleChannelInboundHandler<LoginRequestPacket> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, LoginRequestPacket msg) throws Exception {
        LoginResponsePacket loginResponsePacket = new LoginResponsePacket();
        if (loginCheck(msg)) {
            LoginUtils.markAsLogin(ctx.channel());
            System.out.println(String.format(DateUtils.now() + " 用户%s登录成功", msg.getName()));
            loginResponsePacket.setSuccess(true);
            loginResponsePacket.setReason("登录成功");
        } else {
            System.out.println(String.format(DateUtils.now() + "用户%s登录失败", msg.getName()));
            loginResponsePacket.setSuccess(false);
            loginResponsePacket.setReason("用户密码错误");
        }
        ctx.channel().writeAndFlush(loginResponsePacket);
    }

    private boolean loginCheck(LoginRequestPacket packet) {
        return true;
    }
}
