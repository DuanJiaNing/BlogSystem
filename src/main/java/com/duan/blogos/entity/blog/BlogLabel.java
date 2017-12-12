package com.duan.blogos.entity.blog;

import lombok.Data;

/**
 * Created on 2017/12/12.
 *
 * @author DuanJiaNing
 */
@Data
public class BlogLabel {

    private Integer id;
    private Integer bloggerId;
    private String title;
    private java.sql.Timestamp createDate;

}
