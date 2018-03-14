package com.duan.blogos.dto.blogger;

import lombok.Data;

import java.io.Serializable;

/**
 * Created on 2017/12/15.
 * 博主信息统计
 *
 * @author DuanJiaNing
 */
@Data
public class BloggerStatisticsDTO implements Serializable {

    private static final long serialVersionUID = 7340323568288854227L;

    //发表的博文数
    private int blogCount;

    //总字数
    private long wordCount;

    //收获的喜欢数
    private long likeCount;

    //送出的喜欢数
    private long likedCount;

    //创建的博文类别数
    private int categoryCount;

    //创建的标签数
    private int labelCount;

    //收藏的博文数
    private int collectCount;

    //文章被收藏数
    private int collectedCount;

    //链接数量
    private int linkCount;
}
