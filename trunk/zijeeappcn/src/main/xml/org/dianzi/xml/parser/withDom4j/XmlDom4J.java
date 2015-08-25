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
     * 生成xml文件；
     */
    public void createXMLFile() throws IOException {
        // 使用 DocumentHelper 类创建一个文档实例。 DocumentHelper 是生成 XML 文档节点的 dom4j API
        // 工厂类。
        Document document = DocumentHelper.createDocument();

        // 使用 addElement() 方法创建根元素 catalog 。addElement() 用于向 XML 文档中增加元素。
        Element catalogElement = document.addElement("catalog");
        // 在 catalog 元素中使用 addComment() 方法添加注释“An XML catalog”。
        catalogElement.addComment("An XML Catalog");
        // 在 catalog 元素中使用 addProcessingInstruction() 方法增加一个处理指令。
        catalogElement.addProcessingInstruction("target", "text");

        // 在 catalog 元素中使用 addElement() 方法增加 journal 元素。
        Element journal = catalogElement.addElement("journal");
        // 使用 addAttribute() 方法向 journal 元素添加 title 和 publisher 属性。
        journal.addAttribute("title", "XML Zone");
        journal.addAttribute("publisher", "IBM Devoloperment");

        // 添加节点journal的子节点article,并设置其属性；
        Element articleElement = journal.addElement("article");
        articleElement.addAttribute("level", "Intermediate");
        articleElement.addAttribute("date", "December-2008");

        // 添加节点articleElement的子结点title,并使用 setText() 方法设置其元素的文本。
        Element titleElement = articleElement.addElement("title");
        titleElement.setText("又是下雨天");

        // 添加节点articleElement的子结点author.添加子结点的子结点firstname、lastname,并设置其文件；
        Element authorElement = articleElement.addElement("author");
        Element firstNameElement = authorElement.addElement("firstname");
        firstNameElement.setText("Marcello");
        Element lastNameElement = authorElement.addElement("lastname");
        lastNameElement.setText("Vitaletti");

        // 可以使用 addDocType() 方法添加文档类型说明。

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
     * 修改xml文件指定节点的属性；
     * 
     * @param inputXml xml文件流
     * @oldAttributeValue 原属性；
     * @attributeValue 要修改成的值；
     * @param XPath 要修改节点属性的表达式；如："//article/@level"
     *            则表示修改节点level(父节点为article)的属性 特别说明:@后面表示的是属性;
     */
    @SuppressWarnings("unchecked")
    public Document modifyXMLNodeAttributeByName(File inputXml, String XPath,
            String oldAttributeValue, String attributeValue) {
        if (XPath.indexOf("@") < 0) {
            System.out.println("参数XPath无效,请在要修改的属性前加入'@'");
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
                if (attribute.getValue().equals(oldAttributeValue))// 把原属性修改为新的属性；
                    attribute.setValue(attributeValue);
            }

        } catch (DocumentException e) {
            e.printStackTrace();
        }
        return document;

    }

    /**
     * 修改指定节点的属性值；
     * 
     * @param inputXml xml文件流
     * @param XPath 要修改节点属性的表达式；如："//article/@level"
     *            则表示修改节点level(父节点为article)的属性
     * @param attributeValue 属性新值；
     */
    @SuppressWarnings("unchecked")
    public Document modifyXMLNodeAttributeByName(File inputXml, String XPath,
            String attributeValue) {
        if (XPath.indexOf("@") < 0) {
            System.out.println("参数XPath无效,请在要修改的属性前加入'@'");
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
                // 把原属性修改为新的属性；
                attribute.setValue(attributeValue);
            }

        } catch (DocumentException e) {
            e.printStackTrace();
        }
        return document;
    }

    /**
     * 获取某一节点的属性值；
     * 
     * @param inputxml xml文件；
     * @param XPath
     * @return
     */
    @SuppressWarnings("unchecked")
    public String[] getNodeAttributeValue(File inputxml, String XPath) {
        String nodeAttri = "";// 储存节点属性值;
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
     * 修改指定节点的文本值；
     * 
     * @param inputXml
     * @param XPath 要修改节点属性的表达式；如："//article/level" 则表示article节点下的所有level节点的文本；
     * @param newText 新的文本值；
     */
    @SuppressWarnings("unchecked")
    public Document modifyXMLNodeTextByName(File inputXml, String XPath,
            String newText) throws DocumentException {
        if (XPath.indexOf("@") >= 0) {
            System.out.println("参数XPath无效!");
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
     * 替换指定节点文本的值。
     * 
     * @param inputXml xml文件流
     * @param XPath 要修改节点属性的表达式；如："//article/level" 则表示article节点下的所有level节点的文本；
     * @param oldText 原文本
     * @param newText 新文本；
     */
    @SuppressWarnings("unchecked")
    public Document modifyXMLNodeTextByName(File inputXml, String XPath,
            String oldText, String newText) throws DocumentException {
        if (XPath.indexOf("@") >= 0) {
            System.out.println("参数XPath无效!");
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
     * 获取某一节点的文本内容；
     * 
     * @param inputxml xml文件；
     * @param XPath
     * @return
     */
    @SuppressWarnings("unchecked")
    public String[] getNodeTextValue(File inputxml, String XPath) {
        String nodeTextValue = "";// 储存节点属性值;
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
     * 保存xml文件;
     * 
     * @param document xml文件流;
     * @param filePath 文件存储的全路径(包括文件名)
     * @code 储存的编码;
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

    // 测试；
    public static void main(String[] args) {
        XmlDom4J dom4jParser = new XmlDom4J();
        // 生成XML
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