package org.dianzi.xml.parser.temps;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
//import org.w3c.dom.Node;
//import org.w3c.dom.NodeList;
import org.w3c.dom.Text;

public class WriteXML {

    public static void main(String[] args) throws Exception {
        // 产生Builder
        DocumentBuilderFactory factory = null;
        DocumentBuilder builder = null;
        Document doc = null;
        factory = DocumentBuilderFactory.newInstance();
        builder = factory.newDocumentBuilder();
        // 产生文档
        doc = builder.newDocument();// 注意与读取区别
        // 产生各种节点，形成树形文档
        // 根节点 属性 其他注释
        Element root = doc.createElement("imust"); // 根节点
        Element manager = doc.createElement("class_s");
        Element stu = doc.createElement("student");
        Text txt = doc.createTextNode("三好学生");

        root.appendChild(manager); // root追加一个子结点
        manager.appendChild(stu); // manager追加一个子结点
        stu.appendChild(txt); // 将student子节点的值添加进去

        Attr attr = doc.createAttribute("location");// 设置imust节点的属性
        attr.setNodeValue("内蒙古科技大学"); // 设置属性的值
        root.setAttributeNode(attr);

        stu.setAttribute("name", "林秋荣"); // 设置student的属性name和值
        stu.setAttribute("sex", "man"); // 设置student的属性sex和值

        doc.appendChild(root);
        doc.setXmlVersion("1.1"); // 设置xml版本号
        doc.setXmlStandalone(true);

        // 保存成文件 //产生转换器 //准备源，准备一个结果
        // Source Result
        // DOMSource(Document), SAXSource, StreamSource
        // DOMResult, SAXResult, StreamResult(File)
        TransformerFactory tff = null;
        Transformer tfr = null;
        tff = TransformerFactory.newInstance();
        tfr = tff.newTransformer();

        DOMSource source = new DOMSource(doc);
        StreamResult result = new StreamResult("lqr.xml");
        tfr.transform(source, result);
        // 写xml的步骤就是上面的，当然也还有其他的方法。
    }
}

