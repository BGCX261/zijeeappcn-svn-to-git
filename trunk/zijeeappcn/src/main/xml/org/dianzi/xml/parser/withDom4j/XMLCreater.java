package org.dianzi.xml.parser.withDom4j;

import org.dom4j.Document;
import org.dom4j.DocumentFactory;
import org.dom4j.Element;
import org.dom4j.QName;

/**
 * 使用DocumentFactory对象创建一个空的Document对象。DocumentFactory对象由DocumentFactory.
 * getInstance()静态方法产生。对Document对象调用addElement()方法将创建XML根节点，并将该节点返回。
 * 也可以手工创建一个Element对象并调用Document.setRootElement()方法把它设置为根节点
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
     * Element通过addElement()为自己增加一个子节点到当前所有子节点的后面。该方法可以接受三种不同类型的参数：(QName
     * qname)、(String name)或者(String qualifiedName, String
     * namespaceURI)。该方法返回增加的子节点的Element对象。
     * Element通过addAttribute()为自己增加属性。该方法可以接受两种不同类型的参数：(QName qname, String
     * value)或者(String name, String value)。该方法返回自身的Element对象。
     * Element通过addText()为自己增加文本内容。该方法只接受String类型参数，并返回自身的Element对象。
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
     * 要删除XML树上的一棵子树，首先要找到该子树的根节点，然后对该节点调用detach()方法。注意：如果对根节点调用了detach()方法，
     * 将导致该XML树不再完整（一个XML文件需要有且仅有一个根节点）。
     * 要清除Element下的所有子节点（包括Element和text），可以对该Element调用clearContent
     * ()方法。该方法不会清除Element的属性。
     * 要清除Element下的某个Attribute，首先要获得该Attribute对象，然后把它作为参数调用Element的remove()方法。
     */
    public void deleteSubtree(Element subtreeRoot) {
        subtreeRoot.detach();
        return;
    }
    
    
    /**
     * 可以对一个Element对象调用isTextOnly()方法判断它是否只含有text或者是空节点。对Element对象调用addText()
     * 方法将把一个字符串附加到Element中
     * ，但不会修改它原来拥有的text或者子节点。如果Element是isTextOnly()，要修改原来含有的text
     * ，可以先调用clearContent()，再调用addText()并把新值传入。
     */
    public void updateText(Element element, String newText) {
        if (element.isTextOnly()) {
            element.clearContent();
            element.addText(newText);
        }
        return;
    }


}
