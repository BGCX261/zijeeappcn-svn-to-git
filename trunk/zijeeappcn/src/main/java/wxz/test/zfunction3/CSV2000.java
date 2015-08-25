package wxz.test.zfunction3;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.Field.Index;
import org.apache.lucene.document.Field.Store;
import org.apache.lucene.index.CorruptIndexException;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.index.Term;
import org.apache.lucene.queryParser.MultiFieldQueryParser;
import org.apache.lucene.queryParser.QueryParser;
import org.apache.lucene.search.BooleanClause;
import org.apache.lucene.search.BooleanQuery;
import org.apache.lucene.search.Filter;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.QueryWrapperFilter;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.search.WildcardQuery;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.store.LockObtainFailedException;
import org.apache.lucene.util.Version;

public class CSV2000 {

    static String fileName = "E:\\5000.csv";

    static String[] title = { " Name", "CardNo", "Descriot", "CtfTp", "CtfId", "Gender", "Birthday", "Address", "Zip", "Dirty", "District1",
            "District2", "District3", "District4", "District5", "District6", "FirstNm", "LastNm", "Duty", "Mobile", "Tel", "Fax", "EMail", "Nation",
            "Taste", "Education", "Company", "CTel", "CAddress", "CZip", "Family", "Version", "id" };

    static int count = 0;

    static String indexPath = "E:\\csv5000";
    private static Analyzer analyzer = new StandardAnalyzer(Version.LUCENE_36);

    private static String default_name = " Name";

    public static void main(String[] args) throws Exception {
        String queryStr = "王艳";
        String[] field = new String[] { default_name };
        search(queryStr);
    }

    public static void search(String queryStr) throws Exception {

        // 1、把要搜索的文本解析为Query对象
        // 指定在哪些字段查询
        // QueryParser: 是一个解析用户输入的工具，可以通过扫描用户输入的字符串，生成Query对象。
        QueryParser queryParser = new MultiFieldQueryParser(Version.LUCENE_36, title, analyzer);
        // Query:查询，lucene中支持模糊查询，语义查询，短语查询，组合查询等等,如有TermQuery,BooleanQuery,RangeQuery,WildcardQuery等一些类。
        // Query query = queryParser.parse(queryStr);

        
        
        BooleanQuery query = new BooleanQuery();
        for (int i = 0; i < queryStr.length(); i++) {
            QueryParser parser1 = new QueryParser(Version.LUCENE_36, default_name, new StandardAnalyzer(Version.LUCENE_36));
            query.add(parser1.parse(queryStr.charAt(i) + ""), BooleanClause.Occur.MUST);
        }

        System.out.println(query);

        // 2、进行查询
        File indexFile = new File(indexPath);

        // IndexSearcher 是用来在索引库中进行查询的
        Directory directory = FSDirectory.open(indexFile);
        IndexReader indexReader = IndexReader.open(directory);
        IndexSearcher indexSearcher = new IndexSearcher(indexReader);
        // Filter 过滤器，我们可以将查出来的结果进行过滤，可以屏蔽掉一些不想给用户看到的内容
        Filter filter =null;
            
          //使用过滤器   最后一个为true时包含边界部分，为false时不包含边界部分  
            //倒数第二个为true时，包含查询边界，为false时不包含  
//        TermRangeFilter filter2 = new TermRangeFilter("ename", new BytesRef("h"), new BytesRef("n"), true, true);
            // 范围过滤
//            Filter filter3=FieldCacheRangeFilter.newDoubleRange("price", 20D, 50D, true, true);  
            
        
          //使用QueryWrapperFilter进行过滤
//         filter=new QueryWrapperFilter(new WildcardQuery(new Term(default_name,"*."+queryStr+".*")));
            
            
        // 10000表示一次性在数据库中查询多少个文档
        // topDocs 类似集合
        TopDocs topDocs = indexSearcher.search(query, filter, 10000);
        System.out.println("总共有【" + topDocs.totalHits + "】条匹配的结果");// 注意这里的匹配结果是指文档的个数，而不是文档中包含搜索结果的个数
        // 3、打印结果
        for (ScoreDoc scoreDoc : topDocs.scoreDocs) {
            int docSn = scoreDoc.doc;// 文档内部编号
            Document document = indexSearcher.doc(docSn);// 根据文档编号取出相应的文档
            for (int i = 0; i < title.length; i++) {
                System.out.print(title[i] + ":" + document.get(title[i]) + ",");
            }
            System.out.println();
        }
    }

    private static void importData() throws IOException, CorruptIndexException, LockObtainFailedException, FileNotFoundException {
        count = title.length;

        File indexFile = new File(indexPath);
        Directory directory = FSDirectory.open(indexFile);
        // IndexWriter是用来操作（增、删、改）索引库的
        // true，表示每次都创建新的，有了就删掉再创建
        IndexWriterConfig conf = new IndexWriterConfig(Version.LUCENE_36, analyzer);
        IndexWriter indexWriter = new IndexWriter(directory, conf);

        File file = new File(fileName);
        System.out.println("以行为单位读取文件内容，一次读一整行：");
        BufferedReader reader = new BufferedReader(new FileReader(file));
        String tempString = null;
        int line = 1;
        // 一次读入一行，直到读入null为文件结束
        while ((tempString = reader.readLine()) != null) {
            String[] a = tempString.split(",");
            Document document = new Document();
            if (a.length == count) {
                for (int i = 0; i < count; i++) {
                    document.add(new Field(title[i], a[i], Store.YES, Index.ANALYZED));
                }
            } else {
                System.out.println("line " + line + ": " + tempString);
            }

            indexWriter.addDocument(document);
            if (line % 50 == 0) {
                indexWriter.commit();
            }
            line++;
        }

        reader.close();

        indexWriter.commit();
        indexWriter.close();// 涉及到资源的都需要释放
        System.out.println("--->end<---");
    }
}
