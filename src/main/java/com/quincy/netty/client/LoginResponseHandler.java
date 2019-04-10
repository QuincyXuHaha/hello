package com.quincy.netty.client;

import com.quincy.netty.protocol.resp.LoginResponsePacket;
import com.quincy.netty.util.DateUtils;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * 登录响应处理器
 *
 * @author quincy
 * @date 2019/4/10 星期三
 */
public class LoginResponseHandler extends SimpleChannelInboundHandler<LoginResponsePacket> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, LoginResponsePacket msg) {
        if (msg.isSuccess()) {
            System.out.println(DateUtils.now() + " 客户端登录成功");
        } else {
            System.out.println(DateUtils.now() + " 客户端登录失败，原因：" + msg.getReason());
        }
    }
}
