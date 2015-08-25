package org.dianzi.xml.parser.dom;

import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class DomUtil {
	  /**
     * Search for a child with the given nodeName. If recursive, search in all
     * the child of firdt level, then if not found, search in the 2nd level of
     * the first child, ...
     * 
     * @param parent
     *            parent node
     * @param nodeName
     *            node name
     * @param recursive
     *            boolean to know if we got through the xml tree
     * @return a node
     */
    public static Node findChild(Node parent, String nodeName, boolean recursive) {
        parent.normalize();
        Node result = null;
        if (parent != null && nodeName != null) {
            NodeList nl = parent.getChildNodes();
            for (int i = 0; i < nl.getLength() && result == null; i++) {
                if (nodeName.equals(nl.item(i).getNodeName())) {
                    result = nl.item(i);
                }
            }
            // now, search recursively if required
            if (result == null && recursive) {
                for (int i = 0; i < nl.getLength() && result == null; i++) {
                    result = findChild(nl.item(i), nodeName, true);
                }
            }
        }
        return result;
    }

    /**
     * Search for a child with the given nodeName. If recursive, search in all
     * the child of firdt level, then if not found, search in the 2nd level of
     * the first child, ...
     * 
     * @param parent
     *            parent node
     * @param namespaceURI
     *            The namespaceURI of the node
     * @param nodeName
     *            node name
     * @param recursive
     *            boolean to know if we got through the xml tree
     * @return a node
     */
    public static Node findChild(Node parent, String namespaceURI, String nodeName, boolean recursive) {
        parent.normalize();
        String prefix = getPrefixForNamespaceURIRecursive(parent, namespaceURI);
        return(findChild(parent, prefix+nodeName, recursive)); 
    }
    
    public static NodeList findChildren(Node parent,String namespaceURI,  String nodeName,
            boolean recursive) {
        parent.normalize();
        String prefix = getPrefixForNamespaceURIRecursive(parent, namespaceURI);
        if(prefix!=null)
        	return(findChildren(parent, prefix+nodeName, recursive));
        else
        	return(findChildren(parent, nodeName, recursive));
        
    }
    
    /**
     * Search for children with the given nodeName. If recursive, search in all
     * the children of first level, then search in the 2nd level of the first
     * children, ...
     * 
     * @param parent
     *            parent node
     * @param nodeName
     *            node name
     * @param recursive
     *            boolean to know if we got through the xml tree
     * @return a node list of nodes with this name
     */
    public static NodeList findChildren(Node parent, String nodeName,
        boolean recursive) {
        parent.normalize();
//        NodeSet nodeList = new NodeSet();
//        if (parent != null && nodeName != null) {
//            NodeList nl = parent.getChildNodes();
//            for (int i = 0; i < nl.getLength(); i++) {
//                if (nodeName.equals(nl.item(i).getNodeName())) {
//                    nodeList.addElement(nl.item(i));
//                }
//            }
//            // now, search recursively if required
//            if (recursive) {
//                for (int i = 0; i < nl.getLength(); i++) {
//                    nodeList = findChildren(nl.item(i), nodeName, nodeList);
//                }
//            }
//        }
//        return nodeList;
        return null;
    }

    
    
    /**
     * Return the value of the attribute in the node
     * 
     * @param n
     *            the node
     * @param attName
     *            the name of the attribute
     * @return the value of the attribute, null if not found
     */
    public static String getAttributeValue(Node n, String attName) {
        NamedNodeMap atts = n.getAttributes();
        Node att = atts.getNamedItem(attName);
        if (att != null) {
            return att.getNodeValue();
        }
        return null;
    }
    
    /**
     * Return the first child of a node, regardless <i>text</i> node
     * 
     * @param node
     * @return
     */
    public static Node getFirstChild(Node node) {
        node.normalize();
        Node result = node.getFirstChild();
        while (result.getNodeType() == Node.TEXT_NODE) {
            result = result.getNextSibling();
        }
        return result;
    }

    /**
     * Return the next sibling of a node, regardless <i>text</i> node
     * 
     * @param node
     * @return
     */
    public static Node getNextSibling(Node node) {
        node.normalize();
        Node result = node.getNextSibling();
        while (result.getNodeType() == Node.TEXT_NODE) {
            result = result.getNextSibling();
        }
        return result;
    }
    
    public static NodeList getChildren(Node parent) {
        parent.normalize();
//        NodeSet nodeList = new NodeSet();
//        if (parent != null ) {
//            NodeList nl = parent.getChildNodes();
//            for (int i = 0; i < nl.getLength(); i++) {
////				Changed this to guarantee we only return elements (not comments, CDATA, etc...)
////                if (nl.item(i).getNodeType() != Node.TEXT_NODE) {
//               	if (nl.item(i).getNodeType() == Node.ELEMENT_NODE) {
//                	nodeList.addElement(nl.item(i));
//                }
//            }
//        }
//        return nodeList;
        return null;
    }
    /**
     * Search a document to see if a namespace is declared in it and if it is
     * returns this namespace URI
     * @param node 
     * @param namespaceURI 
     * @param deep 
     * @return
     */
    public static String getPrefixForNamespaceURI(Node node,
            String namespaceURI) {
        String result = "";
     
            // Search in root node attributes
            NamedNodeMap attributes = node.getAttributes();
            int i = 0;
            if(attributes != null){
                while (i < attributes.getLength()) {
                    Node attr = attributes.item(i++);
                    if (namespaceURI.equals(attr.getNodeValue())) {
                        String nodeName = attr.getNodeName();
                        if (nodeName.startsWith("xmlns:")) {
                            result = nodeName.replaceFirst("xmlns:", "")+":";
                            return result;
                        } else if (nodeName.startsWith("xmlns")) {
                            return result;
                        }
                    }
                }
            }
            // Search in child nodes attributes
            i = 0;
            NodeList nl = node.getChildNodes();
            while (i < nl.getLength()) {
                Node tmpNode = nl.item(i++);
                String prefix = getPrefixForNamespaceURI(tmpNode, namespaceURI);
                if (prefix != null) {
                    return prefix;
                }
            }
            return null;
       
    }
    public static String getPrefixForNamespaceURIRecursive(Node node,
            String namespaceURI) {
            String result = "";
            if (namespaceURI != null && !"".equals(namespaceURI)) {
                while (node.getParentNode() != null) {
                    node = node.getParentNode();
                }
                // Search in root node attributes
                NamedNodeMap attributes = node.getAttributes();
                int i = 0;
                if(attributes != null){
                    while (i < attributes.getLength()) {
                        Node attr = attributes.item(i++);
                        if (namespaceURI.equals(attr.getNodeValue())) {
                            String nodeName = attr.getNodeName();
                            if (nodeName.startsWith("xmlns:")) {
                                result = nodeName.replaceFirst("xmlns:", "")+":";
                                return result;
                            } else if (nodeName.startsWith("xmlns")) {
                                return result;
                            }
                        }
                    }
                }
                // Search in child nodes attributes
                i = 0;
                NodeList nl = node.getChildNodes();
                while (i < nl.getLength()) {
                    Node tmpNode = nl.item(i++);
                    String prefix = getPrefixForNamespaceURI(tmpNode,
                            namespaceURI);
                    if (prefix != null) {
                        return prefix;
                    }
                }
            }
       
        return result;
    }
    
    public static String getNamespaceURIForPrefixRecursive(Node node,
            String prefix) {
            String result = "";
            if (prefix != null ) {
                while (node.getParentNode() != null) {
                    node = node.getParentNode();
                }
                // Search in root node attributes
                NamedNodeMap attributes = node.getAttributes();
                int i = 0;
                if(attributes != null){
                    while (i < attributes.getLength()) {
                        Node attr = attributes.item(i++);
                            String nodeName = attr.getNodeName();
                            if (!"".equals(prefix) && nodeName.startsWith("xmlns:"+prefix)) {
                                return attr.getNodeValue();
                                //here should nameName equals "xmlns", not startWith
                            } else if ("".equals(prefix)&& nodeName.equals("xmlns")) {
                                return attr.getNodeValue();
                            }
                        }
                   
                }
                // Search in child nodes attributes
                i = 0;
                NodeList nl = node.getChildNodes();
                while (i < nl.getLength()) {
                    Node tmpNode = nl.item(i++);
                    String nameSpaceURI = getNamespaceURIForPrefix(tmpNode,
                    		prefix);
                    if (nameSpaceURI != null) {
                        return nameSpaceURI;
                    }
                }
            }
       
        return result;
    }
    
    public static String getNamespaceURIForPrefix(Node node, String prefix) {

		// Search in root node attributes
		NamedNodeMap attributes = node.getAttributes();
		int i = 0;
		if (attributes != null) {
			while (i < attributes.getLength()) {
				Node attr = attributes.item(i++);
				String nodeName = attr.getNodeName();
				if (!"".equals(prefix) && nodeName.startsWith("xmlns:" + prefix)) {
					return attr.getNodeValue();
                    //here should nameName equals "xmlns", not startWith
			           	
				} else if ("".equals(prefix) && nodeName.equals("xmlns")) {
					return attr.getNodeValue();
				}

			}
		}
		// Search in child nodes attributes
		i = 0;
		NodeList nl = node.getChildNodes();
		while (i < nl.getLength()) {
			Node tmpNode = nl.item(i++);
			String nameSpaceURI = getNamespaceURIForPrefix(tmpNode, prefix);
			if (nameSpaceURI != null) {
				return nameSpaceURI;
			}
		}
		return null;

	}
    
    public static String getPrefix(String nodeName){
    	if(nodeName.indexOf(":")>-1){
    		return nodeName.split(":")[0];
    	}
    	return "";
    }
    /**
	 * Return the <i>text</i> element of a node
	 * 
	 * The implmentation of the DOM3 getTextContent() API Axiom DOM
	 * implementation doesn't support it
	 * 
	 * 
	 * @param node
	 * @return
	 */
    public static String getTextContent(Node node) {
// node.normalize();
// NodeList list = node.getChildNodes();
// for (int i = 0; i < list.getLength(); i++) {
//            if (list.item(i).getNodeType() == Node.TEXT_NODE) {
//                return list.item(i).getNodeValue();
//            }
//        }
//        return "";
    	
    	StringBuffer sb = new StringBuffer();
		Node child = node.getFirstChild();
		while (child != null) {
			if (child.getNodeType() == Node.TEXT_NODE ||
					child.getNodeType() == Node.CDATA_SECTION_NODE) {
				sb.append(child.getNodeValue());
			}
			child = child.getNextSibling();
		}
		return sb.toString();
    }


    /**
     * Search recursively for children with the given nodeName.
     * 
     * @param parent
     *            parent node
     * @param nodeName
     *            node name
     * @param nodeList
     *            Set of nodes to be completed
     * @return a node list of nodes with this name
     */
//    private static NodeSet findChildren(Node parent, String nodeName,
//        NodeSet nodeList) {
//        if (parent != null && nodeName != null) {
//            NodeList nl = parent.getChildNodes();
//            for (int i = 0; i < nl.getLength(); i++) {
//                if (nodeName.equals(nl.item(i).getNodeName())) {
//                    nodeList.addElement(nl.item(i));
//                }
//            }
//            for (int i = 0; i < nl.getLength(); i++) {
//                nodeList = findChildren(nl.item(i), nodeName, nodeList);
//            }
//        }
//        return nodeList;
//    }
    
}
