package com.duan.blogos.dto.blog;

import com.duan.blogos.entity.blog.BlogCategory;
import com.duan.blogos.entity.blog.BlogLabel;
import lombok.Data;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * Created on 2017/12/16.
 * 博文列表项
 * 博主的博文以列表项的形式将每一篇博文的主要信息展现给读者
 *
 * @author DuanJiaNing
 */
@Data
public class BlogListItemDTO implements Serializable {

    private static final long serialVersionUID = -2446239587924752480L;

    //博文id
    private int id;

    //博文所属类别
    private BlogCategory[] categories;

    //博文标签
    private BlogLabel[] labels;

    //博文标题
    private String title;

    //博文图片
    private String img;

    //博文摘要
    private String summary;

    //首次发布日期
    private Timestamp releaseDate;

    //评论次数
    private int commentCount;

    //博文浏览次数
    private int viewCount;

    //博文被收藏次数
    private int collectCount;

    //喜欢次数
    private int likeCount;

}
