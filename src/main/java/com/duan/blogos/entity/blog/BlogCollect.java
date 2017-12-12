package com.duan.blogos.entity.blog;

import lombok.*;

import java.sql.Timestamp;

/**
 * Created on 2017/12/12.
 *
 * @author DuanJiaNing
 */
@Data
public class BlogCollect {

    private Integer id;
    private Integer blogId;
    private Integer bloggerId;
    private String reason;
    private Timestamp collectDate;

}
