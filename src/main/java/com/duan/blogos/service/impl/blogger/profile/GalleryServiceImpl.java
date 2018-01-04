package com.duan.blogos.service.impl.blogger.profile;

import com.duan.blogos.dao.blogger.BloggerPictureDao;
import com.duan.blogos.entity.blogger.BloggerPicture;
import com.duan.blogos.enums.BloggerPictureCategoryEnum;
import com.duan.blogos.service.blogger.profile.GalleryService;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    public BloggerPicture deletePicture(int pictureId, boolean deleteOnDisk) {
        return null;
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
        if (picture == null || picture.getBloggerId() != bloggerId) return null;

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

}
