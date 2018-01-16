package com.duan.blogos.dao.blog;

import com.duan.blogos.dao.BaseDao;
import com.duan.blogos.entity.blog.BlogStatistics;
import org.springframework.stereotype.Repository;

/**
 * Created on 2017/12/20.
 *
 * @author DuanJiaNing
 */
@Repository
public interface BlogStatisticsDao extends BaseDao<BlogStatistics> {

    /**
     * 通过唯一约束（博文id）删除记录
     *
     * @param blogId 博文id
     * @return 操作影响行数
     */
    int deleteByUnique(int blogId);

    /**
     * 查询博文的统计信息
     *
     * @param blogId 对应博文id
     * @return 查询结果
     */
    BlogStatistics getStatistics(int blogId);

    /**
     * 评论次数加一
     *
     * @param blogId 博文id
     * @return 执行结果
     */
    int updateCommentCountPlus(int blogId);

    /**
     * 博文浏览次数加一
     *
     * @param blogId 博文id
     * @return 执行结果
     */
    int updateViewCountPlus(int blogId);

    /**
     * 博主回复该博文评论的次数加一
     *
     * @param blogId 博文id
     * @return 执行结果
     */
    int updateReplyCommentCountPlus(int blogId);

    /**
     * 博文被收藏次数加一
     *
     * @param blogId 博文id
     * @return 执行结果
     */
    int updateCollectCountPlus(int blogId);

    /**
     * 博文举报次数加一
     *
     * @param blogId 博文id
     * @return 执行结果
     */
    int updateComplainCountPlus(int blogId);

    /**
     * 博文被分享次数加一
     *
     * @param blogId 博文id
     * @return 执行结果
     */
    int updateShareCountPlus(int blogId);

    /**
     * 赞赏次数加一
     *
     * @param blogId 博文id
     * @return 执行结果
     */
    int updateAdmireCountPlus(int blogId);

    /**
     * 喜欢次数加一
     *
     * @param blogId 博文id
     * @return 执行结果
     */
    int updateLikeCountPlus(int blogId);

    /**
     * 评论次数减一
     *
     * @param blogId 博文id
     * @return 执行结果
     */
    int updateCommentCountMinus(int blogId);

    /**
     * 博文浏览次数减一
     *
     * @param blogId 博文id
     * @return 执行结果
     */
    int updateViewCountMinus(int blogId);

    /**
     * 博主回复该博文评论的次数减一
     *
     * @param blogId 博文id
     * @return 执行结果
     */
    int updateReplyCommentCountMinus(int blogId);

    /**
     * 博文被收藏次数减一
     *
     * @param blogId 博文id
     * @return 执行结果
     */
    int updateCollectCountMinus(int blogId);

    /**
     * 博文举报次数减一
     *
     * @param blogId 博文id
     * @return 执行结果
     */
    int updateComplainCountMinus(int blogId);

    /**
     * 博文被分享次数减一
     *
     * @param blogId 博文id
     * @return 执行结果
     */
    int updateShareCountMinus(int blogId);

    /**
     * 赞赏次数减一
     *
     * @param blogId 博文id
     * @return 执行结果
     */
    int updateAdmireCountMinus(int blogId);

    /**
     * 喜欢次数减一
     *
     * @param blogId 博文id
     * @return 执行结果
     */
    int updateLikeCountMinus(int blogId);


    /**
     * 查询评论次数
     *
     * @param blogId 博文id
     * @return 查询结果
     */
    Integer getCommentCount(int blogId);

    /**
     * 查询博文浏览次数
     *
     * @param blogId 博文id
     * @return 查询结果
     */
    Integer getViewCount(int blogId);

    /**
     * 查询博主回复该博文评论的次数
     *
     * @param blogId 博文id
     * @return 查询结果
     */
    Integer getReplyCommentCount(int blogId);

    /**
     * 查询博文被收藏次数
     *
     * @param blogId 博文id
     * @return 查询结果
     */
    Integer getCollectCount(int blogId);

    /**
     * 查询博文举报次数
     *
     * @param blogId 博文id
     * @return 查询结果
     */
    Integer getComplainCount(int blogId);

    /**
     * 查询博文被分享次数
     *
     * @param blogId 博文id
     * @return 查询结果
     */
    Integer getShareCount(int blogId);

    /**
     * 查询赞赏次数
     *
     * @param blogId 博文id
     * @return 查询结果
     */
    Integer getAdmireCount(int blogId);

    /**
     * 查询喜欢次数
     *
     * @param blogId 博文id
     * @return 查询结果
     */
    Integer getLikeCount(int blogId);
}
