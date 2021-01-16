package netty.client.server;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;

import java.net.InetSocketAddress;

public class EchoServer {

    public static void main(String[] args) throws Exception {
        int port = 8888;
        start(port);// ←--  调用服务器的start()方法
    }

    public static void start(int port) throws Exception {
        final EchoServerHandler serverHandler = new EchoServerHandler();
        EventLoopGroup group = new NioEventLoopGroup();   // ←--  ❶ 创建Event-LoopGroup
        try {
            ServerBootstrap b = new ServerBootstrap();   // ←--   ❷ 创建Server-Bootstrap
            b.group(group)
                    .channel(NioServerSocketChannel.class)   //　←--　 ❸ 指定所使用的NIO传输Channel
                    .localAddress(new InetSocketAddress(port)) //←--　 ❹ 使用指定的端口设置套接字地址
                    .childHandler(new ChannelInitializer() {
                        @Override
                        protected void initChannel(Channel channel) throws Exception {
                            channel.pipeline().addLast(serverHandler);

                        }
                    });
            ChannelFuture sync = b.bind().sync();
            sync.channel().closeFuture().sync();
        } finally {
            group.shutdownGracefully().sync();
        }
    }
}