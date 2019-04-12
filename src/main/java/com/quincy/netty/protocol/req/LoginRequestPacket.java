package com.quincy.netty.protocol.req;

import com.quincy.netty.protocol.AbstractPacket;
import com.quincy.netty.command.Command;
import lombok.Data;

/**
 * 登录报文对象
 *
 * @author quincy
 * @date 2019/4/9 星期二
 */
@Data
public class LoginRequestPacket extends AbstractPacket {

    private Long userId;
    private String name;
    private String pwd;

    @Override
    public Byte getCommand() {
        return Command.LOGIN_REQUEST;
    }
}
