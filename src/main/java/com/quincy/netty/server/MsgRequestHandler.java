package com.quincy.netty.server;

import com.quincy.netty.Session;
import com.quincy.netty.protocol.req.MsgRequestPacket;
import com.quincy.netty.protocol.resp.MsgResponsePacket;
import com.quincy.netty.util.SessionUtils;
import io.netty.channel.Channel;
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
//        System.out.println(DateUtils.now() + " 收到客户端消息：【" + msg.getMsg() + "】");
//        MsgResponsePacket msgResponsePacket = new MsgResponsePacket();
//        msgResponsePacket.setVersion(msg.getVersion());
//        if (LoginUtils.hasLogin(ctx.channel())) {
//            msgResponsePacket.setMsg("服务端回复【" + msg.getMsg() + "】");
//        } else {
//            msgResponsePacket.setMsg("服务端回复【 用户未登录，请登录.... 】");
//        }
//        ctx.channel().writeAndFlush(msg);
        // 1、拿到当前连接session
        Session session = SessionUtils.getSession(ctx.channel());
        // 2、封装要发送的消息
        MsgResponsePacket msgResponsePacket = new MsgResponsePacket();
        msgResponsePacket.setFromUserId(session.getUserId());
        msgResponsePacket.setFromName(session.getName());
        msgResponsePacket.setMsg(msg.getMsg());
        // 3、拿到对方的连接
        Channel toUserChannel = SessionUtils.getChannel(msg.getToUserId());
        // 4、发送消息给对方
        if (toUserChannel != null && SessionUtils.hasLogin(toUserChannel)) {
            toUserChannel.writeAndFlush(msgResponsePacket);
        } else {
            System.err.println("[" + msg.getToUserId() + "] 不在线，发送失败!");
            msgResponsePacket.setMsg("[" + msg.getToUserId() + "] 不在线，发送失败!");
            ctx.channel().writeAndFlush(msgResponsePacket);
        }
    }
}
