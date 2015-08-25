package org.dianzi.xml.parser.sax;

import org.xml.sax.ErrorHandler;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;

public class ErrorHandlerImpl implements ErrorHandler   
{   
  
  public void warning(SAXParseException e) throws SAXException   
  {   
    System.out.println("[Warning ]"+getLocationString(e)+":"+e.getMessage());   
  }   
  
  public void error(SAXParseException e) throws SAXException   
  {   
    System.out.println("[Error ]"+getLocationString(e)+":"+e.getMessage());   
  }   
  
  public void fatalError(SAXParseException e) throws SAXException   
  {   
    System.out.println("[Fatal Error ]"+getLocationString(e)+":"+e.getMessage());   
  }   
  
  private String getLocationString(SAXParseException e)   
  {   
    StringBuffer sb=new StringBuffer();   
    String publicId=e.getPublicId();   
    if(publicId!=null)   
    {   
      sb.append(publicId);   
      sb.append(" ");   
    }   
       
    String systemId=e.getSystemId();   
    if(systemId!=null)   
    {   
      sb.append(systemId);   
      sb.append(" ");   
    }   
       
    sb.append(e.getLineNumber());   
    sb.append(":");   
    sb.append(e.getColumnNumber());   
    return sb.toString();   
  }   
}  