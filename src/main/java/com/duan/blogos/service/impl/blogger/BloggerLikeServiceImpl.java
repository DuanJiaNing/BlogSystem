package com.duan.blogos.service.impl.blogger;

import com.duan.blogos.dao.blog.BlogLikeDao;
import com.duan.blogos.entity.blog.BlogLike;
import com.duan.blogos.service.blogger.BloggerLikeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created on 2018/3/11.
 *
 * @author DuanJiaNing
 */
@Service
public class BloggerLikeServiceImpl implements BloggerLikeService {

    @Autowired
    private BlogLikeDao likeDao;

    @Override
    public boolean getLikeState(int bloggerId, int blogId) {
        BlogLike like = likeDao.getLike(bloggerId, blogId);
        return like != null;
    }
}
