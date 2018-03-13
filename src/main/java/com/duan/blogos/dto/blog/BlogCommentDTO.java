package com.duan.blogos.dto.blog;

import com.duan.blogos.dto.blogger.BloggerDTO;
import lombok.Data;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * Created on 2017/12/14.
 * 博文评论
 *
 * @author DuanJiaNing
 */
@Data
public class BlogCommentDTO implements Serializable {

    private static final long serialVersionUID = 7217522025154588809L;

    // 评论id
    private int id;

    // 博文id
    private int blogId;

    // 评论者
    private BloggerDTO spokesman;

    // 被评论者
//    private BloggerDTO listener;

    // 评论内容
    private String content;

    // 评论时间
    private Timestamp releaseDate;

    // 评论状态
    private int state;

}
