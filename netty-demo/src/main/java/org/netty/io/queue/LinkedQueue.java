package org.netty.io.queue;

import java.util.concurrent.LinkedBlockingQueue;

public class LinkedQueue<T> {

    private static final LinkedBlockingQueue<String> queue = new LinkedBlockingQueue<>(5);


    public static void main(String[] args) throws InterruptedException {

        for (int i = 0; i < 10; i++) {
            queue.put(String.valueOf(i));
        }

    }
}
