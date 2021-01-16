package netty.client.oio;

import lombok.SneakyThrows;

import java.io.IOException;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class BioServer {

    private static final String text = "千古江山，英雄无觅，孙仲谋处。\n" +
            "舞榭歌台，风流总被、雨打风吹去。\n" +
            "斜阳草树，寻常巷陌，\n" +
            "人道寄奴曾住。\n" +
            "想当年，金戈铁马，气吞万里如虎。\n" +
            "元嘉草草，封狼居胥，\n" +
            "赢得仓皇北顾。\n" +
            "四十三年，望中犹记，烽火扬州路。\n" +
            "可堪回首，佛狸祠下，\n" +
            "一片神鸦社鼓。\n" +
            "凭谁问：廉颇老矣，尚能饭否？";

    public static void server(int port) throws IOException {
        ServerSocket serverSocket = new ServerSocket(port);
        try {
            Socket socket = serverSocket.accept();
            new Thread(new Runnable() {
                @SneakyThrows
                @Override
                public void run() {
                    OutputStream os = null;
                    try {
                        for (; ; ) {
                            os = socket.getOutputStream();
                            os.write(text.getBytes());
                            os.flush();
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    } finally {
                        socket.close();
                    }

                }
            }).start();
        } catch (Exception e) {

        }
    }
}