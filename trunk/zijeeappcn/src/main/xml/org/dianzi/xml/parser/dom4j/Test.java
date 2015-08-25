package org.dianzi.xml.parser.dom4j;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

/** * @description ����xml�ַ��� * @author �޴����� * @Time ����11:01:31 */
public class Test {
    public void readStringXml(String xml) {
        Document doc = null;
        try { // ��ȡ������XML�ĵ�
              // SAXReader����һ���ܵ�����һ�����ķ�ʽ����xml�ļ������� //
              // SAXReader reader = new SAXReader(); //User.hbm.xml��ʾ��Ҫ������xml�ĵ�
              // Document document = reader.read(new File("User.hbm.xml"));
              // �������ͨ������xml�ַ����� doc = DocumentHelper.parseText(xml); /
              // ���ַ���תΪXML
            Element rootElt = doc.getRootElement(); // ��ȡ���ڵ�
            System.out.println("���ڵ㣺" + rootElt.getName());
            // �õ����ڵ������
            Iterator iter = rootElt.elementIterator("head"); // ��ȡ���ڵ��µ��ӽڵ�head
            // ����head�ڵ�
            while (iter.hasNext()) {
                Element recordEle = (Element) iter.next();
                String title = recordEle.elementTextTrim("title"); // �õ�head�ڵ��µ��ӽڵ�titleֵ
                System.out.println("title:" + title);
                Iterator iters = recordEle.elementIterator("script"); // ��ȡ�ӽڵ�head�µ��ӽڵ�script
                // ����Header�ڵ��µ�Response�ڵ�
                while (iters.hasNext()) {
                    Element itemEle = (Element) iters.next();
                    String username = itemEle.elementTextTrim("username");
                    // �õ�head�µ��ӽڵ�script�µ��ֽڵ�username��ֵ
                    String password = itemEle.elementTextTrim("password");
                    System.out.println("username:" + username);
                    System.out.println("password:" + password);
                }
            }
            Iterator iterss = rootElt.elementIterator("body"); // /��ȡ���ڵ��µ��ӽڵ�body
            // ����body�ڵ�
            while (iterss.hasNext()) {
                Element recordEless = (Element) iterss.next();
                String result = recordEless.elementTextTrim("result"); // �õ�body�ڵ��µ��ӽڵ�resultֵ
                System.out.println("result:" + result);
                Iterator itersElIterator = recordEless.elementIterator("form"); // ��ȡ�ӽڵ�body�µ��ӽڵ�form
                // ����Header�ڵ��µ�Response�ڵ�
                while (itersElIterator.hasNext()) {
                    Element itemEle = (Element) itersElIterator.next();
                    String banlce = itemEle.elementTextTrim("banlce"); // �õ�body�µ��ӽڵ�form�µ��ֽڵ�banlce��ֵ
                    String subID = itemEle.elementTextTrim("subID");
                    System.out.println("banlce:" + banlce);
                    System.out.println("subID:" + subID);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /** * @description ��xml�ַ���ת����map * @param xml * @return Map */
    public static Map readStringXmlOut(String xml) {
        Map map = new HashMap();
        Document doc = null;
        try {
            doc = DocumentHelper.parseText(xml); // ���ַ���תΪXML
            Element rootElt = doc.getRootElement(); // ��ȡ���ڵ�
            System.out.println("���ڵ㣺" + rootElt.getName()); // �õ����ڵ������
            Iterator iter = rootElt.elementIterator("head"); // ��ȡ���ڵ��µ��ӽڵ�head
            // ����head�ڵ�
            while (iter.hasNext()) {
                Element recordEle = (Element) iter.next();
                String title = recordEle.elementTextTrim("title"); // �õ�head�ڵ��µ��ӽڵ�titleֵ
                System.out.println("title:" + title);
                map.put("title", title);
                Iterator iters = recordEle.elementIterator("script"); // ��ȡ�ӽڵ�head�µ��ӽڵ�script
                // ����Header�ڵ��µ�Response�ڵ�
                while (iters.hasNext()) {
                    Element itemEle = (Element) iters.next();
                    String username = itemEle.elementTextTrim("username");
                    // �õ�head�µ��ӽڵ�script�µ��ֽڵ�username��ֵ
                    String password = itemEle.elementTextTrim("password");
                    System.out.println("username:" + username);
                    System.out.println("password:" + password);
                    map.put("username", username);
                    map.put("password", password);
                }
            }
            Iterator iterss = rootElt.elementIterator("body"); // /��ȡ���ڵ��µ��ӽڵ�body
            // ����body�ڵ�
            while (iterss.hasNext()) {
                Element recordEless = (Element) iterss.next();
                String result = recordEless.elementTextTrim("result"); // �õ�body�ڵ��µ��ӽڵ�resultֵ
                System.out.println("result:" + result);
                Iterator itersElIterator = recordEless.elementIterator("form"); // ��ȡ�ӽڵ�body�µ��ӽڵ�form
                // ����Header�ڵ��µ�Response�ڵ�
                while (itersElIterator.hasNext()) {
                    Element itemEle = (Element) itersElIterator.next();
                    String banlce = itemEle.elementTextTrim("banlce"); // �õ�body�µ��ӽڵ�form�µ��ֽڵ�banlce��ֵ
                    String subID = itemEle.elementTextTrim("subID");
                    System.out.println("banlce:" + banlce);
                    System.out.println("subID:" + subID);
                    map.put("result", result);
                    map.put("banlce", banlce);
                    map.put("subID", subID);
                }
            }
        } catch (DocumentException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return map;
    }

    public static void main(String[] args) { // ��������Ҫ������xml�ַ�������
        String xmlString = "<html>" + "<head>" + "<title>dom4j����һ������</title>"
                + "<script>" + "<username>yangrong</username>"
                + "<password>123456</password>" + "</script>" + "</head>"
                + "<body>" + "<result>0</result>" + "<form>"
                + "<banlce>1000</banlce>" + "<subID>36242519880716</subID>"
                + "</form>" + "</body>" + "</html>";
        /* * Test2 test = new Test2(); test.readStringXml(xmlString); */
        Map map = readStringXmlOut(xmlString);
        Iterator iters = map.keySet().iterator();
        while (iters.hasNext()) {
            String key = iters.next().toString(); // �õ���
            String val = map.get(key).toString(); // �õ�ֵ
            System.out.println(key + "=" + val);
        }
    }
}