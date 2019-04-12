package com.quincy.netty.server;

import com.quincy.netty.Session;
import com.quincy.netty.protocol.req.CreateGroupRequestPacket;
import com.quincy.netty.protocol.resp.CreateGroupResponsePacket;
import com.quincy.netty.util.SessionUtils;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * 创建群聊请求处理器
 *
 * @author quincy
 * @date 2019/4/12 星期五
 */
public class CreateGroupRequestHandler extends SimpleChannelInboundHandler<CreateGroupRequestPacket> {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, CreateGroupRequestPacket msg) throws Exception {
        List<String> userIdList = msg.getUserIdList();
        List<String> nameList = new ArrayList<>();
        // 1、创建一个通道组
        ChannelGroup channelGroup = new DefaultChannelGroup(ctx.executor());
        for (String userId : userIdList) {
            Channel channel = SessionUtils.getChannel(Long.valueOf(userId));
            if (channel != null) {
                channelGroup.add(channel);
                Session session = SessionUtils.getSession(channel);
                nameList.add(session.getName());
            }
        }
        // 2、封装返回消息
        CreateGroupResponsePacket packet = new CreateGroupResponsePacket();
        packet.setGroupId(UUID.randomUUID().toString().substring(5, 10));
        packet.setSuccess(true);
        packet.setUserNameList(nameList);

        // 3、给所有人发送拉群通知
        channelGroup.writeAndFlush(packet);
        System.out.print("群创建成功，id 为[" + packet.getGroupId() + "], ");
        System.out.println("群里面有：" + packet.getUserNameList());
    }
}
