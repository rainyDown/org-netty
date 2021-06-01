package org.netty.io.demo.io;

import java.io.*;

public class FileIOTest {


    private static final String text = "梦里不知天在水,满船清梦压星河";

    /**
     * 字节流输出
     */
    public static void main(String[] args) {
        File file = new File("D://hospital_temperature_record.sql");

        File targetFile = new File("D://a.sql");
        //writeFile(file);

        //readFile(file);

        readFileToOther(file, targetFile);
    }

    private static void readFileToOther(File file, File targetFile) {

        if (!targetFile.exists()) {
            try {
                targetFile.createNewFile();

                FileInputStream is = new FileInputStream(file);
                byte[] bytes = new byte[1024];

                FileOutputStream os = new FileOutputStream(targetFile);

                int length = 0;
                while ((length = is.read(bytes)) != -1) {
                    os.write(bytes);
                }

                is.close();
                os.close();

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private static void readFile(File file) {
        Long start = System.currentTimeMillis();
        try {
            FileInputStream fis = new FileInputStream(file);

            byte[] bytes = new byte[1024];
            StringBuilder stringBuilder = new StringBuilder();

            int length = 0;
            while ((length = fis.read(bytes)) != -1) {
                stringBuilder.append(new String(bytes, 0, length));
            }
            fis.close();

            System.out.println(stringBuilder.toString());
            System.out.println("耗时:" + (System.currentTimeMillis() - start) + "s");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void writeFile(File file) {

        try {
            FileOutputStream fos = new FileOutputStream(file, true);
            fos.write(text.getBytes());
            fos.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
