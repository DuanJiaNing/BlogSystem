package com.duan.blogos.service.blogger;

import com.duan.blogos.dto.blogger.BloggerDTO;
import com.duan.blogos.entity.blogger.BloggerAccount;
import com.duan.blogos.result.ResultBean;

/**
 * Created on 2017/12/14.
 * 针对博主账户的业务
 * <p>
 * 1 新增账户
 * 2 根据用户名或密码获取博主账户，可用于 登陆/注册 时校验用户
 * 3 根据博主id获取博主账户
 * 4 更新账户信息
 *
 * @author DuanJiaNing
 */
public interface BloggerAccountService {

    /**
     * 新增账户
     *
     * @param userName 用户名
     * @param password 密码
     * @return 博主id
     */
    int insertAccount(String userName, String password);

    /**
     * 根据用户名或密码获取博主账户，可用于 登陆/注册 时校验用户
     *
     * @param userName 用户名
     * @param password 密码
     * @return 查询结果
     */
    BloggerAccount getAccount(String userName, String password);

    /**
     * 根据博主id获取博主账户
     *
     * @param bloggerId 博主id
     * @return 查询结果
     */
    BloggerAccount getAccount(int bloggerId);

    /**
     * 更新账户信息
     *
     * @param bloggerId   博主id
     * @param newUserName 新用户名
     * @param newPassword 新密码
     * @return 更新失败为false
     */
    boolean updateAccount(int bloggerId, String newUserName, String newPassword);

    /**
     * 获取博主信息
     *
     * @param blogId 博文id
     * @return 查询结果
     */
    ResultBean<BloggerDTO> getBloggerByBlog(int blogId);

    /**
     * 获取博主信息
     *
     * @param bloggerId 博主id
     * @return 查询结果
     */
    ResultBean<BloggerDTO> getBlogger(int bloggerId);

}
