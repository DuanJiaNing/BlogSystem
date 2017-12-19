package com.duan.blogos.service.impl.blogger;

import com.duan.blogos.dao.blogger.BloggerAccountDao;
import com.duan.blogos.dto.blogger.BloggerDTO;
import com.duan.blogos.entity.blogger.BloggerAccount;
import com.duan.blogos.result.ResultBean;
import com.duan.blogos.service.blogger.BloggerAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created on 2017/12/19.
 *
 * @author DuanJiaNing
 */
@Service("bloggerAccountService")
public class BloggerAccountServiceImpl implements BloggerAccountService {

    @Autowired
    private BloggerAccountDao accountDao;

    @Override
    public int insertAccount(String userName, String password) {
        return 0;
    }

    @Override
    public BloggerAccount getAccount(String userName, String password) {
        return null;
    }

    @Override
    public BloggerAccount getAccount(int bloggerId) {
        return null;
    }

    @Override
    public BloggerAccount getAccount(String bloggerName) {
        return accountDao.getAccountByName(bloggerName);
    }

    @Override
    public boolean updateAccount(int bloggerId, String newUserName, String newPassword) {
        return false;
    }

    @Override
    public ResultBean<BloggerDTO> getBloggerByBlog(int blogId) {
        return null;
    }

    @Override
    public ResultBean<BloggerDTO> getBlogger(int bloggerId) {
        return null;
    }
}
