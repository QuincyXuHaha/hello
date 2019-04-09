package com.quincy.netty.protocol;

/**
 * 指令
 *
 * @author quincy
 * @date 2019/4/9 星期二
 */
public interface Command {

    /**
     * 登录请求指令
     */
    Byte LOGIN_REQUEST = 1;
    /**
     * 登录响应指令
     */
    Byte LOGIN_RESPONSE = 2;

}
