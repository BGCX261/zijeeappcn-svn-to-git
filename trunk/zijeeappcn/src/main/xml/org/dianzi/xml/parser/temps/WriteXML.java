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
        // ����Builder
        DocumentBuilderFactory factory = null;
        DocumentBuilder builder = null;
        Document doc = null;
        factory = DocumentBuilderFactory.newInstance();
        builder = factory.newDocumentBuilder();
        // �����ĵ�
        doc = builder.newDocument();// ע�����ȡ����
        // �������ֽڵ㣬�γ������ĵ�
        // ���ڵ� ���� ����ע��
        Element root = doc.createElement("imust"); // ���ڵ�
        Element manager = doc.createElement("class_s");
        Element stu = doc.createElement("student");
        Text txt = doc.createTextNode("����ѧ��");

        root.appendChild(manager); // root׷��һ���ӽ��
        manager.appendChild(stu); // manager׷��һ���ӽ��
        stu.appendChild(txt); // ��student�ӽڵ��ֵ��ӽ�ȥ

        Attr attr = doc.createAttribute("location");// ����imust�ڵ������
        attr.setNodeValue("���ɹſƼ���ѧ"); // �������Ե�ֵ
        root.setAttributeNode(attr);

        stu.setAttribute("name", "������"); // ����student������name��ֵ
        stu.setAttribute("sex", "man"); // ����student������sex��ֵ

        doc.appendChild(root);
        doc.setXmlVersion("1.1"); // ����xml�汾��
        doc.setXmlStandalone(true);

        // ������ļ� //����ת���� //׼��Դ��׼��һ�����
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
        // дxml�Ĳ����������ģ���ȻҲ���������ķ�����
    }
}

