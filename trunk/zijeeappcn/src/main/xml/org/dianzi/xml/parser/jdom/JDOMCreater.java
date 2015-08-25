package org.dianzi.xml.parser.jdom;

import java.io.IOException;

import org.jdom.Document;
import org.jdom.Element;
import org.jdom.output.XMLOutputter;

public class JDOMCreater {
    public static void main(String[] args) throws IOException {
        Document doc = new Document(new Element("games"));
        Element newGame = new Element("game").setText("Final Fantasy VI");
        newGame.setAttribute("genre", "rpg");

        doc.getRootElement().addContent(newGame);
        XMLOutputter domstream = new XMLOutputter();
        domstream.output(doc, System.out);
    }
}
