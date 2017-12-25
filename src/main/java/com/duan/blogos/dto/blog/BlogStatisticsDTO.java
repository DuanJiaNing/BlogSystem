package com.duan.blogos.dto.blog;

import lombok.Data;

import java.io.Serializable;

/**
 * Created on 2017/12/25.
 *
 * @author DuanJiaNing
 */
@Data
public class BlogStatisticsDTO implements Serializable {

    private static final long serialVersionUID = -6010225954839747692L;

    // 表id
    private int id;

    //对应博文id
    private int blogId;

    //评论次数
    private int commentCount;

    //博文浏览次数
    private int viewCount;

    //博主回复该博文评论的次数
    private int replyCommentCount;

    //博文被收藏次数
    private int collectCount;

    //博文举报次数
    private int complainCount;

    //博文被分享次数
    private int shareCount;

    //赞赏次数
    private int admireCount;

    //喜欢次数
    private int likeCount;


}
