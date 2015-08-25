package org.dianzi.xml.parser.temp;

import org.w3c.dom.Document;

//import     org.w3c.dom.*;   
//import     org.apache.xerces.dom.DocumentImpl;   
//import     org.apache.xerces.dom.DOMImplementationImpl;   
import     org.apache.xerces.parsers.DOMParser;   

public class Test {
	public static void main(String[] args) {
		try {
			DOMParser parser = new DOMParser();
			parser.parse("./src/sample.xml");
			Document doc = parser.getDocument();
			System.out.println(doc.getNodeType());
		} catch (Exception e) {
			System.out.println(e);
		}
	}

}
