package com.duan.blogos.service.blogger.blog;

import com.duan.blogos.enums.BlogStatusEnum;

/**
 * Created on 2017/12/18.
 *
 * @author DuanJiaNing
 */
public interface BlogService {

    /**
     * 新增博客
     *
     * @param bloggerId  博主id
     * @param categories 类别
     * @param labels     标签
     * @param status     状态
     * @param title      标题
     * @param content    内容
     * @param summary    摘要
     * @param keyWords   关键字
     * @return 新纪录id
     */
    int insertBlog(int bloggerId, int[] categories, int[] labels, BlogStatusEnum status,
                   String title, String content, String summary, String[] keyWords);

    void updateBlogCategory(int blogId,int[] newCategories);

    void updateBlogLabel(int blogId,int[] newCategories);

    void updateBlogContent(int blogId,String newContent);


}
