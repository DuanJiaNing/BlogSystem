package com.duan.blogos.entity.blog;

import lombok.Data;

import java.io.Serializable;

/**
 * Created on 2017/12/14.
 *
 * @author DuanJiaNing
 */
@Data
public class BlogStatistics implements Serializable {

    private static final long serialVersionUID = 2806945822343341735L;

    // 表id
    private Integer id;

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

    //喜欢次数
    private Integer likeCount;

}
