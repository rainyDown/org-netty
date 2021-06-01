package org.netty.io.demo.nio.filechannel;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class NioCopy {

    public static void main(String[] args) throws IOException {
        nioCopy();
    }

    private static void nioCopy() throws IOException {
        String path = "D:\\a.sql";
        // nioCpoyFile(path);
        nioCopyTrans(path);
    }

    private static void nioCopyFile(String path) throws IOException {
        File file = new File(path);
        if (!file.exists()) {
            System.out.println("文件不存在");
        }
        File out = new File("E:\\b.sql");
        int len;
        FileInputStream fis = null;
        FileChannel channel = null;
        FileOutputStream fos = null;
        FileChannel outChannel = null;
        try {
            fis = new FileInputStream(file);
            fos = new FileOutputStream(out);
            outChannel = fos.getChannel();
            channel = fis.getChannel();
            ByteBuffer bf = ByteBuffer.allocate(1024);
            while ((len = channel.read(bf)) != -1) {
                bf.flip();
                int outlen = 0;
                while ((outlen = outChannel.write(bf)) != 0) {
                    System.out.println("写入的字节数:{}" + outlen);
                }
                bf.clear();
            }
            outChannel.force(true);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            fis.close();
            channel.close();
            fos.close();
            outChannel.close();
        }
    }

    public static void nioCopyTrans(String path) throws IOException {
        File file = new File(path);
        File file1 = new File("D:\\b.sql");
        if (!file.exists()) {
            return;
        }
        if (!file1.exists()) {
            file1.createNewFile();
        }
        try {
            FileChannel in = new FileInputStream(file).getChannel();
            FileChannel out = new FileOutputStream(file1).getChannel();
            in.transferTo(0, in.size(), out);
            out.transferFrom(in, 0, out.size());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

}
