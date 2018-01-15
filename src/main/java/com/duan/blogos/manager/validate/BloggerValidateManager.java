package com.duan.blogos.manager.validate;

import com.duan.blogos.entity.blogger.BloggerAccount;
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
     * 检查博主是否有权限操纵某些类别图片
     *
     * @param bloggerId 博主id
     * @param category  图片类别
     * @return 可以操纵返回true
     */
    public boolean checkBloggerPictureLegal(int bloggerId, int category) {
        int pictureManagerId = propertiesManager.getPictureManagerBloggerId();

        //图片管理者可以上传任何类别图片
        if (bloggerId == pictureManagerId) return true;
        else { //非图片管理者只能上传部分类别图片
            if (BloggerPictureCategoryEnum.isUniqueCategory(category)) return false;
            else return true;
        }
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
}
