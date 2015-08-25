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

	// Document可以看作是XML在内存中的一个镜像,那么一旦获取这个Document 就意味着可以通过对
	// 内存的操作来实现对XML的操作,首先第一步获取XML相关的Document
	@SuppressWarnings("unused")
    private Document doc = null;

	public ReadXmlUtil() {

	}

	public ReadXmlUtil(String xmlFile) throws Exception {
		// 很明显该类是一个单例,先获取产生DocumentBuilder工厂
		// 的工厂,在通过这个工厂产生一个DocumentBuilder,
		// DocumentBuilder就是用来产生Document的
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		DocumentBuilder db = dbf.newDocumentBuilder();
		// 这个Document就是一个XML文件在内存中的镜像
		// String xmlPath = ReadXmlUtil.class.getResource("/").getPath();
		// System.out.println(xmlPath);
		// xmlPath = new File(xmlPath).getParent()+"/" + xmlFile;
		String xmlPath = ReadXmlUtil.class.getClassLoader().getResource(
				"emailSetting.xml").getPath();
		System.out.println(xmlPath);
		doc = db.parse(new File(xmlPath));
	}

	// 该方法负责把XML文件的内容显示出来
	public String getParameterValueByName(String nodeName,
			String parameterName, Document doct) throws Exception {
		// 在xml文件里,只有一个根元素,先把根元素拿出来看看
		String parameterValue = "";
		Element element = doct.getDocumentElement();
		System.out.println("根元素为:" + element.getTagName());
		NodeList mailList = doct.getElementsByTagName(nodeName);// 取节点名
		System.out.println("mail:" + mailList.getLength());
		Node fatherNode = mailList.item(0);// 取节点第一条数据
		System.out.println("父节点为:" + fatherNode.getNodeName());
		// 把父节点的属性拿出来
		NamedNodeMap attributes = fatherNode.getAttributes();
		for (int i = 0; i < attributes.getLength(); i++) {
			Node attribute = attributes.item(i);
			System.out.println("main:" + attribute.getNodeName() + " 相对应的属性值为:"
					+ attribute.getNodeValue());
		}
		NodeList childNodes = fatherNode.getChildNodes();
		System.out.println(childNodes.getLength());
		for (int j = 0; j < childNodes.getLength(); j++) {
			Node childNode = childNodes.item(j);
			String localNodeName = "";
			// 如果这个节点属于Element ,再进行取值
			if (childNode instanceof Element) {
				localNodeName = childNode.getNodeName();
				if (localNodeName.equals(parameterName)) {
					parameterValue = childNode.getFirstChild().getNodeValue();
					break;
				}
				// System.out.println("子节点名为:"+childNode.getNodeName()+"相对应的值为"+childNode.getFirstChild().getNodeValue());
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
		System.out.println("documentBuilderFactory：" + documentBuilderFactory);
		String docFactory = System.getProperty("javax.xml.parsers.DocumentBuilderFactory");
		System.out.println("docFactory:"+docFactory);
		
		
		
		
		DocumentBuilder db = documentBuilderFactory.newDocumentBuilder();
		File file = new File("src/sample.xml");
		System.out.println(file.getAbsolutePath());
		System.out.println(db);
		// 这个Document就是一个XML文件在内存中的镜像
		Document doc = db.parse(file);
		doc.normalize();
		System.out.println(doc);
		
		System.out.println("==========================");
	}
}
