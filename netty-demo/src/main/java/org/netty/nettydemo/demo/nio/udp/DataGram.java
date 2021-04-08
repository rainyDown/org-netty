package org.netty.nettydemo.demo.nio.udp;

import java.io.IOException;
import java.net.DatagramSocket;
import java.net.InetSocketAddress;
import java.net.SocketException;
import java.nio.channels.DatagramChannel;

public class DataGram {
    public static void main(String[] args) throws SocketException {
        DatagramSocket socket = new DatagramSocket();
        DatagramChannel channel = socket.getChannel();
        try {
            channel.configureBlocking(true);
            channel.socket().bind(new InetSocketAddress(8080));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
