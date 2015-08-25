package org.dianzi.xml.parser.temps;

import java.util.HashMap;
import java.util.Map;

import javax.xml.transform.Source;
import javax.xml.transform.dom.DOMResult;
import javax.xml.transform.dom.DOMSource;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

import sun.rmi.runtime.Log;

public class Testsss {

	
    @SuppressWarnings("unchecked")
    public Map parseSource(Log logger, Source src) {
        Map map = new HashMap();
        DOMSource domSrc = null;
        if (src instanceof DOMSource) {
            domSrc = (DOMSource) src;
        } else {
            try {
                DOMResult dr = new DOMResult();
                // Dom.identityTransform(src, dr);
                domSrc = new DOMSource(dr.getNode());
            } catch (Exception e) {
                // ErrorUtil.printError("Error converting record into DOMSource",
                // e);
            }
        }
        // Get a Document from the DOMSource
        Node node = domSrc.getNode();
        Document doc;
        if (node instanceof Document) {
            doc = (Document) node;
        } else {
            doc = node.getOwnerDocument();
        }
        Element root = doc.getDocumentElement();

        if (root != null) {
            // map.setOperationName(root.getNodeName());
            // logger.info("root name :" + root.getNodeName());
            Node child = root.getFirstChild();
            while (child != null) {
                // logger.info("info --C child.getNodeType():"
                // + child.getNodeType());
                if (child.getNodeType() == Node.ELEMENT_NODE) {
                    // logger.info("childKeyAndValue----:" + child.getNodeName()
                    // + "=" + child.getNodeValue());
                    // logger.info("childKeyAndValue-----:" +
                    // child.getNodeName()
                    // + "=" + child.getTextContent());
                    map.put(child.getNodeName(), child.getTextContent());
                }
                child = child.getNextSibling();
            }
        }
        // getNode(root, map);
        // ////
        // logger.info("start===========");
        // Set set = map.keySet();
        // for (Iterator iter = set.iterator(); iter.hasNext();) {
        // String key = (String) iter.next();
        // logger.info(key + "=" + map.get(key));
        // }
        //		
        // logger.info("end===========");
        return map;
    }
}
