package com.duan.blogos.service.impl.audience;

import com.duan.blogos.service.audience.BlogOperateService;
import org.springframework.stereotype.Service;

/**
 * Created on 2017/12/26.
 *
 * @author DuanJiaNing
 */
@Service
public class BlogOperateServiceImpl implements BlogOperateService {

    @Override
    public int insertComment(int blogId, int spokesmanId, int listenerId, String content) {
        return 0;
    }

    @Override
    public int insertShare(int blogId, int sharerId) {
        return 0;
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
