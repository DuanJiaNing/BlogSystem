package com.duan.blogos.util;

/**
 * Created on 2018/1/3.
 *
 * @author DuanJiaNing
 */
public class ImageUtils {


    /**
     * 根据图片文件名获得图片类型
     *
     * @param imagePath 全路径
     * @return 类型
     */
    public static String getType(String imagePath) {
        if (imagePath.endsWith(".jpg") || imagePath.endsWith(".jpeg")) return "jpeg";
        if (imagePath.endsWith(".png")) return "png";
        if (imagePath.endsWith(".gif")) return "gif";
        else return "png";
    }
}
