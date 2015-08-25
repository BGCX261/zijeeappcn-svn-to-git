package org.dianzi.xml.parser.dom4j;

import java.io.File;
import java.util.Iterator;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;

// dom4j与JDOM一样，也是一种用于解析XML文档的开放源代码的XML框架，dom4j也应用于java平台，dom4j API使用了java集合框架并完全支持DOM、SAX和JAXP。与JDOM不同的是，dom4j使用接口和抽象类，虽然dom4j的API相对复杂些，但它提供了比JDOM更好的灵活性。dom4j也使用SAX解析器来分析XML文档，创建dom4j树。此外dom4j也可以接收DOM格式的内容，并提供了从dom4j树到SAX事件流或W3C DOM树的输出机制。与JDOM不同，dom4j自带了一个SAX解析器Aelfred2，如果没有显示的设置SAX解析器，也没有通过系统属性org.xml.sax.driver设置解析器，dom3j将会使用JAXP来加载JAXP配置的解析器，如果创建解析器失败，那么最后才使用dom4j自带的Aelfred2解析器。

public class Dom4jModifier {
    
    @SuppressWarnings("unchecked")
    public Document modifyDocument(File inputXml) {
        try {
            SAXReader saxReader = new SAXReader();
            Document document = saxReader.read(inputXml);
            document.addDocType("dbconfig", null, "db.dtd");
            List list = document.content();
            // Iterator iter = document.nodeIterator();
            Iterator iter = list.iterator();

            Element element = (Element) iter.next();
            element.element("db").attribute("type").setValue("mysql");
            element.element("db").element("url").setText(
                    "jdbc:mysql://localhost/mySql");
            element.element("db").element("driver").setText(
                    "com.mysql.jdbc.Driver");
            element.element("db").element("user").setText("root");
            element.element("db").element("password").setText("xlc");

            // 设置输出格式
            OutputFormat outFmt = new OutputFormat("    ", true);
            outFmt.setEncoding("UTF-8");
            /**
             * write into a file
             * XMLWriter xmlWriter=new XMLWriter(new
             * java.io.FileWriter("domdb-modified.xml"),outFmt);
             */
            XMLWriter xmlWriter = new XMLWriter(System.out, outFmt);
            xmlWriter.write(document);
            xmlWriter.flush();
            xmlWriter.close();
            return document;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    public static void main(String[] args) throws Exception {
        Dom4jModifier dom4jParser = new Dom4jModifier();
        Document document = dom4jParser.modifyDocument(new File("domdb.xml"));

        OutputFormat outFmt = new OutputFormat("    ", true);
        outFmt.setEncoding("UTF-8");
        XMLWriter xmlWriter = new XMLWriter(System.out, outFmt);
        xmlWriter.write(document);
        xmlWriter.flush();
        xmlWriter.close();
    }
}