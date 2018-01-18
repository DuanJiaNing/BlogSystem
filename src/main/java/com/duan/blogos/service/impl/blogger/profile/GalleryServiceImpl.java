package com.duan.blogos.service.impl.blogger.profile;

import com.duan.blogos.dao.blogger.BloggerPictureDao;
import com.duan.blogos.entity.blogger.BloggerPicture;
import com.duan.blogos.enums.BloggerPictureCategoryEnum;
import com.duan.blogos.exception.internal.InternalIOException;
import com.duan.blogos.exception.internal.SQLException;
import com.duan.blogos.manager.BloggerPropertiesManager;
import com.duan.blogos.manager.ImageManager;
import com.duan.blogos.result.ResultBean;
import com.duan.blogos.service.blogger.profile.GalleryService;
import com.duan.blogos.util.CollectionUtils;
import com.duan.blogos.util.ImageUtils;
import com.duan.blogos.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

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

    @Autowired
    private BloggerPropertiesManager bloggerPropertiesManager;

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
    public int insertPicture(MultipartFile file, int bloggerId, String bewrite, BloggerPictureCategoryEnum category,
                             String title) {

        int cate = category.getCode();
        String path;

        //保存到磁盘
        try {
            path = imageManager.saveImageToDisk(file, bloggerId, cate);
        } catch (IOException e) {
            e.printStackTrace();
            return -1;
        }
        if (path == null) return -1;

        // 如果是图片管理员上传唯一（包括了头像）图片，需要移动其文件夹
        int pictureManagerId = bloggerPropertiesManager.getPictureManagerBloggerId();
        if (pictureManagerId == bloggerId && BloggerPictureCategoryEnum.isPictureManagerUniqueCategory(cate)) {
            // 如果设备上已经有该唯一图片，将原来的图片移到私有文件夹，同时修改数据库
            removeBloggerUniquePictureIfNecessary(bloggerId, category);
        } else if (BloggerPictureCategoryEnum.isBloggerUniqueCategory(cate)) {
            // 博主上传了博主唯一的图片，如头像
            removeBloggerUniquePictureIfNecessary(bloggerId, category);
        }

        //插入新纪录
        String ti = StringUtils.isEmpty(title) ? ImageUtils.getImageName(file.getOriginalFilename()) : title;
        return insertPicture(bloggerId, path, bewrite, category, ti);

    }

    /*
     * 腾地方
     * 移到唯一类图片到私有图片文件夹，同时修改数据库记录
     * @param bloggerId 博主id
     */
    private void removeBloggerUniquePictureIfNecessary(int bloggerId, BloggerPictureCategoryEnum uniqueCate) {
        BloggerPicture unique = pictureDao.getBloggerUniquePicture(bloggerId, uniqueCate.getCode());

        if (unique != null) {
            try {
                //移动原来的唯一图片到默认类别图片所在文件夹
                String newPath = imageManager.moveImage(unique, bloggerId, BloggerPictureCategoryEnum.PRIVATE);

                //更新数据库记录
                unique.setCategory(BloggerPictureCategoryEnum.PRIVATE.getCode());
                unique.setPath(newPath);
                pictureDao.update(unique);

            } catch (IOException e) {
                e.printStackTrace();
                // 移动文件出错，文件移动情况未知，麻烦大了
                // MAYBUG 回滚数据库操作，但磁盘操作无法预料，也无法处理
                throw new InternalIOException(e);
            }
        }

    }

    @Override
    public boolean deletePicture(int bloggerId, int pictureId, boolean deleteOnDisk) {

        BloggerPicture picture = getPicture(pictureId);

        // 对于唯一类别图片，图片管理员只能以更新（上传）的方式删除图片，因为这些图片必须时刻存在，头像除外
        int pictureManagerId = bloggerPropertiesManager.getPictureManagerBloggerId();
        int cate = picture.getCategory();
        if (bloggerId == pictureManagerId &&
                BloggerPictureCategoryEnum.isPictureManagerUniqueCategoryWithoutAvatar(cate))
            return false;

        //删除数据库记录
        String path = picture.getPath();
        int effect = pictureDao.delete(pictureId);
        if (effect <= 0) return false;

        if (deleteOnDisk) {
            //删除磁盘文件
            boolean succ = imageManager.deleteImageFromDisk(path);
            // 删除失败时抛出异常，使数据库事务回滚
            if (!succ) throw new InternalIOException();
        }

        return true;
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
        return pictureDao.getPictureById(pictureId) != null;
    }

    @Override
    public BloggerPicture getDefaultPicture(BloggerPictureCategoryEnum category) {
        return pictureDao.getBloggerUniquePicture(bloggerPropertiesManager.getPictureManagerBloggerId(),
                category.getCode());
    }

    @Override
    public ResultBean<List<BloggerPicture>> listBloggerPicture(int bloggerId, BloggerPictureCategoryEnum category,
                                                               int offset, int rows) {

        List<BloggerPicture> result;
        if (category == null) {
            result = pictureDao.listPictureByBloggerId(bloggerId, offset, rows);
        } else {
            result = pictureDao.listPictureByBloggerAndCategory(bloggerId, category.getCode(), offset, rows);
        }

        if (CollectionUtils.isEmpty(result)) return null;

        return new ResultBean<>(result);
    }

    @Override
    public boolean updatePicture(int pictureId, BloggerPictureCategoryEnum category, String bewrite, String title) {

        BloggerPicture oldPicture = pictureDao.getPictureById(pictureId);

        // 修改设备上图片路径，如果需要的话
        String newPath = null;
        if (category != null && category.getCode() != oldPicture.getCategory()) { // 修改了类别
            int bloggerId = oldPicture.getBloggerId();
            try {

                int pictureManagerId = bloggerPropertiesManager.getPictureManagerBloggerId();
                // 如果为图片管理员在操作
                if (pictureManagerId == bloggerId) {

                    // 以下两种情况将更新失败，对于唯一类别图片，且图片管理员在操作的情况下，要修改类别或删除图片，只能
                    // 以 普通 -> 唯一 的修改方向替换图片，因为这些图片必须时刻存在，头像视为普通类别

                    // 1 目标类别是唯一类别，原先类别也为唯一类别      唯一 -> 唯一
                    // 2 目标类别为普通类别，原先类别为唯一类别        唯一 -> 普通

                    int oldCategory = oldPicture.getCategory();
                    if ((BloggerPictureCategoryEnum.isPictureManagerUniqueCategoryWithoutAvatar(oldCategory) &&
                            BloggerPictureCategoryEnum.isPictureManagerUniqueCategoryWithoutAvatar(category.getCode())) ||
                            (BloggerPictureCategoryEnum.isPictureManagerUniqueCategoryWithoutAvatar(oldCategory) &&
                                    !BloggerPictureCategoryEnum.isPictureManagerUniqueCategoryWithoutAvatar(category.getCode()))) {
                        return false;
                    } else {

                        //腾位置，如果需要的话
                        removeBloggerUniquePictureIfNecessary(bloggerId, category);

                        //移动到目标目录
                        newPath = imageManager.moveImage(oldPicture, bloggerId, category);
                    }

                } else {
                    // 不是图片管理员则只需移动文件即可
                    newPath = imageManager.moveImage(oldPicture, bloggerId, category);
                }

            } catch (IOException e) {
                e.printStackTrace();
                throw new InternalIOException(e);
            }
        }

        BloggerPicture newPicture = new BloggerPicture();
        newPicture.setBewrite(bewrite);
        newPicture.setBloggerId(oldPicture.getBloggerId());
        newPicture.setCategory(category == null ? oldPicture.getCategory() : category.getCode());
        newPicture.setId(oldPicture.getId());
        newPicture.setTitle(title);
        newPicture.setPath(newPath == null ? oldPicture.getPath() : newPath);

        int effect = pictureDao.update(newPicture);
        if (effect <= 0) return false;

        return true;
    }

    @Override
    public void cleanBlogPicture(int bloggerId) {
        // TODO 在每次博文删除时清理博文中引用的图片以释放磁盘空间
    }

}
