package com.duan.blogos.service.impl.blogger.blog;

import com.duan.blogos.entity.blog.BlogLabel;
import com.duan.blogos.result.ResultBean;
import com.duan.blogos.service.blogger.blog.LabelService;
import org.springframework.stereotype.Service;

/**
 * Created on 2017/12/19.
 *
 * @author DuanJiaNing
 */
@Service("labelService")
public class LabelServiceImpl implements LabelService {
    @Override
    public int insertLabel(int bloggerId, String title) {
        return 0;
    }

    @Override
    public boolean updateLabel(int labelId, int newBloggerId, String newTitle) {
        return false;
    }

    @Override
    public BlogLabel deleteLabel(int labelId) {
        return null;
    }

    @Override
    public ResultBean<BlogLabel> listLabel(int bloggerId) {
        return null;
    }
}
