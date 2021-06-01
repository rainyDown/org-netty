package netty.client.server;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class TestLocal {
    public static void main(String[] args) {
        ThreadLocal<Map<String,String>>  mapThreadLocal = new ThreadLocal<>();

        mapThreadLocal.set(Collections.emptyMap());
        Map<String,String> back = mapThreadLocal.get();

        System.out.println(back);
    }
}
