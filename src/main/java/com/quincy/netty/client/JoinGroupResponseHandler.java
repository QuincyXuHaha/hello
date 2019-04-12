package com.quincy.netty.client;

import com.quincy.netty.protocol.resp.JoinGroupResponsePacket;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * @author quincy
 * @date 2019/4/12 星期五
 */
public class JoinGroupResponseHandler extends SimpleChannelInboundHandler<JoinGroupResponsePacket> {


    @Override
    protected void channelRead0(ChannelHandlerContext ctx, JoinGroupResponsePacket msg) throws Exception {
        if (msg.isSuccess()) {
            System.out.println(msg.getName() + "加入群[" + msg.getGroupId() + "]成功!");
        } else {
            System.err.println(msg.getName() + "加入群[" + msg.getGroupId() + "]失败，原因为：" + msg.getReason());
        }
    }
}
