package com.duan.blogos.service.impl.blogger.blog;

import com.duan.blogos.dao.blog.BlogCategoryDao;
import com.duan.blogos.dto.blogger.BloggerCategoryDTO;
import com.duan.blogos.result.ResultBean;
import com.duan.blogos.service.blogger.blog.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created on 2017/12/19.
 *
 * @author DuanJiaNing
 */
@Service("categoryService")
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private BlogCategoryDao categoryDao;

    @Override
    public ResultBean<List<BloggerCategoryDTO>> listBlogCategory(int bloggerId, int offset, int rows) {
        return null;
    }

    @Override
    public boolean updateBlogCategory(int categoryId, int newBloggerId, String newTitle, String newDesc) {
        return false;
    }

    @Override
    public int insertBlogCategory(int bloggerId, String title, String desc) {
        return 0;
    }

    @Override
    public void deleteCategoryAndBlogsAsWell(int bloggerId, int categoryId) {

    }

    @Override
    public void deleteCategoryAndMoveBlogsTo(int bloggerId, int categoryId, int newBloggerId, int newCategoryId) {

    }

    @Override
    public int countCategoryForExistCheck(int bloggerId, int categoryId) {
        return categoryDao.countCategoryByBloggerIdAndCategoryId(bloggerId, categoryId);
    }
}
