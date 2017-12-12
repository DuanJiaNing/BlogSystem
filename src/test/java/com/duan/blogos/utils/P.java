package com.duan.blogos.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created on 2017/12/12.
 *
 * @author DuanJiaNing
 */
public class P {

    private static final SimpleDateFormat format = new SimpleDateFormat("HH:mm:ss-SSS");

    public static void out(Object obj) {
        System.out.println("--Duan " + format.format(new Date()) + " " + obj);
    }

    public static void outnl(Object obj) {
        System.out.printf("--Duan " + format.format(new Date()) + " " + obj);
    }


}
