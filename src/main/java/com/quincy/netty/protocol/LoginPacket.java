package com.quincy.netty.protocol;

import lombok.Data;

/**
 * 登录报文对象
 *
 * @author quincy
 * @date 2019/4/9 星期二
 */
@Data
public class LoginPacket extends AbstractPacket {

    private Integer userId;
    private String name;
    private String pwd;

    @Override
    public Byte getCommand() {
        return Command.LOGIN_REQUEST;
    }
}
