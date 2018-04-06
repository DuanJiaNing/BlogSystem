package com.duan.blogos.service.impl.blogger;

import com.duan.blogos.dao.blog.BlogCategoryDao;
import com.duan.blogos.dao.blog.BlogStatisticsDao;
import com.duan.blogos.dao.blogger.BloggerPictureDao;
import com.duan.blogos.dto.blog.BlogTitleIdDTO;
import com.duan.blogos.dto.blogger.BlogListItemDTO;
import com.duan.blogos.entity.blog.Blog;
import com.duan.blogos.entity.blog.BlogCategory;
import com.duan.blogos.entity.blog.BlogStatistics;
import com.duan.blogos.enums.BlogFormatEnum;
import com.duan.blogos.enums.BlogStatusEnum;
import com.duan.blogos.enums.BloggerPictureCategoryEnum;
import com.duan.blogos.exception.internal.InternalIOException;
import com.duan.blogos.exception.internal.LuceneException;
import com.duan.blogos.exception.internal.SQLException;
import com.duan.blogos.manager.DataFillingManager;
import com.duan.blogos.manager.FileManager;
import com.duan.blogos.manager.ImageManager;
import com.duan.blogos.manager.properties.BloggerProperties;
import com.duan.blogos.manager.properties.WebsiteProperties;
import com.duan.blogos.restful.ResultBean;
import com.duan.blogos.service.BlogFilterAbstract;
import com.duan.blogos.service.blogger.BloggerBlogService;
import com.duan.blogos.util.CollectionUtils;
import com.duan.blogos.util.FileUtils;
import com.duan.blogos.util.StringUtils;
import com.vladsch.flexmark.ast.Document;
import com.vladsch.flexmark.html.HtmlRenderer;
import com.vladsch.flexmark.parser.Parser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.nio.charset.Charset;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.IntStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipOutputStream;

import static com.duan.blogos.enums.BlogFormatEnum.MD;
import static com.duan.blogos.enums.BlogStatusEnum.PUBLIC;

/**
 * Created on 2017/12/19.
 * 博主检索博文
 *
 * @author DuanJiaNing
 */
@Service
public class BloggerBlogServiceImpl extends BlogFilterAbstract<ResultBean<List<BlogListItemDTO>>> implements BloggerBlogService {

    @Autowired
    private BlogStatisticsDao statisticsDao;

    @Autowired
    private WebsiteProperties websiteProperties;

    @Autowired
    private BloggerProperties bloggerProperties;

    @Autowired
    private DataFillingManager dataFillingManager;

    @Autowired
    private ImageManager imageManager;

    @Autowired
    private FileManager fileManager;

    @Autowired
    private BlogCategoryDao categoryDao;

    @Autowired
    private BloggerPictureDao pictureDao;

    @Override
    public int insertBlog(int bloggerId, int[] categories, int[] labels,
                          BlogStatusEnum status, String title, String content, String contentMd,
                          String summary, String[] keyWords, boolean analysisImg) {

        // 1 插入数据到bolg表
        String ch = dbProperties.getStringFiledSplitCharacterForNumber();
        String chs = dbProperties.getStringFiledSplitCharacterForString();
        Blog blog = new Blog();
        blog.setBloggerId(bloggerId);
        blog.setCategoryIds(StringUtils.intArrayToString(categories, ch));
        blog.setLabelIds(StringUtils.intArrayToString(labels, ch));
        blog.setState(status.getCode());
        blog.setTitle(title);
        blog.setContent(content);
        blog.setContentMd(contentMd);
        blog.setSummary(summary);
        blog.setKeyWords(StringUtils.arrayToString(keyWords, chs));
        blog.setWordCount(content.length());

        int effect = blogDao.insert(blog);
        if (effect <= 0) return -1;

        int blogId = blog.getId();

        // 2 插入数据到blog_statistics表（生成博文信息记录）
        BlogStatistics statistics = new BlogStatistics();
        statistics.setBlogId(blogId);
        effect = statisticsDao.insert(statistics);
        if (effect <= 0) throw new SQLException();

        if (analysisImg) {
            // 3 解析本地图片引用并使自增
            int[] imids = parseContentForImageIds(content, bloggerId);
            // UPDATE: 2018/1/19 更新 自增并没有实际作用
            if (!CollectionUtils.isEmpty(imids)) {
                // 修改图片可见性，引用次数
                Arrays.stream(imids).forEach(id -> imageManager.imageInsertHandle(bloggerId, id));
            }
        }

        // 4 lucene创建索引
        try {
            luceneIndexManager.add(blog);
        } catch (IOException e) {
            e.printStackTrace();
            throw new LuceneException(e);
        }

        return blogId;
    }

    // 解析博文中引用的相册图片
    private int[] parseContentForImageIds(String content, int bloggerId) {
        //http://localhost:8080/image/1/type=public/523?default=5
        //http://localhost:8080/image/1/type=private/1
        String regex = "http://" + websiteProperties.getAddr() + "/image/" + bloggerId + "/.*?/(\\d+)";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(content);

        List<String> res = new ArrayList<>();
        while (matcher.find()) {
            String str = matcher.group();
            int index = str.lastIndexOf("/");
            res.add(str.substring(index + 1));
        }

        return res.stream()
                .mapToInt(Integer::valueOf)
                .distinct()
                .toArray();
    }

    @Override
    public boolean updateBlog(int bloggerId, int blogId, int[] newCategories, int[] newLabels, BlogStatusEnum newStatus,
                              String newTitle, String newContent, String newContentMd, String newSummary, String[] newKeyWords) {

        // 1 更新博文中引用的本地图片（取消引用的useCount--，新增的useCount++）
        Blog oldBlog = blogDao.getBlogById(blogId);
        if (newContent != null) {
            if (!oldBlog.getContent().equals(newContent)) {

                final int[] oldIids = parseContentForImageIds(oldBlog.getContent(), bloggerId); // 1 2 3 4
                final int[] newIids = parseContentForImageIds(newContent, bloggerId); // 1 3 4 6

                // 求交集 1 3 4
                int[] array = IntStream.of(oldIids).filter(value -> {
                    for (int id : newIids) if (id == value) return true;
                    return false;
                }).toArray();

                // -- 2
                int[] allM = new int[oldIids.length + array.length];
                System.arraycopy(oldIids, 0, allM, 0, oldIids.length);
                System.arraycopy(array, 0, allM, oldIids.length, array.length);
                IntStream.of(allM).distinct().forEach(pictureDao::updateUseCountMinus);

                // ++ 6
                int[] allP = new int[newIids.length + array.length];
                System.arraycopy(newIids, 0, allP, 0, newIids.length);
                System.arraycopy(array, 0, allP, newIids.length, array.length);
                IntStream.of(allP).distinct().forEach(id -> {
                    pictureDao.updateUseCountPlus(id);

                    // 将用到的图片修改为public（有必要的话）
                    try {
                        imageManager.moveImageAndUpdateDbIfNecessary(bloggerId, id, BloggerPictureCategoryEnum.PUBLIC);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                });
            }
        }

        // 2 更新博文
        String ch = dbProperties.getStringFiledSplitCharacterForNumber();
        String chs = dbProperties.getStringFiledSplitCharacterForString();
        Blog blog = new Blog();
        blog.setId(blogId);
        if (newCategories != null) blog.setCategoryIds(StringUtils.intArrayToString(newCategories, ch));
        if (newLabels != null) blog.setLabelIds(StringUtils.intArrayToString(newLabels, ch));
        // 博文未通过审核时不能修改状态
        if (newStatus != null && !oldBlog.getState().equals(BlogStatusEnum.VERIFY.getCode()))
            blog.setState(newStatus.getCode());
        if (newTitle != null) blog.setTitle(newTitle);
        if (newContent != null) blog.setContent(newContent);
        if (newSummary != null) blog.setSummary(newSummary);
        if (newContentMd != null) blog.setContentMd(newContentMd);
        if (newKeyWords != null) blog.setKeyWords(StringUtils.arrayToString(newKeyWords, chs));
        int effect = blogDao.update(blog);
        if (effect <= 0) throw new SQLException();

        // 3 更新lucene
        try {
            luceneIndexManager.update(blog);
        } catch (IOException e) {
            e.printStackTrace();
            throw new LuceneException(e);
        }

        return true;
    }

    @Override
    public boolean deleteBlog(int bloggerId, int blogId) {

        Blog blog = blogDao.getBlogById(blogId);
        if (blog == null) return false;

        // 1 删除博文记录
        int effect = blogDao.delete(blogId);
        if (effect <= 0) return false;

        // 2 删除统计信息
        int effectS = statisticsDao.deleteByUnique(blogId);
        // MAYBUG 断点调试时effectS始终为0，但最终事务提交时记录却会正确删除，？？？ 因而注释下面的判断
        //if (effectS <= 0) throw new UnknownException(blog");

        // 3 图片引用useCount--
        int[] ids = parseContentForImageIds(blog.getContent(), bloggerId);
        if (!CollectionUtils.isEmpty(ids))
            Arrays.stream(ids).forEach(pictureDao::updateUseCountMinus);

        // 4 删除lucene索引
        luceneIndexManager.delete(blogId);

        return true;
    }

    @Override
    public boolean deleteBlogPatch(int bloggerId, int[] blogIds) {

        for (int id : blogIds) {
            if (!deleteBlog(bloggerId, id))
                throw new SQLException();
        }

        return true;
    }

    @Override
    public boolean getBlogForCheckExist(int blogId) {
        return !(blogDao.getBlogIdById(blogId) == null);
    }

    @Override
    public ResultBean<Blog> getBlog(int bloggerId, int blogId) {

        Blog blog = blogDao.getBlogById(blogId);

        String ch = dbProperties.getStringFiledSplitCharacterForNumber();
        String chs = dbProperties.getStringFiledSplitCharacterForString();
        String whs = websiteProperties.getUrlConditionSplitCharacter();

        if (blog != null && blog.getBloggerId().equals(bloggerId)) {

            String cids = blog.getCategoryIds();
            String lids = blog.getLabelIds();
            String keyWords = blog.getKeyWords();

            if (!StringUtils.isEmpty(cids))
                blog.setCategoryIds(cids.replace(ch, whs));

            if (!StringUtils.isEmpty(lids))
                blog.setLabelIds(lids.replace(ch, whs));

            if (!StringUtils.isEmpty_(keyWords))
                blog.setKeyWords(keyWords.replace(chs, whs));

            return new ResultBean<>(blog);

        }

        return null;
    }

    @Override
    protected ResultBean<List<BlogListItemDTO>> constructResult(Map<Integer, Blog> blogHashMap,
                                                                List<BlogStatistics> statistics,
                                                                Map<Integer, int[]> blogIdMapCategoryIds,
                                                                Map<Integer, String> blogImgs) {
        // 重组结果
        List<BlogListItemDTO> result = new ArrayList<>();
        for (BlogStatistics s : statistics) {
            Integer blogId = s.getBlogId();
            int[] ids = blogIdMapCategoryIds.get(blogId);
            List<BlogCategory> categories = CollectionUtils.isEmpty(ids) ? null : categoryDao.listCategoryById(ids);
            Blog blog = blogHashMap.get(blogId);
            BlogListItemDTO dto = dataFillingManager.bloggerBlogListItemToDTO(blog, s, categories);
            result.add(dto);
        }

        return new ResultBean<>(result);
    }

    @Override
    public int getBlogId(int bloggerId, String blogName) {
        Integer id = blogDao.getBlogIdByUniqueKey(bloggerId, blogName);
        return Optional.ofNullable(id).orElse(-1);
    }

    @Override
    public List<BlogTitleIdDTO> insertBlogPatch(MultipartFile file, int bloggerId) {

        fileManager.mkdirsIfNotExist(bloggerProperties.getPatchImportBlogTempPath());

        // 保存到临时文件
        String fullPath = bloggerProperties.getPatchImportBlogTempPath() +
                File.separator +
                "temp-" +
                bloggerId +
                "-" +
                System.currentTimeMillis() +
                "-" +
                file.getOriginalFilename();

        FileUtils.saveFileTo(file, fullPath);

        // 解析博文
        List<BlogTitleIdDTO> result = new ArrayList<>();
        final Parser parser = Parser.builder().build();
        final HtmlRenderer renderer = HtmlRenderer.builder().build();

        ZipFile zipFile = null;
        try {
            zipFile = new ZipFile(fullPath);
            Enumeration<? extends ZipEntry> entries = zipFile.entries();
            while (entries.hasMoreElements()) {
                ZipEntry entry = entries.nextElement();
                BufferedInputStream stream = new BufferedInputStream(zipFile.getInputStream(entry));
                InputStreamReader reader = new InputStreamReader(stream, Charset.forName("UTF-8"));

                BlogTitleIdDTO node = analysisAndInsertMdFile(parser, renderer, entry, reader, bloggerId);
                if (node != null)
                    result.add(node);
            }

        } catch (IOException e) {
            throw new InternalIOException(e);
        } finally {
            if (zipFile != null) try {
                zipFile.close();

                // 删除临时文件
                fileManager.deleteFileIfExist(fullPath);

            } catch (IOException e) {
                e.printStackTrace();
                return null;
            }
        }

        return result;
    }

    @Override
    public String getAllBlogForDownload(int bloggerId, BlogFormatEnum format) {
        List<Blog> blogs = blogDao.listAllByFormat(bloggerId, format.getCode());
        if (CollectionUtils.isEmpty(blogs)) return null;

        fileManager.mkdirsIfNotExist(bloggerProperties.getPatchDownloadBlogTempPath());

        String zipFilePath = bloggerProperties.getPatchDownloadBlogTempPath() +
                File.separator +
                System.currentTimeMillis() +
                "-" +
                "total-of-" +
                blogs.size() +
                "-blogs.zip";

        File zipFile = new File(zipFilePath);
        List<String> tempBlogFile = new ArrayList<>();

        // 压缩博文
        ZipOutputStream zipOut = null;
        try {
            zipOut = new ZipOutputStream(new FileOutputStream(zipFile));
            for (Blog blog : blogs) {
                String path = addBlogToZip(blog, zipOut, format);
                if (path != null) {
                    tempBlogFile.add(path);
                }
            }
        } catch (IOException e) {
            throw new InternalIOException(e);
        } finally {
            if (zipOut != null) {
                try {
                    zipOut.close();
                } catch (IOException e) {
                    throw new InternalIOException(e);
                }
            }
        }

        // 统一删除临时博文文件
        tempBlogFile.forEach(fileManager::deleteFileIfExist);

        return zipFile.getAbsolutePath();
    }

    private String addBlogToZip(Blog blog, ZipOutputStream zipOut, BlogFormatEnum format) throws IOException {

        // 新建博文文件
        String title = blog.getTitle();
        String content = format == MD ? blog.getContentMd() : blog.getContent();

        String bp = bloggerProperties.getPatchDownloadBlogTempPath() +
                File.separator +
                title +
                (format == MD ? ".md" : ".html");

        FileOutputStream fo = new FileOutputStream(bp);
        OutputStreamWriter writer = new OutputStreamWriter(fo, "UTF-8");
        writer.write(content);
        writer.close();

        // 添加到 zip 压缩文件
        File entryFile = new File(bp);
        zipOut.putNextEntry(new ZipEntry(entryFile.getName()));

        BufferedInputStream bis = new BufferedInputStream(new FileInputStream(entryFile));
        byte[] buffer = new byte[1024];
        int len;
        while ((len = bis.read(buffer)) > 0) {
            zipOut.write(buffer, 0, len);
        }

        bis.close();
        zipOut.closeEntry();

        return bp;
    }

    // 解析 md 文件读取字符流，新增记录到数据库
    private BlogTitleIdDTO analysisAndInsertMdFile(Parser parser, HtmlRenderer renderer, ZipEntry entry, InputStreamReader reader, int bloggerId) throws IOException {

        if (!entry.getName().endsWith(".md")) return null;

        // 文件名作为标题
        String title = entry.getName().replace(".md", "");

        StringBuilder b = new StringBuilder((int) entry.getSize());
        int len = 0;
        char[] buff = new char[1024];
        while ((len = reader.read(buff)) > 0) {
            b.append(buff, 0, len);
        }

        // reader.close();
        // zip 文件关闭由 370行：zipFile.close() 统一关闭

        // 内容
        String mdContent = b.toString();

        // 对应的 html 内容
        Document document = parser.parse(mdContent);
        String htmlContent = renderer.render(document);

        // 摘要
        String firReg = htmlContent.replaceAll("<.*?>", ""); // 避免 subString 使有遗留的 html 标签，前端显示时会出错
        String tmpStr = firReg.length() > 500 ? firReg.substring(0, 500) : firReg;
        String aftReg = tmpStr.replaceAll("\\n", "");
        String summary = aftReg.length() > 200 ? aftReg.substring(0, 200) : aftReg;

        // UPDATE: 2018/4/4 更新 图片引用
        int id = insertBlog(bloggerId, null, null, PUBLIC, title, htmlContent, mdContent, summary, null, false);
        if (id < 0) return null;

        BlogTitleIdDTO node = new BlogTitleIdDTO();
        node.setTitle(title);
        node.setId(id);

        return node;
    }

}
