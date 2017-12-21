package com.duan.blogos.util;

import java.util.stream.Stream;

/**
 * Created on 2017/12/20.
 *
 * @author DuanJiaNing
 */
public class StringUtils {


    /**
     * int字符串转为int数组，并去重
     */
    public static int[] intStringDistinctToArray(String sour, String regex) {

        return sour == null ? null :
                Stream.of(sour.split(regex))
                        .mapToInt(Integer::valueOf)
                        .distinct()
                        .toArray();

    }

    /**
     * 对象数组拼接为字符串
     */
    public static String arrayToString(Object[] arr, String join) {
        if (arr == null) {
            return null;
        }

        StringBuilder builder = new StringBuilder();
        for (Object obj : arr) {
            builder.append(obj).append(join);
        }

        String r = builder.toString();
        int len = join.length();
        int sum = r.length();
        return r.substring(0, sum - len);
    }

    /**
     * int数组拼接为字符串
     */
    public static String intArrayToString(int[] arr, String join) {
        Integer[] is = new Integer[arr.length];
        for (int i = 0; i < arr.length; i++) {
            is[i] = arr[i];
        }

        return arrayToString(is, join);
    }

    /**
     * 检查数值字符串是否被指定表达式隔开
     *
     * @param str   字符串
     * @param regex 正则表达式
     * @return 正确返回true
     */
    public static boolean isIntStringSplitByChar(String str, String regex) {
        if (str == null || regex == null) return false;

        try {
            String[] strings = str.split(regex);
            for (String s : strings) {
                int i = Integer.valueOf(s);
            }
            return true;
        } catch (IllegalArgumentException e) {
            return false;
        }
    }

}
