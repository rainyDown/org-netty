package org.netty.netty.discard;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufAllocator;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

import java.net.ServerSocket;

public class NettyDiscardServer {

    private final int port;

    ServerBootstrap strap = new ServerBootstrap();

    public NettyDiscardServer(int port) {
        this.port = port;
    }

    public void runServer() {

        EventLoopGroup bossGroup = new NioEventLoopGroup();

        EventLoopGroup workerGroup = new NioEventLoopGroup();

        try {
            strap.group(bossGroup, workerGroup);
            strap.channel(NioServerSocketChannel.class);
            strap.localAddress(port);
            strap.option(ChannelOption.SO_KEEPALIVE, true);
            strap.option(ChannelOption.ALLOCATOR, ByteBufAllocator.DEFAULT);
            strap.childHandler(new ChannelInitializer<SocketChannel>() {
                @Override
                protected void initChannel(SocketChannel socketChannel) throws Exception {
                    socketChannel.pipeline().addLast(new NettyDiscardHandler());
                }
            });
            //开始绑定服务器
            ChannelFuture channelFuture = strap.bind().sync();
            System.out.println("绑定端口:" + port + "success");

            channelFuture.channel().closeFuture().sync();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }
    }

    public static void main(String[] args) {
        int port = 8088;
        new NettyDiscardServer(port).runServer();
    }
}
