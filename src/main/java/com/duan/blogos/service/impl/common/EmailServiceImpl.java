package com.duan.blogos.service.impl.common;

import com.duan.blogos.manager.properties.WebsiteProperties;
import com.duan.blogos.service.common.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created on 2018/4/7.
 *
 * @author DuanJiaNing
 */
@Service
public class EmailServiceImpl implements EmailService {

    @Autowired
    protected WebsiteProperties websiteProperties;

    @Override
    public void sendFeedback(int bloggerId, String content, String contact) {

    }

}
