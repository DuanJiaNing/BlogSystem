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
    private Integer id;
    private Integer blogId;
    private Integer bloggerId;
    private String reason;
    private Timestamp collectDate;

}
