package com.duan.blogos.web.api.blogger;

import com.duan.blogos.entity.blogger.BloggerPicture;
import com.duan.blogos.enums.BloggerPictureCategoryEnum;
import com.duan.blogos.service.blogger.profile.GalleryService;
import com.duan.blogos.util.ImageUtils;
import org.omg.CORBA_2_3.portable.OutputStream;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import static com.duan.blogos.util.ImageUtils.getType;

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

    /**
     * 输出图片
     */
    @RequestMapping(value = "/{imageId}", method = RequestMethod.GET)
    public void get(HttpServletRequest request, HttpServletResponse response,
                    @PathVariable("bloggerId") Integer bloggerId,
                    @PathVariable("imageId") Integer imageId) {
        checkAccount(request, bloggerId);

        BloggerPicture picture = galleryService.getPicture(imageId);
        if (picture == null) {
            picture = galleryService.getPictureByPropertiesCategory(
                    BloggerPictureCategoryEnum.BLOGGER_DEFAULT_PICTURE.getCode());
        }

        try (ServletOutputStream os = response.getOutputStream()) {
            String imagePath = picture.getPath();
            String type = getType(imagePath);
            response.setContentType("image/" + type);
            File image = new File(imagePath);
            BufferedImage read = ImageIO.read(image);
            ImageIO.write(read, type, os);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @RequestMapping(method = RequestMethod.POST)
    public void upload(HttpServletRequest request,
                       @PathVariable("bloggerId") Integer bloggerId,
                       @RequestParam("bewrite") String bewrite,
                       @RequestParam("category") Integer category,
                       @RequestParam("title") String title) {

    }

}
