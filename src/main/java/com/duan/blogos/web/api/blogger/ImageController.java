package com.duan.blogos.web.api.blogger;

import com.duan.blogos.entity.blogger.BloggerPicture;
import com.duan.blogos.enums.BloggerPictureCategoryEnum;
import com.duan.blogos.manager.ImageUploadManager;
import com.duan.blogos.result.ResultBean;
import com.duan.blogos.service.blogger.profile.GalleryService;
import com.duan.blogos.util.ImageUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * Created on 2018/1/2.
 * 图片上传，下载
 *
 * @author DuanJiaNing
 */
@Controller
@RequestMapping("/image/{bloggerId}")
public class ImageController extends BaseBloggerController {

    @Autowired
    private GalleryService galleryService;

    @Autowired
    private ImageUploadManager imageUploadManager;

    /**
     * 输出图片
     */
    @RequestMapping(value = "/{imageId}", method = RequestMethod.GET)
    public void get(HttpServletRequest request, HttpServletResponse response,
                    @PathVariable("bloggerId") Integer bloggerId,
                    @PathVariable("imageId") Integer imageId) {
        checkAccount(request, bloggerId);

        BloggerPicture picture = galleryService.getPicture(imageId, bloggerId);
        if (picture == null) {
            picture = galleryService.getPictureByPropertiesCategory(
                    BloggerPictureCategoryEnum.BLOGGER_DEFAULT_PICTURE.getCode());
        }

        try (ServletOutputStream os = response.getOutputStream()) {
            String path = picture.getPath();
            File image = new File(path);
            if (!image.exists()) handlerOperateFail(request);

            String type = ImageUtils.getImageMimeType(image.getName());
            if (type == null) handlerOperateFail(request);

            response.setContentType("image/" + type);

            BufferedImage read = ImageIO.read(image);
            ImageIO.write(read, type, os);
        } catch (IOException e) {
            e.printStackTrace();
            handlerOperateFail(request, e);
        }
    }

    /**
     * 上传图片
     */
    @RequestMapping(method = RequestMethod.POST)
    @ResponseBody
    public ResultBean upload(MultipartHttpServletRequest request,
                             @PathVariable("bloggerId") Integer bloggerId,
                             @RequestParam("category") Integer category,
                             @RequestParam(value = "bewrite", required = false) String bewrite,
                             @RequestParam(value = "title", required = false) String title) {
        checkAccount(request, bloggerId);

        MultipartFile file = request.getFile("image");// 与页面input的name相同
        int id = 0;
        if (ImageUtils.isImageFile(file)) {
            //保存到磁盘
            String path = null;
            try {
                path = imageUploadManager.saveImageToDisk(file, bloggerId, category);
            } catch (IOException e) {
                e.printStackTrace();
                handlerOperateFail(request);
            }
            if (path == null) handlerOperateFail(request);

            //数据库新增记录
            //TODO
            id = galleryService.insertPicture(bloggerId, bewrite, path,
                    BloggerPictureCategoryEnum.valueOf(category), title);
        }
        if (id <= 0) handlerOperateFail(request);

        return new ResultBean<>(id);
    }
}
