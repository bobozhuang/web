package com.briup.webServer.v2.request;

import java.io.BufferedReader;
import java.util.HashMap;
import java.util.Map;

public class Request {
	private String method = "";
	private String resource = "";
	private Map<String ,String> params = new HashMap<String,String>();
	
	public Request(BufferedReader in) {
		try {
			String requestLine = in.readLine();
			
			if(!"".equals(requestLine) && requestLine!=null){
				
				String[] infos = requestLine.split(" ");
				method = infos[0];
				String value = infos[1];
				System.out.println(infos[0]);
				//infos[1]在get方式传参的时候，如果有参数，资源名？key=value&key2=value
				System.out.println(infos[1]);
				if(getMethod().toUpperCase().equals("GET")){
					if(value.contains("?")){
						//如果包含？，将从？后开始知道结尾
						String kv = value.substring(value.indexOf("?")+1);
						//后解析分析参数添加到集合中
						parseParam(kv);
						
						resource = value.substring(0, value.indexOf("?")); 

					}else{
						resource = value;
						System.out.println("请求完成"+resource);
					}
				}else if (getMethod().toUpperCase().equals("POST") ) {
					resource = value;
					System.out.println("post进入了:"+resource);
					String line = null;
					int requestBodyLen = 0;
					//请求头，找有没有Content-length
					while ((line = in.readLine()) != null) {
						if (line.contains("Content-Length")) {
							requestBodyLen = Integer.parseInt(line.substring(line.indexOf(":")+1).trim());
							//返回的字符串排除前后空白，从而拿到Length
						}
						if(line.equals("")){
							break;
						}
					}
					if(requestBodyLen >0){
						StringBuffer buff = new StringBuffer();
						for (int i = 0; i < requestBodyLen; i++) {
							buff.append((char)in.read());
						}
						parseParam(buff.toString());
					}
					
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public void parseParam(String kv) {
		// 解析参数，完成放入map中
		//username=lgd&password=1214&gender=on
		String[] strs = kv.split("&");
		for (int i = 0; i < strs.length; i++) {
			params.put(strs[i].split("=")[0],strs[i].split("=")[1]);
		}
	}
	public String getParamenter(String key){
		return params.get(key);
	}
	
	public String getMethod() {
		return method;
	}
	public String getResource() {
		return resource;
	}
	
}
