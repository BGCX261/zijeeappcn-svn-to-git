package org.dianzi.xml.parser.dom4j;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;

public class String2XML {

    /**
     * @param args
     * @throws DocumentException 
     */
    public static void main(String[] args) throws DocumentException {
     // �ַ���תXML
        String xmlStr = "......";
        StringBuffer buf = new StringBuffer();
        buf.append(        "<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
        buf.append(      "<root available-locales=\"en_US\" default-locale=\"en_US\"><Name language-id=\"en_US\">ԤԼ�Һ�</Name></root>");
        Document document = DocumentHelper.parseText(xmlStr);
        // XMLת�ַ���
        Document document1 = null;
        String text = document1.asXML();
    }
}
