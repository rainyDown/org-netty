package org.netty.nettydemo.demo.io;

import java.io.*;

public class ZIFUIOTest {

    private static final String text = "众里寻他千百度，蓦然回首，那人却在，灯火阑珊处";

    /**
     * 字符流
     */

    public static void main(String[] args) throws IOException {
        File file = new File("D://a.sql");

        writeFile(file);

        readFile(file);
    }

    private static void readFile(File file) throws IOException {

        InputStreamReader reader = new InputStreamReader(new FileInputStream(file), "UTF-8");

        char[] chars = new char[1024];

        StringBuilder stringBuilder = new StringBuilder();
        int length = 0;
        while ((length = reader.read(chars)) != -1) {
            stringBuilder.append(chars, 0, length);
        }

        reader.close();
        System.out.println(stringBuilder.toString());

    }

    private static void writeFile(File file) throws IOException {

        OutputStreamWriter writer = new OutputStreamWriter(new FileOutputStream(file, true), "UTF-8");

        writer.write(text);

        writer.close();

    }


}
