package com.duan.blogos.util;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

/**
 * Created on 2018/2/18.
 *
 * @author DuanJiaNing
 */
public class JiSuApiUtils {

    public static final String APPKEY = "5171de6ae9fd57a6";
    public static final String URL = "http://api.jisuapi.com/sms/send";

    public static String getSmsUrl(String phone, String content) throws UnsupportedEncodingException {
        return URL + "?mobile=" + phone + "&content=" + URLEncoder.encode(content, "utf-8") + "&appkey="
                + APPKEY;
    }

}
