package org.dianzi.xml.parser.dom4j;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;



/**
 * @Title:
 *   �����澯ת�����ò�������
 * @description:
 * @Copyright: Copyright (c) 2008
 * @Company: ultrapower.com
 * @author linengjin 
 * @E-mail:lnj2050@hotmail.com
 * @create time��2010-11-19 ����02:23:52
 * @version 1.0
 */
public class TransferXmlConfig {
	
	
    //�����ļ�
	private static final String CONFIG_FILE = "../conf/unionmon_config.xml";
//	private static final String CONFIG_FILE = "D:/eclipse3.2/workspace3.2/ext_i18n_ultranms/UnionmonAlertTransfer/conf/unionmon_config.xml";
	
	//����
	private static TransferXmlConfig instance = null;
	
	//unionmon���ܸ澯http�����ַ
	private String url = null;
	
	//�¼�����ת��
	private Map severityMap = new HashMap();
	
	//�ַ���
	private String charSetName = null;
	
	//һ��һ���͸澯������
	private int onceSendMaxSize = 1;
	
	//���ո澯�������
	private List filterTitles = new ArrayList();
	
	//���ո澯������й���
	private List filterSeveritys = new ArrayList();
	
	//���ո澯���ܶ�����й���
	private List filterMoNames = new ArrayList();
	
	//���ո澯���ݽ��й���
	private List filterTexts = new ArrayList();
	
	//����Դ����ӳ�� key=classTitle  value=classTitle;
	private Map  mapSubResType = new HashMap();
	
	public synchronized static TransferXmlConfig getInstance(){
		if(instance ==  null){
			instance = new TransferXmlConfig();
		}
		
		return instance;
	}
	
	
	private TransferXmlConfig(){
		loadConfig();
	}
	
	 /**
     * ��������
     */
    private void loadConfig() {
    	try{
            File file = new File(CONFIG_FILE);
            FileInputStream in = new FileInputStream(file);
            Reader reader = new InputStreamReader(in);
            SAXReader sax = new SAXReader();
            Document doc = sax.read(reader);
            initConf(doc);
    	}catch(Exception ex){
    	}
    }
    
    
    /**
     * ��ʼһЩ������������Ϣ
     * @param doc
     * @throws Exception
     */
    private void initConf(Document doc) throws Exception{
    	url = doc.selectSingleNode("/unionmon/url").getText().trim();
        this.charSetName = doc.selectSingleNode("/unionmon/charSetName").getText().trim();
        try{
        	String sOnceSendMaxSize = doc.selectSingleNode("/unionmon/onceSendMaxSize").getText().trim();
        	onceSendMaxSize = Integer.parseInt(sOnceSendMaxSize);
        }catch(Exception ex){
        }
        
        //�澯����ӳ��
        initSeverityMap(doc);
        
        //�����˵ĸ澯����
        initFilterTitles(doc);
        
        //�����˵ĸ澯����
        initFilterSeveritys(doc);
        
        //�����˵ĸ澯����
        initFilterMoNames(doc);
        
        //�����˵ĸ澯����
        initFilterTexts(doc);
        
        //��Ҫ��moName��ȥ����Դʵ��
        initMapSubResType(doc);
    }
    
    
    /**
     * ��ʼ���¼�����ӳ��
     * @param doc
     * @throws Exception
     */
    private void initSeverityMap(Document doc) throws Exception{
    	List severityList = doc.selectNodes("/unionmon/severityTrans/severity");
    	for(int i = 0; i < severityList.size(); i++){
    		Element severityElement = (Element)severityList.get(i);
    		String from = severityElement.attributeValue("from");
    		String to = severityElement.attributeValue("to");
    		severityMap.put(from, to);
    	}
    }
    
    
    /**
     * ��ʼ�������˵ĸ澯����
     * @param doc
     * @throws Exception
     */
    private void initFilterTitles(Document doc){
    	try{
    		List titleList = doc.selectNodes("/unionmon/filters/titleFilter/title");
    		if(titleList == null || titleList.size() <= 0){
    			return;
    		}
    		
    		for(int i = 0; i < titleList.size(); i++){
    		    Element titleElement = (Element)titleList.get(i);
    		    String title = titleElement.getText();
    		    if(title.trim().equals("")){
    		    	continue;
    		    }
    		    filterTitles.add(title);
    		}
    	}catch(Exception ex){
    	}
    }
    
    
    /**
     * ��ʼ�������˵ĸ澯����
     * @param doc
     * @throws Exception
     */
    private void initFilterSeveritys(Document doc){
    	try{
    		List severityList = doc.selectNodes("/unionmon/filters/severityFilter/severity");
    		if(severityList == null || severityList.size() <= 0){
    			return;
    		}
    		
    		for(int i = 0; i < severityList.size(); i++){
    		    Element severityElement = (Element)severityList.get(i);
    		    String severity = severityElement.getText();
    		    if(severity.trim().equals("")){
    		    	continue;
    		    }
    		    filterSeveritys.add(severity);
    		}
    	}catch(Exception ex){
    	}
    }
    
    
    /**
     * ��ʼ�������˵ı��ܶ���
     * @param doc
     * @throws Exception
     */
    private void initFilterMoNames(Document doc){
    	try{
    		List moNameList = doc.selectNodes("/unionmon/filters/moNameFilter/moName");
    		if(moNameList == null || moNameList.size() <= 0){
    			return;
    		}
    		
    		for(int i = 0; i < moNameList.size(); i++){
    		    Element moNameElement = (Element)moNameList.get(i);
    		    String moName = moNameElement.getText();
    		    if(moName.trim().equals("")){
    		    	continue;
    		    }
    		    this.filterMoNames.add(moName);
    		}
    	}catch(Exception ex){
    	}
    }
    
    
    /**
     * ��ʼ�������˵ĸ澯����
     * @param doc
     * @throws Exception
     */
    private void initFilterTexts(Document doc){
    	try{
    		List textList = doc.selectNodes("/unionmon/filters/textFilter/text");
    		if(textList == null || textList.size() <= 0){
    			return;
    		}
    		
    		for(int i = 0; i < textList.size(); i++){
    		    Element textElement = (Element)textList.get(i);
    		    String text = textElement.getText();
    		    if(text.trim().equals("")){
    		    	continue;
    		    }
    		    this.filterTexts.add(text);
    		}
    	}catch(Exception ex){
    	}
    }
    
    
    /**
     * ��ʼ�������˵ĸ澯����
     * @param doc
     * @throws Exception
     */
    private void initMapSubResType(Document doc){
    	try{
    		List lstResTypes = doc.selectNodes("/unionmon/subResTypes/resType");
    		if(lstResTypes == null || lstResTypes.size() <= 0){
    			return;
    		}
    		
    		for(int i = 0; i < lstResTypes.size(); i++){
    		    Element resTypeElement = (Element)lstResTypes.get(i);
    		    String classTitle = resTypeElement.attributeValue("classTitle");
    		    if(classTitle == null || classTitle.trim().equals("")){
    		    	continue;
    		    }
    		    
    		    this.mapSubResType.put(classTitle.toLowerCase(), classTitle);
    		}
    		
    	}catch(Exception ex){
    	}
    }
    
    
	public static void main(String []args){
		TransferXmlConfig.getInstance().getUrl();
	}


	public Map getSeverityMap() {
		return severityMap;
	}


	public String getCharSetName() {
		return charSetName;
	}


	public String getUrl() {
		return url;
	}


	public int getOnceSendMaxSize() {
		return onceSendMaxSize;
	}


	public void setOnceSendMaxSize(int onceSendMaxSize) {
		this.onceSendMaxSize = onceSendMaxSize;
	}


	public List getFilterMoNames() {
		return filterMoNames;
	}


	public List getFilterSeveritys() {
		return filterSeveritys;
	}


	public List getFilterTexts() {
		return filterTexts;
	}


	public List getFilterTitles() {
		return filterTitles;
	}


	public Map getMapSubResType() {
		return mapSubResType;
	}
}
