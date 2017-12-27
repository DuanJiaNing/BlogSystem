package com.duan.blogos.service.impl.audience;

import com.duan.blogos.dao.blog.BlogCommentDao;
import com.duan.blogos.dao.blog.BlogStatisticsDao;
import com.duan.blogos.entity.blog.BlogComment;
import com.duan.blogos.enums.BlogCommentStatusEnum;
import com.duan.blogos.manager.validate.BlogCommentValidateManager;
import com.duan.blogos.service.audience.BlogOperateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created on 2017/12/26.
 *
 * @author DuanJiaNing
 */
@Service
public class BlogOperateServiceImpl implements BlogOperateService {

    @Autowired
    private BlogCommentDao commentDao;

    @Autowired
    private BlogStatisticsDao statisticsDao;

    @Autowired
    private BlogCommentValidateManager commentValidateManager;

    @Override
    public int insertComment(int blogId, int spokesmanId, int listenerId, String content) {

        // 审核评论
        if (!commentValidateManager.checkCommentContent(content)) return -1;

        BlogComment comment = new BlogComment();
        comment.setBlogId(blogId);
        comment.setContent(content);
        comment.setListenerId(listenerId);
        comment.setSpokesmanId(spokesmanId);
        comment.setState(BlogCommentStatusEnum.RIGHTFUL.getCode());
        commentDao.insert(comment);

        //博文评论次数加一
        statisticsDao.updateCommentCountPlus(blogId);

        //新纪录的id没有用到的场景，不予查询返回
        return 1;
    }

    @Override
    public int insertShare(int blogId, int sharerId) {

        // UPDATE: 2017/12/27 对分享者的操作做记录以保留其账户活跃度 etc

        statisticsDao.updateShareCountPlus(blogId);
        Integer count = statisticsDao.getShareCount(blogId);

        return count == null ? -1 : count;
    }

    @Override
    public int insertAdmire(int blogId, int paierId, float money) {
        return 0;
    }

    @Override
    public int insertCollect(int blogId, int collectorId, String reason, int categoryId) {
        return 0;
    }

    @Override
    public void deleteCollect(int bloggerId, int blogId) {

    }

    @Override
    public int insertLike(int blogId, int likerId) {
        return 0;
    }

    @Override
    public void deleteLike(int likerId, int blogId) {

    }

    @Override
    public int insertComplain(int blogId, int complainId, String content) {
        return 0;
    }

}
