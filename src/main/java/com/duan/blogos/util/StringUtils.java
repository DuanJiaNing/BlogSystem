package com.duan.blogos.util;

import com.duan.blogos.exception.runtime.StringSplitException;

import java.util.regex.PatternSyntaxException;
import java.util.stream.Stream;

/**
 * Created on 2017/12/20.
 *
 * @author DuanJiaNing
 */
public class StringUtils {


    // int字符串转为int数组
    public static int[] intStringToArray(String sour, String regex) {

        return sour == null ? null :
                Stream.of(sour.split(regex))
                        .mapToInt(Integer::valueOf)
                        .distinct()
                        .toArray();

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
