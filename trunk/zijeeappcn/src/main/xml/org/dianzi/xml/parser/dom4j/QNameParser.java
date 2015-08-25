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
     * ���Ե���Namespace(String prefix, String uri)
     * ���췽������һ���µ�Namespace����
     * Ҳ���Ե���Namespace.get(String prefix, String uri) 
     * ��̬�������һ���µ�Namespace����
     * 
     * ���Ե���QName(String name)���췽������һ��û��namespace��qualified name�����ߵ���QName(String
     * name, Namespace namespace)���췽������һ����namespace��qualified name��
     */
    public static void main(String[] args) throws DocumentException {
        File xmlFile = new File("./src/sampleQName.xml");
        SAXReader reader = new SAXReader();
        Document doc = reader.read(xmlFile);
        printRootQNameInfo(doc);
    }

}
