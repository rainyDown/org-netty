package org.netty.io.demo.io;

import java.io.*;

public class ZIFUBufferTest {

    private static final String text = "眼光有梭，足以照映一世之豪。背胛有负，足以荷载四国之重";

    public static void main(String[] args) throws IOException {
        File file = new File("D://a.sql");

        writeFile(file);

        readFile(file);
    }

    private static void readFile(File file) throws IOException {

        BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(file)));

        StringBuilder stringBuilder = new StringBuilder();
        char[] chars = new char[1024];
        int length = 0;
        String line;
        /*while ((length = reader.read(chars)) != -1) {
            stringBuilder.append(new String(chars));
        }*/

        while ((line = reader.readLine()) != null) {
            stringBuilder.append(line);
        }
        System.out.println(stringBuilder.toString());
    }

    private static void writeFile(File file) throws IOException {

        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file)));

        writer.write(text);

        writer.close();
    }


}
