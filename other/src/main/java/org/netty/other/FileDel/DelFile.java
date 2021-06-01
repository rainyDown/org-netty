package org.netty.other.FileDel;

import org.netty.other.util.MyDateUtils;
import org.netty.other.util.MyListUtils;

import java.io.File;

public class DelFile {

    private static Integer save = 2;


    public static void main(String[] args) {
        //删除设备端日志  默认删除上个月
        File deviceLogFile = new File("D:\\");
        if (deviceLogFile.isDirectory()) {
            File[] files = deviceLogFile.listFiles();
            for (File file : files) {
                if (!file.isDirectory()) continue;
                //只查询两层文件夹 年份和月份
                String fileName = file.getName();
                if (checkFileName(fileName)) {
                    int year = Integer.parseInt(fileName);
                    if (year >= Integer.parseInt(MyDateUtils.getYear())) {
                        File[] monthFiles = file.listFiles();
                        for (File monthFile : monthFiles) {
                            String monthFileName = monthFile.getName();
                            int nowMonth = MyDateUtils.getMonth();
                            int awd = calcDeleteMonth(nowMonth);
                            if (Integer.parseInt(monthFileName) < awd) {
                                monthFile.delete();
                            }
                        }
                    } else {
                        file.delete();
                    }
                }
            }
        }

    }

    public static boolean checkFileName(String fileName) {

        Boolean res = true;

        if ("".equals(fileName) || fileName == null) {
            res = false;
        }
        if (fileName.length() != 4) {
            res = false;
        }
        if (!fileName.startsWith("2")) {
            res = false;
        }
        char[] chars = fileName.toCharArray();
        for (char ch : chars) {
            if (!Character.isDigit(ch)) {
                res = false;
            }
        }
        return res;
    }

    public static int calcDeleteMonth(Integer month) {
        if (save < 1) {
            save = 1;
        }
        return month - (save - 1) <= 0 ? month : month - (save - 1);
    }
}
