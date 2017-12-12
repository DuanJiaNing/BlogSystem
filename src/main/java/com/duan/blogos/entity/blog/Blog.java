package com.duan.blogos.entity.blog;

import lombok.*;

import java.sql.Timestamp;

/**
 * Created on 2017/12/12.
 *
 * @author DuanJiaNing
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Blog {


    //博文id
    private Integer id;

    //博文所属博主id
    private Integer bloggerId;

    //博文所属类别id
    private String categoryIds;

    //博文包含的标签
    private String labelIds;

    //文章状态
    private Integer state;

    //博文标题
    private String title;

    //博文主体内容
    private String content;

    //博文摘要
    private String summary;

    //首次发布日期
    private Timestamp releaseDate;

    //最后一次修改时间
    private Timestamp nearestModifyDate;

    //博文关键字
    private String keyWords;

    //评论次数
    private Integer commentCount;

    //博文浏览次数
    private Integer viewCount;

    //博主回复该博文评论的次数
    private Integer replyCommentCount;

    //博文被收藏次数
    private Integer collectCount;

    //博文举报次数
    private Integer complainCount;

    //博文被分享次数
    private Integer shareCount;

    //赞赏次数
    private Integer admireCount;
}
