package org.netty.thread.callback;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

public class ThreadCallback {


    static class HotWater implements Callable<Boolean> {

        @Override
        public Boolean call() throws Exception {
            return true;
        }
    }


    static class WashWater implements Callable<Boolean> {

        @Override
        public Boolean call() throws Exception {
            return true;
        }
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        Callable<Boolean> h = new HotWater();
        Callable<Boolean> w = new WashWater();

        FutureTask<Boolean> hTask = new FutureTask<>(h);
        FutureTask<Boolean> wTask = new FutureTask<>(w);

        Thread thread = new Thread(hTask,"烧水");
        Thread thread1 = new Thread(wTask,"洗碗");

        thread.start();
        thread1.start();

        Boolean aBoolean = hTask.get();
        Boolean aBoolean1 = wTask.get();



    }

}
