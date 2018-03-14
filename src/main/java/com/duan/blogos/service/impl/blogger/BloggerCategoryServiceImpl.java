package com.duan.blogos.service.impl.blogger;

import com.duan.blogos.dao.blog.BlogCategoryDao;
import com.duan.blogos.dao.blog.BlogDao;
import com.duan.blogos.dao.blogger.BloggerPictureDao;
import com.duan.blogos.dto.blogger.BloggerCategoryDTO;
import com.duan.blogos.entity.blog.Blog;
import com.duan.blogos.entity.blog.BlogCategory;
import com.duan.blogos.entity.blogger.BloggerPicture;
import com.duan.blogos.enums.BlogStatusEnum;
import com.duan.blogos.exception.internal.SQLException;
import com.duan.blogos.manager.*;
import com.duan.blogos.manager.properties.BloggerProperties;
import com.duan.blogos.manager.properties.DbProperties;
import com.duan.blogos.restful.ResultBean;
import com.duan.blogos.service.blogger.BloggerCategoryService;
import com.duan.blogos.util.ArrayUtils;
import com.duan.blogos.util.CollectionUtils;
import com.duan.blogos.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static com.duan.blogos.enums.BloggerPictureCategoryEnum.DEFAULT_BLOGGER_BLOG_CATEGORY_ICON;

/**
 * Created on 2017/12/19.
 *
 * @author DuanJiaNing
 */
@Service
public class BloggerCategoryServiceImpl implements BloggerCategoryService {

    @Autowired
    private BlogCategoryDao categoryDao;

    @Autowired
    private DataFillingManager fillingManager;

    @Autowired
    private DbProperties dbProperties;

    @Autowired
    private StringConstructorManager constructorManager;

    @Autowired
    private BloggerProperties bloggerProperties;

    @Autowired
    private ImageManager imageManager;

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
            result.add(getBloggerCategoryDTO(bloggerId, category));
        }

        return new ResultBean<>(result);
    }

    @Override
    public boolean updateBlogCategory(int bloggerId, int categoryId, int newIconId, String newTitle,
                                      String newBewrite) {

        BlogCategory category = categoryDao.getCategory(bloggerId, categoryId);
        Integer oldIconId = category.getIconId();
        if (!StringUtils.isEmpty(newTitle)) category.setTitle(newTitle);
        if (!StringUtils.isEmpty(newBewrite)) category.setBewrite(newBewrite);
        if (newIconId > 0) category.setIconId(newIconId);
        category.setId(categoryId);
        int effect = categoryDao.update(category);
        if (effect <= 0) return false;

        // 修改图片可见性，引用次数
        imageManager.imageUpdateHandle(bloggerId, newIconId, oldIconId);

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

        // 修改图片可见性，引用次数
        imageManager.imageInsertHandle(bloggerId, iconId);

        return category.getId();
    }

    @Override
    public boolean deleteCategoryAndMoveBlogsTo(int bloggerId, int categoryId, int newCategoryId) {

        BlogCategory category = categoryDao.getCategory(bloggerId, categoryId);
        if (category == null) return false;

        // 图片引用次数--
        Integer iconId;
        if ((iconId = category.getIconId()) != null && pictureDao.getUseCount(iconId) > 0) {
            pictureDao.updateUseCountMinus(iconId);
        }

        // 删除数据库类别记录
        int effectDelete = categoryDao.delete(categoryId);
        if (effectDelete <= 0) throw new SQLException();

        // 修改博文类别
        List<Blog> blogs = blogDao.listAllCategoryByBloggerId(bloggerId);
        String sp = dbProperties.getStringFiledSplitCharacterForNumber();

        // 移除类别即可
        if (newCategoryId <= 0) {
            blogs.forEach(blog -> {

                int[] cids = StringUtils.intStringDistinctToArray(blog.getCategoryIds(), sp);
                if (CollectionUtils.intArrayContain(cids, categoryId)) {
                    int[] ar = ArrayUtils.removeFromArray(cids, categoryId);
                    blog.setCategoryIds(StringUtils.intArrayToString(ar, sp));
                    int effectUpdate = blogDao.update(blog);
                    if (effectUpdate <= 0) throw new SQLException();
                }

            });

        } else { // 替换类别
            blogs.forEach(blog -> {

                int[] cids = StringUtils.intStringDistinctToArray(blog.getCategoryIds(), sp);
                if (CollectionUtils.intArrayContain(cids, categoryId)) {
                    ArrayUtils.replace(cids, categoryId, newCategoryId);
                    blog.setCategoryIds(StringUtils.intArrayToString(cids, sp));
                    int effectUpdate = blogDao.update(blog);
                    if (effectUpdate <= 0) throw new SQLException();
                }
            });

        }

        return true;
    }

    @Override
    public BloggerCategoryDTO getCategory(int bloggerId, int categoryId) {
        return getBloggerCategoryDTO(bloggerId, categoryDao.getCategory(bloggerId, categoryId));
    }

    // 获得单个类别
    private BloggerCategoryDTO getBloggerCategoryDTO(int bloggerId, BlogCategory category) {

        Integer iconId = category.getIconId();

        BloggerPicture icon;
        if (iconId == null) {
            // 默认图片
            int pictureManagerId = bloggerProperties.getPictureManagerBloggerId();
            icon = pictureDao.getBloggerUniquePicture(pictureManagerId, DEFAULT_BLOGGER_BLOG_CATEGORY_ICON.getCode());
        } else {
            icon = pictureDao.getPictureById(iconId);
        }

        if (icon != null)
            icon.setPath(constructorManager.constructPictureUrl(icon, DEFAULT_BLOGGER_BLOG_CATEGORY_ICON));

        int count = blogDao.countBlogByCategory(bloggerId, category.getId(), BlogStatusEnum.PUBLIC.getCode());
        return fillingManager.blogCategoryToDTO(category, icon, count);
    }

}
