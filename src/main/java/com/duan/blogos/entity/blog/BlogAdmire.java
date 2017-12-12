package com.duan.blogos.entity.blog;

import lombok.*;

import java.sql.Timestamp;

/**
 * Created on 2017/12/12.
 *
 * @author DuanJiaNing
 */
@Data
public class BlogAdmire {

    private Integer id;
    private Integer blogId;
    private Integer paierId;
    private Integer earnerId;
    private Double money;
    private Timestamp tranDate;

}
