package wxz.test.url.connection;


import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class TestMain {
	private static final String RESP_ENCODING = "GBK";
	
	public static void main(String[] args) throws Exception {
//		http://172.21.1.66:9083/inout/
//		http://172.21.1.66:9084/inout/
		
//		for (int i = 0; i < 10; i++) {
//			callorderMonth();
//		}
		String strURL = "http://hiphotos.baidu.com/%D8%BC%C3%BB%96Z%CC%DB%D8%AF/pic/item/2575ed95f603738dce0f6469b31bb051f919ecaa.jpg?v=tbs";
		strURL="http://imgsrc.baidu.com/forum/pic/item/c8ea15ce36d3d539996c118d3a87e950352ab02e.jpg";
		String saveFilePath ="D:\\a";
		callServer( strURL, saveFilePath);
		strURL = "http://www.baidu.com";
		callServer(strURL);
		System.out.println("==end==");
	}
	
	public static  void callorderMonth() throws Exception{
		String strURL1 = "http://127.0.0.1:9980/inout/";
		String requestData1 =new StringBuffer()
		.append("<?xml version=\"1.0\" encoding=\"GBK\"?>")
		.append("<InnerBus>")
			.append("<ProcID>1201006061602108894197035</ProcID>")
			.append("<SvcCont>")
			.append("<ServiceSubReq>")
			.append("<MSISDN>15884248475</MSISDN>")
			.append("<OprCode>01</OprCode>")
			.append("<BizCode>600906002000005001</BizCode>")
			.append("<NAS_GGSN_ID>211.136.24.2</NAS_GGSN_ID>")
			.append("<TerminalID>0001</TerminalID>")
			.append("<AccessPlatformID>0041009</AccessPlatformID>")
			.append("<OprTime>20100606160205</OprTime>")
			.append("<OprSrc>002</OprSrc>")
			.append("<TriggerContentID></TriggerContentID>")
			.append("<SubscribeKey>222</SubscribeKey>")
			.append("</ServiceSubReq>")
			.append("</SvcCont>")
			.append("</InnerBus>").toString(); 
		callServer(strURL1, requestData1,"orderMonth");
	}
	
	
	public static boolean callServer(String strURL,String requestData,String name) throws Exception {
		URL url = new URL(strURL);
		HttpURLConnection huc = (HttpURLConnection) url.openConnection();
		// set data to server
		huc.setRequestMethod("POST");
		huc.setRequestProperty("Content-Type", "text/xml; charset=GBK");
		byte[] bb = requestData.getBytes("GBK");
		huc.setDoOutput(true);
		OutputStream out = huc.getOutputStream();
		out.write(bb);
		out.flush();
		out.close();
		huc.connect();
		
		// get data from server
		InputStream inSteam = huc.getInputStream();
		BufferedReader reader = new BufferedReader(new InputStreamReader(
				inSteam, RESP_ENCODING));
		StringBuffer response = new StringBuffer();
		int code = huc.getResponseCode();
		if (code >= 200 && code < 300) {
			String temline = "";
			while ((temline = reader.readLine()) != null) {
				response.append(temline);
			}
			huc.disconnect();
//			return new String(response);
//			int indexof = response.toString().indexOf("<RspCode>000000</RspCode>");
//			if(indexof > 0){
//				return true;	
//			}
			System.out.println(name + ":"+response.toString());
			
		}
		return false;
	}
	
	public static boolean callServer(String strURL) throws Exception {
		URL url = new URL(strURL);
		HttpURLConnection huc = (HttpURLConnection) url.openConnection();
		huc.connect();
		// get data from server
		InputStream inSteam = huc.getInputStream();
		BufferedReader reader = new BufferedReader(new InputStreamReader(
				inSteam, RESP_ENCODING));
		StringBuffer response = new StringBuffer();
		int code = huc.getResponseCode();
		if (code >= 200 && code < 300) {
			String temline = "";
			while ((temline = reader.readLine()) != null) {
				response.append(temline);
			}
			huc.disconnect();
			System.out.println("result:"+response.toString());
		}
		
//		BufferedReader reader = new BufferedReader(new InputStreamReader(in));
//		if (code >= 200 && code < 300) {
//			
//			PrintWriter out1 = new PrintWriter(new BufferedWriter(new FileWriter(new File(saveFilePath))));
//			char[] b = new char[1024];
//			String s = null;
//			while((s = reader.readLine()) != null){
//				out1.print(s);
//			}
//			out1.flush();
//		}
		
		
		return false;
	}
	
	
	public static boolean callServer(String strURL,String saveFilePath)  {
		URL url;
		HttpURLConnection huc = null;
		InputStream in = null;
		FileOutputStream out = null;
		try {
			url = new URL(strURL);
			huc = (HttpURLConnection) url.openConnection();
			huc.connect();
			// get data from server
			in = huc.getInputStream();
			int code = huc.getResponseCode();
			File file = new File(saveFilePath);
			
//			if (code >= 200 && code < 300) {
//				out = new FileOutputStream(new File(saveFilePath));
//				byte[] b = new byte[1024];
//				while(in.read(b) != -1){
//					out.write(b);
//				}
//				out.flush();
//			}
			
//			BufferedReader reader = new BufferedReader(new InputStreamReader(in));
//			
//			if (code >= 200 && code < 300) {
//				
//				PrintWriter out1 = new PrintWriter(new BufferedWriter(new FileWriter(new File(saveFilePath))));
//				char[] b = new char[1024];
//				String s = null;
//				while((s = reader.readLine()) != null){
//					out1.print(s);
//				}
//				out1.flush();
//			}
			
//			过程就是：
//			1、得到相应的网络输入流
//			InputStream is = ***.getInputStream();
//			2、本地创建一个输出流
			out = new FileOutputStream(file);
//			3、写到本地
			int i;
			System.out.println("available:"+in.available());
			while ((i = in.read()) != -1) {
				out.write(i);
			}
			out.flush(); 
			
		} catch (MalformedURLException e1) {
			e1.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (in != null) {
				try {
					in.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if (out != null) {
				try {
					out.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if (null != huc) {
				huc.disconnect();
			}
		}
		return false;
	}
}
