package org.dianzi.xml.parser.sax;
//org.xml.sax.ContentHandler接口: 通过实现该接口给出自己的解析实现。
//org.xml.sax.ErrorHandler接口：如果SAX应用程序需要实现定制的错误处理，那么它必须实现这个接口，并调用XMLReader对象的setErrorHandler()方法向解析器注册异常处理实例，这样，解析器将通过这个接口报告所有的错误和警告。
import org.xml.sax.Attributes;   
import org.xml.sax.ContentHandler;   
import org.xml.sax.Locator;   
import org.xml.sax.SAXException;   
  
public class ContentHandlerImpl implements ContentHandler   
{   
  /** *//**  
   * 文档开始事件  
   */  
  public void startDocument() throws SAXException   
  {   
    System.out.println("<?xml version=\"0\" encoding=\"utf-8\"?>");   
  }   
     
  /** *//**  
   * 接收处理指令事件  
   */  
  public void processingInstruction(String target, String data) throws SAXException   
  {   
    System.out.println("<?"+target+" "+data+"?>");   
  }   
     
  /** *//**  
   * 元素开始事件  
   * 参数说明：  
   *   uri - 名称空间 URI，如果元素没有任何名称空间 URI，或者没有正在执行名称空间处理，则为空字符串。  
   *   localName - 本地名称（不带前缀），如果没有正在执行名称空间处理，则为空字符串。  
   *   qName - 限定的名称（带有前缀），如果限定的名称不可用，则为空字符串。  
   *   attributes - 附加到元素的属性。如果没有属性，则它将是空的 Attributes 对象。  
   */  
  public void startElement(String uri, String localName, String qName, Attributes attrs) throws SAXException   
  {   
    System.out.print("<"+qName);//输出元素名称   
    int len=attrs.getLength();//元素属性列表长度   
       
    //利用循环输出属性列表   
    for(int i=0;i<len;i++)   
    {   
      System.out.print(" ");   
      System.out.print(attrs.getQName(i));   
      System.out.print("=\"");   
      System.out.print(attrs.getValue(i));   
      System.out.print("\"");   
    }   
    System.out.print(">");   
  }   
     
  /** *//**  
   * 元素中字符数据事件：接收元素中字符数据  
   * 注意：应用程序不要试图读取ch数组指定范围外的数据，(即start至length之外)  
   *      有些解析器将使用ignorableWhitespace()方法来报告元素内容中的空白，而不是characters()方法，如:进行有效性验证的解析器  
   */  
  public void characters(char[] ch, int start, int length) throws SAXException   
  {   
    System.out.print(new String(ch,start,length));   
  }   
  
  /** *//**  
   * 结束元素事件  
   */  
  public void endElement(String uri, String localName, String qName) throws SAXException   
  {   
    System.out.print("</"+qName+">");   
  }   
  
  public void endDocument() throws SAXException   
  {   
       
  }   
  
  public void endPrefixMapping(String prefix) throws SAXException   
  {   
       
  }   
  
  public void ignorableWhitespace(char[] ch, int start, int length) throws SAXException   
  {   
       
  }   
  
  public void setDocumentLocator(Locator locator)   
  {   
       
  }   
  
  public void skippedEntity(String name) throws SAXException   
  {   
       
  }   
  
  public void startPrefixMapping(String prefix, String uri) throws SAXException   
  {   
       
  }   
  
}  