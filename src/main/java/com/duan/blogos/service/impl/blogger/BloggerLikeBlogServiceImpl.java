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
import com.duan.blogos.service.blogger.BloggerLikeBlogService;
import com.duan.blogos.util.CollectionUtils;
import com.duan.blogos.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created on 2018/3/11.
 *
 * @author DuanJiaNing
 */
@Service
public class BloggerLikeBlogServiceImpl implements BloggerLikeBlogService {

    @Autowired
    private BlogLikeDao likeDao;

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
    public boolean getLikeState(int bloggerId, int blogId) {
        BlogLike like = likeDao.getLike(bloggerId, blogId);
        return like != null;
    }

    @Override
    public ResultBean<List<FavouriteBlogListItemDTO>> listLikeBlog(int bloggerId, int offset, int rows, BlogSortRule sortRule) {
        List<BlogLike> likes = likeDao.listLikeBlog(bloggerId, offset, rows);
        if (CollectionUtils.isEmpty(likes)) return null;

        //排序
        List<BlogStatistics> temp = new ArrayList<>();
        //方便排序后的重组
        Map<Integer, BlogLike> blogLikeMap = new HashMap<>();
        for (BlogLike like : likes) {
            int blogId = like.getBlogId();
            BlogStatistics statistics = statisticsDao.getStatistics(blogId);
            temp.add(statistics);
            blogLikeMap.put(blogId, like);
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
            BlogLike like = blogLikeMap.get(blogId);
            FavouriteBlogListItemDTO dto = fillingManager.likeBlogListItemToDTO(bloggerId, like, listItemDTO, bloggerDTO);
            result.add(dto);
        }

        return new ResultBean<>(result);
    }

    @Override
    public int countByBloggerId(int bloggerId) {
        return likeDao.countLikeByLikerId(bloggerId);
    }
}
