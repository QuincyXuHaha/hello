package com.quincy.netty.server;

import com.quincy.netty.Session;
import com.quincy.netty.protocol.req.QuitGroupRequestPacket;
import com.quincy.netty.protocol.resp.QuitGroupResponsePacket;
import com.quincy.netty.util.SessionUtils;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;

/**
 * @author quincy
 * @date 2019/4/12 星期五
 */
public class QuitGroupRequestHandler extends SimpleChannelInboundHandler<QuitGroupRequestPacket> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, QuitGroupRequestPacket msg) throws Exception {
        // 移除群组
        Session session = SessionUtils.getSession(ctx.channel());
        ChannelGroup channelGroup = SessionUtils.getChannelGroup(msg.getGroupId());
        channelGroup.remove(ctx.channel());
        // 通知群友
        QuitGroupResponsePacket packet = new QuitGroupResponsePacket();
        packet.setGroupId(msg.getGroupId());
        packet.setName(session.getName());
        packet.setSuccess(true);
        channelGroup.writeAndFlush(packet);
    }
}
