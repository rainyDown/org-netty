package org.netty.other.mearsure;

public class Tl {

    public static void main(String[] args) {
        ThreadLocal<String> local = new ThreadLocal<>();
        local.set("1");
        local.set("2");
        System.out.println(local.get());

        ThreadLocal<String> inherLocal = new InheritableThreadLocal<>();
        inherLocal.set("1");
        System.out.println(inherLocal.get());
    }
}
