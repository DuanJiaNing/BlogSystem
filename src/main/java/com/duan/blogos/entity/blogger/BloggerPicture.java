package com.duan.blogos.entity.blogger;

import lombok.Data;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * Created on 2017/12/12.
 * 博主照片
 *
 * @author DuanJiaNing
 */
@Data
public class BloggerPicture implements Serializable {

    private static final long serialVersionUID = -3280469592527512808L;

    //id
    private Integer id;

    //所属博主id
    private Integer bloggerId;

    //描述
    private String bewrite;

    //类别
    private Integer category;

    //保存路径/url
    private String path;

    //名字
    private String title;

    //上传时间
    private Timestamp uploadDate;

    // 图片被引用次数
    private Integer useCount;

}
