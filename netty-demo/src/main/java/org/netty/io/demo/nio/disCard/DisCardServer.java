package org.netty.io.demo.nio.disCard;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;

public class DisCardServer {

    public static void main(String[] args) throws IOException {

        Selector selector = Selector.open();

        InetSocketAddress address = new InetSocketAddress("127.0.0.1", 8088);
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        serverSocketChannel.configureBlocking(false);

        serverSocketChannel.bind(address);

        serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
        System.out.println("server start success");
        while (selector.select() > 0) {
            Iterator<SelectionKey> keys = selector.selectedKeys().iterator();

            while (keys.hasNext()) {
                SelectionKey key = keys.next();
                if (key.isReadable()) {
                    int len = 0;
                    ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
                    SocketChannel channel = (SocketChannel) key.channel();
                    while ((len = channel.read(byteBuffer)) != -1) {
                        byteBuffer.flip();
                        System.out.println(new String(byteBuffer.array(), 0, byteBuffer.limit()));
                        byteBuffer.clear();
                    }
                    channel.close();
                }
                if (key.isAcceptable()) {
                    System.out.println("新的连接"+0);
                    SocketChannel socketChannel = serverSocketChannel.accept();
                    socketChannel.configureBlocking(false);
                    socketChannel.register(selector, SelectionKey.OP_READ);

                }
                keys.remove();
            }

        }
        serverSocketChannel.close();

    }
}
