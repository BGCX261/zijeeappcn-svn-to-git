package org.dianzi.xml.parser.withDom4j;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.Node;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;

public class XmlDom4J {

    /**
     * ����xml�ļ���
     */
    public void createXMLFile() throws IOException {
        // ʹ�� DocumentHelper �ഴ��һ���ĵ�ʵ���� DocumentHelper ������ XML �ĵ��ڵ�� dom4j API
        // �����ࡣ
        Document document = DocumentHelper.createDocument();

        // ʹ�� addElement() ����������Ԫ�� catalog ��addElement() ������ XML �ĵ�������Ԫ�ء�
        Element catalogElement = document.addElement("catalog");
        // �� catalog Ԫ����ʹ�� addComment() �������ע�͡�An XML catalog����
        catalogElement.addComment("An XML Catalog");
        // �� catalog Ԫ����ʹ�� addProcessingInstruction() ��������һ������ָ�
        catalogElement.addProcessingInstruction("target", "text");

        // �� catalog Ԫ����ʹ�� addElement() �������� journal Ԫ�ء�
        Element journal = catalogElement.addElement("journal");
        // ʹ�� addAttribute() ������ journal Ԫ����� title �� publisher ���ԡ�
        journal.addAttribute("title", "XML Zone");
        journal.addAttribute("publisher", "IBM Devoloperment");

        // ��ӽڵ�journal���ӽڵ�article,�����������ԣ�
        Element articleElement = journal.addElement("article");
        articleElement.addAttribute("level", "Intermediate");
        articleElement.addAttribute("date", "December-2008");

        // ��ӽڵ�articleElement���ӽ��title,��ʹ�� setText() ����������Ԫ�ص��ı���
        Element titleElement = articleElement.addElement("title");
        titleElement.setText("����������");

        // ��ӽڵ�articleElement���ӽ��author.����ӽ����ӽ��firstname��lastname,���������ļ���
        Element authorElement = articleElement.addElement("author");
        Element firstNameElement = authorElement.addElement("firstname");
        firstNameElement.setText("Marcello");
        Element lastNameElement = authorElement.addElement("lastname");
        lastNameElement.setText("Vitaletti");

        // ����ʹ�� addDocType() ��������ĵ�����˵����

        OutputFormat format = new OutputFormat();
        format.setEncoding("gb2312");
        /**
         * write into a file
         * XMLWriter output = new XMLWriter(
                new FileWriter(new File("catalog.xml")), format);
         */
        XMLWriter output = new XMLWriter(System.out, format);
        output.write(document);
        output.close();
    }

    /**
     * �޸�xml�ļ�ָ���ڵ�����ԣ�
     * 
     * @param inputXml xml�ļ���
     * @oldAttributeValue ԭ���ԣ�
     * @attributeValue Ҫ�޸ĳɵ�ֵ��
     * @param XPath Ҫ�޸Ľڵ����Եı��ʽ���磺"//article/@level"
     *            ���ʾ�޸Ľڵ�level(���ڵ�Ϊarticle)������ �ر�˵��:@�����ʾ��������;
     */
    @SuppressWarnings("unchecked")
    public Document modifyXMLNodeAttributeByName(File inputXml, String XPath,
            String oldAttributeValue, String attributeValue) {
        if (XPath.indexOf("@") < 0) {
            System.out.println("����XPath��Ч,����Ҫ�޸ĵ�����ǰ����'@'");
            return null;
        }
        SAXReader saxReader = new SAXReader();
        Document document = null;
        try {
            document = saxReader.read(inputXml);
            List list = document.selectNodes(XPath);
            Iterator iter = list.iterator();
            while (iter.hasNext()) {
                Attribute attribute = (Attribute) iter.next();
                if (attribute.getValue().equals(oldAttributeValue))// ��ԭ�����޸�Ϊ�µ����ԣ�
                    attribute.setValue(attributeValue);
            }

        } catch (DocumentException e) {
            e.printStackTrace();
        }
        return document;

    }

    /**
     * �޸�ָ���ڵ������ֵ��
     * 
     * @param inputXml xml�ļ���
     * @param XPath Ҫ�޸Ľڵ����Եı��ʽ���磺"//article/@level"
     *            ���ʾ�޸Ľڵ�level(���ڵ�Ϊarticle)������
     * @param attributeValue ������ֵ��
     */
    @SuppressWarnings("unchecked")
    public Document modifyXMLNodeAttributeByName(File inputXml, String XPath,
            String attributeValue) {
        if (XPath.indexOf("@") < 0) {
            System.out.println("����XPath��Ч,����Ҫ�޸ĵ�����ǰ����'@'");
            return null;
        }
        SAXReader saxReader = new SAXReader();
        Document document = null;
        try {
            document = saxReader.read(inputXml);
            List list = document.selectNodes(XPath);
            Iterator iter = list.iterator();
            while (iter.hasNext()) {
                Attribute attribute = (Attribute) iter.next();
                // ��ԭ�����޸�Ϊ�µ����ԣ�
                attribute.setValue(attributeValue);
            }

        } catch (DocumentException e) {
            e.printStackTrace();
        }
        return document;
    }

    /**
     * ��ȡĳһ�ڵ������ֵ��
     * 
     * @param inputxml xml�ļ���
     * @param XPath
     * @return
     */
    @SuppressWarnings("unchecked")
    public String[] getNodeAttributeValue(File inputxml, String XPath) {
        String nodeAttri = "";// ����ڵ�����ֵ;
        if (XPath.indexOf("@") < 0) {
            return null;
        }
        SAXReader saxReader = new SAXReader();
        Document document = null;
        try {
            document = saxReader.read(inputxml);
            List list = document.selectNodes(XPath);
            Iterator it = list.iterator();
            while (it.hasNext()) {
                Attribute attri = (Attribute) it.next();
                nodeAttri += attri.getValue() + ",";
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (nodeAttri.length() > 0) {
            nodeAttri = nodeAttri.substring(0, nodeAttri.length() - 1);
        }
        return nodeAttri.split(",");
    }

    /**
     * �޸�ָ���ڵ���ı�ֵ��
     * 
     * @param inputXml
     * @param XPath Ҫ�޸Ľڵ����Եı��ʽ���磺"//article/level" ���ʾarticle�ڵ��µ�����level�ڵ���ı���
     * @param newText �µ��ı�ֵ��
     */
    @SuppressWarnings("unchecked")
    public Document modifyXMLNodeTextByName(File inputXml, String XPath,
            String newText) throws DocumentException {
        if (XPath.indexOf("@") >= 0) {
            System.out.println("����XPath��Ч!");
            return null;
        }
        SAXReader saxReader = new SAXReader();
        Document document = null;
            document = saxReader.read(inputXml);
            List list = document.selectNodes(XPath);
            Iterator iter = list.iterator();
            while (iter.hasNext()) {
                Element elementText = (Element) iter.next();
                elementText.setText(newText);
            }
        return document;
    }

    /**
     * �滻ָ���ڵ��ı���ֵ��
     * 
     * @param inputXml xml�ļ���
     * @param XPath Ҫ�޸Ľڵ����Եı��ʽ���磺"//article/level" ���ʾarticle�ڵ��µ�����level�ڵ���ı���
     * @param oldText ԭ�ı�
     * @param newText ���ı���
     */
    @SuppressWarnings("unchecked")
    public Document modifyXMLNodeTextByName(File inputXml, String XPath,
            String oldText, String newText) throws DocumentException {
        if (XPath.indexOf("@") >= 0) {
            System.out.println("����XPath��Ч!");
            return null;
        }
        SAXReader saxReader = new SAXReader();
        Document document = null;
            document = saxReader.read(inputXml);
            List list = document.selectNodes(XPath);
            Iterator iter = list.iterator();
            while (iter.hasNext()) {
                Element elementText = (Element) iter.next();
                if (elementText.getText().equals(oldText))
                    elementText.setText(newText);
            }
        return document;
    }

    /**
     * ��ȡĳһ�ڵ���ı����ݣ�
     * 
     * @param inputxml xml�ļ���
     * @param XPath
     * @return
     */
    @SuppressWarnings("unchecked")
    public String[] getNodeTextValue(File inputxml, String XPath) {
        String nodeTextValue = "";// ����ڵ�����ֵ;
        if (XPath.indexOf("@") >= 0) {
            return null;
        }
        SAXReader saxReader = new SAXReader();
        Document document = null;
        try {
            document = saxReader.read(inputxml);
            List list = document.selectNodes(XPath);
            
            Node n = document.selectSingleNode(XPath);
            n.getStringValue();
            
            Iterator it = list.iterator();
            while (it.hasNext()) {
                Element text = (Element) it.next();
                nodeTextValue += text.getText() + ",";
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (nodeTextValue.length() > 0) {
            nodeTextValue = nodeTextValue.substring(0,
                    nodeTextValue.length() - 1);
        }
        return nodeTextValue.split(",");
    }

    /**
     * ����xml�ļ�;
     * 
     * @param document xml�ļ���;
     * @param filePath �ļ��洢��ȫ·��(�����ļ���)
     * @code ����ı���;
     */
    public void saveXmlFile(Document document, String filePath, String code) {
        if (document == null) {
            return;
        }
        XMLWriter output;
        try {
            OutputFormat format = new OutputFormat();
            format.setEncoding(code);
            output = new XMLWriter(new FileWriter(new File(filePath)), format);
            output.write(document);
            output.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // ���ԣ�
    public static void main(String[] args) {
        XmlDom4J dom4jParser = new XmlDom4J();
        // ����XML
        // dom4jParser.createXMLFile();
        File file = new File("src/sample.xml");
        // dom4jParser.saveXmlFile(document, "F://test.xml", "GBK");

        /*
         * String[] attrArray=dom4jParser.getNodeAttributeValue(file,
         * "//article/@level"); if(attrArray!=null){ for(int
         * i=0;i<attrArray.length;i++){
         * System.out.println("Attribute is :"+attrArray[i]); } }
         */

        String[] nodeText = dom4jParser.getNodeTextValue(file,
                "//students/student/name");
        if (nodeText != null) {
            for (int i = 0; i < nodeText.length; i++) {
                System.out.println("NODE TEXT IS:" + nodeText[i]);
            }
        }

    }
}