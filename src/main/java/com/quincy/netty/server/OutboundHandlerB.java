package com.quincy.netty.server;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelOutboundHandlerAdapter;
import io.netty.channel.ChannelPromise;

/**
 * @author quincy
 * @date 2019/4/10 星期三
 */
public class OutboundHandlerB extends ChannelOutboundHandlerAdapter {

    @Override
    public void write(ChannelHandlerContext ctx, Object msg, ChannelPromise promise) throws Exception {
        System.out.println(this.getClass().getSimpleName() + ": " + msg);
        super.write(ctx, msg, promise);
    }
}
