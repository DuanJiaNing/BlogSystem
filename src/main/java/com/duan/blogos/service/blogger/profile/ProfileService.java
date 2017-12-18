package com.duan.blogos.service.blogger.profile;

/**
 * Created on 2017/12/14.
 * 博主个人资料服务
 *
 * @author DuanJiaNing
 */
public interface ProfileService {

    /**
     * 新增博主资料
     *
     * @param bloggerId 博主id
     * @param phone     手机号
     * @param email     电子邮件
     * @param aboutMe   关于我
     * @param intro     一句话简介
     * @return 新纪录id
     */
    int insertBloggerProfile(int bloggerId, String phone, String email, String aboutMe, String intro);

    /**
     * 更新博主头像
     *
     * @param pictureId 图片id
     */
    void updateBloggerAvatar(int pictureId);

    /**
     * 更新博主信息
     *
     * @param bloggerId  博主id
     * @param newPhone   新的电话号码，不改变传 null
     * @param newEmail   新的邮箱，不改变传 null
     * @param newAboutMe 新的“关于我”，不改变传 null
     * @param newIntro   新的一句话简介，不改变传 null
     */
    void updateBloggerProfile(int bloggerId, String newPhone, String newEmail, String newAboutMe, String newIntro);

}
