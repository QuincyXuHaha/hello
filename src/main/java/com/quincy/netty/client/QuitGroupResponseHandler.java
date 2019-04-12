package com.quincy.netty.client;

import com.quincy.netty.protocol.resp.QuitGroupResponsePacket;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * @author quincy
 * @date 2019/4/12 星期五
 */
public class QuitGroupResponseHandler extends SimpleChannelInboundHandler<QuitGroupResponsePacket> {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, QuitGroupResponsePacket msg) throws Exception {
        if (msg.isSuccess()) {
            System.out.println(msg.getName() + "退出群聊【" + msg.getGroupId() + "】成功");
        } else {
            System.err.println(msg.getName() + "退出群聊【" + msg.getGroupId() + "】失败,原因是：" + msg.getReason());
        }
    }
}
