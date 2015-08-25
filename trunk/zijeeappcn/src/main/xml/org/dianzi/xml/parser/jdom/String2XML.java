package org.dianzi.xml.parser.jdom;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.StringReader;

import org.jdom.Document;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;
import org.jdom.output.Format;
import org.jdom.output.XMLOutputter;
import org.xml.sax.InputSource;

public class String2XML {

    /**
     * @param args
     * @throws IOException 
     * @throws JDOMException 
     */
    public static void main(String[] args) throws JDOMException, IOException {
        // �ַ���תXML
        String xmlStr = ".....";
        StringReader sr = new StringReader(xmlStr);
        InputSource is = new InputSource(sr);
        Document doc = (new SAXBuilder()).build(is);

        // XMLת�ַ���
        Format format = Format.getPrettyFormat();
        format.setEncoding("gb2312");// ����xml�ĵ����ַ�Ϊgb2312�������������
        XMLOutputter xmlout = new XMLOutputter(format);
        ByteArrayOutputStream bo = new ByteArrayOutputStream();
        xmlout.output(doc, bo);
        String xmlStrs = bo.toString();
    }

}
