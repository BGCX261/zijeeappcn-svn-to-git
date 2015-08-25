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
      //关闭或打开验证   
      xmlReader.setFeature("http://xml.org/sax/features/validation",true);   
      //注册事件处理器   
      xmlReader.setContentHandler(new ContentHandlerImpl());   
      //注册异常处理器   
      xmlReader.setErrorHandler(new ErrorHandlerImpl());   
         
      xmlReader.parse(new InputSource(new FileInputStream("saxdb.xml")));   
    } catch (Exception e)   
    {   
      System.out.println(e.getMessage());   
    }   
  }   
}