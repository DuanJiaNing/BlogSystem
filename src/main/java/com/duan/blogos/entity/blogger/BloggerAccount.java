package com.duan.blogos.entity.blogger;

import lombok.*;

import java.sql.Timestamp;

/**
 * Created on 2017/12/12.
 *
 * @author DuanJiaNing
 */
@Data
public class BloggerAccount {

    private Integer id;
    private Integer profileId;
    private String username;
    private String password;
    private String intro;
    private Timestamp registerDate;

}
