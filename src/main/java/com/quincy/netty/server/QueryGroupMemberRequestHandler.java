package com.quincy.netty.server;

import com.quincy.netty.protocol.req.QueryGroupMemberRequestPacket;
import com.quincy.netty.protocol.resp.QueryGroupMemberResponsePacket;
import com.quincy.netty.util.SessionUtils;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * @author quincy
 * @date 2019/4/12 星期五
 */
@ChannelHandler.Sharable
public class QueryGroupMemberRequestHandler extends SimpleChannelInboundHandler<QueryGroupMemberRequestPacket> {

    public static final QueryGroupMemberRequestHandler INSTANCE = new QueryGroupMemberRequestHandler();
    protected QueryGroupMemberRequestHandler(){}

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, QueryGroupMemberRequestPacket msg) throws Exception {
        // 拿到所有群成员的名字
        List<String> nameList = new ArrayList<>();
        String groupId = msg.getGroupId();
        ChannelGroup channelGroup = SessionUtils.getChannelGroup(groupId);
        channelGroup.forEach(channel -> nameList.add(SessionUtils.getSession(channel).getName()));
        // 返回给客户端
        QueryGroupMemberResponsePacket packet = new QueryGroupMemberResponsePacket();
        packet.setGroupId(groupId);
        packet.setNameList(nameList);
        ctx.channel().writeAndFlush(packet);
    }
}
