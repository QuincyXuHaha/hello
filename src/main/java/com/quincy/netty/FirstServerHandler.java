package com.quincy.netty;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import java.nio.charset.Charset;
import java.util.Date;

/**
 * 服务端业务处理责任链节点
 *
 * @author quincy
 * @date 2019/4/8 星期一
 */
public class FirstServerHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        System.out.println(new Date() + ": 有客户端连接");
        // get data
        ByteBuf buffer = getByteBuf(ctx);
        // write data to server
        ctx.channel().writeAndFlush(buffer);
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        // read data from client
        ByteBuf buf = (ByteBuf) msg;
        System.out.println(new Date() + "从客户端读取到的数据，" + buf.toString(Charset.forName("utf-8")));

        // return biz data to client
        ByteBuf resp = getByteBuf(ctx);
        ctx.channel().writeAndFlush(resp);
    }

    private ByteBuf getByteBuf(ChannelHandlerContext ctx) {
        // get byteBuf
        ByteBuf buffer = ctx.alloc().buffer();
        // write biz data
        buffer.writeBytes("服务端已收到请求，这里是~~~~".getBytes(Charset.forName("utf-8")));
        return buffer;
    }

}
