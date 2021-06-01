package org.netty.io.demo.nio.socketchannel;

import java.io.File;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

public class ServerSocketCopy {

    public static void main(String[] args) {

    }

    public void receive() {
        File file = new File("E:\\c.sql");
        if (!file.exists()) {
            try {
                file.createNewFile();
                ServerSocket serverSocket = new ServerSocket(8089);
                Socket fileSocket = serverSocket.accept();
                SocketChannel channel = fileSocket.getChannel();
                ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
                int length = 0;
                while ((length = channel.read(byteBuffer)) > 0) {

                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
