package org.dianzi.xml.parser.sax;

import java.io.FileInputStream;   
  
import org.xml.sax.InputSource;   
import org.xml.sax.XMLReader;   
import org.xml.sax.helpers.XMLReaderFactory;   
  
public class SaxParserTest   
{   
  public static void main(String[] args)   
  {   
    try  
    {   
      XMLReader xmlReader=XMLReaderFactory.createXMLReader();   
      //�رջ����֤   
      xmlReader.setFeature("http://xml.org/sax/features/validation",true);   
      //ע���¼�������   
      xmlReader.setContentHandler(new ContentHandlerImpl());   
      //ע���쳣������   
      xmlReader.setErrorHandler(new ErrorHandlerImpl());   
         
      xmlReader.parse(new InputSource(new FileInputStream("saxdb.xml")));   
    } catch (Exception e)   
    {   
      System.out.println(e.getMessage());   
    }   
  }   
}