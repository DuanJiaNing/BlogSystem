package com.duan.blogos.web.api.blogger;

import com.duan.blogos.dto.blogger.BloggerCategoryDTO;
import com.duan.blogos.result.ResultBean;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created on 2018/1/11.
 * 博主博文类别
 * <p>
 * 1 查看所有类别
 * 2 查看指定类别
 * 3 增加类别
 * 4 修改类别
 * 5 删除类别
 *
 * @author DuanJiaNing
 */
@RestController
@RequestMapping("/blogger/{bloggerId}/category")
public class BloggerBlogCategoryController extends BaseBloggerController {

    /**
     * 查看所有类别
     */
    @RequestMapping(method = RequestMethod.GET)
    public ResultBean<List<BloggerCategoryDTO>> get(HttpServletRequest request,
                                                    @PathVariable("bloggerId") Integer bloggerId,
                                                    @RequestParam(value = "offset", required = false) Integer offset,
                                                    @RequestParam(value = "rows", required = false) Integer rows) {

        return null;
    }


    /**
     * 查看指定类别
     */
    @RequestMapping(value = "/{categoryId}", method = RequestMethod.GET)
    public ResultBean<BloggerCategoryDTO> get(HttpServletRequest request,
                                              @PathVariable("bloggerId") Integer bloggerId,
                                              @PathVariable("categoryId") Integer categoryId) {

        return null;
    }


    /**
     * 增加类别
     */
    @RequestMapping(method = RequestMethod.POST)
    public ResultBean add(HttpServletRequest request,
                          @PathVariable("bloggerId") Integer bloggerId,
                          @RequestParam(value = "iconId", required = false) Integer iconId,
                          @RequestParam("title") String title,
                          @RequestParam(value = "bewrite", required = false) String bewrite) {

        return null;
    }


    /**
     * 修改类别
     */
    @RequestMapping(value = "/{categoryId}", method = RequestMethod.GET)
    public ResultBean update(HttpServletRequest request,
                             @PathVariable("bloggerId") Integer bloggerId,
                             @PathVariable("categoryId") Integer categoryId,
                             @RequestParam(value = "iconId", required = false) Integer newIconId,
                             @RequestParam(value = "title", required = false) String newTitle,
                             @RequestParam(value = "bewrite", required = false) String newBewrite) {

        return null;
    }

    /**
     * 删除类别
     */
    @RequestMapping(value = "/{categoryId}", method = RequestMethod.DELETE)
    public ResultBean delete(HttpServletRequest request,
                             @PathVariable("bloggerId") Integer bloggerId,
                             @PathVariable("categoryId") Integer categoryId) {

        return null;
    }


}
