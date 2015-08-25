package org.dianzi.xml.parser.withDom4j;

import org.dom4j.Document;
import org.dom4j.DocumentFactory;
import org.dom4j.Element;
import org.dom4j.QName;

/**
 * ʹ��DocumentFactory���󴴽�һ���յ�Document����DocumentFactory������DocumentFactory.
 * getInstance()��̬������������Document�������addElement()����������XML���ڵ㣬�����ýڵ㷵�ء�
 * Ҳ�����ֹ�����һ��Element���󲢵���Document.setRootElement()������������Ϊ���ڵ�
 */
public class XMLCreater {
    private DocumentFactory factory = null;
    private Document doc = null;
    private Element root = null;

    public XMLCreater() {
        factory = DocumentFactory.getInstance();
        doc = factory.createDocument();
    }

    public Element generateRoot(String name) {
        root = doc.addElement(name);
        return root;
    }

    public Element generateRoot(QName qname) {
        root = doc.addElement(qname);
        return root;
    }

    public Element generateRoot(Element element) {
        doc.setRootElement(element);
        root = element;
        return root;
    }
    
    /**
     * Elementͨ��addElement()Ϊ�Լ�����һ���ӽڵ㵽��ǰ�����ӽڵ�ĺ��档�÷������Խ������ֲ�ͬ���͵Ĳ�����(QName
     * qname)��(String name)����(String qualifiedName, String
     * namespaceURI)���÷����������ӵ��ӽڵ��Element����
     * Elementͨ��addAttribute()Ϊ�Լ��������ԡ��÷������Խ������ֲ�ͬ���͵Ĳ�����(QName qname, String
     * value)����(String name, String value)���÷������������Element����
     * Elementͨ��addText()Ϊ�Լ������ı����ݡ��÷���ֻ����String���Ͳ����������������Element����
     */
    public void addElement(Element rootElement) {
        Element childElement1 = rootElement.addElement("author");
        childElement1.addAttribute("name","Toby");
        childElement1.addAttribute("location","Germany");
        childElement1.addText("Tobias Rademacher");

        Element childElement2 = rootElement.addElement("author");
        childElement2.addAttribute("name","James");
        childElement2.addAttribute("name","UK");
        childElement2.addText("James Strachan");
        return;
    }
    
    /**
     * Ҫɾ��XML���ϵ�һ������������Ҫ�ҵ��������ĸ��ڵ㣬Ȼ��Ըýڵ����detach()������ע�⣺����Ը��ڵ������detach()������
     * �����¸�XML������������һ��XML�ļ���Ҫ���ҽ���һ�����ڵ㣩��
     * Ҫ���Element�µ������ӽڵ㣨����Element��text�������ԶԸ�Element����clearContent
     * ()�������÷����������Element�����ԡ�
     * Ҫ���Element�µ�ĳ��Attribute������Ҫ��ø�Attribute����Ȼ�������Ϊ��������Element��remove()������
     */
    public void deleteSubtree(Element subtreeRoot) {
        subtreeRoot.detach();
        return;
    }
    
    
    /**
     * ���Զ�һ��Element�������isTextOnly()�����ж����Ƿ�ֻ����text�����ǿսڵ㡣��Element�������addText()
     * ��������һ���ַ������ӵ�Element��
     * ���������޸���ԭ��ӵ�е�text�����ӽڵ㡣���Element��isTextOnly()��Ҫ�޸�ԭ�����е�text
     * �������ȵ���clearContent()���ٵ���addText()������ֵ���롣
     */
    public void updateText(Element element, String newText) {
        if (element.isTextOnly()) {
            element.clearContent();
            element.addText(newText);
        }
        return;
    }


}
