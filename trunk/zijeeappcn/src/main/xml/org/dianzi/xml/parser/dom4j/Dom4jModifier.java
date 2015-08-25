package org.dianzi.xml.parser.dom4j;

import java.io.File;
import java.util.Iterator;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;

// dom4j��JDOMһ����Ҳ��һ�����ڽ���XML�ĵ��Ŀ���Դ�����XML��ܣ�dom4jҲӦ����javaƽ̨��dom4j APIʹ����java���Ͽ�ܲ���ȫ֧��DOM��SAX��JAXP����JDOM��ͬ���ǣ�dom4jʹ�ýӿںͳ����࣬��Ȼdom4j��API��Ը���Щ�������ṩ�˱�JDOM���õ�����ԡ�dom4jҲʹ��SAX������������XML�ĵ�������dom4j��������dom4jҲ���Խ���DOM��ʽ�����ݣ����ṩ�˴�dom4j����SAX�¼�����W3C DOM����������ơ���JDOM��ͬ��dom4j�Դ���һ��SAX������Aelfred2�����û����ʾ������SAX��������Ҳû��ͨ��ϵͳ����org.xml.sax.driver���ý�������dom3j����ʹ��JAXP������JAXP���õĽ��������������������ʧ�ܣ���ô����ʹ��dom4j�Դ���Aelfred2��������

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

            // ���������ʽ
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