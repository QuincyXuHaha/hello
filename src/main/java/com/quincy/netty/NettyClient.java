package com.quincy.netty;

import com.quincy.netty.client.FirstClientHandler;
import com.quincy.netty.client.LoginResponseHandler;
import com.quincy.netty.client.MsgResponseHandler;
import com.quincy.netty.protocol.PacketDecoder;
import com.quincy.netty.protocol.PacketEncoder;
import com.quincy.netty.protocol.req.LoginRequestPacket;
import com.quincy.netty.protocol.req.MsgRequestPacket;
import com.quincy.netty.util.SessionUtils;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInboundHandler;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

import java.util.Scanner;

/**
 * 客户端
 *
 * @author quincy
 * @date 2019/4/4 星期四
 */
public class NettyClient {

    private static final ChannelInboundHandler FIRST = new FirstClientHandler();

    public static void main(String[] args) {

        Bootstrap bootstrap = new Bootstrap();
        NioEventLoopGroup worker = new NioEventLoopGroup();
        bootstrap
                // 1、指定线程模型
                .group(worker)
                // 2、指定io模型
                .channel(NioSocketChannel.class)
                // 3、处理读写逻辑
                .handler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    protected void initChannel(SocketChannel ch) throws Exception {
                        ch.pipeline().addLast(new Spliter());
                        ch.pipeline().addLast(new PacketDecoder());
                        ch.pipeline().addLast(new LoginResponseHandler());
                        ch.pipeline().addLast(new MsgResponseHandler());
                        ch.pipeline().addLast(new PacketEncoder());
                    }
                });
        bootstrap.connect("127.0.0.1", 8000).addListener(future -> {
            if (future.isSuccess()) {
                Channel channel = ((ChannelFuture) future).channel();
                startConsoleThread(channel);
            }
        });
    }

    private static void startConsoleThread(Channel channel) {
        new Thread(() -> {
            Scanner sc = new Scanner(System.in);
            LoginRequestPacket loginRequestPacket = new LoginRequestPacket();
            while (!Thread.interrupted()) {
                if (!SessionUtils.hasLogin(channel)) {
                    System.out.print("输入用户名登录: ");
                    String username = sc.nextLine();
                    loginRequestPacket.setName(username);
                    // 密码使用默认的
                    loginRequestPacket.setPwd("pwd");
                    // 发送登录数据包
                    channel.writeAndFlush(loginRequestPacket);
                    waitForLoginResponse();
                } else {
                    Long toUserId = Long.valueOf(sc.next());
                    String message = sc.next();
                    channel.writeAndFlush(new MsgRequestPacket(toUserId, message));
                }
            }
        }).start();

    }

    private static void waitForLoginResponse() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException ignored) {
        }
    }

//    private static void connect(Bootstrap bootstrap, String host, int port, int retry) {
//        bootstrap.connect(host, port).addListener(future -> {
//            if (future.isSuccess()) {
//                System.out.println("连接成功!");
//            } else if (retry == 0) {
//                System.err.println("重试次数已用完，放弃连接！");
//            } else {
//                // 第几次重连
//                int order = (10 - retry) + 1;
//                // 本次重连的间隔
//                int delay = 1 << order;
//                System.err.println(new Date() + ": 连接失败，第" + order + "次重连……");
//                bootstrap.config().group().schedule(() -> connect(bootstrap, host, port, retry - 1), delay, TimeUnit
//                        .MILLISECONDS);
//            }
//        });
//    }

}
