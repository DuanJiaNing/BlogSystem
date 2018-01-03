package com.duan.blogos.manager;

import com.duan.blogos.entity.blogger.BloggerPicture;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

/**
 * Created on 2018/1/2.
 * url构造管理者
 *
 * @author DuanJiaNing
 */
@Component
public class UrlConstructorManager {

    /**
     * 构造图片的url
     *
     * @param picture 图片
     * @param request 请求参数
     * @return 获取图片的url
     */
    public String constructPictureUrl(BloggerPicture picture, HttpServletRequest request) {
        //http://tnfs.tngou.net/image/lore/151107/fc2b0b50e9ed622a1e5385a15f0a33d2.png

        //http://localhost:8080/image/{bloggerId}/{pictureId}
        StringBuffer buffer = new StringBuffer(50);
        buffer.append("http://")
                .append(request.getLocalName())
                .append("/image/")
                .append(picture.getBloggerId())
                .append("/")
                .append(picture.getId());

        return buffer.toString();
    }
}
