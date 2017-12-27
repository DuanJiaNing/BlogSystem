package com.duan.blogos.manager.validate;

import org.springframework.stereotype.Component;

/**
 * Created on 2017/12/26.
 * 博客验证
 *
 * @author DuanJiaNing
 */
@Component
public class BlogCommentValidateManager {

    /**
     * 审核评论内容
     *
     * @param content 评论内容
     * @return 审核通过为true
     */
    public boolean checkCommentContent(String content) {
        //TODO 博文评论审核
        return true;
    }

}
