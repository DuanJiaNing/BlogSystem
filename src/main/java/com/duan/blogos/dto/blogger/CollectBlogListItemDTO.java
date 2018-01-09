package com.duan.blogos.dto.blogger;

import com.duan.blogos.dto.blog.BlogListItemDTO;
import com.duan.blogos.dto.blog.BlogMainContentDTO;
import lombok.Data;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * Created on 2017/12/18.
 * 博主收藏的博文清单
 *
 * @author DuanJiaNing
 */
@Data
public class CollectBlogListItemDTO implements Serializable {

    private static final long serialVersionUID = 1348316821909506029L;

    // 记录id
    private int id;

    //博主id
    private int bloggerId;

    // 博文内容
    private BlogListItemDTO blog;

    //作者id
    private BloggerDTO author;

    //收藏的理由
    private String reason;

    //收藏时间
    private Timestamp collectDate;

    //收藏到自己的哪一个类别之下
    private int categoryId;

}
