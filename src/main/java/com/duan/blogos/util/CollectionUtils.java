package com.duan.blogos.util;

import java.util.Collection;
import java.util.List;

/**
 * Created on 2017/12/20.
 *
 * @author DuanJiaNing
 */
public class CollectionUtils {

    /**
     * 判断数组中是否包含某一元素
     */
    public static boolean intArrayContain(int[] sour, int find) {
        if (isEmpty(sour)) {
            return false;
        }

        for (int i : sour) {
            if (i == find) {
                return true;
            }
        }

        return false;
    }

    /**
     * 判断集合是否为空集合
     */
    public static boolean isEmpty(Collection<?> sour) {
        return sour == null || sour.size() == 0;
    }

    /**
     * 判断数组是否为空
     */
    public static <T> boolean isEmpty(T[] sour) {
        return sour == null || sour.length == 0;
    }

    /**
     * 判断数组是否为空
     */
    public static boolean isEmpty(int[] sour) {
        return sour == null || sour.length == 0;
    }
}
