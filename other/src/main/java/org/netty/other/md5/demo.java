package org.netty.other.md5;

public class demo {
    public static void main(String[] args) {

        String pass = "34a38f2dc15b281f44cd9bf21775ea8a";

        String timeStamp = "1620379918429";

        String des = MD5Utils.getMD5Code(pass+timeStamp);
        System.out.println(des);
    }
}
