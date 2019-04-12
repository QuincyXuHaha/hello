package com.quincy.netty.server;

import com.quincy.netty.util.DateUtils;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import java.nio.charset.Charset;

/**
 * 服务端业务处理责任链节点
 *
 * @author quincy
 * @date 2019/4/8 星期一
 */
@ChannelHandler.Sharable
public class FirstServerHandler extends ChannelInboundHandlerAdapter {

    public static final FirstServerHandler INSTANCE = new FirstServerHandler();
    protected FirstServerHandler(){}

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
//        System.out.println(DateUtils.now() + " 有客户端连接");
//        // get data
//        ByteBuf buffer = ctx.alloc().buffer();
//        buffer.writeBytes((DateUtils.now() + " 服务端收到客户端连接").getBytes(Charset.forName("utf-8")));
//        // write data to server
//        ctx.channel().writeAndFlush(buffer);
        super.channelActive(ctx);
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) {
        // read data from client
//        ByteBuf buf = (ByteBuf) msg;
//        ByteBuf respBuf = null;
//        // 解码
//        AbstractPacket packet = PacketCodeC.INSTANCE.decode(buf);
//        if (LoginUtils.hasLogin(ctx.channel())) {
//            if (packet instanceof LoginRequestPacket) {
//                LoginRequestPacket loginRequestPacket = (LoginRequestPacket) packet;
//                LoginResponsePacket loginResponsePacket = new LoginResponsePacket();
//                loginResponsePacket.setVersion(packet.getVersion());
//                // 登录校验
//                if (loginCheck(loginRequestPacket)) {
//                    LoginUtils.markAsLogin(ctx.channel());
//                    System.out.println(String.format(DateUtils.now() + " 用户%s登录成功", loginRequestPacket.getName()));
//                    loginResponsePacket.setSuccess(true);
//                    loginResponsePacket.setReason("登录成功");
//                } else {
//                    System.out.println(String.format(DateUtils.now() + "用户%s登录失败", loginRequestPacket.getName()));
//                    loginResponsePacket.setSuccess(false);
//                    loginResponsePacket.setReason("用户密码错误");
//                }
//                // 编码
//                respBuf = PacketCodeC.INSTANCE.encode(ctx.alloc(), loginResponsePacket);
//            } else if (packet instanceof MsgRequestPacket) {
//                MsgRequestPacket msgRequestPacket = (MsgRequestPacket) packet;
//                System.out.println(DateUtils.now() + "收到客户端消息：" + msgRequestPacket.getMsg());
//                MsgResponsePacket msgResponsePacket = new MsgResponsePacket();
//                msgResponsePacket.setMsg("服务端回复【" + msgRequestPacket.getMsg() + "】");
//                msgResponsePacket.setVersion(packet.getVersion());
//                respBuf = PacketCodeC.INSTANCE.encode(ctx.alloc(), msgResponsePacket);
//            }
//        }
//        ctx.channel().writeAndFlush(respBuf);

        ByteBuf byteBuf = (ByteBuf) msg;
        System.out.println(DateUtils.now() + ": 服务端读到数据 -> " + byteBuf.toString(Charset.forName("utf-8")));
    }


}
