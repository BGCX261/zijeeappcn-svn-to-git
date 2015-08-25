package org.dianzi.xml.parser.dom;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.io.StringWriter;

import javax.xml.XMLConstants;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMResult;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;

import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;


public class Dom{

	private static ThreadLocal<Transformer> transformers = new ThreadLocal<Transformer>();
    private static ThreadLocal<DocumentBuilder> builders = new ThreadLocal<DocumentBuilder>();
   
    private static DocumentBuilderFactory documentBuilderFactory ;
    private static TransformerFactory transformerFactory;
    
    static {
    	documentBuilderFactory=  DocumentBuilderFactory.newInstance();
    	documentBuilderFactory.setNamespaceAware(true);
    	
//		System.setProperty("javax.xml.transform.TransformerFactory", "com.sun.org.apache.xalan.internal.xsltc.trax.TransformerFactoryImpl"); 
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
    		//   For other JRE, use the TransformerFactory bundled with the JRE
    		transformerFactory = TransformerFactory.newInstance();    	
    	}
    	
		//Use the TransformerFactory bundled with the JRE
//    	TransformerFactory transformerFactoryDefault = TransformerFactory.newInstance();
    	
    	
		//Xalan doesn't handle namespaces correctly in all cases.
//    	Properties cbesbConfig = CbesbConfigUtil.getCbesbConfigProperties();
//    	String transFactProp = cbesbConfig.getProperty(ComponentConfigConstants.TRANSFORMER_FACTORY);
//    	if (transFactProp != null && !transFactProp.equals("")) {
//    		System.setProperty("javax.xml.transform.TransformerFactory", transFactProp);
//    		try{
//    			transformerFactory = TransformerFactory.newInstance();
//    		}
//    		catch (TransformerFactoryConfigurationError ex){
//    		    String errmsg = "Transformer configuration error. ";
//    		    logger.warn("Transformer configuration error :" + ex.getMessage() );
//    		    System.out.println("Use the default TransformerFactoryImpl from JRE.");
//    		    transformerFactory= transformerFactoryDefault;
//    		}
//    	}    
//    	else
//    		transformerFactory= transformerFactoryDefault;
//    	
    }
    
    public static Document newDocument() {
        DocumentBuilder db = getBuilder();
        return db.newDocument();
    }
    
    /**
     * Parse an XML document located using an {@link InputSource} using the
     * pooled document builder.
     */
    public static Document parse(InputSource inputSource) throws SAXException,IOException{
        DocumentBuilder db = getBuilder();
        return db.parse(inputSource);
    }
    
    
    public static DocumentBuilder getBuilder() {
        DocumentBuilder builder = builders.get();
        if (builder == null) {
            synchronized (documentBuilderFactory) {
             
                 try {
					builder = documentBuilderFactory.newDocumentBuilder();
				} catch (ParserConfigurationException e) {
					throw new RuntimeException(e);
				}
            }
            builders.set(builder);
        }
        return builder;
    }
    
    private static Transformer getTransformer() {
        Transformer t = transformers.get();
        if (t == null) {
            synchronized(transformerFactory) {
	            try {
	                t = transformerFactory.newTransformer();
	            } catch (TransformerConfigurationException e) {
	                String errmsg = "Transformer configuration error!";
	                throw new Error(errmsg, e);
	            }
	        }
            transformers.set(t);
        }
        return t;
    }
    
    
    /**
     * Create a document from a String
     * 
     * @param xml
     *            xml string
     * @return document representing the document
     */
    public static Document createDocumentFromString(String xml) {
        Document doc = null;
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory
                .newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            byte[] msgByte = xml.getBytes("utf-8");
            ByteArrayInputStream in = new ByteArrayInputStream(msgByte);
            InputSource inputSource = new InputSource(in);
            doc = builder.parse(inputSource);
        } catch (Exception e) {
//        	 logger.error("Exception in createDocumentFromString(): " + e.getMessage());
//             if (logger.isDebugEnabled()) {
//                 logger.debug("Exception in createDocumentFromString():", e);
//             }
        }
        return doc;
    }
    
    public static void validate(Document domDoc, Schema schema) throws SAXException, IOException {
    	Validator validator = schema.newValidator();
    	validator.validate(new DOMSource(domDoc));
    }
    
//	public synchronized static void validate(Reader r, File schemaFile) throws SAXException, ParserConfigurationException, IOException  {
//		DocumentBuilderFactory docBuilderfactory = DocumentBuilderFactory.newInstance();
//		docBuilderfactory.setNamespaceAware(true);
//		DocumentBuilder parser = docBuilderfactory.newDocumentBuilder();
//		Document document = parser.parse(new InputSource(r));
//
//		// create a SchemaFactory capable of understanding WXS schemas
//		SchemaFactory factory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
//
//		// load a WXS schema, represented by a Schema instance
//		Source schemaSource = new StreamSource(schemaFile);
//		Schema schema = factory.newSchema(schemaSource);
//
//		// create a Validator instance, which can be used to validate an instance document
//		Validator validator = schema.newValidator();
//
//		// validate the DOM tree
//		try {
//			validator.validate(new DOMSource(document));
//		} catch (SAXException e) {
//			throw e;
//		}
//	}
    
	/**
 	*	This function creates a Dom tree from XML file and the XML file gets validated with schemaFile
 	* 
 	*	@version 	1.0
 	* 	
 	*/
	public synchronized static Document getDomTree(File inFile, File schemaFile) throws SAXException, ParserConfigurationException, IOException  {
		Document domTree = null;
	    DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
	    
        if (schemaFile != null) {
	        SchemaFactory sf = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
	        dbFactory.setSchema(sf.newSchema(schemaFile));
        }
        
        DocumentBuilder builder = dbFactory.newDocumentBuilder();
        dbFactory.setNamespaceAware(true);
        Node node = builder.parse(inFile);
        node.normalize();
        domTree = (Document)node;
		return domTree;
	}

	public synchronized static Document getDomTree(String xml, File schemaFile) throws SAXException, ParserConfigurationException, IOException  {
		
		return getDomTree( new ByteArrayInputStream(xml.getBytes("utf-8")), schemaFile);
	}
	
	public synchronized static Document getDomTree(InputStream s, File schemaFile) throws SAXException, ParserConfigurationException, IOException  {
		return getDomTree(new InputSource(s), schemaFile);
	}
	
	public synchronized static Document getDomTree(Reader r, File schemaFile) throws SAXException, ParserConfigurationException, IOException  {
		return getDomTree(new InputSource(r), schemaFile);
	}
	
	public synchronized static Document getDomTree(InputSource source, File schemaFile) throws SAXException, ParserConfigurationException, IOException  {
		Document domTree = null;
	    DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
	    dbFactory.setNamespaceAware(true);
	    
        if (schemaFile != null) {
	        SchemaFactory sf = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
	        dbFactory.setSchema(sf.newSchema(schemaFile));
        }
        
        DocumentBuilder builder = dbFactory.newDocumentBuilder();
        
        Node node = builder.parse(source);
        node.normalize();
        domTree = (Document)node;
		return domTree;
	}
	
	/**
 	*	This function creates a new element and adds it to document.
 	* 
 	*	@version 	1.0
 	* 	
 	*/

	public static Element createElement(Document doc,String elemName)  throws Exception {
        Element currElem = doc.createElement(elemName);	// Create element
		
		//Append root to doc
		doc.appendChild( currElem );
		
		return	currElem;	
	}

   /**
 	*	This function creates a new element and adds it to parentElm.
 	* 
 	*	@version 	1.0
 	* 	
 	*/

	public static Element createElement(Document doc,Element parentElem, String elemName)  throws Exception {
        Element currElem = doc.createElement(elemName);	// Create element
		
		//Append root to doc
		parentElem.appendChild( currElem );
		
		return	currElem;	
	}
	
   /**
 	*	This function creates an Attribute
 	* 
 	*	@version 	1.0
 	* 	
 	*/

	public static void createAttribute(Document doc,Element currentNode,String attrName,String attrValue)  throws Exception {
	    Attr attrPointer=null;
		attrPointer=doc.createAttribute(attrName);
		if (attrValue!=null)	attrPointer.setValue(attrValue);
		else	attrPointer.setValue("");
		currentNode.setAttributeNode( attrPointer );
	}

   /**
 	*	This function creates a new node and adds it to Parent Element in a given XML document.
 	* 
 	*	@version 	1.0
 	* 	
 	*/

	public static Element createNode(Document doc,Element parentNode, String nodeName, String nodeValue)	 throws Exception {
        Element currentNode = doc.createElement(nodeName);	// Create element
        
        // if name is not null then create name attribute
  		if (nodeValue!=null)	{
        	currentNode.appendChild(doc.createTextNode(nodeValue));
		}
		// If the node value is null, create an empty node to make sure all elements
		// have the matching closing XML element
		else
		{
			currentNode.appendChild(doc.createTextNode(""));	
		}
    	
		//attach element to Root element
  		parentNode.appendChild( currentNode );
  		
		return	currentNode;	
	}

	

    /**
     * Create a String result from a DOM document
     * 
     * @param document
     *            the DOM Document
     * @return a String representation of the DOM Document
     * @throws TransformerException
     */
    public static String createStringFromDOMDocument(Document document)
        throws TransformerException {
        document.normalize();
        Source source = new DOMSource(document);
        StringWriter out = new StringWriter();
        Result resultStream = new StreamResult(out);
        identityTransform(source, resultStream);
        String result = out.toString();

        return result;
    }
    
    public static InputStream SourceToInputStream(Source source) throws Exception {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		StreamResult streamResult = new StreamResult(baos);
        identityTransform(source, streamResult);
		return new ByteArrayInputStream(baos.toByteArray());
	}

    /**
     * Create a String result from a DOM Node
     * 
     * @param node
     *            the DOM Node
     * @return a String representation of the DOM Document
     * @throws TransformerException
     */
    public static String createStringFromDOMNode(Node node) throws TransformerException {
        // node.normalize();
        Source source = new DOMSource(node);
        StringWriter out = new StringWriter();
        Result resultStream = new StreamResult(out);
        Dom.identityTransform(source, resultStream, "utf-8", true);
        String result = out.toString();
        return result;
    }
    
  
	/**
	 * Write the DOMSource to OutputStream.
	 *
	 * @param src The DOM source.
	 * @param os The OutputStream.
	 * @param charSetName The character set name for encoding.
	 * @throws Exception
	 */
	public static void writeDOMSource(DOMSource src, OutputStream os, String charSetName) throws Exception {
		OutputStreamWriter writer = null;
		if (charSetName == null) writer = new OutputStreamWriter(os);
		else writer = new OutputStreamWriter(os, charSetName);
		StreamResult result = new StreamResult(writer);
		Dom.identityTransform(src, result, charSetName, false);
		writer.close();
 }

	public static void identityTransform(Source source, Result result) throws TransformerConfigurationException, TransformerException {
		identityTransform(source, result, null, false);
	}
	
	public static void identityTransform(Source source, Result result, String charEncoding, boolean omitXmlDecl) throws TransformerConfigurationException, TransformerException {
		//Use the TransformerFactory bundled with the JRE
		//Xalan doesn't handle namespaces correctly in all cases.
//		System.setProperty("javax.xml.transform.TransformerFactory", "com.sun.org.apache.xalan.internal.xsltc.trax.TransformerFactoryImpl"); 
//		TransformerFactory tf = TransformerFactory.newInstance();
//		Transformer t = tf.newTransformer();
		Transformer t = Dom.getTransformer();
		t.setOutputProperty(OutputKeys.INDENT, "yes");
		t.setOutputProperty(OutputKeys.METHOD, "xml");
		if (charEncoding == null) {
			t.setOutputProperty(OutputKeys.ENCODING, "utf-8");
		} else {
			t.setOutputProperty(OutputKeys.ENCODING, charEncoding);
		}
		if (omitXmlDecl) {
			t.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "true");
		} else {
			t.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "false");
		}
		t.transform(source, result);
	}
	
	public static DOMSource sourceToDOMSource(Source src) throws Exception {
		if (src instanceof DOMSource) {
			return (DOMSource)src;
		} else {
			DOMResult domRslt = new DOMResult();
			identityTransform(src, domRslt);
			return new DOMSource(domRslt.getNode());
		}
	}
	  /**
	 * @deprecated 
     * please use the method in DomUtil instead
	 */
    public static Node findChild(Node parent, String nodeName, boolean recursive) {
       
        return DomUtil.findChild(parent, nodeName, recursive);
    }

    /**
	 * @deprecated 
     * please use the method in DomUtil instead
	 */
    public static Node findChild(Node parent, String namespaceURI, String nodeName, boolean recursive) {
       return DomUtil.findChild(parent, namespaceURI, nodeName, recursive);
    }
    /**
	 * @deprecated 
     * please use the method in DomUtil instead
	 */
    public static NodeList findChildren(Node parent,String namespaceURI,  String nodeName,
            boolean recursive) {
       return DomUtil.findChildren(parent, namespaceURI, nodeName, recursive);
        
    }
    
    /**
	 * @deprecated 
     * please use the method in DomUtil instead
	 */
    public static NodeList findChildren(Node parent, String nodeName,
        boolean recursive) {
       
        return DomUtil.findChildren(parent, nodeName, recursive);
    }

    
    
    /**
	 * @deprecated 
     * please use the method in DomUtil instead
	 */
    public static String getAttributeValue(Node n, String attName) {
      
        return DomUtil.getAttributeValue(n, attName);
    }
    
    /**
	 * @deprecated 
     * please use the method in DomUtil instead
	 */
    public static Node getFirstChild(Node node) {
      
        return DomUtil.getFirstChild(node);
    }

    /**
	 * @deprecated 
     * please use the method in DomUtil instead
	 */
    public static Node getNextSibling(Node node) {
       
        return DomUtil.getNextSibling(node);
    }
    /**
	 * @deprecated 
     * please use the method in DomUtil instead
	 */
    public static NodeList getChildren(Node parent) {
     
        return DomUtil.getChildren(parent);
    }
    /**
	 * @deprecated 
     * please use the method in DomUtil instead
	 */
    public static String getPrefixForNamespaceURI(Node node,
            String namespaceURI) {
     
            return DomUtil.getPrefixForNamespaceURI(node, namespaceURI);
       
    }
    /**
	 * @deprecated 
     * please use the method in DomUtil instead
	 */
    public static String getPrefixForNamespaceURIRecursive(Node node,
            String namespaceURI) {
         
        return DomUtil.getPrefixForNamespaceURIRecursive(node, namespaceURI);
    }
    /**
	 * @deprecated 
     * please use the method in DomUtil instead
	 */
    public static String getNamespaceURIForPrefixRecursive(Node node,
            String prefix) {
          
        return DomUtil.getNamespaceURIForPrefixRecursive(node, prefix);
    }
    
    /**
	 * @deprecated 
     * please use the method in DomUtil instead
	 */
    public static String getNamespaceURIForPrefix(Node node, String prefix) {

		
		return DomUtil.getNamespaceURIForPrefix(node, prefix);

	}
    
 
    /**
	 * @deprecated 
     * please use the method in DomUtil instead
	 */
    public static String getTextContent(Node node) {
    	return DomUtil.getTextContent(node);
    }

}
