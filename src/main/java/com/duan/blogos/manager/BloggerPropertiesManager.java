package com.duan.blogos.manager;

import lombok.Data;

/**
 * Created on 2017/12/28.
 *
 * @author DuanJiaNing
 */
@Data
public class BloggerPropertiesManager {

    /**
     * 博主友情链接默认请求条数
     */
    private Integer requestBloggerLinkCount;

    /**
     * 博主收藏博文默认请求条数
     */
    private Integer requestBloggerCollectCount;

    /**
     * 博主博文类别默认请求条数
     */
    private Integer requestBloggerBlogCategoryCount;

    /**
     * 博主获取博文列表时的默认获取条数
     */
    private Integer requestBlogListCount;

    /**
     * 博主相册图片默认请求数量
     */
    private Integer requestBloggerPictureCount;

    /**
     * 博主图片保存根路径
     */
    private String bloggerImageRootPath;

    /**
     * 拥有唯一图片管理权限的博主的id
     */
    private Integer pictureManagerBloggerId;

    /**
     * 默认的博文收藏类别
     */
    private Integer defaultBlogCollectCategory;

    /**
     * 保存在session属性中的博主id对应的名字
     */
    private String sessionNameOfBloggerId;

    /**
     * 保存在session属性中的博主用户名对应的名字
     */
    private String sessionNameOfBloggerName;

    /**
     * 保存在session属性中的错误信息对应的名字
     */
    private String sessionNameOfErrorMsg;

}
