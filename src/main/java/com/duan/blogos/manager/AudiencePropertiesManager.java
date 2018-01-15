package com.duan.blogos.manager;

import lombok.Data;

/**
 * Created on 2017/12/20.
 * 读者访问时使用的默认资源
 *
 * @author DuanJiaNing
 */
@Data
public class AudiencePropertiesManager {

    /**
     * 默认返回的博主博文列表数量
     */
    private Integer requestBloggerBlogListCount;

    /**
     * 默认返回的博主博文评论数量
     */
    private Integer requestBloggerBlogCommentCount;

}
