package com.quincy.netty.server;

import com.quincy.netty.protocol.req.MsgRequestPacket;
import com.quincy.netty.protocol.resp.MsgResponsePacket;
import com.quincy.netty.util.DateUtils;
import com.quincy.netty.util.LoginUtils;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * 消息请求处理器
 *
 * @author quincy
 * @date 2019/4/10 星期三
 */
public class MsgRequestHandler extends SimpleChannelInboundHandler<MsgRequestPacket> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, MsgRequestPacket msg) {
        System.out.println(DateUtils.now() + " 收到客户端消息：【" + msg.getMsg() + "】");
        MsgResponsePacket msgResponsePacket = new MsgResponsePacket();
        msgResponsePacket.setVersion(msg.getVersion());
        if (LoginUtils.hasLogin(ctx.channel())) {
            msgResponsePacket.setMsg("服务端回复【" + msg.getMsg() + "】");
        } else {
            msgResponsePacket.setMsg("服务端回复【 用户未登录，请登录.... 】");
        }
        ctx.channel().writeAndFlush(msg);
    }
}
