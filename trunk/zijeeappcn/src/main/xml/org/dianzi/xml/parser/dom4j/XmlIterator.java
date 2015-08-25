package org.dianzi.xml.parser.dom4j;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

/**
 * Do need dom4j.jar
 */
public class XmlIterator {

    @SuppressWarnings("unchecked")
    public void printXMLTree(Document doc) {
        // 取得节点对象
        Element root = doc.getRootElement();
        List arr = new ArrayList();
        printElement(root, 1, arr);// 根节点深度为1
        for (int i = 0; i < arr.size(); i++) {
            System.out.println(arr.get(i));
        }
        return;
    }

    /**
     * 遍历标签元素的迭代方法
     * 
     * @param element 标签元素
     * @param level 元素深度
     * @param resultList 遍历的结果
     * @return 返回resultList 每一个tempList包含四个元素
     * @get(0)：记录元素名称，即打印<A>B</A>的A
     * @get()：打印该标签的值，即打印<A>B</A>的B
     * @get()：元素深度
     * @get()：元素的属性及对应值列表，即<A c=“d” >B</A>=》attrObjList.get(0)存c
     *                        =》attrObjList.get()存d
     */
    @SuppressWarnings("unchecked")
    private List printElement(Element element, Integer level, List resultList) {

        List tempList = new ArrayList();
        tempList.add(element.getQualifiedName());// 记录元素名称，即打印<A>B</A>的A
        tempList.add(element.getText());// 打印该标签的值，即打印<A>B</A>的B
        // 打印标签值不能放在下面的if中，可能第二层就有值，如<root><A>B</A></root>
        // tempList.add(level);// 元素深度；
        Iterator eleAttriItor = element.attributeIterator();
        if (eleAttriItor.hasNext()) {// 元素有参数的，遍历参数的个数
            List attrObjList = new ArrayList(element.attributes().size());
            while (eleAttriItor.hasNext()) {
                Attribute eleAttr = (Attribute) eleAttriItor.next();

                List attrObj = new ArrayList();
                attrObj.add(eleAttr.getName());
                attrObj.add(eleAttr.getValue());
                attrObjList.add(attrObj);
            }
            tempList.add(attrObjList);
        } else {
            // 没参数的
        }
        resultList.add(tempList);

        // 迭代
        for (Iterator iter = element.elementIterator(); iter.hasNext();) {
            Element sub = (Element) iter.next();
            printElement(sub, level + 1, resultList);// 深度加一
        }
        return resultList;
    }

    /**
     * @param args
     * @throws DocumentException
     * @throws Exception
     */
    public static void main(String[] args) throws DocumentException {
        File file = new File("./src/sample.xml");
        //创建读取对象
        SAXReader saxReader = new SAXReader();
        saxReader.setValidation(false);// 不验证xml文件内的dtd
        Document doc = saxReader.read(file);
        System.out.println(doc);
        System.out.println(doc.asXML());
        XmlIterator xml = new XmlIterator();
        xml.printXMLTree(doc);
    }
}
