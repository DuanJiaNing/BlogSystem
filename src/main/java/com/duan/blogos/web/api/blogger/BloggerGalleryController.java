package com.duan.blogos.web.api.blogger;

import com.duan.blogos.entity.blogger.BloggerPicture;
import com.duan.blogos.result.ResultBean;
import com.duan.blogos.service.blogger.profile.GalleryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.RequestContext;

import javax.servlet.http.HttpServletRequest;

/**
 * Created on 2018/1/2.
 * 相册
 * 获取照片
 *
 * @author DuanJiaNing
 */
@RestController
@RequestMapping("/blogger/{bloggerId}/gallery")
public class BloggerGalleryController extends BaseBloggerController {

    @Autowired
    private GalleryService galleryService;

    /**
     * 根据id获取图片
     */
    @RequestMapping(value = "/{pictureId}", method = RequestMethod.GET)
    public ResultBean<BloggerPicture> get(HttpServletRequest request,
                                          @PathVariable("bloggerId") Integer bloggerId,
                                          @PathVariable("pictureId") Integer pictureId) {
        checkAccount(request, bloggerId);

        RequestContext context = new RequestContext(request);
        if (pictureId <= 0) throw exceptionManager.getParameterIllegalException(context);

        BloggerPicture picture = galleryService.getPicture(pictureId, bloggerId);
        if (picture == null) handlerEmptyResult(request);

        String url = urlConstructorManager.constructPictureUrl(picture);
        picture.setPath(url);

        return new ResultBean<>(picture);
    }
}
