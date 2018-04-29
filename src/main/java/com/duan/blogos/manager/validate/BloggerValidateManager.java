package com.duan.blogos.manager.validate;

import com.duan.blogos.dao.blog.BlogCategoryDao;
import com.duan.blogos.dao.blogger.BloggerAccountDao;
import com.duan.blogos.dao.blogger.BloggerPictureDao;
import com.duan.blogos.entity.blogger.BloggerPicture;
import com.duan.blogos.enums.BloggerPictureCategoryEnum;
import com.duan.blogos.manager.properties.BloggerProperties;
import com.duan.blogos.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * Created on 2017/12/26.
 * 博主账户相关信息验证
 *
 * @author DuanJiaNing
 */
@Component
public class BloggerValidateManager {

    @Autowired
    private BloggerAccountDao accountDao;

    @Autowired
    private BloggerPictureDao pictureDao;

    @Autowired
    private BlogCategoryDao categoryDao;

    @Autowired
    private BloggerProperties propertiesManager;

    /**
     * 检查博主是否存在
     *
     * @param id 博主id
     * @return 存在返回true
     */
    public boolean checkAccountExist(int id) {
        return accountDao.getAccountById(id) != null;
    }

    /**
     * 检查博文类别是否存在
     *
     * @param bloggerId  博主id
     * @param categoryId 类别id
     * @return 存在时返回true
     */
    public boolean checkBloggerBlogCategoryExist(int bloggerId, int categoryId) {
        return categoryDao.getCategory(bloggerId, categoryId) != null;
    }

    /**
     * 检查博主是否有权限操纵（新增，更新，删除）某些类别图片
     *
     * @param bloggerId 博主id
     * @param category  图片类别
     * @return 可以操纵返回true
     */
    public boolean checkBloggerPictureLegal(int bloggerId, int category) {
        int pictureManagerId = propertiesManager.getPictureManagerBloggerId();

        // 图片管理者可以操作任何类别图片,非图片管理者不能操作系统默认图片
        return bloggerId == pictureManagerId || !BloggerPictureCategoryEnum.isDefaultPictureCategory(category);
    }

    /**
     * 检查当前博主是否登录
     *
     * @param bloggerId 博主id
     * @return 登录返回true
     */
    public boolean checkBloggerSignIn(HttpServletRequest request, Integer bloggerId) {

        // 检查当前登录否
        HttpSession session = request.getSession();
        Object obj = session.getAttribute(propertiesManager.getSessionNameOfBloggerId());

        return bloggerId.equals(obj);

    }

    /**
     * 检查博主是否有指定图片
     *
     * @param bloggerId 博主id
     * @param pictureId 图片id
     * @return 有返回true
     */
    public boolean checkBloggerPictureExist(int bloggerId, int pictureId) {
        BloggerPicture picture = pictureDao.getPictureById(pictureId);
        if (picture != null && Integer.valueOf(bloggerId).equals(picture.getBloggerId()))
            return true;

        return false;
    }

    /**
     * 注册时检查用户名合法性
     *
     * @param username 用户名
     * @return 合法返回true
     */
    public boolean checkUserName(String username) {
        if (StringUtils.isEmpty_(username)) return false;

        // UPDATE: 2018/2/2 更新 当前版本对用户名（如格式）不做限制
        return true;
    }

    /**
     * 校验密码：6-12 为，由字母和数字组成
     *
     * @param password 密码
     * @return 合法返回true
     */
    public boolean checkPassword(String password) {
        if (StringUtils.isEmpty_(password)) return false;

        String regex = "^(?:(?=.*[A-z])(?=.*[0-9])).{6,12}$";
        return password.matches(regex);
    }

    /**
     * 检查博主主页个人信息栏位置，0 在左，1 在右
     *
     * @param mainPageNavPos 位置
     * @return 非 0 或 1 时返回 false
     */
    public boolean checkMainPageNavPos(int mainPageNavPos) {
        return mainPageNavPos == 0 || mainPageNavPos == 1;
    }
}
