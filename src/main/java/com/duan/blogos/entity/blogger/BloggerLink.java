package com.duan.blogos.entity.blogger;

import lombok.Data;

import java.io.Serializable;

/**
 * Created on 2017/12/12.
 * 博主链接
 *
 * @author DuanJiaNing
 */
@Data
public class BloggerLink implements Serializable {

    private static final long serialVersionUID = -6606102132213390615L;

    // id
    private Integer id;

    //博主id
    private Integer bloggerId;

    //图片id
    private Integer iconId;

    //标题
    private String title;

    //url
    private String url;

    //描述
    private String bewrite;

}
