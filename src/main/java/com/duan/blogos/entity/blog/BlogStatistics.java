package com.duan.blogos.entity.blog;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * Created on 2017/12/14.
 * 博文统计信息
 *
 * @author DuanJiaNing
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BlogStatistics implements Serializable {

    private static final long serialVersionUID = 2806945822343341735L;

    // 表id
    private Integer id;

    //对应博文id
    private Integer blogId;

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

    //发布日期
    private Timestamp releaseDate;

}
