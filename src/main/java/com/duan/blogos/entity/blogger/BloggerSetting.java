package com.duan.blogos.entity.blogger;

import lombok.Data;

/**
 * Created on 2018/3/26.
 *
 * @author DuanJiaNing
 */
@Data
public class BloggerSetting {

    // id
    private Integer id;

    // 博主id
    private Integer bloggerId;

    // 博主主页个人信息栏位置，0为左，1为右
    private Integer mainPageNavPos;
}
