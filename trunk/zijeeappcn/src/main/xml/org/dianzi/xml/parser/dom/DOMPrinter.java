package org.dianzi.xml.parser.dom;

import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;

import com.sun.org.apache.xerces.internal.parsers.DOMParser;

/**
 * DOM�еĺ��ĸ�����ǽڵ㡣DOM�ڷ���XML�ĵ�ʱ��
 * �������XML�ĵ��ĸ�������(Ԫ�ء����ԡ��ı���ע�͡�����ָ���)ӳ��Ϊһ������(�ڵ�)��
 * ���ڴ��У���Щ�ڵ��γ�һ���ĵ���
 * ��������һ���ڵ㣬���е�ÿһ���ڵ�Ҳ��һ����(����)��
 * ����˵��DOM���Ƕ��������һ����������������ͨ���������еĽڵ�����ȡXML�ĵ������ݡ�
 * PS�����Խڵ��Ǹ�����Ԫ�صģ����ܱ�������Ԫ�ص��ӽڵ㣬��������Ϊһ�������Ľڵ�
 */
public class DOMPrinter {
    public static void main(String[] args) {
        try {
            /** ��ȡDocument���� */
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
        case Node.PROCESSING_INSTRUCTION_NODE:// Ԥ����ָ������
            printNodeInfo(node);
            break;
        case Node.ELEMENT_NODE:// Ԫ�ؽڵ�����
            printNodeInfo(node);
            printAttribute(node);
            break;
        case Node.TEXT_NODE:// �ı��ڵ�����
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
     * ���ݽڵ����ʹ�ӡ�ڵ�
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
     * ��ӡ�ڵ�����
     * 
     * @param aNode �ڵ�
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