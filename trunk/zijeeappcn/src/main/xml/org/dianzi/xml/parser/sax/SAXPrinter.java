package org.dianzi.xml.parser.sax;

import java.io.File;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

// org.xml.sax.DefalutHandler类:  可以扩展该类，给出自己的解析实现
//SAX处理机制：SAX是一种基于事件驱动的API。利用SAX解析XML文档，牵涉到两个部分：解析器和事件处理器。解析器负责读取XML文档，并向事件处理器发生事件，如元素开始和元素结束事件；而事件处理器则负责对事件做出响应，对传递的XML数据进行处理。
//
//测试用的xml文件：db.xml

public class SAXPrinter extends DefaultHandler {

    /**
     * 文档开始事件
     */
    public void startDocument() throws SAXException {
        System.out.println("<?xml version=\"0\" encoding=\"utf-8\"?>");
    }

    /** */
    /**
     * 接收处理指令事件
     */
    public void processingInstruction(String target, String data)
            throws SAXException {
        System.out.println("<?" + target + " " + data + "?>");
    }


    /**
     * 元素开始事件 参数说明： uri - 名称空间 URI，如果元素没有任何名称空间 URI，或者没有正在执行名称空间处理，则为空字符串。
     * localName - 本地名称（不带前缀），如果没有正在执行名称空间处理，则为空字符串。 qName -
     * 限定的名称（带有前缀），如果限定的名称不可用，则为空字符串。 attributes - 附加到元素的属性。如果没有属性，则它将是空的
     * Attributes 对象。
     */
    public void startElement(String uri, String localName, String qName,
            Attributes attrs) throws SAXException {
        System.out.print("<" + qName);// 输出元素名称
        int len = attrs.getLength();// 元素属性列表长度

        // 利用循环输出属性列表
        for (int i = 0; i < len; i++) {
            System.out.print(" ");
            System.out.print(attrs.getQName(i));
            System.out.print("=\"");
            System.out.print(attrs.getValue(i));
            System.out.print("\"");
        }
        System.out.print(">");
    }

    /** */
    /**
     * 元素中字符数据事件：接收元素中字符数据 注意：应用程序不要试图读取ch数组指定范围外的数据，(即start至length之外)
     * 有些解析器将使用ignorableWhitespace
     * ()方法来报告元素内容中的空白，而不是characters()方法，如:进行有效性验证的解析器
     */
    public void characters(char[] ch, int start, int length)
            throws SAXException {
        System.out.print(new String(ch, start, length));
    }

    /**
     * 结束元素事件
     */
    public void endElement(String uri, String localName, String qName)
            throws SAXException {
        System.out.print("</" + qName + ">");
    }

    public static void main(String[] args) {
        SAXParserFactory spf = SAXParserFactory.newInstance();

        try {
            SAXParser sp = spf.newSAXParser();
            sp.parse(new File("db.xml"), new SAXPrinter());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}