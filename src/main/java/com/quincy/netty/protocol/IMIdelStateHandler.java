package com.quincy.netty.protocol;

import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.timeout.IdleStateEvent;
import io.netty.handler.timeout.IdleStateHandler;

import java.util.concurrent.TimeUnit;

/**
 * 空闲检测
 *
 * @author quincy
 * @date 2019/4/15 星期一
 */
public class IMIdelStateHandler extends IdleStateHandler {

    public IMIdelStateHandler() {
        super(15, 0, 0, TimeUnit.SECONDS);
    }

    @Override
    protected void channelIdle(ChannelHandlerContext ctx, IdleStateEvent evt) throws Exception {
        System.out.println(15 + "秒内未读到数据，关闭连接");
        ctx.channel().close();
    }
}
