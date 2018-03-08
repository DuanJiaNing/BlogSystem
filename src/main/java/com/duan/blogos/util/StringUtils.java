package com.duan.blogos.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
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

        return StringUtils.isEmpty(sour) ? null :
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
     * 将字符串切分
     *
     * @param sour  字符串
     * @param regex 切分表达式
     * @return 字符串数组
     */
    public static String[] stringArrayToArray(String sour, String regex) {
        return StringUtils.isEmpty(sour) ? null : sour.split(regex);
    }

    /**
     * int数组拼接为字符串
     */
    public static String intArrayToString(int[] arr, String join) {
        if (CollectionUtils.isEmpty(arr)) return "";

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
                int i = Integer.parseInt(s);
            }
            return true;
        } catch (IllegalArgumentException e) {
            return false;
        }
    }

    /**
     * 检查字符串是否为 null
     *
     * @param str 字符串
     * @return 是返回true
     */
    public static boolean isEmpty(String str) {
        return str == null || "".equals(str) || "null".equals(str);
    }

    /**
     * 检查字符串是否为 null，允许 "null" 字符串
     *
     * @param str 字符串
     * @return 是返回true
     */
    public static boolean isEmpty_(String str) {
        return str == null || "".equals(str);
    }

    /**
     * 检查字符串是否为url
     *
     * @param url 字符串
     * @return 是返回true
     */
    public static boolean isURL(String url) {
        return !isEmpty(url) && url.matches("((http|ftp|https)://)(([a-zA-Z0-9\\._-]+\\.[a-zA-Z]{2,6})|([0-9]{1,3}" +
                "\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}))(:[0-9]{1,4})*(/[a-zA-Z0-9\\&%_\\./-~-]*)?");
    }

    /**
     * 检查字符串是否为电话号码
     * http://www.jb51.net/article/96201.htm
     *
     * @return 是为true
     */
    public static boolean isPhone(String phone) {
        String cellPhone = "^((13[0-9])|(14[5|7])|(15([0-3]|[5-9]))|(18[0-9]))\\d{8}$";
        String telephone = "^(0\\d{2}-\\d{8}(-\\d{1,4})?)|(0\\d{3}-\\d{7,8}(-\\d{1,4})?)$";
        return !isEmpty(phone) && (phone.matches(cellPhone) || phone.matches(telephone));
    }

    /**
     * 检查字符串是否为邮箱
     *
     * @return 是否true
     */
    public static boolean isEmail(String email) {
        return !isEmpty(email) && email.matches("\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*");
    }

    /**
     * 将字符串不可逆转化为 sha 字节数组
     *
     * @param str 字符串
     * @return 转化结果
     */
    public static byte[] toSha(String str) throws NoSuchAlgorithmException {
        MessageDigest sha = MessageDigest.getInstance("SHA");
        sha.update(str.getBytes());
        return sha.digest();
    }


    /**
     * 将普通字符串转为 16 字符串
     *
     * @param str 普通字符串
     * @return 16 进制字符串
     */
    public static String stringToUnicode(String str) {
        if (isEmpty_(str)) return "";

        StringBuilder builder = new StringBuilder();
        for (char ch : str.toCharArray()) {
            int ich = (int) ch;
            String hex = Integer.toHexString(ich);
            // 形如 \\u3e 或 \\ua 或 \\ua23 的浏览器 html 无法解析
            if (hex.length() == 2) hex = "00" + hex;
            else if (hex.length() == 1) hex = "000" + hex;
            else if (hex.length() == 3) hex = "0" + hex;

            builder.append("\\u").append(hex);
        }

        return builder.toString();
    }

    /**
     * 将 16 字符串转普通字符串
     *
     * @param unicode 16 进制字符串
     * @return 普通字符串
     */
    public static String unicodeToString(String unicode) {
        if (isEmpty_(unicode)) return "";

        StringBuffer string = new StringBuffer();
        String[] hex = unicode.split("\\\\u");

        for (int i = 1; i < hex.length; i++) {

            // 转换出每一个代码点
            int data = Integer.parseInt(hex[i], 16);

            // 追加成string
            string.append((char) data);
        }

        return string.toString();
    }


}
