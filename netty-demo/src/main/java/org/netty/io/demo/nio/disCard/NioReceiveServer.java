package org.netty.io.demo.nio.disCard;

import org.netty.io.util.IOUtil;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class NioReceiveServer {

    private static Charset charset = Charset.forName("UTF-8");

    public class Client {

        private String fileName;

        private Long fileLength;

        private InetSocketAddress inetSocketAddress;

        private Long startTime;

        private FileChannel outChannel;
    }

    private ByteBuffer byteBuffer = ByteBuffer.allocate(1024);

    Map<SelectableChannel, Client> clientMap = new HashMap<>();

    public void startServer() throws IOException {

        Selector selector = Selector.open();

        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();

        serverSocketChannel.configureBlocking(false);

        InetSocketAddress address = new InetSocketAddress("127.0.0.1", 8088);

        serverSocketChannel.bind(address);

        serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);

        System.out.println("the server is listening ......");

        while (selector.select() > 0) {

            Iterator<SelectionKey> it = selector.selectedKeys().iterator();
            while (it.hasNext()) {
                SelectionKey next = it.next();
                if (next.isAcceptable()) {
                    ServerSocketChannel channel = (ServerSocketChannel) next.channel();
                    SocketChannel socket = channel.accept();
                    if (socket == null) continue;
                    socket.configureBlocking(false);
                    SelectionKey register = socket.register(selector, SelectionKey.OP_READ);
                    Client client = new Client();
                    client.inetSocketAddress = (InetSocketAddress) socket.getRemoteAddress();

                    clientMap.put(socket, client);
                    System.out.println(((InetSocketAddress) socket.getRemoteAddress()).getAddress() + " 连接成功");

                } else if (next.isReadable()) {
                    processData(next);
                }
                it.remove();
            }
        }

    }

    private void processData(SelectionKey key) throws IOException {

        Client client = clientMap.get(key.channel());
        SocketChannel socketChannel = (SocketChannel) key.channel();

        int num = 0;
        try {
            byteBuffer.clear();
            while ((num = socketChannel.read(byteBuffer)) != -1) {
                byteBuffer.flip();
                //根据文件名,创建服务器端文件
                if (null == client.fileName) {
                    String fileName = charset.decode(byteBuffer).toString();

                    File file = new File("D:\\" + fileName);
                    if (!file.exists()) {
                        file.createNewFile();
                    }
                    client.fileName = fileName;
                    String fullName = file.getAbsolutePath() + File.separator + fileName;
                    FileChannel fileChannel = new FileOutputStream(file).getChannel();
                    client.outChannel = fileChannel;
                } else if (0 == client.fileLength) {
                    long fileLength = byteBuffer.getLong();
                    client.fileLength = fileLength;
                    client.startTime = System.currentTimeMillis();
                    System.out.println(client.fileName + "开始传输.....");

                } else {
                    client.outChannel.write(byteBuffer);
                }
                byteBuffer.clear();
            }
            key.cancel();

        } catch (Exception e) {
            key.cancel();
            e.printStackTrace();
            return;
        }
        if (num == -1) {
            //代表文件读完
            IOUtil.closeQuietly(client.outChannel);
            System.out.println("上传完毕...");
            key.cancel();
            System.out.println("共耗时:" + (System.currentTimeMillis() - client.startTime) + "ms");
        }
    }

    public static void main(String[] args) throws IOException {
        NioReceiveServer receiveServer = new NioReceiveServer();
        receiveServer.startServer();
    }

}
