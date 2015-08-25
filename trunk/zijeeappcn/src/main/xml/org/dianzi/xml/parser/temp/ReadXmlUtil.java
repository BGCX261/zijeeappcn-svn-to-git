package org.dianzi.xml.parser.temp;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import java.io.*;

public class ReadXmlUtil {

	// Document���Կ�����XML���ڴ��е�һ������,��ôһ����ȡ���Document ����ζ�ſ���ͨ����
	// �ڴ�Ĳ�����ʵ�ֶ�XML�Ĳ���,���ȵ�һ����ȡXML��ص�Document
	@SuppressWarnings("unused")
    private Document doc = null;

	public ReadXmlUtil() {

	}

	public ReadXmlUtil(String xmlFile) throws Exception {
		// �����Ը�����һ������,�Ȼ�ȡ����DocumentBuilder����
		// �Ĺ���,��ͨ�������������һ��DocumentBuilder,
		// DocumentBuilder������������Document��
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		DocumentBuilder db = dbf.newDocumentBuilder();
		// ���Document����һ��XML�ļ����ڴ��еľ���
		// String xmlPath = ReadXmlUtil.class.getResource("/").getPath();
		// System.out.println(xmlPath);
		// xmlPath = new File(xmlPath).getParent()+"/" + xmlFile;
		String xmlPath = ReadXmlUtil.class.getClassLoader().getResource(
				"emailSetting.xml").getPath();
		System.out.println(xmlPath);
		doc = db.parse(new File(xmlPath));
	}

	// �÷��������XML�ļ���������ʾ����
	public String getParameterValueByName(String nodeName,
			String parameterName, Document doct) throws Exception {
		// ��xml�ļ���,ֻ��һ����Ԫ��,�ȰѸ�Ԫ���ó�������
		String parameterValue = "";
		Element element = doct.getDocumentElement();
		System.out.println("��Ԫ��Ϊ:" + element.getTagName());
		NodeList mailList = doct.getElementsByTagName(nodeName);// ȡ�ڵ���
		System.out.println("mail:" + mailList.getLength());
		Node fatherNode = mailList.item(0);// ȡ�ڵ��һ������
		System.out.println("���ڵ�Ϊ:" + fatherNode.getNodeName());
		// �Ѹ��ڵ�������ó���
		NamedNodeMap attributes = fatherNode.getAttributes();
		for (int i = 0; i < attributes.getLength(); i++) {
			Node attribute = attributes.item(i);
			System.out.println("main:" + attribute.getNodeName() + " ���Ӧ������ֵΪ:"
					+ attribute.getNodeValue());
		}
		NodeList childNodes = fatherNode.getChildNodes();
		System.out.println(childNodes.getLength());
		for (int j = 0; j < childNodes.getLength(); j++) {
			Node childNode = childNodes.item(j);
			String localNodeName = "";
			// �������ڵ�����Element ,�ٽ���ȡֵ
			if (childNode instanceof Element) {
				localNodeName = childNode.getNodeName();
				if (localNodeName.equals(parameterName)) {
					parameterValue = childNode.getFirstChild().getNodeValue();
					break;
				}
				// System.out.println("�ӽڵ���Ϊ:"+childNode.getNodeName()+"���Ӧ��ֵΪ"+childNode.getFirstChild().getNodeValue());
			}
		}

		return parameterValue;
	}
	
	
	
	
	
	
	
	
	@SuppressWarnings("unused")
    private static ThreadLocal<Transformer> transformers = new ThreadLocal<Transformer>();
    @SuppressWarnings("unused")
    private static ThreadLocal<DocumentBuilder> builders = new ThreadLocal<DocumentBuilder>();
   
    private static DocumentBuilderFactory documentBuilderFactory ;
    @SuppressWarnings("unused")
    private static TransformerFactory transformerFactory;
    
    static {
    	documentBuilderFactory=  DocumentBuilderFactory.newInstance();
    	documentBuilderFactory.setNamespaceAware(true);
    	
    	System.out.println("JVM vendor: " + System.getProperty("java.vm.vendor"));
    	System.out.println("JVM name: " + System.getProperty("java.vm.name"));
    	
    	if (System.getProperty("java.vm.vendor").startsWith("Sun")) {
        	String originalTransFactory = System.getProperty("javax.xml.transform.TransformerFactory");

    		System.setProperty("javax.xml.transform.TransformerFactory", "com.sun.org.apache.xalan.internal.xsltc.trax.TransformerFactoryImpl");
    		transformerFactory = TransformerFactory.newInstance();
    		
        	if (originalTransFactory == null) {
        		System.clearProperty("javax.xml.transform.TransformerFactory");
        	} else {
        		System.setProperty("javax.xml.transform.TransformerFactory", originalTransFactory);
        	}
    	}
    	else {
    		transformerFactory = TransformerFactory.newInstance();    	
    	}
    }
	public static void main(String[] args) throws ParserConfigurationException,
			SAXException, IOException {
		System.out.println("==========================");
		
		System.out.println("JVM vendor: "
				+ System.getProperty("java.vm.vendor"));
		System.out.println("JVM name: " + System.getProperty("java.vm.name"));
		String originalTransFactory = System
				.getProperty("javax.xml.transform.TransformerFactory");
		System.out.println("TransformerFactory: " + originalTransFactory);
		System.out.println("documentBuilderFactory��" + documentBuilderFactory);
		String docFactory = System.getProperty("javax.xml.parsers.DocumentBuilderFactory");
		System.out.println("docFactory:"+docFactory);
		
		
		
		
		DocumentBuilder db = documentBuilderFactory.newDocumentBuilder();
		File file = new File("src/sample.xml");
		System.out.println(file.getAbsolutePath());
		System.out.println(db);
		// ���Document����һ��XML�ļ����ڴ��еľ���
		Document doc = db.parse(file);
		doc.normalize();
		System.out.println(doc);
		
		System.out.println("==========================");
	}
}
