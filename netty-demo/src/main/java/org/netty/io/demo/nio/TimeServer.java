package org.netty.io.demo.nio;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;


public class TimeServer {
    public static void main(String[] args) throws InterruptedException {

        Integer port = 8888;

        ServerBootstrap serverBootstrap = new ServerBootstrap();

        EventLoopGroup son = new NioEventLoopGroup();
        EventLoopGroup parent = new NioEventLoopGroup();
        serverBootstrap.group(parent, son)
                .channel(NioServerSocketChannel.class)
                .childHandler(new TimeServerHandler())
                .handler(new LoggingHandler(LogLevel.DEBUG));

        ChannelFuture sync = serverBootstrap.bind(port).sync();
        System.out.println("server start by port :" + port);
        sync.channel().closeFuture().sync();

    }
}
