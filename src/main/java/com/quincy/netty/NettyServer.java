package com.quincy.netty;


import com.quincy.netty.client.HeartBeatTimerHandler;
import com.quincy.netty.protocol.IMIdelStateHandler;
import com.quincy.netty.protocol.PacketCodecHandler;
import com.quincy.netty.server.AuthHandler;
import com.quincy.netty.server.IMHandler;
import com.quincy.netty.server.LoginRequestHandler;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

/**
 * 服务端
 *
 * @author quincy
 * @date 2019/4/4 星期四
 */
public class NettyServer {

    public static void main(String[] args) {
        // 监听端口，accept新连接的线程组
        EventLoopGroup boss = new NioEventLoopGroup();
        // 处理每个连接的线程组
        EventLoopGroup worker = new NioEventLoopGroup();

        ServerBootstrap serverBootstrap = new ServerBootstrap();
        serverBootstrap
                .group(boss, worker) // 指定线程组
                .channel(NioServerSocketChannel.class)// 指定io模型，这里是nio
                .childHandler(new ChannelInitializer<NioSocketChannel>() {
                    @Override
                    protected void initChannel(NioSocketChannel ch) throws Exception {
                        ch.pipeline().addLast(new IMIdelStateHandler());
                        ch.pipeline().addLast(new Spliter());
                        ch.pipeline().addLast(PacketCodecHandler.INSTANCE);
                        ch.pipeline().addLast(HeartBeatTimerHandler.INSTANCE);
                        ch.pipeline().addLast(LoginRequestHandler.INSTANCE);
                        ch.pipeline().addLast(AuthHandler.INSTANCE);
                        ch.pipeline().addLast(IMHandler.INSTANCE);
                    }
                });//定义处理连接的业务逻辑;
        // 总结：启动一个netty服务端，需要3个参数：线程模型，io模型和连接读写处理逻辑
        bind(serverBootstrap, 8000);
    }

    /**
     * 绑定某个端口，当绑定失败时自动绑定下一个端口直至成功
     *
     * @param serverBootstrap 服务端启动类
     * @param port            初始端口号
     */
    private static void bind(ServerBootstrap serverBootstrap, int port) {
        serverBootstrap.bind(port).addListener(future -> {
            String msg = future.isSuccess() ? "成功" : "失败";
            System.out.println(String.format("端口%d绑定%s", port, msg));
            if (!future.isSuccess()) {
                bind(serverBootstrap, port + 1);
            }
        });
    }

}

