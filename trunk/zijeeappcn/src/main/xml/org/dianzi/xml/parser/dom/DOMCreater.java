package org.dianzi.xml.parser.dom;

import java.io.FileNotFoundException;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import com.sun.org.apache.xml.internal.serialize.XMLSerializer;

public class DOMCreater {
    public static void main(String[] args) throws ParserConfigurationException {
        // DOMImplementation domImp =
        // DOMImplementationImpl.getDOMImplementation();
        DocumentBuilderFactory builderFact = DocumentBuilderFactory
                .newInstance();
        DocumentBuilder builder = builderFact.newDocumentBuilder();

        Document doc = builder.newDocument();
        // Document doc = domImp.createDocument(null, null, null);
        Element root = doc.createElement("games");
        Attr attr = doc.createAttribute("ddd");
        attr.setValue("ddd");
        root.setAttributeNode(attr);
        
        Element child1 = doc.createElement("game");
        child1.appendChild(doc.createTextNode("Final Fantasy VII"));
        child1.setAttribute("genre", "rpg");
        root.appendChild(child1);
        doc.appendChild(root);
      
        try {
            
            /**
             * write into a file
             * serial = new XMLSerializer(new
             * java.io.FileOutputStream("domcreate.xml"), null);
             */
            XMLSerializer serial = new XMLSerializer(System.out, null);
            serial.serialize(doc);
        } catch (FileNotFoundException e1) {
            e1.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
