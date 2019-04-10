package com.quincy.netty.server;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

/**
 * @author quincy
 * @date 2019/4/10 星期三
 */
public class InboundHandlerA extends ChannelInboundHandlerAdapter {

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        System.out.println("InboundHandlerA: " + msg);
        super.channelRead(ctx, msg);
    }
}
