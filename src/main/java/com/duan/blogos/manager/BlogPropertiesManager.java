package com.duan.blogos.manager;

import lombok.Data;

/**
 * Created on 2018/1/11.
 *
 * @author DuanJiaNing
 */
@Data
public class BlogPropertiesManager {

    /**
     * 博文默认类别，创作时未指定类别，删除类别时未指定移动到的类别，则移动到默认类别
     */
    private Integer defaultBlogCategory;

}
