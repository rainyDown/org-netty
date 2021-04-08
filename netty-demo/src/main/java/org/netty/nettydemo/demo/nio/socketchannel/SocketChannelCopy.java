package org.netty.nettydemo.demo.nio.socketchannel;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;

public class SocketChannelCopy {

    public Charset charset = Charset.forName("UTF-8");

    public void sendFile() throws IOException {
        String filePath = "E:\\a.sql";
        String destFile = "E:\\b.sql";

        File srcFile = new File(filePath);
        if (srcFile.exists()) {
            srcFile.createNewFile();
        }
        try {
            FileChannel fileChannel = new FileInputStream(srcFile).getChannel();
            SocketChannel socketChannel = SocketChannel.open();
            socketChannel.socket().bind(new InetSocketAddress("127.0.0.1", 8099));
        /*while (!socketChannel.finishConnect()){

        }*/
            ByteBuffer fileName = charset.encode(destFile);
            //发送文件名
            socketChannel.write(fileName);
            fileName.flip();
            ByteBuffer buff = ByteBuffer.allocate(1024);
            int length = 0;
            while ((length = fileChannel.read(buff)) > 0) {
                buff.flip();
                socketChannel.write(buff);
                buff.clear();
            }
            if (length == -1) {
                fileChannel.close();
                socketChannel.shutdownOutput();
                socketChannel.close();
            }
        } catch (Exception e) {
            System.out.println("fial");
        }
    }

    public static void main(String[] args) throws IOException {
        SocketChannelCopy channelCopy = new SocketChannelCopy();
        channelCopy.sendFile();
    }

}
