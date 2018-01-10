package com.duan.blogos.manager;

import lombok.Data;

/**
 * Created on 2018/1/3.
 * 站点配置
 *
 * @author DuanJiaNing
 */
@Data
public class WebsitePropertiesManager {

    /**
     * 站点域名
     */
    private String addr;

    /**
     * lucene生成的索引保存路径
     */
    private String luceneIndexDir;

}
