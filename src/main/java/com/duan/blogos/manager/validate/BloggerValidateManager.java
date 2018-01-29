package com.duan.blogos.manager.validate;

import com.duan.blogos.dao.blogger.BloggerPictureDao;
import com.duan.blogos.entity.blogger.BloggerAccount;
import com.duan.blogos.entity.blogger.BloggerPicture;
import com.duan.blogos.enums.BloggerPictureCategoryEnum;
import com.duan.blogos.manager.BloggerPropertiesManager;
import com.duan.blogos.service.blogger.BloggerAccountService;
import com.duan.blogos.service.blogger.blog.CategoryService;
import com.duan.blogos.service.blogger.profile.GalleryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * Created on 2017/12/26.
 * 博主账户验证
 *
 * @author DuanJiaNing
 */
@Component
public class BloggerValidateManager {

    @Autowired
    private BloggerAccountService bloggerAccountService;

    @Autowired
    private GalleryService galleryService;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private BloggerPictureDao pictureDao;

    @Autowired
    private BloggerPropertiesManager propertiesManager;

    /**
     * 检查博主是否存在
     *
     * @param id 博主id
     * @return 存在返回账户，否则返回null
     */
    public BloggerAccount checkAccount(Integer id) {
        BloggerAccount account;
        if (id == null || id <= 0 || (account = bloggerAccountService.getAccount(id)) == null) {
            return null;
        }
        return account;
    }

    /**
     * 检查博主是否有对应的博文类别
     *
     * @param bloggerId  博主id
     * @param categoryId 类别id
     * @return 有返回true
     */
    public boolean checkBloggerBlogCategoryExist(Integer bloggerId, Integer categoryId) {
        if (bloggerId == null || bloggerId <= 0 || categoryId == null || categoryId <= 0) {
            return false;
        }

        int count = categoryService.countCategoryForExistCheck(bloggerId, categoryId);
        return count >= 1;
    }

    /**
     * 检查博文类别是否存在
     *
     * @param bloggerId   博主id
     * @param categoryIds 类别id
     * @return 都存在时返回true
     */
    public boolean checkBloggerBlogCategoryExist(int bloggerId, int... categoryIds) {
        for (int id : categoryIds) {
            if (categoryService.countCategoryForExistCheck(bloggerId, id) < 1)
                return false;
        }

        return true;
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

        // 图片管理者可以操作任何类别图片,非图片管理者不能操作只有图片管理者才能操作的图片类=类别
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

        return obj != null && bloggerId.equals(obj);

    }

    /**
     * 检查博主是否有指定图片
     *
     * @param bloggerId 博主id
     * @param pictureId 图片id
     * @return 有返回true
     */
    public boolean checkBloggerPictureExist(int bloggerId, int pictureId) {
        return galleryService.getPicture(pictureId, bloggerId) != null;
    }

    /**
     * 检查指定博主是否为图片管理者
     *
     * @param bloggerId 博主id
     * @return 是返回true
     */
    public boolean isPictureManagerBlogger(int bloggerId) {
        return propertiesManager.getPictureManagerBloggerId() == bloggerId;
    }


    /**
     * 检查图片是否为默认图片
     *
     * @param pictureId 图片id
     * @return 是返回true
     */
    public boolean isDefaultPicture(int pictureId) {
        if (pictureId <= 0) return false;

        BloggerPicture picture = pictureDao.getPictureById(pictureId);
        if (picture == null || !BloggerPictureCategoryEnum.isDefaultPictureCategory(picture.getCategory()))
            return false;

        return true;
    }

    /**
     * 注册时检查用户名合法性
     *
     * @param username 用户名
     * @return 合法返回true
     */
    public boolean checkUserName(String username) {
        // TODO
        return true;
    }

    /**
     * 注册时检查用户名合法性
     *
     * @param password 用户名
     * @return 合法返回true
     */
    public boolean checkPassword(String password) {
        // TODO
        return true;
    }
}
