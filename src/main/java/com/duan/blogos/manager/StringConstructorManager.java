package com.duan.blogos.manager;

import com.duan.blogos.entity.blogger.BloggerPicture;
import com.duan.blogos.enums.BloggerPictureCategoryEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.File;

/**
 * Created on 2018/1/2.
 * 字符拼接管理者
 *
 * @author DuanJiaNing
 */
@Component
public class StringConstructorManager {

    @Autowired
    private WebsitePropertiesManager websitePropertiesManager;

    @Autowired
    private BloggerPropertiesManager propertiesManager;

    /**
     * 构造图片的url
     *
     * @param picture         图片
     * @return 获取图片的url
     */
    public String constructPictureUrl(BloggerPicture picture) {
        if (picture == null) return null;

        // 参见ImageController
        StringBuilder buffer = new StringBuilder(50);
        int cate = picture.getCategory();
        buffer.append("http://")
                .append(websitePropertiesManager.getAddr())
                .append("/image/")
                .append(picture.getBloggerId())
                .append("/type=")
                // 私有图片登录才能获取
                .append(cate == BloggerPictureCategoryEnum.PRIVATE.getCode() ? "private" : "public")
                .append("/")
                .append(picture.getId())
                .append("?default=")
                // 默认图片类别与图片类别是一致的
                .append(cate == BloggerPictureCategoryEnum.PRIVATE.getCode() ?
                        BloggerPictureCategoryEnum.BLOGGER_UNIQUE_PICTURE.getCode() : cate);

        return buffer.toString();
    }

    /**
     * 拼接图片保存位置所在文件夹路径
     *
     * @param bloggerName  博主名
     * @param categoryName 图片所属类别名
     * @return 文件夹路径
     */
    public String constructImageDirPath(String bloggerName, String categoryName) {
        String rootDirPath = propertiesManager.getBloggerImageRootPath();
        return rootDirPath + File.separator + bloggerName + File.separator + categoryName;
    }
}
