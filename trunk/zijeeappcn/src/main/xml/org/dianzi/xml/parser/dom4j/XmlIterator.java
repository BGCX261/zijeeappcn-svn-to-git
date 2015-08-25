package org.dianzi.xml.parser.dom4j;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

/**
 * Do need dom4j.jar
 */
public class XmlIterator {

    @SuppressWarnings("unchecked")
    public void printXMLTree(Document doc) {
        // ȡ�ýڵ����
        Element root = doc.getRootElement();
        List arr = new ArrayList();
        printElement(root, 1, arr);// ���ڵ����Ϊ1
        for (int i = 0; i < arr.size(); i++) {
            System.out.println(arr.get(i));
        }
        return;
    }

    /**
     * ������ǩԪ�صĵ�������
     * 
     * @param element ��ǩԪ��
     * @param level Ԫ�����
     * @param resultList �����Ľ��
     * @return ����resultList ÿһ��tempList�����ĸ�Ԫ��
     * @get(0)����¼Ԫ�����ƣ�����ӡ<A>B</A>��A
     * @get()����ӡ�ñ�ǩ��ֵ������ӡ<A>B</A>��B
     * @get()��Ԫ�����
     * @get()��Ԫ�ص����Լ���Ӧֵ�б���<A c=��d�� >B</A>=��attrObjList.get(0)��c
     *                        =��attrObjList.get()��d
     */
    @SuppressWarnings("unchecked")
    private List printElement(Element element, Integer level, List resultList) {

        List tempList = new ArrayList();
        tempList.add(element.getQualifiedName());// ��¼Ԫ�����ƣ�����ӡ<A>B</A>��A
        tempList.add(element.getText());// ��ӡ�ñ�ǩ��ֵ������ӡ<A>B</A>��B
        // ��ӡ��ǩֵ���ܷ��������if�У����ܵڶ������ֵ����<root><A>B</A></root>
        // tempList.add(level);// Ԫ����ȣ�
        Iterator eleAttriItor = element.attributeIterator();
        if (eleAttriItor.hasNext()) {// Ԫ���в����ģ����������ĸ���
            List attrObjList = new ArrayList(element.attributes().size());
            while (eleAttriItor.hasNext()) {
                Attribute eleAttr = (Attribute) eleAttriItor.next();

                List attrObj = new ArrayList();
                attrObj.add(eleAttr.getName());
                attrObj.add(eleAttr.getValue());
                attrObjList.add(attrObj);
            }
            tempList.add(attrObjList);
        } else {
            // û������
        }
        resultList.add(tempList);

        // ����
        for (Iterator iter = element.elementIterator(); iter.hasNext();) {
            Element sub = (Element) iter.next();
            printElement(sub, level + 1, resultList);// ��ȼ�һ
        }
        return resultList;
    }

    /**
     * @param args
     * @throws DocumentException
     * @throws Exception
     */
    public static void main(String[] args) throws DocumentException {
        File file = new File("./src/sample.xml");
        //������ȡ����
        SAXReader saxReader = new SAXReader();
        saxReader.setValidation(false);// ����֤xml�ļ��ڵ�dtd
        Document doc = saxReader.read(file);
        System.out.println(doc);
        System.out.println(doc.asXML());
        XmlIterator xml = new XmlIterator();
        xml.printXMLTree(doc);
    }
}
