package com.duan.blogos.manager;

import com.duan.blogos.dao.blogger.BloggerAccountDao;
import com.duan.blogos.dao.blogger.BloggerPictureDao;
import com.duan.blogos.entity.blogger.BloggerAccount;
import com.duan.blogos.entity.blogger.BloggerPicture;
import com.duan.blogos.enums.BloggerPictureCategoryEnum;
import com.duan.blogos.exception.internal.InternalIOException;
import com.duan.blogos.util.ImageUtils;
import com.duan.blogos.util.StringUtils;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Optional;

import static com.duan.blogos.enums.BloggerPictureCategoryEnum.PUBLIC;

/**
 * Created on 2018/1/3.
 * 图片管理
 *
 * @author DuanJiaNing
 */
@Component
public class ImageManager {

    @Autowired
    private StringConstructorManager constructorManager;

    @Autowired
    private BloggerAccountDao accountDao;

    @Autowired
    private BloggerPictureDao pictureDao;

    /**
     * 将图片保存到博主的对应文件夹下
     */
    public String saveImageToDisk(byte[] bs, String name, int bloggerId, int category) throws IOException {

        String type = ImageUtils.getImageMimeType(name);
        if (type == null) return null;

        BloggerAccount account = accountDao.getAccountById(bloggerId);
        String dirPath = constructorManager.constructImageDirPath(account.getUsername(),
                BloggerPictureCategoryEnum.valueOf(category).name());

        File specDir = new File(dirPath);
        if (!specDir.exists() || !specDir.isDirectory()) specDir.mkdirs();

        //文件名统一添加前缀 "时间-" 以避免覆盖
        name = System.currentTimeMillis() + "-" + handleImageName(name, type);

        File image = new File(specDir.getAbsolutePath() + File.separator + name);
        if (!image.exists() || image.isDirectory()) {
            FileOutputStream stream = new FileOutputStream(image);
            stream.write(bs);
            stream.close();
        }

        return image.getAbsolutePath();
    }

    /**
     * 将图片保存到博主的对应文件夹下
     *
     * @param file      图片
     * @param bloggerId 博主id
     * @param category  图片类别
     * @return 路径
     */
    public String saveImageToDisk(MultipartFile file, int bloggerId, int category) throws IOException {
        //后缀一定要为图片类型
        String type = ImageUtils.getImageType(file);
        if (type == null) return null;

        BloggerAccount account = accountDao.getAccountById(bloggerId);
        String dirPath = constructorManager.constructImageDirPath(account.getUsername(),
                BloggerPictureCategoryEnum.valueOf(category).name());

        File specDir = new File(dirPath);
        if (!specDir.exists() || !specDir.isDirectory()) specDir.mkdirs();

        //页面使用 <%@ page pageEncoding="utf-8" %> 指令，否则会出现文件名中文乱码
        //文件名统一添加前缀 "时间-" 以避免覆盖
        String name = System.currentTimeMillis() + "-" + handleImageName(file.getOriginalFilename(), type);

        File image = new File(specDir.getAbsolutePath() + File.separator + name);
        if (!image.exists() || image.isDirectory()) file.transferTo(image);

        return image.getAbsolutePath();
    }

    //拼接图片名，确保以文件类型为后缀
    private String handleImageName(String name, String type) {
        String[] sp = name.split("\\.");
        if (sp.length == 1) { //没有后缀
            return name + "." + type;
        } else if (sp.length == 2) { //有后缀
            if (!sp[1].equals(type)) { //后缀不正确
                return name.replace(sp[1], type);
            } else return name; //后缀正确
        } else {
            String red = name.replace(".", "");
            return red + "." + type;
        }
    }

    /**
     * 从磁盘中删除一个图片文件
     *
     * @param path 图片路径
     * @return 成功删除为true，否则失败
     */
    public boolean deleteImageFromDisk(String path) {
        if (StringUtils.isEmpty(path)) return false;

        File image = new File(path);
        if (image.exists() && image.isFile()) {
            return image.delete();
        }

        return false;
    }

    /**
     * 移动设备上的图片
     *
     * @param picture   要移动的图片
     * @param bloggerId 移动到的目标博主
     * @param category  目标博主的类别
     * @return 新的图片保存路径
     */
    public String moveImage(BloggerPicture picture, int bloggerId, BloggerPictureCategoryEnum category) throws IOException {

        BloggerAccount account = accountDao.getAccountById(bloggerId);
        String dirPath = constructorManager.constructImageDirPath(account.getUsername(), category.name());
        File newDir = new File(dirPath);
        if (!newDir.exists() || !newDir.isDirectory()) newDir.mkdirs();

        File oldPicture = new File(picture.getPath());
        File newPicture = new File(newDir.getAbsolutePath() + File.separator + oldPicture.getName());
        if (oldPicture.equals(newPicture)) return oldPicture.getAbsolutePath();

        FileUtils.moveFile(oldPicture, newPicture);

        return newPicture.getAbsolutePath();
    }

    /**
     * 修改图片类别，并移动到对应类别
     *
     * @param bloggerId 博主id
     * @param pictureId 图片id
     * @param category  类别
     * @return 移动了返回true
     */
    public boolean moveImageAndUpdateDbIfNecessary(int bloggerId, int pictureId, BloggerPictureCategoryEnum category)
            throws IOException {
        if (pictureId <= 0 || category == null) return false;

        BloggerPicture picture = pictureDao.getPictureById(pictureId);
        if (picture == null || picture.getCategory() == category.getCode()) return false;

        String newPath = moveImage(picture, bloggerId, category);
        picture.setPath(newPath);
        picture.setCategory(category.getCode());
        return pictureDao.update(picture) > 0;

    }

    /**
     * 检查图片类别，如果新的图片为PRIVATE，则修改为PUBLIC，并移动文件，同时将其useCount++，旧图片的useCount--
     *
     * @param bloggerId    博主id
     * @param newPictureId 新图片id
     * @param oldPictureId 旧图片id
     * @return 操作成功返回true
     */
    public boolean moveImageAndUpdateDbAndUseCountIfNecessary(int bloggerId, int newPictureId, int oldPictureId) throws IOException {

        if (newPictureId == oldPictureId) return false;

        // 将新图片指向图片修改为公开并移动目录（需要的话）
        if (newPictureId > 0) {
            // UPDATE: 2018/1/28 更新 是否“公开”图片由 Service 决定，而不是方法最终调用处
            BloggerPicture picture = pictureDao.getPictureById(newPictureId);
            // 私有图片才有必要对齐进行“公开”处理
            if (picture.getCategory().equals(BloggerPictureCategoryEnum.PRIVATE.getCode()))
                moveImageAndUpdateDbIfNecessary(bloggerId, newPictureId, PUBLIC);

            //新图片useCount++
            pictureDao.updateUseCountPlus(newPictureId);
        }

        //旧图片useCount--
        if (oldPictureId > 0) {
            pictureDao.updateUseCountMinus(oldPictureId);
        }

        return true;
    }

    /**
     * 新增操作可能指定了新的图片引用
     *
     * @param bloggerId 博主id
     * @param pictureId 图片id
     * @return 操作成功返回true
     */
    public boolean imageInsertHandle(int bloggerId, int pictureId) {
        if (pictureId < 0) return false;

        try {
            return moveImageAndUpdateDbAndUseCountIfNecessary(bloggerId, pictureId, -1);
        } catch (IOException e) {
            e.printStackTrace();
            throw new InternalIOException(e);
        }
    }

    /**
     * 更新操作可能指定了新的图片引用
     *
     * @param bloggerId    博主id
     * @param newPictureId 新图片id
     * @param oldPictureId 旧图片id
     * @return 操作成功返回true
     */
    public boolean imageUpdateHandle(int bloggerId, int newPictureId, Integer oldPictureId) {

        if (newPictureId > 0 && !Integer.valueOf(newPictureId).equals(oldPictureId)) {
            try {
                return moveImageAndUpdateDbAndUseCountIfNecessary(bloggerId, newPictureId,
                        Optional.ofNullable(oldPictureId).orElse(-1));
            } catch (IOException e) {
                e.printStackTrace();
                throw new InternalIOException(e);
            }
        }

        return false;
    }

}
