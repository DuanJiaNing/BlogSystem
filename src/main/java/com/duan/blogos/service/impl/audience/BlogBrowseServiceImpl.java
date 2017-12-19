package com.duan.blogos.service.impl.audience;

import com.duan.blogos.dto.blog.BlogCommentDTO;
import com.duan.blogos.dto.blog.BlogMainContentDTO;
import com.duan.blogos.entity.blog.BlogStatistics;
import com.duan.blogos.result.ResultBean;
import com.duan.blogos.service.audience.BlogBrowseService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created on 2017/12/19.
 *
 * @author DuanJiaNing
 */
@Service("blogBrowseService")
public class BlogBrowseServiceImpl implements BlogBrowseService {

    @Override
    public ResultBean<BlogMainContentDTO> getBlogMainContent(int blogId) {
        return null;
    }

    @Override
    public ResultBean<BlogStatistics> getBlogStatistics(int blogId) {
        return null;
    }

    @Override
    public ResultBean<List<BlogCommentDTO>> listBlogComment(int blogId, int offset, int rows) {
        return null;
    }

    @Override
    public ResultBean<Integer> insertComment(String content, int spokesmanId, int listenerId, int blogId) {
        return null;
    }

    @Override
    public ResultBean<Integer> insertShareCountIncrement(int blogId) {
        return null;
    }

    @Override
    public ResultBean<Integer> insertAdmire(int blogId, int paierId, int earnerId, float money) {
        return null;
    }

    @Override
    public ResultBean<Integer> insertCollect(int blogId, int cllocterId, String reason, int categoryId) {
        return null;
    }

    @Override
    public ResultBean<Integer> insertComplain(int blogId, int complainerId, String reason) {
        return null;
    }
}
