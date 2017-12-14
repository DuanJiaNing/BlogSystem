package com.duan.blogos.entity.blog;

import lombok.Data;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * Created on 2017/12/12.
 *
 * @author DuanJiaNing
 */
@Data
public class BlogComment implements Serializable {

    private static final long serialVersionUID = -7031768607524908823L;
    private Integer id;
    private Integer blogId;
    private Integer spokesmanId;
    private Integer listenerId;
    private String content;
    private Timestamp releaseDate;
    private Integer state;

}
