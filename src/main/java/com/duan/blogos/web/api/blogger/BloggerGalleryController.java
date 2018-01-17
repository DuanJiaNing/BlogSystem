package com.duan.blogos.web.api.blogger;

import com.duan.blogos.entity.blogger.BloggerPicture;
import com.duan.blogos.enums.BloggerPictureCategoryEnum;
import com.duan.blogos.manager.validate.BloggerValidateManager;
import com.duan.blogos.result.ResultBean;
import com.duan.blogos.service.blogger.profile.GalleryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.RequestContext;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created on 2018/1/2.
 * 相册
 * <p>
 * 1 根据id获取图片
 * 2 获得多张图片
 * 3 更新图片信息
 *
 * @author DuanJiaNing
 */
@RestController
@RequestMapping("/blogger/{bloggerId}/gallery")
public class BloggerGalleryController extends BaseBloggerController {

    @Autowired
    private GalleryService galleryService;

    @Autowired
    private BloggerValidateManager validateManager;

    /**
     * 根据id获取图片
     */
    @RequestMapping(value = "/{pictureId}", method = RequestMethod.GET)
    public ResultBean<BloggerPicture> get(HttpServletRequest request,
                                          @PathVariable("bloggerId") Integer bloggerId,
                                          @PathVariable("pictureId") Integer pictureId) {
        handleBloggerSignInCheck(request, bloggerId);

        RequestContext context = new RequestContext(request);
        if (pictureId <= 0) throw exceptionManager.getParameterIllegalException(context);

        BloggerPicture picture = galleryService.getPicture(pictureId, bloggerId);
        if (picture == null) handlerEmptyResult(request);

        String url = stringConstructorManager.constructPictureUrl(picture, true);
        picture.setPath(url);

        return new ResultBean<>(picture);
    }

    /**
     * 获得多张图片
     */
    @RequestMapping(method = RequestMethod.GET)
    public ResultBean<List<BloggerPicture>> list(HttpServletRequest request,
                                                 @PathVariable("bloggerId") Integer bloggerId,
                                                 @RequestParam(value = "category", required = false) Integer category,
                                                 @RequestParam(value = "offset", required = false) Integer offset,
                                                 @RequestParam(value = "rows", required = false) Integer rows) {
        handleBloggerSignInCheck(request, bloggerId);
        RequestContext context = new RequestContext(request);

        int cate;
        if (category != null) {

            //检查类别是否存在
            if (BloggerPictureCategoryEnum.valueOf(category) == null) {
                throw exceptionManager.getParameterIllegalException(context);
            }

            //检查权限
            if (validateManager.checkBloggerPictureLegal(bloggerId, category)) cate = category;
            else throw exceptionManager.getUnauthorizedException(context);
        } else cate = -1;

        int os = offset == null || offset < 0 ? 0 : offset;
        int rs = rows == null || rows < 0 ? bloggerPropertiesManager.getRequestBloggerPictureCount() : rows;

        ResultBean<List<BloggerPicture>> result = galleryService.listBloggerPicture(bloggerId,
                cate == -1 ? null : BloggerPictureCategoryEnum.valueOf(cate), os, rs);
        if (result == null) handlerEmptyResult(request);

        for (BloggerPicture picture : result.getData()) {
            String url = stringConstructorManager.constructPictureUrl(picture, true);
            picture.setPath(url);
        }

        return result;
    }

    /**
     * 更新图片信息
     */
    @RequestMapping(value = "/{pictureId}", method = RequestMethod.PUT)
    public ResultBean update(HttpServletRequest request,
                             @PathVariable("bloggerId") Integer bloggerId,
                             @PathVariable("pictureId") Integer pictureId,
                             @RequestParam(value = "category", required = false) Integer newCategory,
                             @RequestParam(value = "bewrite", required = false) String newBeWrite,
                             @RequestParam(value = "title", required = false) String newTitle) {

        handleBloggerSignInCheck(request, bloggerId);
        RequestContext context = new RequestContext(request);

        // 检查博主是否有指定图片
        BloggerPicture picture = galleryService.getPicture(pictureId);
        if (picture == null || !bloggerId.equals(picture.getBloggerId())) {
            throw exceptionManager.getParameterIllegalException(context);
        }

        if (newCategory == null && newBeWrite == null && newTitle == null) {
            throw exceptionManager.getParameterIllegalException(context);
        }

        if (newCategory != null) {

            //检查类别是否存在
            if (BloggerPictureCategoryEnum.valueOf(newCategory) == null) {
                throw exceptionManager.getParameterIllegalException(context);
            }

            //检查权限
            if (!validateManager.checkBloggerPictureLegal(bloggerId, newCategory))
                throw exceptionManager.getUnauthorizedException(context);
        }

        boolean result = galleryService.updatePicture(pictureId,
                newCategory == null ? null : BloggerPictureCategoryEnum.valueOf(newCategory), newBeWrite, newTitle);
        if (!result) handlerOperateFail(request);

        return new ResultBean<>("");
    }

}
