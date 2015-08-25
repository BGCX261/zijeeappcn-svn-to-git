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
 *   友联告警转出配置参数解析
 * @description:
 * @Copyright: Copyright (c) 2008
 * @Company: ultrapower.com
 * @author linengjin 
 * @E-mail:lnj2050@hotmail.com
 * @create time：2010-11-19 下午02:23:52
 * @version 1.0
 */
public class TransferXmlConfig {
	
	
    //配置文件
	private static final String CONFIG_FILE = "../conf/unionmon_config.xml";
//	private static final String CONFIG_FILE = "D:/eclipse3.2/workspace3.2/ext_i18n_ultranms/UnionmonAlertTransfer/conf/unionmon_config.xml";
	
	//单例
	private static TransferXmlConfig instance = null;
	
	//unionmon接受告警http服务地址
	private String url = null;
	
	//事件级别转换
	private Map severityMap = new HashMap();
	
	//字符集
	private String charSetName = null;
	
	//一次一起发送告警最大个数
	private int onceSendMaxSize = 1;
	
	//按照告警标题过滤
	private List filterTitles = new ArrayList();
	
	//按照告警级别进行过滤
	private List filterSeveritys = new ArrayList();
	
	//按照告警被管对象进行过滤
	private List filterMoNames = new ArrayList();
	
	//按照告警内容进行过滤
	private List filterTexts = new ArrayList();
	
	//子资源类型映射 key=classTitle  value=classTitle;
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
     * 加载配置
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
     * 初始一些基本的配置信息
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
        
        //告警级别映射
        initSeverityMap(doc);
        
        //被过滤的告警标题
        initFilterTitles(doc);
        
        //被过滤的告警级别
        initFilterSeveritys(doc);
        
        //被过滤的告警对象
        initFilterMoNames(doc);
        
        //被过滤的告警内容
        initFilterTexts(doc);
        
        //需要传moName过去的资源实例
        initMapSubResType(doc);
    }
    
    
    /**
     * 初始化事件级别映射
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
     * 初始化被过滤的告警标题
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
     * 初始化被过滤的告警级别
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
     * 初始化被过滤的被管对象
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
     * 初始化被过滤的告警内容
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
     * 初始化被过滤的告警内容
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
