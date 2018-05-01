package com.duan.blogos.manager.properties;

import lombok.Data;

/**
 * Created on 2018/1/3.
 * 站点配置参数
 *
 * @author DuanJiaNing
 */
@Data
public class WebsiteProperties {

    /**
     * 站点域名
     */
    private String addr;

    /**
     * lucene生成的索引保存路径
     */
    private String luceneIndexDir;

    /**
     * 默认的url请求参数的间隔字符
     * 如url中传递多个博文类别id时：1,2,3,8 这里间隔字符为","
     */
    private String urlConditionSplitCharacter;

    /**
     * 网站管理者的邮箱
     */
    private String manageEmailAddress;

    /**
     * 配置了smtp 的邮件发送者
     */
    private String mailSenderAddress;

    /**
     * 配置了smtp 的邮件发送者的授权码
     */
    private String mailSenderPassword;

    /**
     * 默认获取活跃博主数
     */
    private Integer WebsiteActiveBloggerCount;
}
