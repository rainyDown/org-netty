package org.netty.io.reactor;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;

public class EchoServerHandler implements Runnable {

    final SocketChannel channel;

    final SelectionKey key;

    final ByteBuffer byteBuffer = ByteBuffer.allocate(1024);

    static final int RECIEVING = 0, SENDING = 0;

    int state = RECIEVING;

    public EchoServerHandler(Selector selector, SocketChannel socketChannel) throws IOException {
        channel = socketChannel;
        socketChannel.configureBlocking(false);
        key = socketChannel.register(selector, 0);
        key.attach(this);
        key.interestOps(SelectionKey.OP_READ);
        selector.wakeup();
    }

    @Override
    public void run() {
        //写入通道
        try {
            if (state == SENDING) {
                channel.write(byteBuffer);
                byteBuffer.clear();
                key.interestOps(SelectionKey.OP_READ);
                //进入接受状态
                state = RECIEVING;

            } else {
                //从通道中读取
                int length = 0;
                while ((length = channel.read(byteBuffer)) != -1) {
                    System.out.println(new String(byteBuffer.array(), 0, length));
                }
                //读完之后,准备写入通道
                byteBuffer.flip();
                key.interestOps(SelectionKey.OP_WRITE);
                state = SENDING;
            }
            key.cancel();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
