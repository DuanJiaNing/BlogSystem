package com.duan.blogos.entity.blog;

import lombok.*;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * Created on 2017/12/12.
 *
 * @author DuanJiaNing
 */
@Data
public class BlogCollect implements Serializable {

    private static final long serialVersionUID = 2508868745231893082L;

    // 记录id
    private Integer id;

    // 博文id
    private Integer blogId;

    //收藏者id
    private Integer bloggerId;

    //作者id
    private Integer authorId;

    //收藏的理由
    private String reason;

    //收藏时间
    private Timestamp collectDate;

    //收藏到自己的哪一个类别之下
    private Integer categoryId;

}
