package com.duan.blogos.service.audience;

import com.duan.blogos.dto.blog.BlogComment;
import com.duan.blogos.dto.blog.BlogMainContent;
import com.duan.blogos.dto.blogger.Blogger;
import com.duan.blogos.entity.blog.BlogStatistics;
import com.duan.blogos.result.ResultBean;

import java.util.List;

/**
 * Created on 2017/12/14.
 * 博文浏览服务
 *
 * @author DuanJiaNing
 */
public interface BlogBrowseService {

    /**
     * 获得博文主体信息
     *
     * @param blogId 博文id
     * @return 查询结果
     */
    ResultBean<BlogMainContent> getBlogMainContent(int blogId);

    /**
     * 获取博文统计信息
     *
     * @param blogId 博文id
     * @return 查询结果
     */
    ResultBean<BlogStatistics> getBlogStatistics(int blogId);

    /**
     * 获取博主信息
     *
     * @param blogId 博文id
     * @return 查询结果
     */
    ResultBean<Blogger> getBlogger(int blogId);

    /**
     * 获得博文评论列表
     *
     * @param blogId 博文id
     * @return 查询结果
     */
    ResultBean<List<BlogComment>> listBlogComment(int blogId);

    /**
     * 持久化评论记录，同时博文评论次数加一
     *
     * @param content     评论内容
     * @param spokesmanId 评论者id（只有已注册的博主才能发起评论）
     * @param listenerId  被评论者id
     * @param blogId      被评论文章id
     * @return 操作结果
     */
    ResultBean<Void> insertComment(String content, int spokesmanId, int listenerId, int blogId);

    /**
     * 博文被分享，分享次数加一
     *
     * @param blogId 博文id
     * @return 操作结果
     */
    ResultBean<Void> insertShareCountIncrement(int blogId);

    /**
     * 持久化赞赏记录，同时博文赞赏次数加一
     *
     * @param blogId   博文id
     * @param paierId  支付者id（若已登录则为登陆者id，为null则为未注册未登录的读者/游客）
     * @param earnerId 博文作者（博主）id
     * @param money    金额
     * @return 操作结果
     */
    ResultBean<Void> insertAdmire(int blogId, int paierId, int earnerId, float money);

    /**
     * 持久化文章收藏记录，同时博文收藏次数加一
     *
     * @param blogId     博文id
     * @param cllocterId 收藏者id
     * @param reason     收藏理由
     * @param categoryId 收藏到自己的哪一个类别之下
     * @return 执行结果
     */
    ResultBean<Void> insertCollect(int blogId, int cllocterId, String reason, int categoryId);

    /**
     * 投诉博文，同时投诉记录加一
     *
     * @param blogId       博文id
     * @param complainerId 投诉者id
     * @param reason       投诉理由
     * @return 执行结果
     */
    ResultBean<Void> insertComplain(int blogId, int complainerId, String reason);

}
