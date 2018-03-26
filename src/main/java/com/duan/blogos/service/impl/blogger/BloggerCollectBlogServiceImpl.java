package com.duan.blogos.service.impl.blogger;

import com.duan.blogos.common.BlogSortRule;
import com.duan.blogos.dao.blog.*;
import com.duan.blogos.dao.blogger.BloggerAccountDao;
import com.duan.blogos.dao.blogger.BloggerPictureDao;
import com.duan.blogos.dao.blogger.BloggerProfileDao;
import com.duan.blogos.dto.blog.BlogListItemDTO;
import com.duan.blogos.dto.blogger.BloggerDTO;
import com.duan.blogos.dto.blogger.FavouriteBlogListItemDTO;
import com.duan.blogos.entity.blog.*;
import com.duan.blogos.entity.blogger.BloggerAccount;
import com.duan.blogos.entity.blogger.BloggerPicture;
import com.duan.blogos.entity.blogger.BloggerProfile;
import com.duan.blogos.enums.BloggerPictureCategoryEnum;
import com.duan.blogos.manager.DataFillingManager;
import com.duan.blogos.manager.StringConstructorManager;
import com.duan.blogos.manager.comparator.BlogListItemComparatorFactory;
import com.duan.blogos.manager.properties.DbProperties;
import com.duan.blogos.restful.ResultBean;
import com.duan.blogos.service.blogger.BloggerCollectBlogService;
import com.duan.blogos.util.CollectionUtils;
import com.duan.blogos.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created on 2017/12/19.
 *
 * @author DuanJiaNing
 */
@Service
public class BloggerCollectBlogServiceImpl implements BloggerCollectBlogService {

    @Autowired
    private BlogCollectDao collectDao;

    @Autowired
    private BlogStatisticsDao statisticsDao;

    @Autowired
    private BlogCategoryDao categoryDao;

    @Autowired
    private BlogLabelDao labelDao;

    @Autowired
    private BlogDao blogDao;

    @Autowired
    private BloggerAccountDao accountDao;

    @Autowired
    private BloggerProfileDao profileDao;

    @Autowired
    private BloggerPictureDao pictureDao;

    @Autowired
    private DataFillingManager fillingManager;

    @Autowired
    private DbProperties dbProperties;

    @Autowired
    private StringConstructorManager constructorManager;

    @Override
    public ResultBean<List<FavouriteBlogListItemDTO>> listCollectBlog(int bloggerId, int categoryId, int offset, int rows, BlogSortRule sortRule) {
        List<BlogCollect> collects = collectDao.listCollectBlog(bloggerId, categoryId, offset, rows);
        if (CollectionUtils.isEmpty(collects)) return null;

        //排序
        List<BlogStatistics> temp = new ArrayList<>();
        //方便排序后的重组
        Map<Integer, BlogCollect> blogCollectMap = new HashMap<>();
        for (BlogCollect collect : collects) {
            int blogId = collect.getBlogId();
            BlogStatistics statistics = statisticsDao.getStatistics(blogId);
            temp.add(statistics);
            blogCollectMap.put(blogId, collect);
        }
        BlogListItemComparatorFactory factory = new BlogListItemComparatorFactory();
        temp.sort(factory.get(sortRule.getRule(), sortRule.getOrder()));

        //构造结果
        List<FavouriteBlogListItemDTO> result = new ArrayList<>();
        for (BlogStatistics statistics : temp) {
            int blogId = statistics.getBlogId();

            // BlogListItemDTO
            Blog blog = blogDao.getBlogById(blogId);
            String ch = dbProperties.getStringFiledSplitCharacterForNumber();

            // category
            int[] cids = StringUtils.intStringDistinctToArray(blog.getCategoryIds(), ch);
            List<BlogCategory> categories = null;
            if (!CollectionUtils.isEmpty(cids)) {
                categories = categoryDao.listCategoryById(cids);
            }

            // label
            int[] lids = StringUtils.intStringDistinctToArray(blog.getLabelIds(), ch);
            List<BlogLabel> labels = null;
            if (!CollectionUtils.isEmpty(lids)) {
                labels = labelDao.listLabelById(lids);
            }

            BlogListItemDTO listItemDTO = fillingManager.blogListItemToDTO(statistics,
                    CollectionUtils.isEmpty(categories) ? null : categories.toArray(new BlogCategory[categories.size()]),
                    CollectionUtils.isEmpty(labels) ? null : labels.toArray(new BlogLabel[labels.size()]),
                    blog, null);

            // BloggerDTO
            int authorId = blog.getBloggerId();
            BloggerAccount account = accountDao.getAccountById(authorId);
            BloggerProfile profile = profileDao.getProfileByBloggerId(authorId);
            BloggerPicture avatar = profile.getAvatarId() == null ? null :
                    pictureDao.getPictureById(profile.getAvatarId());

            // 使使用默认的博主头像
            if (avatar == null) {
                avatar = new BloggerPicture();
                avatar.setCategory(BloggerPictureCategoryEnum.PUBLIC.getCode());
                avatar.setBloggerId(authorId);
                avatar.setId(-1);
            }

            String url = constructorManager.constructPictureUrl(avatar, BloggerPictureCategoryEnum.DEFAULT_BLOGGER_AVATAR);
            avatar.setPath(url);

            BloggerDTO bloggerDTO = fillingManager.bloggerAccountToDTO(account, profile, avatar);

            // 结果
            BlogCollect collect = blogCollectMap.get(blogId);
            FavouriteBlogListItemDTO dto = fillingManager.collectBlogListItemToDTO(bloggerId, collect, listItemDTO, bloggerDTO);
            result.add(dto);
        }

        return new ResultBean<>(result);
    }

    @Override
    public boolean updateCollect(int bloggerId, int blogId, String newReason, int newCategory) {
        int effect = collectDao.updateByUnique(bloggerId, blogId, newReason, null);
        return effect > 0;
    }

    @Override
    public boolean getCollectState(int bloggerId, int blogId) {
        BlogCollect collect = collectDao.getCollect(bloggerId, blogId);
        return collect != null;
    }

    @Override
    public int countByBloggerId(int bloggerId) {
        return collectDao.countByCollectorId(bloggerId);
    }
}
