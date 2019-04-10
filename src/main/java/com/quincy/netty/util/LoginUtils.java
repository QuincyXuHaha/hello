package com.quincy.netty.util;

import com.quincy.netty.Attributes;
import io.netty.channel.Channel;

/**
 * 登录
 *
 * @author quincy
 * @date 2019/4/9 星期二
 */
public class LoginUtils {

    public static void markAsLogin(Channel channel) {
        channel.attr(Attributes.LOGIN).set(true);
    }

    public static boolean hasLogin(Channel channel) {
        return channel.attr(Attributes.LOGIN) != null;
    }
}
