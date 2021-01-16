package netty.client.oio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.util.Iterator;
import java.util.Set;

public class AioServer {

    private static final String text = "东风夜放花千树，\n" +
            "更吹落，星如雨。\n" +
            "宝马雕车香满路。\n" +
            "凤箫声动，玉壶光转，\n" +
            "一夜鱼龙舞。\n" +
            "蛾儿雪柳黄金缕，\n" +
            "笑语盈盈暗香去。\n" +
            "众里寻他千百度，\n" +
            "蓦然回首，那人却在，\n" +
            "灯火阑珊处。";

    public static void server(int port) throws IOException {
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        serverSocketChannel.configureBlocking(false);
        InetSocketAddress socketAddress = new InetSocketAddress(port);
        //将服务器绑定到端口
        serverSocketChannel.bind(socketAddress);

        Selector selector = Selector.open();
        serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
        final ByteBuffer byteBuffer = ByteBuffer.wrap(text.getBytes());
        for (; ; ) {
            try {
                selector.select();
            } catch (Exception e) {
                e.printStackTrace();
                break;
            }
            Set<SelectionKey> selectionKeys = selector.keys();
            Iterator<SelectionKey> iterator = selectionKeys.iterator();
            while (iterator.hasNext()) {
                SelectionKey key = iterator.next();
                iterator.remove();
                try {
                    if (key.isAcceptable()) {  //检查一个时间是否已经就绪
                        ServerSocketChannel channel = (ServerSocketChannel) key.channel();
                        SocketChannel client = channel.accept();
                        client.configureBlocking(false);
                        //← --  接受客户端，并将它注册到选择器
                        client.register(selector, SelectionKey.OP_WRITE | SelectionKey.OP_READ, byteBuffer.duplicate());
                        System.out.println("Accepted connection from " + client);
                    }
                    if (key.isWritable()) { //← --  检查套接字是否已经准备好写数据
                        SocketChannel client = (SocketChannel) key.channel();
                        ByteBuffer buffer = (ByteBuffer) key.attachment();
                        while (buffer.hasRemaining()) {
                            if (client.write(buffer) == 0) {   //← --  将数据写到已连接的客户端
                                break;
                            }
                        }
                        client.close();//← --  关闭连接
                    }

                } catch (Exception e) {
                    key.cancel();
                    try {
                        key.channel().close();
                    } catch (IOException cex) {
                    }
                }
            }

        }
    }
}
