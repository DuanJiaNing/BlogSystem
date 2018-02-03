package com.duan.blogos.dto.blogger;

import com.duan.blogos.entity.blog.BlogCategory;
import lombok.Data;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * Created on 2017/12/19.
 * 博主博文列表项，用于博主查看自己发布的文章列表
 *
 * @author DuanJiaNing
 */
@Data
public class BlogListItemDTO implements Serializable {

    private static final long serialVersionUID = 2159619552751683259L;

    //博文id
    private int id;

    //博文所属类别
    private BlogCategory[] categories;

    //状态
    private int state;

    //博文标题
    private String title;

    //博文摘要
    private String summary;

    //首次发布日期
    private Timestamp releaseDate;

    //最近修改日期
    private Timestamp nearestModifyDate;

    //评论次数
    private int commentCount;

    //博文浏览次数
    private int viewCount;

    //博文被收藏次数
    private int collectCount;

    //喜欢次数
    private int likeCount;

    //字数
    private int wordCount;

    //投诉次数
    private int complainCount;

}
