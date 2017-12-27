package com.duan.blogos.service.impl.audience;

import com.duan.blogos.dao.blog.*;
import com.duan.blogos.entity.blog.*;
import com.duan.blogos.enums.BlogCommentStatusEnum;
import com.duan.blogos.manager.validate.BlogCommentValidateManager;
import com.duan.blogos.service.audience.BlogOperateService;
import com.duan.blogos.util.CollectionUtils;
import com.duan.blogos.util.StringUtils;
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
    private BlogAdmireDao admireDao;

    @Autowired
    private BlogCollectDao collectDao;

    @Autowired
    private BlogLikeDao likeDao;

    @Autowired
    private BlogComplainDao complainDao;

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
        Integer id = comment.getId();

        return id == null ? -1 : id;
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

        BlogAdmire admire = new BlogAdmire();
        admire.setBlogId(blogId);
        admire.setMoney(money);
        admire.setPaierId(paierId);
        admireDao.insert(admire);

        //博文赞赏次数加一
        statisticsDao.updateAdmireCountPlus(blogId);

        Integer id = admire.getId();
        return id == null ? -1 : id;
    }

    @Override
    public int insertCollect(int blogId, int collectorId, String reason, int categoryId) {

        BlogCollect collect = new BlogCollect();
        collect.setBlogId(blogId);
        collect.setCategoryId(categoryId < 0 ? null : categoryId);
        collect.setCollectorId(collectorId);
        collect.setReason(StringUtils.isEmpty(reason) ? null : reason);
        collectDao.insert(collect);

        //博文收藏次数加一
        statisticsDao.updateCollectCountPlus(blogId);

        Integer id = collect.getId();
        return id == null ? -1 : id;
    }

    @Override
    public int insertLike(int blogId, int likerId) {

        BlogLike like = new BlogLike();
        like.setBlogId(blogId);
        like.setLikerId(likerId);
        likeDao.insert(like);

        //博文喜欢次数加一
        statisticsDao.updateLikeCountPlus(blogId);

        Integer count = statisticsDao.getLikeCount(blogId);
        return count == null ? -1 : count;
    }

    @Override
    public int insertComplain(int blogId, int complainId, String content) {

        BlogComplain complain = new BlogComplain();
        complain.setBlogId(blogId);
        complain.setComplainerId(complainId);
        complain.setContent(content);
        complainDao.insert(complain);

        //博文投诉次数加一
        statisticsDao.updateComplainCountPlus(blogId);

        Integer id = complain.getId();
        return id == null ? -1 : id;
    }

    @Override
    public boolean deleteCollect(int bloggerId, int blogId) {
        int effect = collectDao.deleteCollectByBloggerId(bloggerId, blogId);
        if (effect <= 0) return false;

        //博文收藏数减一
        statisticsDao.updateCollectCountMinus(blogId);

        return true;
    }

    @Override
    public boolean deleteLike(int likerId, int blogId) {
        int effect = likeDao.deleteLikeByBloggerId(likerId, blogId);
        if (effect <= 0) return false;

        //博文喜欢数减一
        statisticsDao.updateLikeCountMinus(blogId);

        return true;
    }


}
