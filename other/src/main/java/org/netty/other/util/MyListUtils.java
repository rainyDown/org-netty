package org.netty.other.util;


import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;

/**
 * @author luowx on 2018/11/5 0005.
 */
public class MyListUtils<T> {


    /**
     * 并集
     *
     * @param list1
     * @param list2
     * @return
     */
    public List<T> intersection(final List<T> list1, final List<T> list2) {
        List<T> newList = new ArrayList<>();
        newList.addAll(list1);
        newList.removeAll(list2);
        newList.addAll(list2);
        return newList;
    }

    /**
     * 差集
     *
     * @param list1
     * @param list2
     * @return
     */
    public List<T> difference(final List<T> list1, final List<T> list2) {
        List<T> newList = new ArrayList<>();
        newList.addAll(list1);
        newList.removeAll(list2);
        return newList;
    }

    /**
     * 交集
     *
     * @param list1
     * @param list2
     * @return
     */
    public List<T> union(final List<T> list1, final List<T> list2) {
        List<T> newList = new ArrayList<>();
        newList.addAll(list1);
        newList.retainAll(list2);
        return newList;
    }

    /**
     * 去除重复元素
     *
     * @param list
     * @return
     */
    public List<T> removeDuplicate(List<T> list) {
        HashSet h = new HashSet(list);
        list.clear();
        list.addAll(h);
        return list;
    }

    /**
     * @param list
     * @return
     */
    public static boolean listIsEmpty(List list) {
        return list != null && list.size() > 0;
    }

    /**
     * @param array
     * @return
     */
    public static boolean arrayIsEmpty(Object[] array) {
        return array != null && array.length > 0;
    }

    /**
     * String类型列表转字符串
     *
     * @param list
     * @return
     */
    public static String listToString(List list) {
        StringBuffer sb = new StringBuffer();
        if (listIsEmpty(list)) {
            for (Object var : list) {
                sb.append(String.valueOf(var));
                sb.append(",");
            }
        }
        return sb.length() > 0 ? sb.substring(0, sb.length() - 1) : "";
    }

    /**
     * String类型列表转字符串
     *
     * @param list
     * @return
     */
    public static List<Long> stringListToLong(List<String> list) {
        List<Long> result = new ArrayList<>();
        if (listIsEmpty(list)) {
            for (String var : list) {
                if (var != null && var.trim().length() > 0) {
                    result.add(Long.valueOf(var));
                }
            }
        }
        return result;
    }

    /**
     * 随机生成字母加数字的密码
     *
     * @param lengths 密码的位数
     * @return
     */
    public static String getStringRandom(int lengths) {
        String val = "";
        Random random = new Random();
        //参数lengths，表示生成几位随机数
        for (int i = 0; i < lengths; i++) {
            String strOrNum = random.nextInt(2) % 2 == 0 ? "str" : "num";
            //随机输出是字母还是数字
            if ("str".equalsIgnoreCase(strOrNum)) {
                //随机输出是大写字母还是小写字母
                int temp = random.nextInt(2) % 2 == 0 ? 65 : 97;
                val += (char) (random.nextInt(26) + temp);
            } else if ("num".equalsIgnoreCase(strOrNum)) {
                val += String.valueOf(random.nextInt(10));
            }
        }
        return val;
    }

}
