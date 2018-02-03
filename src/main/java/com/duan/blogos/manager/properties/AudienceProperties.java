package com.duan.blogos.manager.properties;

import lombok.Data;

/**
 * Created on 2017/12/20.
 * 读者配置参数
 *
 * @author DuanJiaNing
 */
@Data
public class AudienceProperties {

    /**
     * 默认返回的博主博文列表数量
     */
    private Integer requestBloggerBlogListCount;

    /**
     * 默认返回的博主博文评论数量
     */
    private Integer requestBloggerBlogCommentCount;

}
