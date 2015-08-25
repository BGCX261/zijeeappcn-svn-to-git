package org.dianzi.xml.parser.jdom;

import java.io.File;

import org.jdom.Document;
import org.jdom.Element;
import org.jdom.input.SAXBuilder;
import org.jdom.output.Format;
import org.jdom.output.XMLOutputter;

//JDOM利用了java语言的优秀特性，极大地简化了对XML文档的处理，相比DOM简单易用。JDOM也使用对象树来表示XML文档，JDOM使用SAXj解析器来分析XML文档，构建JDOM树。然而JOMD本身并没有提供解析器，它使用其他开发商提供的标准SAX解析器，JDOM默认通过JAXP来选择解析器，可以通过手动知道解析器的类名来设置。

public class JDOMConverter {
    public static void main(String[] args) {
        SAXBuilder saxBuilder = new SAXBuilder();
        try {
            Document doc = saxBuilder.build(new File("domdb.xml"));

            // 首先创建好节点
            Element eltDb = new Element("db");
            Element eltDriver = new Element("driver");
            Element eltUrl = new Element("url");
            Element eltUser = new Element("user");
            Element eltPassword = new Element("password");

            // 设置节点的值
            eltDriver.setText("com.mysql.jdbc.Driver");
            eltUrl.setText("jdbc:mysql://localhost/mySql");
            eltUser.setText("root");
            eltPassword.setText("xlc");

            // 添加到根节点
            eltDb.addContent(eltDriver);
            eltDb.addContent(eltUrl);
            eltDb.addContent(eltUser);
            eltDb.addContent(eltPassword);
            // 根节点设置属性
            eltDb.setAttribute("type", "mysql");

            Element root = doc.getRootElement();
            // root.removeChild("db");//删除节点
            root.addContent(eltDb);// 增加节点

            // 修改db节点中内容
            root.getChild("db").getChild("user").setText("system");
            root.getChild("db").getChild("password").setText("manager");

            XMLOutputter xmlOut = new XMLOutputter();

            // 设置XML格式
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