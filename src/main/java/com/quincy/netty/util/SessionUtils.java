package com.quincy.netty.util;

import com.quincy.netty.Attributes;
import com.quincy.netty.Session;
import io.netty.channel.Channel;

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
