package com.duan.blogos.manager;

import com.duan.blogos.entity.blog.Blog;
import com.duan.blogos.exception.internal.LuceneException;
import com.duan.blogos.manager.properties.WebsiteProperties;
import com.duan.blogos.util.StringUtils;
import org.apache.lucene.analysis.cn.smart.SmartChineseAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.StringField;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.*;
import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.*;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.file.Paths;

/**
 * Created on 2018/1/10.
 * 博文全文检索，通过标题、摘要、关键字、内容检索
 *
 * @author DuanJiaNing
 */
@Component
public class BlogLuceneIndexManager {

    private static final String INDEX_BLOG_ID = "blog_id";
    private static final String INDEX_BLOG_TITLE = "title";
    private static final String INDEX_BLOG_SUMMARY = "summary";
    private static final String INDEX_BLOG_KEY_WORDS = "key_words";
    private static final String INDEX_BLOG_CONTENT = "content";

    @Autowired
    private WebsiteProperties propertiesManager;

    /**
     * 获取IndexWriter实例
     */
    private IndexWriter getWriter() throws IOException {
        String path = propertiesManager.getLuceneIndexDir();
        // 创建一个索引位置
        Directory dir = FSDirectory.open(Paths.get(path));

        //文档被索引之前，首先需要对文档内容进行分词处理，并且而剔除一些冗余的词句（例如：a，the,they等),这部分工作就是
        // 由 Analyzer 来做的。
        SmartChineseAnalyzer analyzer = new SmartChineseAnalyzer();
        IndexWriterConfig iwc = new IndexWriterConfig(analyzer);

        //IndexWriter是在索引过程中的中心组件。IndexWriter这个类创建一个新的索引并且添加文档到一个已有的索引中。 你可以
        // 把IndexWriter 想象成让你可以对索引进行写操作的对象，但是不能让你读取或搜索。
        return new IndexWriter(dir, iwc);
    }

    /**
     * 添加博客索引
     *
     * @param blog 博客
     */
    public void add(Blog blog) throws IOException {
        IndexWriter writer = getWriter();

        //相当于数据库中的一条记录
        Document doc = new Document();
        if (checkAndAdd(doc, blog)) {
            removeHtmlFromBlogContent(blog);

            //添加索引记录
            writer.addDocument(doc);
            writer.close();
        }
    }

    // 将 blog content 中的 html 标签移除
    private void removeHtmlFromBlogContent(Blog blog) {
        String content = blog.getContent();
        String res = content.replaceAll("<.*?>", " ");
        blog.setContent(res);
    }

    // 检查blog用于创建索引的属性是否完备
    private boolean checkAndAdd(Document doc, Blog blog) {

        boolean res = false;

        //Field 相当于记录的字段，存储索引和元数据
        //yes是会将数据存进索引，如果查询结果中需要将记录显示出来就要存进去
        doc.add(new StringField(INDEX_BLOG_ID, blog.getId() + "", Field.Store.YES));

        String title = blog.getTitle();
        if (!StringUtils.isEmpty(title)) {
            doc.add(new TextField(INDEX_BLOG_TITLE, title, Field.Store.YES));
            res = true;
        }

        String summary = blog.getSummary();
        if (!StringUtils.isEmpty(summary)) {
            doc.add(new TextField(INDEX_BLOG_SUMMARY, summary, Field.Store.YES));
            res = true;
        }

        String keywords = blog.getKeyWords();
        if (!StringUtils.isEmpty(keywords)) {
            doc.add(new TextField(INDEX_BLOG_KEY_WORDS, keywords, Field.Store.YES));
            res = true;
        }

        String content = blog.getContent();
        if (!StringUtils.isEmpty(content)) {
            doc.add(new TextField(INDEX_BLOG_CONTENT, content, Field.Store.YES));
            res = true;
        }

        return res;
    }

    /**
     * 更新博客索引
     *
     * @param blog 博文
     */
    public void update(Blog blog) throws IOException {
        IndexWriter writer = getWriter();
        Document doc = new Document();

        if (checkAndAdd(doc, blog)) {
            removeHtmlFromBlogContent(blog);
            writer.updateDocument(new Term(INDEX_BLOG_ID, blog.getId() + ""), doc);
        }

        writer.close();
    }

    /**
     * 删除指定博客的索引
     *
     * @param blogId 博文id
     */
    public boolean delete(int blogId) {

        try (IndexWriter writer = getWriter()) {

            writer.deleteDocuments(new Term(INDEX_BLOG_ID, blogId + ""));
            writer.forceMergeDeletes(); // 强制删除
            writer.commit();

        } catch (IOException e) {
            throw new LuceneException(e);
        }

        return true;
    }

    /**
     * 搜索博文
     *
     * @param word  关键字
     * @param count 返回数量
     * @return 符合的博文id
     */
    public int[] search(String word, int count) throws IOException, ParseException {
        if (StringUtils.isEmpty(word) || count <= 0) return null;

        String path = propertiesManager.getLuceneIndexDir();

        Directory dir = FSDirectory.open(Paths.get(path));
        IndexReader reader = DirectoryReader.open(dir);
        IndexSearcher is = new IndexSearcher(reader);
        BooleanQuery.Builder booleanQuery = new BooleanQuery.Builder();
        SmartChineseAnalyzer analyzer = new SmartChineseAnalyzer();

        // 或 查询
        booleanQuery.add((new QueryParser(INDEX_BLOG_TITLE, analyzer)).parse(word), BooleanClause.Occur.SHOULD);
        booleanQuery.add((new QueryParser(INDEX_BLOG_KEY_WORDS, analyzer)).parse(word), BooleanClause.Occur.SHOULD);
        booleanQuery.add((new QueryParser(INDEX_BLOG_SUMMARY, analyzer)).parse(word), BooleanClause.Occur.SHOULD);
        booleanQuery.add((new QueryParser(INDEX_BLOG_CONTENT, analyzer)).parse(word), BooleanClause.Occur.SHOULD);

        //检索
        TopDocs top = is.search(booleanQuery.build(), count);

        Integer[] result = new Integer[count];
        int sum = 0;
        for (ScoreDoc doc : top.scoreDocs) {
            Document document = is.doc(doc.doc);
            result[sum++] = Integer.parseInt(document.get(INDEX_BLOG_ID));
        }
        if (sum == 0) return null;

        int[] rs = new int[sum];
        for (int i = 0; i < sum; i++) {
            rs[i] = result[i];
        }

        dir.close();
        analyzer.close();
        reader.close();

        return rs;
    }
}
