package com.duan.blogos.util;

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

}
