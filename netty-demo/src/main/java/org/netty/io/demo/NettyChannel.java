package org.netty.io.demo;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

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
