package org.netty.thread;

import lombok.SneakyThrows;

import java.util.concurrent.Callable;

public class ThreadJoin {

    public static final int SLEEP_GAP = 500;

    public static final String getCurrentName() {
        return Thread.currentThread().getName();
    }

    static class WashThread extends Thread {

        public WashThread() {
            super("洗茶线程.....");
        }

        @SneakyThrows
        @Override
        public void run() {
            System.out.println("洗好茶壶");
            System.out.println("洗好茶叶");
            System.out.println("拿茶叶");
            Thread.sleep(SLEEP_GAP);
            System.out.println("水开了");
            System.out.println("运行结束..........");
        }
    }

    static class HotThread extends Thread {

        public HotThread() {
            super("烧水线程......");
        }

        @SneakyThrows
        @Override
        public void run() {
            System.out.println("洗好水壶");
            System.out.println("灌上凉水");
            System.out.println("放在火上");
            //线程睡眠一段时间，代表烧水中
            Thread.sleep(SLEEP_GAP);
            System.out.println("洗完了");
        }
    }

    public static void main(String[] args) {
        Thread hThread = new HotThread();
        Thread wThread = new WashThread();

        hThread.start();
        wThread.start();

        try {
            hThread.join();
            wThread.join();
            Thread.currentThread().setName("主线程");
            System.out.println("泡茶喝");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
