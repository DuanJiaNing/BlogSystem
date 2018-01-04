package com.duan.blogos.manager;

import com.duan.blogos.dao.blogger.BloggerAccountDao;
import com.duan.blogos.entity.blogger.BloggerAccount;
import com.duan.blogos.enums.BloggerPictureCategoryEnum;
import com.duan.blogos.util.ImageUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

/**
 * Created on 2018/1/3.
 *
 * @author DuanJiaNing
 */
@Component
public class ImageUploadManager {

    @Autowired
    private BloggerPropertiesManager propertiesManager;

    @Autowired
    private BloggerAccountDao accountDao;

    /**
     * 将图片保存到博主的对应文件夹下
     *
     * @param file      图片
     * @param bloggerId 博主id
     * @param category  图片类别
     * @return 路径
     */
    public String saveImageToDisk(MultipartFile file, int bloggerId, int category) throws IOException {
        //后缀一定要为图片类型
        String type = ImageUtils.getImageType(file);
        if (type == null) return null;

        String rootDirPath = propertiesManager.getBloggerImageRootPath();
        String categoryName = BloggerPictureCategoryEnum.valueOf(category).name();

        BloggerAccount account = accountDao.getAccountById(bloggerId);
        String username = account.getUsername();
        File specDir = new File(rootDirPath + File.separator + username + File.separator + categoryName + File.separator);
        if (!specDir.exists() || !specDir.isDirectory()) specDir.mkdirs();

        //页面使用 <%@ page pageEncoding="utf-8" %> 指令，否则会出现文件名中文乱码
        String name = handleImageName(file.getOriginalFilename(), type);
        File image = new File(specDir.getAbsolutePath() + File.separator + name);
        if (!image.exists() || image.isDirectory()) file.transferTo(image);

        return image.getAbsolutePath();
    }

    //拼接图片名，确保以文件类型为后缀
    private String handleImageName(String name, String type) {
        String[] sp = name.split("\\.");
        if (sp.length == 1) { //没有后缀
            return name + "." + type;
        } else if (sp.length == 2) { //有后缀
            if (!sp[1].equals(type)) { //后缀不正确
                return name.replace(sp[1], type);
            } else return name; //后缀正确
        } else {
            String red = name.replace(".", "");
            return red + "." + type;
        }
    }


}
