package org.netty.nettydemo.demo;

import io.netty.channel.Channel;
import io.netty.channel.ChannelFactory;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.SocketChannel;

public class NettyChannel {




    public void check() throws IOException {
        File file = new File("D://hospital_temperature_record.sql");
        FileInputStream is = null;
        try {
            is = new FileInputStream(file);
            FileChannel channel = is.getChannel();
            ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
            int by = channel.read(byteBuffer);
            while (by != -1) {
                byteBuffer.flip();
                while (byteBuffer.hasRemaining()) {
                    System.out.println((char) byteBuffer.get());
                }
                byteBuffer.compact();
                by = channel.read(byteBuffer);
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (is != null) {
                    is.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

        }

    }
}
