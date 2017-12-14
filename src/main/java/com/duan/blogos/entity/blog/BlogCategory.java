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
public class BlogCategory implements Serializable {

    private static final long serialVersionUID = -7413640669767387180L;
    private Integer id;
    private Integer bloggerId;
    private String title;
    private String desc;
    private Timestamp createDate;

}
