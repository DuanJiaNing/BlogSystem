package com.duan.blogos.manager;

import org.springframework.stereotype.Component;

import java.io.File;

/**
 * Created on 2018/4/5.
 * <p>
 * 系统文件管理者
 *
 * @author DuanJiaNing
 */
@Component
public class FileManager {

    /**
     * 删除文件
     *
     * @param path 路径
     * @return 成功删除返回 true
     */
    public boolean deleteFileIfExist(String path) {
        File file = new File(path);
        return file.exists() && file.isFile() && file.delete();
    }

    /**
     * 创建目录，如果不存在的话
     *
     * @param path 路径
     * @return 成功创建返回 true
     */
    public boolean mkdirsIfNotExist(String path) {
        File dir = new File(path);
        return (!dir.exists() || dir.isFile()) && dir.mkdirs();
    }
}
