package com.quincy.netty.server;

import com.quincy.netty.command.Command;
import com.quincy.netty.protocol.AbstractPacket;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.util.HashMap;
import java.util.Map;

/**
 * 合并指令处理器
 *
 * @author quincy
 * @date 2019/4/15 星期一
 */
@ChannelHandler.Sharable
public class IMHandler extends SimpleChannelInboundHandler<AbstractPacket> {

    public static final IMHandler INSTANCE = new IMHandler();

    private IMHandler() {
    }

    private static final Map<Byte, SimpleChannelInboundHandler<? extends AbstractPacket>> HANDLER_MAP = new HashMap<>();

    static {
        HANDLER_MAP.put(Command.CREATE_GROUP_REQUEST, CreateGroupRequestHandler.INSTANCE);
        HANDLER_MAP.put(Command.JOIN_GROUP_REQUEST, JoinGroupRequestHandler.INSTANCE);
        HANDLER_MAP.put(Command.QUIT_GROUP_REQUEST, QuitGroupRequestHandler.INSTANCE);
        HANDLER_MAP.put(Command.QUERY_GROUP_MEMBER_REQUEST, QueryGroupMemberRequestHandler.INSTANCE);
        HANDLER_MAP.put(Command.GROUP_MSG_REQUEST, MsgRequestHandler.INSTANCE);
    }


    @Override
    protected void channelRead0(ChannelHandlerContext ctx, AbstractPacket msg) throws Exception {
        HANDLER_MAP.get(msg.getCommand()).channelRead(ctx, msg);
    }
}
