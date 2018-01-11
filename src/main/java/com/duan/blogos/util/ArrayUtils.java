package com.duan.blogos.util;

/**
 * Created on 2018/1/11.
 *
 * @author DuanJiaNing
 */
public class ArrayUtils {

    /**
     * 添加元素到数组中，添加时重复元素仍会添加
     *
     * @param src 源数组
     * @param as  添加的元素
     * @return 添加后的数组
     */
    public static int[] addToArray(int[] src, int... as) {

        int len = as.length;
        int slen = src.length;

        int[] des = new int[slen + len];
        System.arraycopy(src, 0, des, 0, slen);
        System.arraycopy(as, 0, des, src.length, len);

        return des;
    }

    /**
     * 从数组中移除值
     *
     * @param src 源数组
     * @param as  要移除的值
     * @return 移除后的数组
     */
    public static int[] removeFromArray(int[] src, int... as) {

        int len = as.length;
        int slen = src.length;
        if (len > slen) return src;

        int[] des = new int[slen];
        int c = 0;
        out:
        for (int i = 0; i < slen; i++) {
            for (int j = 0; j < len; j++) {
                if (as[j] == src[i]) continue out;
            }

            des[c++] = src[i];
        }

        if (c < slen) {
            int[] res = new int[c];
            System.arraycopy(des, 0, res, 0, c);
            return res;
        } else return des;

    }

    /**
     * 替换元素
     *
     * @param src  源数组
     * @param from 目标替换值
     * @param to   替换结果值
     */
    public static void replace(int[] src, int from, int to) {
        for (int i = 0; i < src.length; i++) {
            if (src[i] == from) src[i] = to;
        }
    }

}
