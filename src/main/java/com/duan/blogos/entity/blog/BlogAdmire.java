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
    private Integer id;
    private Integer blogId;
    private Integer paierId;
    private Double money;
    private Timestamp tranDate;

}
