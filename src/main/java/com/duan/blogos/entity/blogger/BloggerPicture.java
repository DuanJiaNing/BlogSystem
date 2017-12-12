package com.duan.blogos.entity.blogger;

import lombok.Data;

import java.sql.Timestamp;

/**
 * Created on 2017/12/12.
 *
 * @author DuanJiaNing
 */
@Data
public class BloggerPicture {

    private Integer id;
    private Integer bloggerId;
    private String desc;
    private Integer category;
    private String path;
    private String title;
    private Timestamp uploadDate;

}
