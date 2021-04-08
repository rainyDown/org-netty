package org.netty.nettydemo.demo.nio.udp;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;
import java.util.Date;
import java.util.Scanner;

public class UdpClient {

    public void send(String[] args) {
        try {
            DatagramChannel channel = DatagramChannel.open();
            channel.configureBlocking(false);
            Scanner scanner = new Scanner(System.in);
            ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
            System.out.println("udp 客户端启动成功");
            System.out.println("请输入内容");
            while (scanner.hasNext()) {
                String next = scanner.next();
                byteBuffer.put((new Date(System.currentTimeMillis()).getTime() + ">>" + next).getBytes());
                byteBuffer.flip();
                channel.send(byteBuffer, new InetSocketAddress("127.0.0.1", 8080));
                byteBuffer.clear();
            }
            channel.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new UdpClient().send(null);
    }

}
