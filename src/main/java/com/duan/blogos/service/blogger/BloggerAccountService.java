package com.duan.blogos.service.blogger;

import com.duan.blogos.entity.blogger.BloggerAccount;

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
     * 根据博主id获取博主账户
     *
     * @param bloggerId 博主id
     * @return 查询结果
     */
    BloggerAccount getAccount(int bloggerId);

    /**
     * 根据博主名字获取博主账户
     *
     * @param bloggerName 博主名字
     * @return 查询结果
     */
    BloggerAccount getAccount(String bloggerName);

    /**
     * 删除账号
     *
     * @param bloggerId 博主id
     * @return 上传成功返回true
     */
    boolean deleteAccount(int bloggerId);

    /**
     * 更新用户名
     *
     * @param bloggerId   博主id
     * @param newUserName 新的用户名
     * @return 更新成功返回true
     */
    boolean updateAccountUserName(int bloggerId, String newUserName);

    /**
     * 更新密码
     *
     * @param bloggerId   博主id
     * @param oldPassword 旧密码
     * @param newPassword 新的密码
     * @return 更新成功返回true
     */
    boolean updateAccountPassword(int bloggerId, String oldPassword, String newPassword);

    /**
     * 根据博主电话号码获得账户
     *
     * @param phone 电话号码
     * @return 结果
     */
    BloggerAccount getAccountByPhone(String phone);
}
