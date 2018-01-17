package com.duan.blogos.service.audience;

import com.duan.blogos.dto.blog.BlogCommentDTO;
import com.duan.blogos.dto.blog.BlogMainContentDTO;
import com.duan.blogos.result.ResultBean;

import java.util.List;

/**
 * Created on 2017/12/14.
 * 博文浏览服务，该接口定义主要服务于用户点击一篇博文并进入博文浏览页面时博文内容主体以及博文相关数据的获取，以及用户可
 * 对该篇博文执行的操作
 * <p>
 * 1 获得博文主体信息
 * 2 获取博文统计信息
 * 3 获得博文评论列表
 * 4 评论
 * 5 分享
 * 6 赞赏
 * 7 收藏
 * 8 举报
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
    ResultBean<BlogMainContentDTO> getBlogMainContent(int blogId);

    /**
     * 获得博文评论列表，这里获取的是审核通过的评论
     *
     * @param blogId 博文id
     * @param offset 结果集起始位置
     * @param rows   行数
     * @return 查询结果
     */
    ResultBean<List<BlogCommentDTO>> listBlogComment(int blogId, int offset, int rows);

    /**
     * 评论
     * 持久化评论记录，同时博文评论次数加一
     *
     * @param content     评论内容
     * @param spokesmanId 评论者id（只有已注册的博主才能发起评论）
     * @param listenerId  被评论者id
     * @param blogId      被评论文章id
     * @return 操作结果，新增记录id
     */
    ResultBean<Integer> insertComment(String content, int spokesmanId, int listenerId, int blogId);

    /**
     * 分享
     * 博文被分享，分享次数加一
     *
     * @param blogId 博文id
     * @return 操作结果，新增记录id
     */
    ResultBean<Integer> insertShareCountIncrement(int blogId);

    /**
     * 赞赏
     * 持久化赞赏记录，同时博文赞赏次数加一
     *
     * @param blogId   博文id
     * @param paierId  支付者id（若已登录则为登陆者id，为null则为未注册未登录的读者/游客）
     * @param earnerId 博文作者（博主）id
     * @param money    金额
     * @return 操作结果，新增记录id
     */
    ResultBean<Integer> insertAdmire(int blogId, int paierId, int earnerId, float money);

    /**
     * 收藏
     * 持久化文章收藏记录，同时博文收藏次数加一
     *
     * @param blogId     博文id
     * @param cllocterId 收藏者id
     * @param reason     收藏理由
     * @param categoryId 收藏到自己的哪一个类别之下
     * @return 执行结果，新增记录id
     */
    ResultBean<Integer> insertCollect(int blogId, int cllocterId, String reason, int categoryId);

    /**
     * 举报
     * 投诉博文，同时投诉记录加一
     *
     * @param blogId       博文id
     * @param complainerId 投诉者id
     * @param reason       投诉理由
     * @return 执行结果，新增记录id
     */
    ResultBean<Integer> insertComplain(int blogId, int complainerId, String reason);

}
