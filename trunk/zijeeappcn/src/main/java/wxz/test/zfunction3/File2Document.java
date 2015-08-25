package wxz.test.zfunction3;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;

import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.Field.Index;
import org.apache.lucene.document.Field.Store;

public class File2Document {
    /**
     * File--->Document
     * 
     * @param filePath
     *            File路径
     * 
     * @return Document对象
     */
    public static Document file2Document(String filePath) {
        // 文件要存放：name,content,size,path
        File file = new File(filePath);

        Document document = new Document();
        // Store.YES 是否存储 yes no compress(压缩之后再存)
        // Index 是否进行索引 Index.ANALYZED 分词后进行索引,NOT_ANALYZED 不索引，NOT_ANALYZED
        // 不分词直接索引
        document.add(new Field("name", file.getName(), Store.YES,
                Index.ANALYZED));
        document.add(new Field("content", readFileContent(file), Store.YES,
                Index.ANALYZED));
        document.add(new Field("size", String.valueOf(file.length()),
                Store.YES, Index.NOT_ANALYZED));// 不分词,但是有时需要索引,文件大小(int)转换成String
        document.add(new Field("path", file.getAbsolutePath(), Store.YES,
                Index.NOT_ANALYZED));// 不需要根据文件的路径来查询
        return document;
    }

    /**
     * 读取文件内容
     * 
     * @param file
     *            File对象
     * @return File的内容
     */
    private static String readFileContent(File file) {
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(
                    new FileInputStream(file)));
            StringBuffer content = new StringBuffer();
            try {
                for (String line = null; (line = reader.readLine()) != null;) {
                    content.append(line).append("\n");
                }
            } catch (IOException e) {

                e.printStackTrace();
            }
            return content.toString();
        } catch (FileNotFoundException e) {

            e.printStackTrace();
        }
        return null;
    }

    /**
     * <pre>
     * 获取name属性值的两种方法  
     * 1.Filed field = document.getFiled("name");  
     *         field.stringValue();  
     * 2.document.get("name");
     * </pre>
     * 
     * @param document
     */
    public static void printDocumentInfo(Document document) {
        System.out.println("name -->" + document.get("name"));
        System.out.println("content -->" + document.get("content"));
        System.out.println("path -->" + document.get("path"));
        System.out.println("size -->" + document.get("size"));
    }
}
