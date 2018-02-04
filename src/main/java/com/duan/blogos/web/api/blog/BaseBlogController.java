package com.duan.blogos.web.api.blog;

import com.duan.blogos.manager.properties.AudienceProperties;
import com.duan.blogos.web.api.BaseCheckController;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created on 2017/12/26.
 * 该家族中的结果数据是依附于博文的，博主无效登录即可获取
 *
 * @author DuanJiaNing
 */
public class BaseBlogController extends BaseCheckController {

    @Autowired
    protected AudienceProperties audienceProperties;

}
