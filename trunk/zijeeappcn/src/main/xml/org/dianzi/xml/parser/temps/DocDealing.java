package org.dianzi.xml.parser.temps;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

//��ȡxml�ĳ���
public class DocDealing {
    public static void main(String[] args) throws Throwable {
        // 1����DocumentBuilderFactory DocumentBuilder
        DocumentBuilderFactory dbf = null;
        DocumentBuilder db = null;
        Document doc = null;
        // 2����DocumentBuilderFactory DocumentBuilder����
        dbf = DocumentBuilderFactory.newInstance();

        // 3����DocumentBuilder����
        db = dbf.newDocumentBuilder();
        // 4��ʼ�����������Document����
        // doc=db.parse("cdcatalog.xml");
        doc = db.parse("lqr.xml");
        // ��ʼ����Document

        NodeList nl = doc.getElementsByTagName("student");
        for (int i = 0; i < nl.getLength(); i++) {
            Node node = nl.item(i);
            System.out.println(node.getNodeName());
            System.out.println(node.getNodeValue());
            if (node instanceof Element) {
                Element ele = (Element) node;

                String str = ele.getAttribute("name");
                if (str != null)
                    System.out.println(str);
                String st = ele.getAttribute("sex");
                if (st != null)
                    System.out.println(st);
            }
        }
    }
}
