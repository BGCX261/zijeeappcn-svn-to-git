package org.dianzi.xml.parser.dom;

import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;

import com.sun.org.apache.xerces.internal.parsers.DOMParser;

/**
 * DOM中的核心概念就是节点。DOM在分析XML文档时，
 * 将将组成XML文档的各个部分(元素、属性、文本、注释、处理指令等)映射为一个对象(节点)。
 * 在内存中，这些节点形成一课文档树
 * 整棵树是一个节点，树中的每一个节点也是一棵树(子树)，
 * 可以说，DOM就是对这棵树的一个对象描述，我们通过访问树中的节点来存取XML文档的内容。
 * PS：属性节点是附属于元素的，不能被看做是元素的子节点，更不能作为一个单独的节点
 */
public class DOMPrinter {
    public static void main(String[] args) {
        try {
            /** 获取Document对象 */
            DOMParser parser = new DOMParser();
            parser.parse("src/dbconfig.xml");
            Document document = parser.getDocument();
            System.out.println(document);// [#document: null]
            printNode(document);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void printNode(Node node) {
        short nodeType = node.getNodeType();
        switch (nodeType) {
        case Node.PROCESSING_INSTRUCTION_NODE:// 预处理指令类型
            printNodeInfo(node);
            break;
        case Node.ELEMENT_NODE:// 元素节点类型
            printNodeInfo(node);
            printAttribute(node);
            break;
        case Node.TEXT_NODE:// 文本节点类型
            printNodeInfo(node);
            break;
        default:
            break;
        }

        Node child = node.getFirstChild();
        while (child != null) {
            printNode(child);
            child = child.getNextSibling();
        }
    }

    /** */
    /**
     * 根据节点类型打印节点
     * 
     * @param node
     */
    public static void printNodeInfo(Node node) {
        if (node.getNodeType() == Node.ELEMENT_NODE) {
            System.out.println("NodeName: " + node.getNodeName());
        } else if (node.getNodeType() == Node.TEXT_NODE) {
            String value = node.getNodeValue().trim();
            if (!value.equals(""))
                System.out.println("NodeValue: " + value);
            else
                System.out.println();
        } else {
            System.out
                    .println(node.getNodeName() + " : " + node.getNodeValue());
        }
    }

    /** */
    /**
     * 打印节点属性
     * 
     * @param aNode 节点
     */
    public static void printAttribute(Node aNode) {
        NamedNodeMap attrs = aNode.getAttributes();
        if (attrs != null) {
            for (int i = 0; i < attrs.getLength(); i++) {
                Node attNode = attrs.item(i);
                System.out.println("Attribute: " + attNode.getNodeName()
                        + "=\"" + attNode.getNodeValue() + "\"");
            }
        }
    }
}