package org.dianzi.xml.parser.dom4j;

import java.io.File;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.QName;
import org.dom4j.io.SAXReader;

public class QNameParser {
    /******************** SAMPLE XML FILE *************************
    <heavyz:Sample
      xmlns:heavyz="http://www.heavyzheng.com/schema/sample.xsd">
      <heavyz:HelloWorld/>
    </heavyz:Sample>
    *************************************************************/
    /************************* OUTPUT *****************************
    localname       : Sample
    namespace prefix: heavyz
    namespace URI   : http://www.heavyzheng.com/schema/sample.xsd
    qualified name  : heavyz:Sample
    *************************************************************/
    public static void printRootQNameInfo(Document doc) {
        Element root = doc.getRootElement();
        
        QName qname = root.getQName();
        System.out.println("local name      : " + qname.getName());
        System.out.println("namespace prefix: " + qname.getNamespacePrefix());
        System.out.println("namespace URI   : " + qname.getNamespaceURI());
        System.out.println("qualified name  : " + qname.getQualifiedName());
        return;
    }

    /**
     * 可以调用Namespace(String prefix, String uri)
     * 构造方法构造一个新的Namespace对象；
     * 也可以调用Namespace.get(String prefix, String uri) 
     * 静态方法获得一个新的Namespace对象。
     * 
     * 可以调用QName(String name)构造方法构造一个没有namespace的qualified name；或者调用QName(String
     * name, Namespace namespace)构造方法构造一个有namespace的qualified name。
     */
    public static void main(String[] args) throws DocumentException {
        File xmlFile = new File("./src/sampleQName.xml");
        SAXReader reader = new SAXReader();
        Document doc = reader.read(xmlFile);
        printRootQNameInfo(doc);
    }

}
