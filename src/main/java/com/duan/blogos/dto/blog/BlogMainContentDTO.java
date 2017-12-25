package com.duan.blogos.dto.blog;

import com.duan.blogos.entity.blog.BlogCategory;
import com.duan.blogos.entity.blog.BlogLabel;
import lombok.Data;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * Created on 2017/12/14.
 * 博文主体内容
 *
 * @author DuanJiaNing
 */
@Data
public class BlogMainContentDTO implements Serializable {

    private static final long serialVersionUID = -9215772012725472707L;

    //博文id
    private int id;

    //博文所属类别
    private BlogCategory[] categories;

    //博文包含的标签
    private BlogLabel[] labels;

    //博文标题
    private String title;

    //博文主体内容
    private String content;

    //博文摘要
    private String summary;

    //博文关键字
    private String[] keyWords;

    //首次发布日期
    private Timestamp releaseDate;

    //最后一次修改时间
    private Timestamp nearestModifyDate;

    //文章状态
    private int status;

    //总字数
    private int wordCount;

}
