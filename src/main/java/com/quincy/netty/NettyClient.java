package com.quincy.netty;

import com.quincy.netty.client.FirstClientHandler;
import com.quincy.netty.client.LoginResponseHandler;
import com.quincy.netty.client.MsgResponseHandler;
import com.quincy.netty.protocol.PacketCodeC;
import com.quincy.netty.protocol.PacketDecoder;
import com.quincy.netty.protocol.PacketEncoder;
import com.quincy.netty.protocol.req.MsgRequestPacket;
import com.quincy.netty.util.LoginUtils;
import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.ByteBuf;
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
//                        ch.pipeline().addLast(new LengthFieldBasedFrameDecoder(Integer.MAX_VALUE, 7, 4));
                        ch.pipeline().addLast(FIRST);
//                        ch.pipeline().addLast(new Spliter());
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
            while (!Thread.interrupted()) {
                if (LoginUtils.hasLogin(channel)) {
                    System.out.println("输入消息发送至服务端：");
                    Scanner scanner = new Scanner(System.in);
                    String line = scanner.nextLine();
                    MsgRequestPacket packet = new MsgRequestPacket();
                    packet.setMsg(line);
                    ByteBuf buf = PacketCodeC.INSTANCE.encode(channel.alloc(), packet);
                    channel.writeAndFlush(buf);
                }
            }
        }).start();

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
