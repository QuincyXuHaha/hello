package com.quincy.netty.util;

import com.quincy.netty.Attributes;
import com.quincy.netty.Session;
import io.netty.channel.Channel;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.util.concurrent.EventExecutor;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author quincy
 * @date 2019/4/11 星期四
 */
public class SessionUtils {

    /**
     * userId -> channel的映射
     */
    private static final ConcurrentHashMap<Long, Channel> USER_CHANNEL_MAP = new ConcurrentHashMap<>();
    /**
     * groupId -> ChannelGroup 的映射
     */
    private static final Map<String, ChannelGroup> GROUP_MAP = new ConcurrentHashMap<>();

    public static ChannelGroup getChannelGroup(String groupId) {
        return GROUP_MAP.get(groupId);
    }

    public static ChannelGroup createGroup(String groupId, EventExecutor executor) {
        ChannelGroup existGroup = GROUP_MAP.get(groupId);
        ChannelGroup channelGroup = new DefaultChannelGroup(executor);
        if (existGroup == null) {
            GROUP_MAP.put(groupId, channelGroup);
            return channelGroup;
        } else {
            System.err.println(String.format("该groupId已存在:【%s】.", groupId));
            return existGroup;
        }
    }

    public static void bindSession(Session session, Channel channel) {
        USER_CHANNEL_MAP.put(session.getUserId(), channel);
        channel.attr(Attributes.SESSION).set(session);
    }

    public static void unbindSession(Channel channel) {
        if (hasLogin(channel)) {
            USER_CHANNEL_MAP.remove(getSession(channel).getUserId());
            channel.attr(Attributes.SESSION).set(null);
        }
    }

    public static boolean hasLogin(Channel channel) {
        return channel.attr(Attributes.SESSION).get() != null;
    }

    public static Session getSession(Channel channel) {
        return channel.attr(Attributes.SESSION).get();
    }

    public static Channel getChannel(Long userId) {
        return USER_CHANNEL_MAP.get(userId);
    }
}
