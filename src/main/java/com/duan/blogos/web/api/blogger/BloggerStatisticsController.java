package com.duan.blogos.web.api.blogger;

import com.duan.blogos.dto.blogger.BloggerStatisticsDTO;
import com.duan.blogos.restful.ResultBean;
import com.duan.blogos.service.blogger.BloggerStatisticsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * Created on 2018/2/11.
 *
 * @author DuanJiaNing
 */
@RestController
@RequestMapping("/blogger/{bloggerId}/statistic")
public class BloggerStatisticsController extends BaseBloggerController {

    @Autowired
    private BloggerStatisticsService statisticsService;

    @RequestMapping(method = RequestMethod.GET)
    public ResultBean<BloggerStatisticsDTO> get(HttpServletRequest request,
                                                @PathVariable Integer bloggerId) {

        handleAccountCheck(request, bloggerId);

        ResultBean<BloggerStatisticsDTO> statistics = statisticsService.getBloggerStatistics(bloggerId);
        if (statistics == null) handlerOperateFail(request);

        return statistics;
    }

}
