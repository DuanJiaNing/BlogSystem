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
     * 默认的url请求参数的间隔字符
     * 如url中传递多个博文类别id时：1,2,3,8 这里间隔字符为","
     */
    private String urlConditionSplitCharacter;

    /**
     * 默认返回的博主博文评论数量
     */
    private Integer requestBloggerBlogCommentCount;

}
