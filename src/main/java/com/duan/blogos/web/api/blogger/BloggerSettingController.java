package com.duan.blogos.web.api.blogger;

import com.duan.blogos.restful.ResultBean;
import com.duan.blogos.service.blogger.BloggerSettingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.RequestContext;

import javax.servlet.http.HttpServletRequest;

/**
 * Created on 2017/12/29.
 * 博主个人设置api
 *
 * @author DuanJiaNing
 */
@RestController
@RequestMapping("/blogger/{bloggerId}/setting")
public class BloggerSettingController extends BaseBloggerController {

    @Autowired
    private BloggerSettingService settingService;

    /**
     * 更新博主主页导航位置
     */
    @RequestMapping(value = "/item=mainPageNavPos", method = RequestMethod.PUT)
    public ResultBean update(HttpServletRequest request,
                             @PathVariable Integer bloggerId,
                             @RequestParam("mainPageNavPos") Integer mainPageNavPos) {

        handleBloggerSignInCheck(request, bloggerId);
        handleMainPageNavPosCheck(request, mainPageNavPos);

        boolean result = settingService.updateMainPageNavPos(bloggerId, mainPageNavPos);
        if (!result) handlerOperateFail(request);

        return new ResultBean<>("");
    }

    private void handleMainPageNavPosCheck(HttpServletRequest request, Integer mainPageNavPos) {
        if (mainPageNavPos == null || !bloggerValidateManager.checkMainPageNavPos(mainPageNavPos)) {
            throw exceptionManager.getParameterIllegalException(new RequestContext(request));
        }
    }


}
