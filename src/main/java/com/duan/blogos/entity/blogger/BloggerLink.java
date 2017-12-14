package com.duan.blogos.entity.blogger;

import lombok.Data;

import java.io.Serializable;

/**
 * Created on 2017/12/12.
 *
 * @author DuanJiaNing
 */
@Data
public class BloggerLink  implements Serializable {

    private static final long serialVersionUID = -6606102132213390615L;
    private Integer id;
    private Integer bloggerId;
    private Integer iconId;
    private String title;
    private String url;
    private String desc;
    private Integer priority;

}
