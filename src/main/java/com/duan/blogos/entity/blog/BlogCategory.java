package com.duan.blogos.entity.blog;

import lombok.*;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * Created on 2017/12/12.
 * 博文类别
 *
 * @author DuanJiaNing
 */
@Data
public class BlogCategory implements Serializable {

    private static final long serialVersionUID = -7413640669767387180L;

    //id
    private Integer id;

    //类别所属博主id
    private Integer bloggerId;

    //类别图标
    private Integer iconId;

    //类别标题
    private String title;

    //类别描述
    private String bewrite;

    //类别创建时间
    private Timestamp createDate;

}
