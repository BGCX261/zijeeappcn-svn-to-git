package org.dianzi.xml.parser.sax;
//org.xml.sax.ContentHandler�ӿ�: ͨ��ʵ�ָýӿڸ����Լ��Ľ���ʵ�֡�
//org.xml.sax.ErrorHandler�ӿڣ����SAXӦ�ó�����Ҫʵ�ֶ��ƵĴ�������ô������ʵ������ӿڣ�������XMLReader�����setErrorHandler()�����������ע���쳣����ʵ������������������ͨ������ӿڱ������еĴ���;��档
import org.xml.sax.Attributes;   
import org.xml.sax.ContentHandler;   
import org.xml.sax.Locator;   
import org.xml.sax.SAXException;   
  
public class ContentHandlerImpl implements ContentHandler   
{   
  /** *//**  
   * �ĵ���ʼ�¼�  
   */  
  public void startDocument() throws SAXException   
  {   
    System.out.println("<?xml version=\"0\" encoding=\"utf-8\"?>");   
  }   
     
  /** *//**  
   * ���մ���ָ���¼�  
   */  
  public void processingInstruction(String target, String data) throws SAXException   
  {   
    System.out.println("<?"+target+" "+data+"?>");   
  }   
     
  /** *//**  
   * Ԫ�ؿ�ʼ�¼�  
   * ����˵����  
   *   uri - ���ƿռ� URI�����Ԫ��û���κ����ƿռ� URI������û������ִ�����ƿռ䴦����Ϊ���ַ�����  
   *   localName - �������ƣ�����ǰ׺�������û������ִ�����ƿռ䴦����Ϊ���ַ�����  
   *   qName - �޶������ƣ�����ǰ׺��������޶������Ʋ����ã���Ϊ���ַ�����  
   *   attributes - ���ӵ�Ԫ�ص����ԡ����û�����ԣ��������ǿյ� Attributes ����  
   */  
  public void startElement(String uri, String localName, String qName, Attributes attrs) throws SAXException   
  {   
    System.out.print("<"+qName);//���Ԫ������   
    int len=attrs.getLength();//Ԫ�������б���   
       
    //����ѭ����������б�   
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
   * Ԫ�����ַ������¼�������Ԫ�����ַ�����  
   * ע�⣺Ӧ�ó���Ҫ��ͼ��ȡch����ָ����Χ������ݣ�(��start��length֮��)  
   *      ��Щ��������ʹ��ignorableWhitespace()����������Ԫ�������еĿհף�������characters()��������:������Ч����֤�Ľ�����  
   */  
  public void characters(char[] ch, int start, int length) throws SAXException   
  {   
    System.out.print(new String(ch,start,length));   
  }   
  
  /** *//**  
   * ����Ԫ���¼�  
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