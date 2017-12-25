package com.duan.blogos.service.impl.audience;

import com.duan.blogos.dao.blog.*;
import com.duan.blogos.dao.blogger.BloggerAccountDao;
import com.duan.blogos.dao.blogger.BloggerPictureDao;
import com.duan.blogos.dao.blogger.BloggerProfileDao;
import com.duan.blogos.dto.blog.BlogCommentDTO;
import com.duan.blogos.dto.blog.BlogMainContentDTO;
import com.duan.blogos.dto.blog.BlogStatisticsDTO;
import com.duan.blogos.dto.blogger.BloggerDTO;
import com.duan.blogos.entity.blog.*;
import com.duan.blogos.entity.blogger.BloggerAccount;
import com.duan.blogos.entity.blogger.BloggerPicture;
import com.duan.blogos.entity.blogger.BloggerProfile;
import com.duan.blogos.enums.BlogCommentStatusEnum;
import com.duan.blogos.enums.BloggerPictureCategoryEnum;
import com.duan.blogos.manager.DataFillingManager;
import com.duan.blogos.manager.DbPropertiesManager;
import com.duan.blogos.result.ResultBean;
import com.duan.blogos.service.audience.BlogBrowseService;
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
public class BlogBrowseServiceImpl implements BlogBrowseService {

    @Autowired
    private BlogDao blogDao;

    @Autowired
    private BlogCategoryDao categoryDao;

    @Autowired
    private BlogLabelDao labelDao;

    @Autowired
    private DbPropertiesManager dbPropertiesManager;

    @Autowired
    private DataFillingManager dataFillingManager;

    @Autowired
    private BlogCommentDao commentDao;

    @Autowired
    private BloggerAccountDao accountDao;

    @Autowired
    private BloggerPictureDao pictureDao;

    @Autowired
    private BloggerProfileDao profileDao;

    @Autowired
    private BlogStatisticsDao statisticsDao;

    @Override
    public ResultBean<BlogMainContentDTO> getBlogMainContent(int blogId) {

        //查询数据
        Blog blog = blogDao.getBlogById(blogId);
        if (blog == null) return null;
        String ch = dbPropertiesManager.getStringFiledSplitCharacterForNumber();
        int[] cids = StringUtils.intStringDistinctToArray(blog.getCategoryIds(), ch);
        int[] lids = StringUtils.intStringDistinctToArray(blog.getLabelIds(), ch);
        List<BlogCategory> categories = cids == null ? null : categoryDao.listCategoryById(cids);
        List<BlogLabel> labels = lids == null ? null : labelDao.listLabelById(lids);

        //填充数据
        BlogMainContentDTO dto = new BlogMainContentDTO();
        dto.setCategories(categories == null ? null : categories.toArray(new BlogCategory[categories.size()]));
        dto.setLabels(labels == null ? null : labels.toArray(new BlogLabel[labels.size()]));
        dto.setId(blog.getId());
        dto.setKeyWords(StringUtils.stringToStringArray(blog.getKeyWords(),
                dbPropertiesManager.getStringFiledSplitCharacterForString()));
        dto.setNearestModifyDate(blog.getNearestModifyDate());
        dto.setReleaseDate(blog.getReleaseDate());
        dto.setStatus(blog.getState());
        dto.setSummary(blog.getSummary());
        dto.setTitle(blog.getTitle());
        dto.setWordCount(blog.getWordCount());
        dto.setContent(blog.getContent());

        return new ResultBean<>(dto);
    }

    @Override
    public ResultBean<BlogStatisticsDTO> getBlogStatistics(int blogId) {
        BlogStatistics statistics = statisticsDao.getStatistics(blogId);
        if (statistics == null) return null;
        BlogStatisticsDTO dto = dataFillingManager.blogStatisticsToDTO(statistics);
        return new ResultBean<>(dto);
    }

    @Override
    public ResultBean<List<BlogCommentDTO>> listBlogComment(int blogId, int offset, int rows) {

        List<BlogCommentDTO> result = new ArrayList<>();

        List<BlogComment> comments = commentDao.listCommentByBlogId(blogId, offset, rows, BlogCommentStatusEnum.RIGHTFUL.getCode());
        for (BlogComment comment : comments) {

            //评论者数据
            int sid = comment.getSpokesmanId();
            BloggerAccount smAccount = accountDao.getAccountById(sid);
            BloggerProfile smProfile = getProfile(sid);
            BloggerPicture smAvatar = getAvatar(sid);
            BloggerDTO smDTO = dataFillingManager.bloggerAccountToDTO(smAccount, smProfile, smAvatar);

            //作者数据
            int lid = comment.getListenerId();
            BloggerAccount lAccount = accountDao.getAccountById(lid);
            BloggerProfile lProfile = getProfile(lid);
            BloggerPicture lAvatar = getAvatar(lid);
            BloggerDTO lDTO = dataFillingManager.bloggerAccountToDTO(lAccount, lProfile, lAvatar);

            BlogCommentDTO dto = dataFillingManager.blogCommentToDTO(comment, smDTO, lDTO);
            result.add(dto);
        }

        return CollectionUtils.isEmpty(result) ? null : new ResultBean<>(result);
    }


    //获得博主资料
    private BloggerProfile getProfile(Integer bloggerId) {
        if (bloggerId == null) return null;
        return profileDao.getProfileByBloggerId(bloggerId);
    }

    //获得博主图片
    private BloggerPicture getAvatar(Integer bloggerId) {
        if (bloggerId == null) return null;
        return pictureDao.getPictureByCategory(bloggerId, BloggerPictureCategoryEnum.BLOGGER_AVATAR.getCode());
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
