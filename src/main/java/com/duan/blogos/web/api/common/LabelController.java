package com.duan.blogos.web.api.common;

import com.duan.blogos.entity.blog.BlogLabel;
import com.duan.blogos.restful.ResultBean;
import com.duan.blogos.service.blogger.BloggerLabelService;
import com.duan.blogos.web.api.BaseCheckController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created on 2018/1/12.
 * 博文标签API，标签的使用不限定博主，即只要标签存在，任何博主都可以使用
 * <p>
 * 1 查看所有标签
 * 2 查看指定标签
 * 3 修改标签
 * 4 删除标签
 * 5 增加标签
 * 6 获取指定博主创建的标签
 *
 * @author DuanJiaNing
 */
@RestController
@RequestMapping("/label")
public class LabelController extends BaseCheckController {

    @Autowired
    private BloggerLabelService bloggerLabelService;

    /**
     * 查看所有标签
     */
    @RequestMapping(method = RequestMethod.GET)
    public ResultBean<List<BlogLabel>> get(HttpServletRequest request,
                                           @RequestParam(value = "offset", required = false) Integer offset,
                                           @RequestParam(value = "rows", required = false) Integer rows) {

        int os = offset == null || offset < 0 ? 0 : offset;
        int rs = rows == null || rows < 0 ? 10 : rows;
        ResultBean<List<BlogLabel>> resultBean = bloggerLabelService.listLabel(os, rs);
        if (resultBean == null) handlerEmptyResult(request);

        return resultBean;
    }


    /**
     * 获取指定标签
     */
    @RequestMapping(value = "/{labelId}", method = RequestMethod.GET)
    public ResultBean<BlogLabel> getLabel(HttpServletRequest request, @PathVariable("labelId") Integer labelId) {

        BlogLabel label = bloggerLabelService.getLabel(labelId);
        if (label == null) handlerEmptyResult(request);

        return new ResultBean<>(label);
    }

}
