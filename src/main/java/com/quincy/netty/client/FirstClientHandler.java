package com.quincy.netty.client;

import com.quincy.netty.DateUtils;
import com.quincy.netty.protocol.AbstractPacket;
import com.quincy.netty.protocol.req.LoginRequestPacket;
import com.quincy.netty.protocol.resp.LoginResponsePacket;
import com.quincy.netty.protocol.PacketCodeC;
import com.quincy.netty.protocol.resp.MsgResponsePacket;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

/**
 * 客户端业务发送逻辑责任链节点
 *
 * @author quincy
 * @date 2019/4/8 星期一
 */
public class FirstClientHandler extends ChannelInboundHandlerAdapter {


    /**
     * 客户端连接成功后会调用的接口
     *
     * @param ctx 通道处理器上下文
     */
    @Override
    public void channelActive(ChannelHandlerContext ctx) {
        System.out.println(DateUtils.now() + ": 客户端开始登录");
        //1、创建登录对象
        LoginRequestPacket loginRequestPacket = new LoginRequestPacket();
        loginRequestPacket.setUserId(System.currentTimeMillis());
        loginRequestPacket.setName("quincy");
        loginRequestPacket.setPwd("password");
        //2、编码
        ByteBuf buf = PacketCodeC.INSTANCE.encode(ctx.alloc(), loginRequestPacket);
        //3、写数据
        ctx.channel().writeAndFlush(buf);
    }

    /**
     * 收到响应后处理数据的方法
     *
     * @param ctx 通道处理器上下文
     * @param msg 请求消息
     */
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) {
        // read data from server
        ByteBuf buf = (ByteBuf) msg;
        AbstractPacket packet = PacketCodeC.INSTANCE.decode(buf);
        if (packet instanceof LoginResponsePacket) {
            LoginResponsePacket loginResponsePacket = (LoginResponsePacket) packet;
            if (loginResponsePacket.isSuccess()) {
                System.out.println(DateUtils.now() + " 客户端登录成功");
            } else {
                System.out.println(DateUtils.now() + " 客户端登录失败，原因：" + loginResponsePacket.getReason());
            }
        } else if (packet instanceof MsgResponsePacket) {
            MsgResponsePacket msgResponsePacket = (MsgResponsePacket) packet;
            System.out.println(DateUtils.now() + " 收到服务端消息：" + msgResponsePacket.getMsg());
        }
    }
}