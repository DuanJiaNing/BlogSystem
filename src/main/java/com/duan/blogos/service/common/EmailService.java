package com.duan.blogos.service.common;

/**
 * Created on 2018/4/7.
 *
 * @author DuanJiaNing
 */
public interface EmailService {

    /**
     * 发送反馈邮件
     * @param bloggerId 发送者博主 id
     * @param content 邮件内容
     * @param contact 发送者留的联系方式，如果有的话
     */
    void sendFeedback(int bloggerId, String content, String contact);

}
