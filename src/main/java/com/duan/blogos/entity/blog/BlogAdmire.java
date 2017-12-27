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
public class BlogAdmire implements Serializable {

    private static final long serialVersionUID = 4120356662820230266L;

    //id
    private Integer id;

    //博文id
    private Integer blogId;

    //赞赏者id
    private Integer paierId;

    //金额
    private Float money;

    //赞赏时间
    private Timestamp tranDate;

}
