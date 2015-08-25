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
        // ���������ַ���������һ��xml�ļ�
        org.dom4j.io.OutputFormat format = org.dom4j.io.OutputFormat.createPrettyPrint();
        format.setEncoding("GBK");
        File file = new File("writeXML.xml");
        System.out.println(file.getAbsolutePath());
        // �����������
        XMLWriter output = new XMLWriter(new FileWriter(file), format);
        output.write(document);
        output.close();
        // format�ɼӿɲ��ӣ������ϵ�ʱ�����ɵ�xml�ļ���ͷΪ<?xml version="1.0" encoding="GB" ?>
        // ����Ϊ<?xml version="1.0" encoding="UTF-8" ?>
        // ��������CSDN���ͣ�ת�������������http://blog.csdn.net/fanyingnew/archive////3.aspx
    }
    
    public static void writeXML1() throws DocumentException, IOException {

        Document doc = org.dom4j.DocumentHelper.createDocument();
        // �����ڵ��������
        Element rootElement = doc.addElement("student");
        // �������ڵ����rootElement,��ǩ��Ϊstudent
        rootElement.setText("hello");
        // �Խڵ���������,�����ȡXML�ļ���ʱ����԰���ȡ����
        rootElement.addAttribute("id", "1");
        // ���ýڵ�����
        Element name = rootElement.addElement("name");
        // ����ӽڵ�
        name.setText("xiaozhang");
        // �����ӽڵ�����
        Element age = rootElement.addElement("age");
        age.setText("");
        OutputFormat fmt = new OutputFormat();
        // ���������ʽ����
        fmt.setEncoding("GBK");
        XMLWriter writer = new XMLWriter(fmt);
        // �������ʽΪ����,����XML�ļ��������
        File file = new File("writeXML1.xml");
        OutputStream out = new FileOutputStream(file.getAbsolutePath());
        // ���������..
        writer.setOutputStream(out);
        // ���������
        writer.write(doc);
        // ���doc����,���γ�XML�ļ�
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
     * Dom4jͨ��XMLWriter����Document�����ʾ��XML��д��һ���ļ���
     * ��ʹ��OutputFormat��ʽ����ָ��д��ķ��ͱ��뷽��
     * ����OutputFormat.createPrettyPrint()�������Ի��һ��Ĭ�ϵ�pretty
     * print���ĸ�ʽ���󡣶�OutputFormat�������setEncoding()��������ָ��XML�ļ��ı��뷽����
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
