package org.dianzi.xml.parser.sax;

import java.io.File;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

// org.xml.sax.DefalutHandler��:  ������չ���࣬�����Լ��Ľ���ʵ��
//SAX������ƣ�SAX��һ�ֻ����¼�������API������SAX����XML�ĵ���ǣ�浽�������֣����������¼��������������������ȡXML�ĵ��������¼������������¼�����Ԫ�ؿ�ʼ��Ԫ�ؽ����¼������¼�������������¼�������Ӧ���Դ��ݵ�XML���ݽ��д���
//
//�����õ�xml�ļ���db.xml

public class SAXPrinter extends DefaultHandler {

    /**
     * �ĵ���ʼ�¼�
     */
    public void startDocument() throws SAXException {
        System.out.println("<?xml version=\"0\" encoding=\"utf-8\"?>");
    }

    /** */
    /**
     * ���մ���ָ���¼�
     */
    public void processingInstruction(String target, String data)
            throws SAXException {
        System.out.println("<?" + target + " " + data + "?>");
    }


    /**
     * Ԫ�ؿ�ʼ�¼� ����˵���� uri - ���ƿռ� URI�����Ԫ��û���κ����ƿռ� URI������û������ִ�����ƿռ䴦����Ϊ���ַ�����
     * localName - �������ƣ�����ǰ׺�������û������ִ�����ƿռ䴦����Ϊ���ַ����� qName -
     * �޶������ƣ�����ǰ׺��������޶������Ʋ����ã���Ϊ���ַ����� attributes - ���ӵ�Ԫ�ص����ԡ����û�����ԣ��������ǿյ�
     * Attributes ����
     */
    public void startElement(String uri, String localName, String qName,
            Attributes attrs) throws SAXException {
        System.out.print("<" + qName);// ���Ԫ������
        int len = attrs.getLength();// Ԫ�������б���

        // ����ѭ����������б�
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
     * Ԫ�����ַ������¼�������Ԫ�����ַ����� ע�⣺Ӧ�ó���Ҫ��ͼ��ȡch����ָ����Χ������ݣ�(��start��length֮��)
     * ��Щ��������ʹ��ignorableWhitespace
     * ()����������Ԫ�������еĿհף�������characters()��������:������Ч����֤�Ľ�����
     */
    public void characters(char[] ch, int start, int length)
            throws SAXException {
        System.out.print(new String(ch, start, length));
    }

    /**
     * ����Ԫ���¼�
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