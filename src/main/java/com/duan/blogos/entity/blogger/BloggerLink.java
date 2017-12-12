package com.duan.blogos.entity.blogger;

import lombok.Data;

/**
 * Created on 2017/12/12.
 *
 * @author DuanJiaNing
 */
@Data
public class BloggerLink {

    private Integer id;
    private Integer bloggerId;
    private Integer iconId;
    private String title;
    private String url;
    private String desc;
    private Integer priority;

}
