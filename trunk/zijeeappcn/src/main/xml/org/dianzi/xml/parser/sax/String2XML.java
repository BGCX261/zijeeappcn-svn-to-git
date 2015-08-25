package org.dianzi.xml.parser.sax;

import java.io.ByteArrayOutputStream;
import java.io.StringReader;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.xml.sax.InputSource;

public class String2XML {

    

    public static void main(String[] args) throws Exception {
        String xmlStr = "......";
        StringReader sr = new StringReader(xmlStr);

        InputSource is = new InputSource(sr);

        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();

        DocumentBuilder builder=factory.newDocumentBuilder();

        Document doc = builder.parse(is);

        //XML转字符串

        TransformerFactory  tf  =  TransformerFactory.newInstance();

        Transformer t = tf.newTransformer();

        t.setOutputProperty("encoding","GB23121");//解决中文问题，试过用GBK不行

        ByteArrayOutputStream  bos  =  new  ByteArrayOutputStream();

        t.transform(new DOMSource(doc), new StreamResult(bos));

        String xmlString = bos.toString();

    }

}
