package com.quincy.netty.client;

import com.quincy.netty.protocol.resp.QueryGroupMemberResponsePacket;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * @author quincy
 * @date 2019/4/12 星期五
 */
public class QueryGroupMemberResponseHandler extends SimpleChannelInboundHandler<QueryGroupMemberResponsePacket> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, QueryGroupMemberResponsePacket msg) throws Exception {
        System.out.println("群[" + msg.getGroupId() + "]中的人包括：" + msg.getNameList());
    }
}
