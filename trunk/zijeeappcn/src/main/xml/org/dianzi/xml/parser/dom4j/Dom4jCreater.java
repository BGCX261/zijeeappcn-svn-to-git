package org.dianzi.xml.parser.dom4j;

import java.io.IOException;

import org.dom4j.Document;
import org.dom4j.DocumentFactory;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.XMLWriter;

public class Dom4jCreater {
    
    public static void createXml() throws IOException{

        Document doc = DocumentHelper.createDocument();

        doc.addProcessingInstruction("xml-stylesheet",
                "type='text/xsl' href='db.xsl'");
        doc.addDocType("dbconfig", null, "db.dtd");

        // Element root=DocumentHelper.createElement("dbconfig");
        // doc.setRootElement(root);
        Element root = doc.addElement("dbconfig");

        Element eltDb = root.addElement("db");
        Element eltDriver = eltDb.addElement("driver");
        Element eltUrl = eltDb.addElement("url");
        Element eltUser = eltDb.addElement("user");
        Element eltPassword = eltDb.addElement("password");

        eltDriver.setText("com.mysql.jdbc.Driver");
        eltUrl.setText("jdbc:mysql://localhost/mySql");
        eltUser.setText("root");
        eltPassword.setText("xlc");
        eltDb.addAttribute("type", "mysql");
        // 设置输出格式
        OutputFormat outFmt = new OutputFormat("", true);
        outFmt.setEncoding("UTF-8");

        /**
         * write into a file
         * 
         * XMLWriter xmlWriter=new XMLWriter(new
         * FileWriter("db.xml"),outFmt);
         */
        XMLWriter xmlWriter = new XMLWriter(System.out, outFmt);
        doc.setRootElement(eltDb);// 设置根节点
        xmlWriter.write(doc);
        xmlWriter.flush();
        xmlWriter.close();
    }

    public static void createXml2() throws IOException{
        DocumentFactory factory  = DocumentFactory.getInstance();
        Document doc = factory.createDocument();
        Element root = doc.addElement("root");
        Element child = root.addElement("child");
        child.addAttribute("key", "value");
        
        doc.setRootElement(child);
        
        
        // 设置输出格式
        OutputFormat outFmt = new OutputFormat();
        XMLWriter xmlWriter = new XMLWriter(System.out, outFmt);
        xmlWriter.write(doc);
        xmlWriter.flush();
        xmlWriter.close();
    }
    
    
    public static void main(String[] args) throws Exception {
        createXml2();
    }
}