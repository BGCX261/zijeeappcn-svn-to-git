package org.dianzi.xml.parser.dom4j;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;

public class String2XML2 {

    /**
     * @param args
     * @throws DocumentException 
     */
    public static void main(String[] args) throws DocumentException {
     // ×Ö·û´®×ªXML
        StringBuffer xmlStr = new StringBuffer();
        xmlStr.append(        "<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
        xmlStr.append(      "<root available-locales=\"en_US\" default-locale=\"en_US\"><Name language-id=\"en_US\">Ô¤Ô¼¹ÒºÅ</Name></root>");
        Document document = DocumentHelper.parseText(xmlStr.toString());
        String str = document.selectSingleNode("/root/Name").getText().trim();
        System.out.println(str);
        
    }
}
