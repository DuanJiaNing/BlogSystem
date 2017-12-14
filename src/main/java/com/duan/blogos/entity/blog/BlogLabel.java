package com.duan.blogos.entity.blog;

import lombok.Data;

import java.io.Serializable;

/**
 * Created on 2017/12/12.
 *
 * @author DuanJiaNing
 */
@Data
public class BlogLabel implements Serializable {

    private static final long serialVersionUID = 4565919455090875775L;
    private Integer id;
    private Integer bloggerId;
    private String title;
    private java.sql.Timestamp createDate;

}
