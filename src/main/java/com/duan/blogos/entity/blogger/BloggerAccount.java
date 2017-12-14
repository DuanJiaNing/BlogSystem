package com.duan.blogos.entity.blogger;

import lombok.*;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * Created on 2017/12/12.
 *
 * @author DuanJiaNing
 */
@Data
public class BloggerAccount implements Serializable {

    private static final long serialVersionUID = -1326020643799217215L;

    //id
    private Integer id;

    //个人资料
    private Integer profileId;

    //用户名
    private String username;

    //密码
    private String password;

    //注册时间
    private Timestamp registerDate;

}
