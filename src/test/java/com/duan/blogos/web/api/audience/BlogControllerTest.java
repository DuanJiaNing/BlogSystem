package com.duan.blogos.web.api.audience;

import com.duan.blogos.BaseTest;
import com.duan.blogos.util.DataProvider;
import com.duan.blogos.util.StringUtils;
import com.duan.blogos.web.api.blogger.BloggerBlogCategoryController;
import com.duan.blogos.web.api.blogger.BloggerBlogController;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.*;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

/**
 * Created on 2017/12/22.
 *
 * @author DuanJiaNing
 */
public class BlogControllerTest extends BaseTest {

    @Autowired
    private BloggerBlogController controller;

    @Autowired
    private BloggerBlogCategoryController categoryController;

    /**
     * 生成随机博文
     */
    @Test
    public void bloggerBlogList() {

        DataProvider provider = new DataProvider();
        int count = 20;
        for (int i = 0; i < count; i++) {
            controller.add(null, 1,
                    "1,2",
                    "2",
                    provider.title(),
                    provider.content(),
                    "",
                    provider.summary(),
                    StringUtils.arrayToString(provider.keyWords(), ","));

        }
    }


    @Test
    public void test() throws IOException {
        String path = "C:\\Users\\ai\\Desktop\\user-7460499-1519801403";
        List<Integer> cids = new ArrayList<>();
        cids.add(1);
        cids.add(2);
        cids.add(5);
        cids.add(9);

        List<Integer> lids = new ArrayList<>();
        lids.add(1);
        lids.add(2);
        lids.add(3);
        lids.add(5);
        int bloggerId = 1;

        File dir = new File(path);
        File[] files = dir.listFiles();

        Arrays.stream(files).forEach(f -> {
            int id = Integer.valueOf(categoryController.add(null, bloggerId, null, f.getName(),
                    null).getData().toString());
            cids.add(id);
        });

        for (File subDir : files) {
            for (File blog : subDir.listFiles()) {
                String title = blog.getName();

                BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(blog), Charset.forName("UTF-8")));

                StringBuilder builder = new StringBuilder(200 >> 10); // 200k
                char[] buffer = new char[1024];
                int len;
                while ((len = reader.read(buffer)) > 0) {
                    builder.append(buffer, 0, len);
                }

                String content = builder.toString();
                String summary = content.substring(0, 130);
                String keyWords = title.substring(0, 4);

                controller.add(null, 1, getIns(cids), getIns(lids), title, content, "", summary, keyWords);

            }
        }

    }

    private Random random = new Random();

    private String getIns(List<Integer> ids) {
        int count = random.nextInt(4);
        int[] c = new int[count];
        int i = 0;
        while (count-- > 0) {
            c[i++] = ids.get(random.nextInt(ids.size()));
        }

        return StringUtils.intArrayToString(c, ",");
    }
}