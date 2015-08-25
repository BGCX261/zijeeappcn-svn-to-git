package org.dianzi.xml.parser.withDom4j;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStream;

import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamSource;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.DocumentResult;
import org.dom4j.io.DocumentSource;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.XMLWriter;

public class XmlTreeWriter {
    
    public static void writeXML() throws DocumentException, IOException{

        String xmlFile = "<xmlfile>TestWorld</xmlfile>";
        Document document = DocumentHelper.parseText(xmlFile);
        // 这样就有字符串生成了一个xml文件
        org.dom4j.io.OutputFormat format = org.dom4j.io.OutputFormat.createPrettyPrint();
        format.setEncoding("GBK");
        File file = new File("writeXML.xml");
        System.out.println(file.getAbsolutePath());
        // 定义输出流。
        XMLWriter output = new XMLWriter(new FileWriter(file), format);
        output.write(document);
        output.close();
        // format可加可不加，当加上的时候生成的xml文件的头为<?xml version="1.0" encoding="GB" ?>
        // 否则为<?xml version="1.0" encoding="UTF-8" ?>
        // 本文来自CSDN博客，转载请标明出处：http://blog.csdn.net/fanyingnew/archive////3.aspx
    }
    
    public static void writeXML1() throws DocumentException, IOException {

        Document doc = org.dom4j.DocumentHelper.createDocument();
        // 创建节点操作对象
        Element rootElement = doc.addElement("student");
        // 创建根节点对象rootElement,标签名为student
        rootElement.setText("hello");
        // 对节点设置内容,后面读取XML文件的时候可以把他取出来
        rootElement.addAttribute("id", "1");
        // 设置节点属性
        Element name = rootElement.addElement("name");
        // 添加子节点
        name.setText("xiaozhang");
        // 设置子节点内容
        Element age = rootElement.addElement("age");
        age.setText("");
        OutputFormat fmt = new OutputFormat();
        // 创建输出格式对象
        fmt.setEncoding("GBK");
        XMLWriter writer = new XMLWriter(fmt);
        // 以输出格式为参数,创建XML文件输出对象
        File file = new File("writeXML1.xml");
        OutputStream out = new FileOutputStream(file.getAbsolutePath());
        // 创建输出流..
        writer.setOutputStream(out);
        // 设置输出流
        writer.write(doc);
        // 输出doc对象,即形成XML文件
    }
    
    
    /**
     * Styling a Document with XSLT Applying XSLT _disibledevent="externalLink"
     * title="External Link" href="http://java.sun.com/xml/">JAXP API from Sun.
     * This allows you to work against any XSLT engine such as Xalan or SAXON.
     * Here is an example of using JAXP to create a transformer and then
     * applying it to a Document.
     */
    public Document styleDocument(Document document, String stylesheet)
            throws Exception {

        // load the transformer using JAXP
        TransformerFactory factory = TransformerFactory.newInstance();
        Transformer transformer = factory.newTransformer(new StreamSource(
                stylesheet));

        // now lets style the given document
        DocumentSource source = new DocumentSource(document);
        DocumentResult result = new DocumentResult();
        transformer.transform(source, result);

        // return the transformed document
        Document transformedDoc = result.getDocument();
        return transformedDoc;
    }

    /**
     * Dom4j通过XMLWriter将由Document对象表示的XML树写入一个文件，
     * 并使用OutputFormat格式对象指定写入的风格和编码方法
     * 调用OutputFormat.createPrettyPrint()方法可以获得一个默认的pretty
     * print风格的格式对象。对OutputFormat对象调用setEncoding()方法可以指定XML文件的编码方法。
     */
    public void writeXML3(Document doc, OutputStream out, String encoding)
            throws IOException {
        OutputFormat format = OutputFormat.createPrettyPrint();
        format.setEncoding("gb2312");
        XMLWriter writer = new XMLWriter(System.out, format);
        writer.write(doc);
        writer.flush();
        return;
    }

    
	/**
	 * @param args
	 * @throws IOException 
	 * @throws Exception 
	 */
    public static void main(String[] args) throws DocumentException,
            IOException {

        writeXML1();
    }
	
}
