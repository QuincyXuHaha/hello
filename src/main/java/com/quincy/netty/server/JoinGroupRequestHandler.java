package com.quincy.netty.server;

import com.quincy.netty.protocol.req.JoinGroupRequestPacket;
import com.quincy.netty.protocol.resp.JoinGroupResponsePacket;
import com.quincy.netty.util.SessionUtils;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;

/**
 * 加入群聊请求处理
 *
 * @author quincy
 * @date 2019/4/12 星期五
 */
@ChannelHandler.Sharable
public class JoinGroupRequestHandler extends SimpleChannelInboundHandler<JoinGroupRequestPacket> {

    public static final JoinGroupRequestHandler INSTANCE = new JoinGroupRequestHandler();
    protected JoinGroupRequestHandler(){}

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, JoinGroupRequestPacket msg) throws Exception {
        // 加入群组
        String groupId = msg.getGroupId();
        ChannelGroup channelGroup = SessionUtils.getChannelGroup(groupId);
        channelGroup.add(ctx.channel());

        // 向所有群成员发送加入消息
        JoinGroupResponsePacket packet = new JoinGroupResponsePacket();
        packet.setGroupId(groupId);
        packet.setName(SessionUtils.getSession(ctx.channel()).getName());
        packet.setSuccess(true);
        channelGroup.writeAndFlush(packet);
    }
}
