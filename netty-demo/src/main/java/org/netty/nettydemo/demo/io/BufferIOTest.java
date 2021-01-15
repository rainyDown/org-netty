package org.netty.nettydemo.demo.io;

import lombok.SneakyThrows;

import java.io.*;

public class BufferIOTest {

    /**
     * 缓冲流(字节流)
     */
    private static final String text = "西风吹老洞庭波，一夜湘君白发多。\n" +
            "醉后不知天在水，满船清梦压星河。";

    public static void main(String[] args) throws IOException {
        File file = new File("D://a.sql");
        write(file);

        readFile(file);
    }

    private static void readFile(File file) throws IOException {
        BufferedInputStream inputStream = new BufferedInputStream(new FileInputStream(file));

        byte[] bytes = new byte[1024];
        StringBuilder sb = new StringBuilder();
        // 读取到的字节数组长度，为-1时表示没有数据
        int length = 0;
        // 循环取数据
        while ((length = inputStream.read(bytes)) != -1) {
            // 将读取的内容转换成字符串
            sb.append(new String(bytes, 0, length));
        }
        // 关闭流
        inputStream.close();
        System.out.println(sb.toString());
    }

    @SneakyThrows
    public static void write(File file) throws IOException {
        BufferedOutputStream os = new BufferedOutputStream(new FileOutputStream(file));
        try {
            os.write(text.getBytes());
            os.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
