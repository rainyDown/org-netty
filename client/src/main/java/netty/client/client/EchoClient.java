package netty.client.client;


import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoop;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.util.CharsetUtil;
import org.springframework.beans.factory.annotation.Value;

import java.net.InetAddress;
import java.net.InetSocketAddress;

public class EchoClient {

    public static void main(String[] args) throws InterruptedException {
        String host = "127.0.0.1";
        int port = 8888;
        bind(host, port);
    }

    public static void bind(String host, Integer port) throws InterruptedException {
        EchoClientHandler clientHandler = new EchoClientHandler();
        EventLoopGroup group = new NioEventLoopGroup();
        Bootstrap b = new Bootstrap();
        try {
            b.group(group)
                    .channel(NioSocketChannel.class)
                    .remoteAddress(new InetSocketAddress(host, port))
                    .handler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel channel) throws Exception {
                            channel.pipeline().addLast(clientHandler);
                        }
                    });
            ChannelFuture sync = b.connect().sync();
            sync.channel().closeFuture().sync();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            group.shutdownGracefully().sync();
        }
    }

    @ChannelHandler.Sharable //←--  标记该类的实例可以被多个Channel共享
    public static class EchoClientHandler extends
            SimpleChannelInboundHandler<ByteBuf> {
        @Override
        public void channelActive(ChannelHandlerContext ctx) {
            // ←--  当被通知Channel是活跃的时候，发送一条消息
            ctx.writeAndFlush(Unpooled.copiedBuffer("Netty rocks!",
                    CharsetUtil.UTF_8));
        }

        @Override
        public void channelRead0(ChannelHandlerContext ctx, ByteBuf in) {
            //    ←--  记录已接收消息的转储
            System.out.println(
                    "Client received: " + in.toString(CharsetUtil.UTF_8));
        }

        //←--  在发生异常时，记录错误并关闭Channel
        @Override
        public void exceptionCaught(ChannelHandlerContext ctx,
                                    Throwable cause) {
            cause.printStackTrace();
            ctx.close();
        }
    }
}
