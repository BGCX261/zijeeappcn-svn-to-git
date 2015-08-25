package org.dianzi.xml.parser.jdom;

import java.io.File;

import org.jdom.Document;
import org.jdom.Element;
import org.jdom.input.SAXBuilder;
import org.jdom.output.Format;
import org.jdom.output.XMLOutputter;

//JDOM������java���Ե��������ԣ�����ؼ��˶�XML�ĵ��Ĵ������DOM�����á�JDOMҲʹ�ö���������ʾXML�ĵ���JDOMʹ��SAXj������������XML�ĵ�������JDOM����Ȼ��JOMD����û���ṩ����������ʹ�������������ṩ�ı�׼SAX��������JDOMĬ��ͨ��JAXP��ѡ�������������ͨ���ֶ�֪�������������������á�

public class JDOMConverter {
    public static void main(String[] args) {
        SAXBuilder saxBuilder = new SAXBuilder();
        try {
            Document doc = saxBuilder.build(new File("domdb.xml"));

            // ���ȴ����ýڵ�
            Element eltDb = new Element("db");
            Element eltDriver = new Element("driver");
            Element eltUrl = new Element("url");
            Element eltUser = new Element("user");
            Element eltPassword = new Element("password");

            // ���ýڵ��ֵ
            eltDriver.setText("com.mysql.jdbc.Driver");
            eltUrl.setText("jdbc:mysql://localhost/mySql");
            eltUser.setText("root");
            eltPassword.setText("xlc");

            // ��ӵ����ڵ�
            eltDb.addContent(eltDriver);
            eltDb.addContent(eltUrl);
            eltDb.addContent(eltUser);
            eltDb.addContent(eltPassword);
            // ���ڵ���������
            eltDb.setAttribute("type", "mysql");

            Element root = doc.getRootElement();
            // root.removeChild("db");//ɾ���ڵ�
            root.addContent(eltDb);// ���ӽڵ�

            // �޸�db�ڵ�������
            root.getChild("db").getChild("user").setText("system");
            root.getChild("db").getChild("password").setText("manager");

            XMLOutputter xmlOut = new XMLOutputter();

            // ����XML��ʽ
            Format fmt = Format.getPrettyFormat();
            fmt.setIndent("    ");
            fmt.setEncoding("utf-8");

            xmlOut.setFormat(fmt);
            xmlOut.output(doc, System.out);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}