package com.duan.blogos.manager;

import com.duan.blogos.entity.blogger.BloggerPicture;
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
     * @param picture 图片
     * @return 获取图片的url
     */
    public String constructPictureUrl(BloggerPicture picture, boolean login) {
        if (picture == null) return null;
        //http://tnfs.tngou.net/image/lore/151107/fc2b0b50e9ed622a1e5385a15f0a33d2.png

        // 参加ImageController
        StringBuffer buffer = new StringBuffer(50);
        buffer.append("http://")
                .append(websitePropertiesManager.getAddr())
                .append("/image/")
                .append(picture.getBloggerId())
                .append(login ? "/type=private/" : "/type=public/")
                .append(picture.getId());

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
