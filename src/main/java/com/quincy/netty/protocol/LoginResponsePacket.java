package com.quincy.netty.protocol;

import lombok.Data;

/**
 * 登录报文响应对象
 *
 * @author quincy
 * @date 2019/4/9 星期二
 */
@Data
public class LoginResponsePacket extends AbstractPacket {

    private boolean success;
    private String reason;

    @Override
    public Byte getCommand() {
        return Command.LOGIN_RESPONSE;
    }
}
