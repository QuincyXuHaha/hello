package com.quincy.netty;

import com.quincy.netty.protocol.PacketCodeC;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;

/**
 * @author quincy
 * @date 2019/4/10 星期三
 */
public class Spliter extends LengthFieldBasedFrameDecoder {

    public Spliter() {
        super(Integer.MAX_VALUE, 7, 4);
    }

    @Override
    protected Object decode(ChannelHandlerContext ctx, ByteBuf in) throws Exception {
        int magic = in.getInt(in.readerIndex());
        if (magic != PacketCodeC.MAGIC_NUMBER) {
            System.out.println("魔法数不正确：" + magic);
            ctx.channel().close();
            return null;
        }
        return super.decode(ctx, in);
    }
}
