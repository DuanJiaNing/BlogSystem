package com.duan.blogos.service.impl.audience;

import com.duan.blogos.dao.blog.BlogCategoryDao;
import com.duan.blogos.dao.blog.BlogCommentDao;
import com.duan.blogos.dao.blog.BlogDao;
import com.duan.blogos.dao.blog.BlogLabelDao;
import com.duan.blogos.dao.blogger.BloggerAccountDao;
import com.duan.blogos.dao.blogger.BloggerPictureDao;
import com.duan.blogos.dao.blogger.BloggerProfileDao;
import com.duan.blogos.dto.blog.BlogCommentDTO;
import com.duan.blogos.dto.blog.BlogMainContentDTO;
import com.duan.blogos.dto.blogger.BloggerDTO;
import com.duan.blogos.entity.blog.Blog;
import com.duan.blogos.entity.blog.BlogCategory;
import com.duan.blogos.entity.blog.BlogComment;
import com.duan.blogos.entity.blog.BlogLabel;
import com.duan.blogos.entity.blogger.BloggerAccount;
import com.duan.blogos.entity.blogger.BloggerPicture;
import com.duan.blogos.entity.blogger.BloggerProfile;
import com.duan.blogos.enums.BlogCommentStatusEnum;
import com.duan.blogos.manager.DataFillingManager;
import com.duan.blogos.manager.StringConstructorManager;
import com.duan.blogos.manager.properties.BloggerProperties;
import com.duan.blogos.manager.properties.DbProperties;
import com.duan.blogos.restful.ResultBean;
import com.duan.blogos.service.audience.BlogBrowseService;
import com.duan.blogos.util.CollectionUtils;
import com.duan.blogos.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static com.duan.blogos.enums.BloggerPictureCategoryEnum.DEFAULT_BLOGGER_AVATAR;

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
    private DbProperties dbProperties;

    @Autowired
    private DataFillingManager dataFillingManager;

    @Autowired
    private StringConstructorManager constructorManager;

    @Autowired
    private BloggerProperties bloggerProperties;

    @Autowired
    private BlogCommentDao commentDao;

    @Autowired
    private BloggerAccountDao accountDao;

    @Autowired
    private BloggerPictureDao pictureDao;

    @Autowired
    private BloggerProfileDao profileDao;

    @Override
    public ResultBean<BlogMainContentDTO> getBlogMainContent(int blogId) {

        //查询数据
        Blog blog = blogDao.getBlogById(blogId);
        if (blog == null) return null;
        String ch = dbProperties.getStringFiledSplitCharacterForNumber();
        int[] cids = StringUtils.intStringDistinctToArray(blog.getCategoryIds(), ch);
        int[] lids = StringUtils.intStringDistinctToArray(blog.getLabelIds(), ch);
        List<BlogCategory> categories = cids == null ? null : categoryDao.listCategoryById(cids);
        List<BlogLabel> labels = lids == null ? null : labelDao.listLabelById(lids);

        //填充数据
        String sc = dbProperties.getStringFiledSplitCharacterForString();
        BlogMainContentDTO dto = dataFillingManager.blogMainContentToDTO(blog, categories, labels, sc);

        return new ResultBean<>(dto);
    }

    @Override
    public ResultBean<List<BlogCommentDTO>> listBlogComment(int blogId, int offset, int rows) {

        List<BlogCommentDTO> result = new ArrayList<>();

        List<BlogComment> comments = commentDao.listCommentByBlogId(blogId, offset, rows,
                BlogCommentStatusEnum.RIGHTFUL.getCode());
        for (BlogComment comment : comments) {

            //评论者数据
            int sid = comment.getSpokesmanId();
            BloggerAccount smAccount = accountDao.getAccountById(sid);
            BloggerProfile smProfile = getProfile(sid);
            BloggerDTO smDTO = dataFillingManager.bloggerAccountToDTO(smAccount, smProfile,
                    getAvatar(smProfile.getAvatarId()));

            BlogCommentDTO dto = dataFillingManager.blogCommentToDTO(comment, smDTO);
            result.add(dto);
        }

        return CollectionUtils.isEmpty(result) ? null : new ResultBean<>(result);
    }

    private BloggerPicture getAvatar(Integer id) {
        BloggerPicture avatar;
        if (id != null) {
            avatar = pictureDao.getPictureById(id);
        } else {
            avatar = pictureDao.getBloggerUniquePicture(bloggerProperties.getPictureManagerBloggerId(),
                    DEFAULT_BLOGGER_AVATAR.getCode());
        }

        if (avatar != null) {
            avatar.setPath(constructorManager.constructPictureUrl(avatar, DEFAULT_BLOGGER_AVATAR));
        }

        return avatar;
    }


    //获得博主资料
    private BloggerProfile getProfile(Integer bloggerId) {
        if (bloggerId == null) return null;
        return profileDao.getProfileByBloggerId(bloggerId);
    }
}
