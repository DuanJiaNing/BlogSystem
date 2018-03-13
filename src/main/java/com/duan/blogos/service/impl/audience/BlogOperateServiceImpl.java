package com.duan.blogos.service.impl.audience;

import com.duan.blogos.dao.blog.BlogCollectDao;
import com.duan.blogos.dao.blog.BlogComplainDao;
import com.duan.blogos.dao.blog.BlogLikeDao;
import com.duan.blogos.dao.blog.BlogStatisticsDao;
import com.duan.blogos.entity.blog.BlogCollect;
import com.duan.blogos.entity.blog.BlogComplain;
import com.duan.blogos.entity.blog.BlogLike;
import com.duan.blogos.service.audience.BlogOperateService;
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
    private BlogStatisticsDao statisticsDao;

    @Autowired
    private BlogCollectDao collectDao;

    @Autowired
    private BlogLikeDao likeDao;

    @Autowired
    private BlogComplainDao complainDao;

    @Override
    public int insertShare(int blogId, int sharerId) {

        statisticsDao.updateShareCountPlus(blogId);
        Integer count = statisticsDao.getShareCount(blogId);

        return count == null ? -1 : count;
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
