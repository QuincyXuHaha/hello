package com.quincy.netty.client;

import com.quincy.netty.protocol.resp.MsgResponsePacket;
import com.quincy.netty.util.DateUtils;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * 消息响应处理器
 *
 * @author quincy
 * @date 2019/4/10 星期三
 */
public class MsgResponseHandler extends SimpleChannelInboundHandler<MsgResponsePacket> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, MsgResponsePacket msg) throws Exception {
        System.out.println(DateUtils.now() + " 收到服务端消息：【" + msg.getMsg() + "】");
    }
}
