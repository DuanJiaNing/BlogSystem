package com.duan.blogos.dto.blogger;

import com.duan.blogos.entity.blogger.BloggerPicture;
import lombok.Data;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * Created on 2017/12/15.
 * 博主创建的类别
 *
 * @author DuanJiaNing
 */
@Data
public class BloggerCategoryDTO implements Serializable {

    private static final long serialVersionUID = -7413640669767387180L;

    //id
    private int id;

    //类别所属博主id
    private int bloggerId;

    // 类别对应的博文数量
    private int count;

    //类别标题
    private String title;

    //类别描述
    private String bewrite;

    //类别创建时间
    private Timestamp createDate;

    //类别对应图标
    private BloggerPicture icon;

}
