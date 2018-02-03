package com.duan.blogos.manager.properties;

import lombok.Data;

/**
 * Created on 2017/12/21.
 * 数据库配置参数
 *
 * @author DuanJiaNing
 */
@Data
public class DbProperties {

    /**
     * 数据库数字间隔字符
     */
    private String stringFiledSplitCharacterForNumber;

    /**
     * 数据库字符串间隔字符
     */
    private String stringFiledSplitCharacterForString;

}
