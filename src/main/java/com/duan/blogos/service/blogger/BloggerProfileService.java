package com.duan.blogos.service.blogger;

import com.duan.blogos.entity.blogger.BloggerProfile;

/**
 * Created on 2017/12/14.
 * 博主个人资料服务
 *
 * @author DuanJiaNing
 */
public interface BloggerProfileService {

    /**
     * 新增博主资料
     *
     * @param bloggerId 博主id
     * @param avatarId  博主头像id
     * @param phone     手机号
     * @param email     电子邮件
     * @param aboutMe   关于我
     * @param intro     一句话简介
     * @return 新纪录id
     */
    int insertBloggerProfile(int bloggerId, int avatarId, String phone, String email, String aboutMe, String intro);

    /**
     * 更新博主信息
     *
     * @param bloggerId  博主id
     * @param avatarId   博主头像id,不改变传 -1
     * @param newPhone   新的电话号码，不改变传 null
     * @param newEmail   新的邮箱，不改变传 null
     * @param newAboutMe 新的“关于我”，不改变传 null
     * @param newIntro   新的一句话简介，不改变传 null
     * @return 更新失败为false
     */
    boolean updateBloggerProfile(int bloggerId, int avatarId, String newPhone, String newEmail, String newAboutMe, String newIntro);

    /**
     * 删除博主信息
     *
     * @param bloggerId 博主id
     * @return 删除成功为true
     */
    boolean deleteBloggerProfile(int bloggerId);

    /**
     * 查询博主信息
     *
     * @param bloggerId 博主id
     * @return 查询结果
     */
    BloggerProfile getBloggerProfile(int bloggerId);

    /**
     * 通过电话号码获得资料
     *
     * @param phone 电话号码
     * @return 结果
     */
    BloggerProfile getBloggerProfileByPhone(String phone);
}
