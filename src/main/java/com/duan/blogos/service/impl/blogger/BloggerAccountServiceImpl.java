package com.duan.blogos.service.impl.blogger;

import com.duan.blogos.dao.blogger.BloggerAccountDao;
import com.duan.blogos.dao.blogger.BloggerPictureDao;
import com.duan.blogos.dto.blogger.BloggerDTO;
import com.duan.blogos.entity.blogger.BloggerAccount;
import com.duan.blogos.entity.blogger.BloggerPicture;
import com.duan.blogos.exception.internal.UnknownInternalException;
import com.duan.blogos.manager.BloggerPropertiesManager;
import com.duan.blogos.manager.ImageManager;
import com.duan.blogos.result.ResultBean;
import com.duan.blogos.service.blogger.BloggerAccountService;
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
    private ImageManager imageManager;

    @Autowired
    private BloggerPropertiesManager propertiesManager;

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

        return account.getId();
    }

    @Override
    public BloggerAccount getAccount(String userName, String password) {
        return null;
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
    public boolean updateAccount(int bloggerId, String newUserName, String newPassword) {

        BloggerAccount account = new BloggerAccount();
        account.setId(bloggerId);
        if (newUserName != null) account.setUsername(newUserName);
        if (newPassword != null) account.setPassword(newPassword);

        int effect = accountDao.update(account);
        if (effect <= 0) return false;

        return true;
    }

    @Override
    public ResultBean<BloggerDTO> getBloggerByBlog(int blogId) {
        return null;
    }

    @Override
    public ResultBean<BloggerDTO> getBlogger(int bloggerId) {
        return null;
    }

    @Override
    public boolean deleteAccount(int bloggerId) {

        // 图片管理员不允许注销账号
        if (propertiesManager.getPictureManagerBloggerId().equals(bloggerId))
            return false;

        // 账户相关数据由关系数据库负责处理
        int effect = accountDao.delete(bloggerId);
        if (effect <= 0) return false;

        //博主图片需要手动删除
        List<BloggerPicture> ps = pictureDao.getPictureByBloggerId(bloggerId);
        ps.forEach(p -> imageManager.deleteImageFromDisk(p.getPath()));

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
}
