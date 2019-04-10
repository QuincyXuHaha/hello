package com.quincy.netty.protocol;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

/**
 * 编码器
 *
 * @author quincy
 * @date 2019/4/10 星期三
 */
public class PacketEncoder extends MessageToByteEncoder<AbstractPacket> {
    @Override
    protected void encode(ChannelHandlerContext ctx, AbstractPacket msg, ByteBuf out) {
        PacketCodeC.INSTANCE.encode(out, msg);
    }
}
