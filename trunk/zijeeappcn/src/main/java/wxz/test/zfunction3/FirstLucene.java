package wxz.test.zfunction3;

import java.io.File;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.queryParser.MultiFieldQueryParser;
import org.apache.lucene.queryParser.QueryParser;
import org.apache.lucene.search.Filter;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.util.Version;

/**
 * @author xudongwang 2012-2-2
 * 
 *         Email:xdwangiflytek@gmail.com
 */
public class FirstLucene {

    /**
     * 源文件路径
     */
    private String filePath01 = "F:\\Workspaces\\workspaceSE\\BlogDemo\\luceneDatasource\\HelloLucene01.txt";
    private String filePath02 = "F:\\Workspaces\\workspaceSE\\BlogDemo\\luceneDatasource\\HelloLucene02.txt";
    private String filePath03 = "F:\\Workspaces\\workspaceSE\\BlogDemo\\luceneDatasource\\HelloLucene03.txt";

    /**
     * 索引路径
     */
    private String indexPath = "F:\\Workspaces\\workspaceSE\\BlogDemo\\luceneIndex";

    /**
     * 分词器，这里我们使用默认的分词器,标准分析器（好几个，但对中文的支持都不好）
     */
    private Analyzer analyzer = new StandardAnalyzer(Version.LUCENE_36);

    /**
     * 创建索引
     * 
     * @throws Exception
     */
    public void createIndex() throws Exception {
        Document document01 = File2Document.file2Document(filePath01);// 要进行索引的单元
        Document document02 = File2Document.file2Document(filePath02);
        Document document03 = File2Document.file2Document(filePath03);

        // 将Document添加到索引库中

        File indexFile = new File(indexPath);
        Directory directory = FSDirectory.open(indexFile);
        // IndexWriter是用来操作（增、删、改）索引库的
        // true，表示每次都创建新的，有了就删掉再创建
        IndexWriterConfig conf = new IndexWriterConfig(Version.LUCENE_35,
                analyzer);
        IndexWriter indexWriter = new IndexWriter(directory, conf);
        indexWriter.addDocument(document01);
        indexWriter.addDocument(document02);
        indexWriter.addDocument(document03);
        indexWriter.close();// 涉及到资源的都需要释放
    }


    /**
     * 搜索
     * 
     * @param queryStr
     *            搜索的关键词
     * @throws Exception
     */
    public void search(String queryStr) throws Exception {

        // 1、把要搜索的文本解析为Query对象
        // 指定在哪些字段查询
        String[] fields = { "name", "content" };
        // QueryParser: 是一个解析用户输入的工具，可以通过扫描用户输入的字符串，生成Query对象。
        QueryParser queryParser = new MultiFieldQueryParser(Version.LUCENE_35,
                fields, analyzer);
        // Query:查询，lucene中支持模糊查询，语义查询，短语查询，组合查询等等,如有TermQuery,BooleanQuery,RangeQuery,WildcardQuery等一些类。
        Query query = queryParser.parse(queryStr);
        // 2、进行查询
        File indexFile = new File(indexPath);

        // IndexSearcher 是用来在索引库中进行查询的
        // IndexSearcher indexSearcher = new
        // IndexSearcher(FSDirectory.open(indexFile));
        Directory directory = FSDirectory.open(indexFile);
        IndexReader indexReader = IndexReader.open(directory);
        IndexSearcher indexSearcher = new IndexSearcher(indexReader);
        // Filter 过滤器，我们可以将查出来的结果进行过滤，可以屏蔽掉一些不想给用户看到的内容
        Filter filter = null;
        // 10000表示一次性在数据库中查询多少个文档
        // topDocs 类似集合
        TopDocs topDocs = indexSearcher.search(query, filter, 10000);
        System.out.println("总共有【" + topDocs.totalHits + "】条匹配的结果");// 注意这里的匹配结果是指文档的个数，而不是文档中包含搜索结果的个数
        // 3、打印结果
        for (ScoreDoc scoreDoc : topDocs.scoreDocs) {
            int docSn = scoreDoc.doc;// 文档内部编号
            Document document = indexSearcher.doc(docSn);// 根据文档编号取出相应的文档
            File2Document.printDocumentInfo(document);// 打印出文档信息
        }
    }

    public static void main(String[] args) throws Exception {
        FirstLucene lucene = new FirstLucene();
        //lucene.createIndex();
        lucene.search("other");
        System.out.println("---------------------------");
        lucene.search("iteye");
        System.out.println("---------------------------");
        lucene.search("too");
        System.out.println("---------------------------");
    }

}
