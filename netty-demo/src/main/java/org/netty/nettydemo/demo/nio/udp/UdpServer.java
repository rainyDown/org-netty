package org.netty.nettydemo.demo.nio.udp;

import com.sun.org.apache.bcel.internal.generic.NEW;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.util.Iterator;
import java.util.Set;

public class UdpServer {

    public void receive() {
        try {
            DatagramChannel datagramChannel = DatagramChannel.open();
            datagramChannel.configureBlocking(false);

            datagramChannel.bind(new InetSocketAddress("127.0.0.1", 8080));
            System.out.println("UDP SERVER 启动");
            Selector selector = Selector.open();
            datagramChannel.register(selector, SelectionKey.OP_READ);
            //通过选择器查询io事件

            while (selector.select() > 0) {
                Iterator<SelectionKey> iterator = selector.selectedKeys().iterator();
                ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
                while (iterator.hasNext()){
                    SelectionKey next = iterator.next();
                    if (next.isReadable()){
                        //读取通道信息
                        SocketAddress receive = datagramChannel.receive(byteBuffer);
                        byteBuffer.flip();
                        System.out.println(new String(byteBuffer.array(),0,byteBuffer.limit()));
                        byteBuffer.clear();
                    }
                }
            }
            selector.close();
            datagramChannel.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static void main(String[] args) {
        new UdpServer().receive();
    }
}
