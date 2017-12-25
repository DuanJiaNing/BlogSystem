package com.duan.blogos.entity.blog;

import lombok.Data;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * Created on 2017/12/12.
 *
 * @author DuanJiaNing
 */
@Data
public class BlogComment implements Serializable {

    private static final long serialVersionUID = -7031768607524908823L;

    //id
    private Integer id;

    //博文id
    private Integer blogId;

    //评论者id
    private Integer spokesmanId;

    //被评论者id
    private Integer listenerId;

    //评论内容
    private String content;

    //评论时间
    private Timestamp releaseDate;

    //评论状态
    private Integer state;

}
