package com.quincy.netty.protocol;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

import java.util.List;

/**
 * 解码器
 *
 * @author quincy
 * @date 2019/4/10 星期三
 */
public class PacketDecoder extends ByteToMessageDecoder {
    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) {
        AbstractPacket pa = PacketCodeC.INSTANCE.decode(in);
        out.add(pa);
    }
}
