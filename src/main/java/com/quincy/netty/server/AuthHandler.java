package com.quincy.netty.server;

import com.quincy.netty.util.LoginUtils;
import com.quincy.netty.util.SessionUtils;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

/**
 * 鉴权
 *
 * @author quincy
 * @date 2019/4/11 星期四
 */
@ChannelHandler.Sharable
public class AuthHandler extends ChannelInboundHandlerAdapter {

    public static final AuthHandler INSTANCE = new AuthHandler();

    protected AuthHandler(){}

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        if (!SessionUtils.hasLogin(ctx.channel())) {
            System.out.println("用户未登录");
            ctx.channel().close();
        } else {
            ctx.pipeline().remove(this);
            super.channelRead(ctx, msg);
        }
    }

    @Override
    public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
        if (LoginUtils.hasLogin(ctx.channel())) {
            System.out.println("当前连接登录验证完毕，无需再次验证, AuthHandler 被移除");
        } else {
            System.out.println("无登录验证，强制关闭连接!");
        }
    }
}
