package com.duan.blogos.service.impl.blogger;

import com.duan.blogos.dao.blog.BlogCollectDao;
import com.duan.blogos.dao.blog.BlogDao;
import com.duan.blogos.dao.blog.BlogLikeDao;
import com.duan.blogos.dao.blog.BlogStatisticsDao;
import com.duan.blogos.dao.blogger.BloggerAccountDao;
import com.duan.blogos.dao.blogger.BloggerPictureDao;
import com.duan.blogos.dao.blogger.BloggerProfileDao;
import com.duan.blogos.dao.blogger.BloggerSettingDao;
import com.duan.blogos.entity.blog.Blog;
import com.duan.blogos.entity.blog.BlogCollect;
import com.duan.blogos.entity.blog.BlogLike;
import com.duan.blogos.entity.blogger.BloggerAccount;
import com.duan.blogos.entity.blogger.BloggerPicture;
import com.duan.blogos.entity.blogger.BloggerProfile;
import com.duan.blogos.entity.blogger.BloggerSetting;
import com.duan.blogos.exception.internal.UnknownInternalException;
import com.duan.blogos.manager.BlogLuceneIndexManager;
import com.duan.blogos.manager.ImageManager;
import com.duan.blogos.manager.properties.BloggerProperties;
import com.duan.blogos.service.blogger.BloggerAccountService;
import com.duan.blogos.util.CollectionUtils;
import com.duan.blogos.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.security.NoSuchAlgorithmException;
import java.util.List;

/**
 * Created on 2017/12/19.
 *
 * @author DuanJiaNing
 */
@Service
public class BloggerAccountServiceImpl implements BloggerAccountService {

    @Autowired
    private BloggerAccountDao accountDao;

    @Autowired
    private BloggerPictureDao pictureDao;

    @Autowired
    private BloggerProfileDao profileDao;

    @Autowired
    private BloggerSettingDao settingDao;

    @Autowired
    private BlogDao blogDao;

    @Autowired
    private BlogCollectDao collectDao;

    @Autowired
    private BlogLikeDao likeDao;

    @Autowired
    private BlogStatisticsDao blogStatisticsDao;

    @Autowired
    private ImageManager imageManager;

    @Autowired
    private BlogLuceneIndexManager luceneIndexManager;

    @Autowired
    private BloggerProperties propertiesManager;

    @Override
    public int insertAccount(String userName, String password) {

        String shaPwd;
        try {
            //将密码通过sha的方式保存
            shaPwd = new BigInteger(StringUtils.toSha(password)).toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            throw new UnknownInternalException(e);
        }

        BloggerAccount account = new BloggerAccount();
        account.setUsername(userName);
        account.setPassword(shaPwd);
        int effect = accountDao.insert(account);
        if (effect <= 0) return -1;

        int bloggerId = account.getId();

        // 生成博主设置数据
        BloggerSetting setting = new BloggerSetting();
        setting.setBloggerId(bloggerId);
        setting.setMainPageNavPos(propertiesManager.getMainPageNavPos());
        settingDao.insert(setting);

        return bloggerId;
    }

    @Override
    public BloggerAccount getAccount(int bloggerId) {
        return accountDao.getAccountById(bloggerId);
    }

    @Override
    public BloggerAccount getAccount(String bloggerName) {
        return accountDao.getAccountByName(bloggerName);
    }

    @Override
    public boolean deleteAccount(int bloggerId) {

        // 图片管理员不允许注销账号
        if (propertiesManager.getPictureManagerBloggerId().equals(bloggerId))
            return false;

        //博主图片需要手动删除
        List<BloggerPicture> ps = pictureDao.getPictureByBloggerId(bloggerId);
        if (!CollectionUtils.isEmpty(ps))
            ps.stream().map(BloggerPicture::getPath).forEach(imageManager::deleteImageFromDisk);

        // 删除博文的lucene索引
        List<Blog> blogIds = blogDao.listAllLabelByBloggerId(bloggerId);
        if (!CollectionUtils.isEmpty(blogIds))
            blogIds.stream().mapToInt(Blog::getId).forEach(luceneIndexManager::delete);

        // 将博主喜欢和收藏的博文的喜欢/收藏次数减一
        List<BlogCollect> collects = collectDao.listAllIdByBloggerId(bloggerId);
        if (!CollectionUtils.isEmpty(collects))
            collects.stream().mapToInt(BlogCollect::getBlogId).forEach(blogStatisticsDao::updateCollectCountMinus);

        List<BlogLike> likes = likeDao.listAllIdByBloggerId(bloggerId);
        if (!CollectionUtils.isEmpty(likes))
            likes.stream().mapToInt(BlogLike::getBlogId).forEach(blogStatisticsDao::updateLikeCountMinus);


        // 账户相关数据删除由关系数据库负责处理
        // 删除 blogger_account 数据
        // ->
        int effect = accountDao.delete(bloggerId);
        if (effect <= 0) return false;

        return true;
    }

    @Override
    public boolean updateAccountUserName(int bloggerId, String newUserName) {

        BloggerAccount account = new BloggerAccount();
        account.setId(bloggerId);
        account.setUsername(newUserName);
        int effect = accountDao.update(account);
        if (effect <= 0) return false;

        return true;
    }

    @Override
    public boolean updateAccountPassword(int bloggerId, String oldPassword, String newPassword) {

        String oldSha;
        String newSha;

        try {
            oldSha = new BigInteger(StringUtils.toSha(oldPassword)).toString();
            newSha = new BigInteger(StringUtils.toSha(newPassword)).toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            throw new UnknownInternalException(e);
        }

        BloggerAccount account = accountDao.getAccountById(bloggerId);
        String oriSha = account.getPassword();

        // 旧密码正确，同时与新密码相同（并没有修改）
        if (!oriSha.equals(oldSha) || oldSha.equals(newSha)) return false;

        BloggerAccount a = new BloggerAccount();
        a.setId(bloggerId);
        a.setPassword(newSha);
        int effect = accountDao.update(a);
        if (effect <= 0) return false;

        return true;
    }

    @Override
    public BloggerAccount getAccountByPhone(String phone) {
        BloggerProfile profile = profileDao.getProfileByPhone(phone);
        if (profile == null) return null;
        return accountDao.getAccountById(profile.getBloggerId());
    }
}
