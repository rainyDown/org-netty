package netty.client.oio;


import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.oio.OioServerSocketChannel;


import java.net.InetSocketAddress;
import java.nio.charset.Charset;

public class NettyOioServer {

    private static final String text = "明月别枝惊鹊，清风半夜鸣蝉。\n" +
            "稻花香里说丰年，听取蛙声一片。\n" +
            "七八个星天外，两三点雨山前。\n" +
            "旧时茅店社林边，路转溪桥忽见。";

    public static void server(int port) throws InterruptedException {
        final ByteBuf byteBuf = Unpooled.unreleasableBuffer(Unpooled.copiedBuffer(text, Charset.forName("UTF-8")));
        EventLoopGroup group = new NioEventLoopGroup();

        try {
            ServerBootstrap b = new ServerBootstrap();
            b.group(group).channel(OioServerSocketChannel.class)
                    .localAddress(new InetSocketAddress(port))
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel socketChannel) throws Exception {
                            socketChannel.pipeline().addLast(new ChannelInboundHandlerAdapter() {
                                @Override
                                public void channelActive(ChannelHandlerContext ctx) throws Exception {
                                    ctx.writeAndFlush(byteBuf.duplicate()).addListener(ChannelFutureListener.CLOSE); //将消息写入客户端,以便消息一被写完就关闭连接
                                }
                            });
                        }
                    });
            ChannelFuture sync = b.bind().sync();
            sync.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            group.shutdownGracefully().sync();
        }
    }
}
