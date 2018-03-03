package com.duan.blogos.util;

import org.springframework.web.multipart.MultipartFile;

import java.io.File;

/**
 * Created on 2018/1/3.
 *
 * @author DuanJiaNing
 */
public class ImageUtils {

    private static final String[] IMAGE_TYPE = {"png", "tif", "jpg", "jpeg", "bmp"};

    /**
     * 获取文件的图片类型
     *
     * @param name 文件名
     * @return 类型
     */
    public static String getImageMimeType(String name) {
        if (StringUtils.isEmpty(name)) return null;

        String[] ts = name.split("\\.");
        if (ts.length != 2) return null;

        String type = ts[1];
        for (String s : IMAGE_TYPE) {
            if (type.equalsIgnoreCase(s)) return s;
        }

        return null;
    }

    /**
     * 判断上传文件是否为图片类型
     *
     * @param file 上传文件
     * @return 是为true
     */
    public static boolean isImageFile(MultipartFile file) {
        if (file == null) return false;

        String mimetype = file.getContentType();
        String type = mimetype.split("/")[0];

        // 要么是图片类型，要么是二进制数据但文件名必须标明为图片类型
        return type.equals("image");
    }

    /**
     * 获得图片文件类型
     *
     * @param file 上传文件
     * @return 类型
     */
    public static String getImageType(MultipartFile file) {
        if (isImageFile(file)) {
            String ct = file.getContentType();
            return ct.split("/")[1];
        } else return null;
    }

    /**
     * 从图片路径中获取文件名
     *
     * @param path 路径
     * @return 文件名
     */
    public static String getImageName(String path) {
        if (StringUtils.isEmpty(path)) return "";

        int li = path.lastIndexOf(File.separator);
        int li1 = path.lastIndexOf(".");
        return path.substring(li + 1, li1);
    }
}
