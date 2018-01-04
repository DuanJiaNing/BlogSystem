package com.duan.blogos.service.impl.blogger.profile;

import com.duan.blogos.dao.blogger.BloggerPictureDao;
import com.duan.blogos.entity.blogger.BloggerPicture;
import com.duan.blogos.enums.BloggerPictureCategoryEnum;
import com.duan.blogos.exception.BaseRuntimeException;
import com.duan.blogos.manager.ImageManager;
import com.duan.blogos.service.blogger.profile.GalleryService;
import com.duan.blogos.util.ImageUtils;
import com.duan.blogos.util.StringUtils;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.RequestContext;

import java.io.IOException;
import java.sql.Timestamp;

/**
 * Created on 2017/12/19.
 *
 * @author DuanJiaNing
 */
@Service
public class GalleryServiceImpl implements GalleryService {

    @Autowired
    private BloggerPictureDao pictureDao;

    @Autowired
    private ImageManager imageManager;

    @Override
    public int insertPicture(int bloggerId, String path, String bewrite, BloggerPictureCategoryEnum category, String title) {
        BloggerPicture picture = new BloggerPicture();
        picture.setBewrite(bewrite);
        picture.setBloggerId(bloggerId);
        picture.setCategory(category.getCode());
        picture.setPath(path);
        picture.setTitle(title);
        int effect = pictureDao.insert(picture);
        if (effect <= 0) return -1;

        return picture.getId();
    }

    @Override
    public int updateUniquePicture(int bloggerId, String bewrite, String path, BloggerPictureCategoryEnum cate, String title) {

        int uniqueCate = cate.getCode();
        BloggerPicture picture = pictureDao.getPictureByCategory(bloggerId, uniqueCate);
        if (picture == null) {
            return insertPicture(bloggerId, path, bewrite, cate, title);
        } else {
            picture.setBewrite(bewrite);
            picture.setBloggerId(bloggerId);
            picture.setCategory(uniqueCate);
            picture.setPath(path);
            picture.setTitle(title);
            picture.setUploadDate(new Timestamp(System.currentTimeMillis()));

            pictureDao.update(picture);
        }

        return pictureDao.getPictureIdByUniqueCategory(uniqueCate);
    }

    @Override
    public int insertPicture(MultipartFile file, int bloggerId, String bewrite, BloggerPictureCategoryEnum category,
                             String title) {

        int cate = category.getCode();
        String path;
        int id;

        //保存到磁盘
        try {
            path = imageManager.saveImageToDisk(file, bloggerId, cate);
        } catch (IOException e) {
            e.printStackTrace();
            return -1;
        }
        if (path == null) return -1;

        //数据库新增记录
        String ti = StringUtils.isEmpty(title) ? ImageUtils.getImageName(path) : title;
        if (BloggerPictureCategoryEnum.isUniqueCategory(cate)) { //唯一类别
            id = updateUniquePicture(bloggerId, bewrite, path, category, ti);
        } else {
            id = insertPicture(bloggerId, path, bewrite, category, ti);
        }

        return id;
    }

    @Override
    public boolean deletePicture(int pictureId, boolean deleteOnDisk) {
        BloggerPicture picture = getPicture(pictureId);
        String path = picture.getPath();

        //删除数据库记录
        int effect = pictureDao.delete(pictureId);
        if (effect <= 0) return false;

        if (deleteOnDisk) {
            //删除磁盘文件
            boolean succ = imageManager.deleteImageFromDisk(path);
            // 删除失败时抛出异常，主要目的为使数据库事务回滚
            if (!succ) throw new BaseRuntimeException("delete image fail");
        }

        return true;
    }

    @Override
    public boolean updatePictureCategory(int pictureId, BloggerPictureCategoryEnum newCategory) {
        return false;
    }

    @Override
    public BloggerPicture getPicture(int pictureId) {
        return pictureDao.getPictureById(pictureId);
    }

    @Override
    public BloggerPicture getPicture(int pictureId, int bloggerId) {
        BloggerPicture picture = pictureDao.getPictureById(pictureId);
        if (picture == null || !picture.getBloggerId().equals(bloggerId)) return null;

        return picture;
    }

    @Override
    public boolean getPictureForCheckExist(int pictureId) {
        return true;
    }

    @Override
    public BloggerPicture getPictureByPropertiesCategory(int category) {
        return pictureDao.getPictureByPropertiesCategory(category);
    }

}
