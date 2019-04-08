package com.quincy.netty;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import java.nio.charset.Charset;
import java.util.Date;

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
     * @param ctx
     * @throws Exception
     */
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        System.out.println(new Date() + ": 客户端写出数据");
        // get data
        ByteBuf buffer = getByteBuf(ctx);
        // write data to server
        ctx.channel().writeAndFlush(buffer);
    }

    private ByteBuf getByteBuf(ChannelHandlerContext ctx) {
        // get byteBuf
        ByteBuf buffer = ctx.alloc().buffer();
        // write biz data
        buffer.writeBytes("netty learning, quincy.".getBytes(Charset.forName("utf-8")));
        return buffer;
    }

    /**
     * 收到数据后处理数据的方法
     *
     * @param ctx
     * @param msg
     * @throws Exception
     */
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        // read data from server
        ByteBuf buf = (ByteBuf) msg;
        System.out.println(new Date() + "从服务端读取到的数据，" + buf.toString(Charset.forName("utf-8")));
    }
}
