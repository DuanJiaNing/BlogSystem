package com.duan.blogos.service.impl.blogger.blog;

import com.duan.blogos.dao.blog.BlogCategoryDao;
import com.duan.blogos.dao.blog.BlogDao;
import com.duan.blogos.dao.blogger.BloggerPictureDao;
import com.duan.blogos.dto.blogger.BloggerCategoryDTO;
import com.duan.blogos.entity.blog.Blog;
import com.duan.blogos.entity.blog.BlogCategory;
import com.duan.blogos.entity.blogger.BloggerPicture;
import com.duan.blogos.exception.BaseRuntimeException;
import com.duan.blogos.manager.DataFillingManager;
import com.duan.blogos.manager.DbPropertiesManager;
import com.duan.blogos.result.ResultBean;
import com.duan.blogos.service.blogger.blog.CategoryService;
import com.duan.blogos.util.ArrayUtils;
import com.duan.blogos.util.CollectionUtils;
import com.duan.blogos.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created on 2017/12/19.
 *
 * @author DuanJiaNing
 */
@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private BlogCategoryDao categoryDao;

    @Autowired
    private DataFillingManager fillingManager;

    @Autowired
    private DbPropertiesManager dbPropertiesManager;

    @Autowired
    private BloggerPictureDao pictureDao;

    @Autowired
    private BlogDao blogDao;

    @Override
    public ResultBean<List<BloggerCategoryDTO>> listBlogCategory(int bloggerId, int offset, int rows) {

        List<BlogCategory> categories = categoryDao.listCategoryByBloggerId(bloggerId, offset, rows);
        if (CollectionUtils.isEmpty(categories)) return null;

        List<BloggerCategoryDTO> result = new ArrayList<>();
        for (BlogCategory category : categories) {
            Integer iconId = category.getIconId();
            BloggerPicture icon = iconId == null ? null : pictureDao.getPictureById(iconId);
            BloggerCategoryDTO dto = fillingManager.blogCategoryToDTO(category, icon);
            result.add(dto);
        }

        return new ResultBean<>(result);
    }

    @Override
    public boolean updateBlogCategory(int categoryId, int newIconId, String newTitle, String newBewrite) {

        BlogCategory category = new BlogCategory();
        if (!StringUtils.isEmpty(newTitle)) category.setTitle(newTitle);
        if (!StringUtils.isEmpty(newBewrite)) category.setBewrite(newBewrite);
        if (newIconId > 0) category.setIconId(newIconId);
        category.setId(categoryId);

        int effect = categoryDao.update(category);
        if (effect <= 0) return false;

        return true;
    }

    @Override
    public int insertBlogCategory(int bloggerId, int iconId, String title, String bewrite) {

        BlogCategory category = new BlogCategory();
        category.setBewrite(bewrite);
        if (iconId > 0) category.setIconId(iconId);
        category.setBloggerId(bloggerId);
        category.setTitle(title);
        int effect = categoryDao.insert(category);
        if (effect <= 0) return -1;

        return category.getId();
    }

    @Override
    public boolean deleteCategoryAndBlogsAsWell(int bloggerId, int categoryId) {

        return false;
    }

    @Override
    public boolean deleteCategoryAndMoveBlogsTo(int bloggerId, int categoryId, int newCategoryId) {

        // 删除数据库类别记录
        int effectDelete = categoryDao.delete(categoryId);
        if (effectDelete <= 0) throw new BaseRuntimeException("delete blog category fail");

        // 修改博文类别
        List<Blog> blogs = blogDao.listAllCategoryByBloggerId(bloggerId);
        String sp = dbPropertiesManager.getStringFiledSplitCharacterForNumber();
        for (Blog blog : blogs) {

            int[] cids = StringUtils.intStringDistinctToArray(blog.getCategoryIds(), sp);
            ArrayUtils.replace(cids, categoryId, newCategoryId);
            blog.setCategoryIds(StringUtils.intArrayToString(cids, sp));
            int effectUpdate = blogDao.update(blog);
            if (effectUpdate <= 0) throw new BaseRuntimeException("update blog category fail");

        }

        return true;
    }

    @Override
    public int countCategoryForExistCheck(int bloggerId, int categoryId) {
        return categoryDao.countCategoryByBloggerIdAndCategoryId(bloggerId, categoryId);
    }

    @Override
    public BloggerCategoryDTO getCategory(int bloggerId, int categoryId) {

        BlogCategory category = categoryDao.getCategory(bloggerId, categoryId);
        Integer iconId = category.getIconId();
        BloggerPicture icon = iconId == null ? null : pictureDao.getPictureById(iconId);
        return fillingManager.blogCategoryToDTO(category, icon);

    }
}
