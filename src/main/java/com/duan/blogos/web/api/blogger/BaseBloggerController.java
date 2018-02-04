package com.duan.blogos.web.api.blogger;

import com.duan.blogos.manager.StringConstructorManager;
import com.duan.blogos.manager.properties.BloggerProperties;
import com.duan.blogos.manager.properties.WebsiteProperties;
import com.duan.blogos.util.CollectionUtils;
import com.duan.blogos.web.api.BaseCheckController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.support.RequestContext;

import javax.servlet.http.HttpServletRequest;

/**
 * Created on 2017/12/28.
 * 该家族的大多数操作都需要博主登录
 *
 * @author DuanJiaNing
 */
public class BaseBloggerController extends BaseCheckController {

    @Autowired
    protected StringConstructorManager stringConstructorManager;

    @Autowired
    protected WebsiteProperties websiteProperties;

    @Autowired
    protected BloggerProperties bloggerProperties;

    /**
     * 检查所有参数是否都为null，在更新时这种情况下更新操作将取消
     *
     * @param objs 当且仅当这些参数全为null时抛出异常
     */
    protected void handleParamAllNullForUpdate(HttpServletRequest request, Object... objs) {
        if (CollectionUtils.isEmpty(objs))
            throw exceptionManager.getParameterIllegalException(new RequestContext(request));

        for (Object obj : objs) {
            if (obj != null) return;
        }

        throw exceptionManager.getParameterIllegalException(new RequestContext(request));
    }

    /**
     * 检查指定博主的图片存在
     *
     * @param bloggerId 博主id
     * @param pictureId 图片id
     */
    protected void handlePictureExistCheck(HttpServletRequest request, Integer bloggerId, Integer pictureId) {
        if (pictureId != null && !bloggerValidateManager.checkBloggerPictureExist(bloggerId, pictureId))
            throw exceptionManager.getUnknownPictureException(new RequestContext(request));
    }
}
