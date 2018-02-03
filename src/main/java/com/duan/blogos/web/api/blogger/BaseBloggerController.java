package com.duan.blogos.web.api.blogger;

import com.duan.blogos.exception.BaseRuntimeException;
import com.duan.blogos.manager.properties.BloggerProperties;
import com.duan.blogos.util.CollectionUtils;
import com.duan.blogos.web.api.BaseRestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.support.RequestContext;

import javax.servlet.http.HttpServletRequest;

/**
 * Created on 2017/12/28.
 *
 * @author DuanJiaNing
 */
public class BaseBloggerController extends BaseRestController {

    @Autowired
    protected BloggerProperties bloggerProperties;

    /**
     * 检查博主是否存在，不存在直接抛出异常
     */
    protected void handleAccountCheck(HttpServletRequest request, Integer bloggerId) {
        RequestContext context = new RequestContext(request);
        BaseRuntimeException exception = checkAccountExist(context, bloggerId);
        if (exception != null) throw exception;
    }

    /**
     * 先检查博主是否存在，后检查检查博主是否登录，在API中，一些获取数据的操作是不需要博主登录的，但类似于修改，删除，
     * 新增以及关键数据的操纵需要验证身份。
     * <p>
     * 如果验证不通过将直接抛出运行时异常。
     *
     * @param bloggerId 博主id
     */
    protected void handleBloggerSignInCheck(HttpServletRequest request, Integer bloggerId) {
        handleAccountCheck(request, bloggerId);

        // 检查当前登录否
        if (!bloggerValidateManager.checkBloggerSignIn(request, bloggerId))
            throw exceptionManager.getBloggerNotLoggedInException(new RequestContext(request));
    }

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
