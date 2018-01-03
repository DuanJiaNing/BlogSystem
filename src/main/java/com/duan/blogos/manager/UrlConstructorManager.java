package com.duan.blogos.manager;

import com.duan.blogos.entity.blogger.BloggerPicture;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created on 2018/1/2.
 * url构造管理者
 *
 * @author DuanJiaNing
 */
@Component
public class UrlConstructorManager {

    @Autowired
    private WebsitePropertiesManager websitePropertiesManager;

    /**
     * 构造图片的url
     *
     * @param picture 图片
     * @return 获取图片的url
     */
    public String constructPictureUrl(BloggerPicture picture) {
        //http://tnfs.tngou.net/image/lore/151107/fc2b0b50e9ed622a1e5385a15f0a33d2.png

        //http://localhost:8080/image/{bloggerId}/{pictureId}
        StringBuffer buffer = new StringBuffer(50);
        buffer.append("http://")
                .append(websitePropertiesManager.getAddr())
                .append("/image/")
                .append(picture.getBloggerId())
                .append("/")
                .append(picture.getId());

        return buffer.toString();
    }
}
