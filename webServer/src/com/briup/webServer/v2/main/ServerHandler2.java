package com.briup.webServer.v2.main;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

import com.briup.webServer.v2.Webclass.WebClass;
import com.briup.webServer.v2.request.Request;
import com.briup.webServer.v2.response.Response;
import com.briup.webServer.v2.util.BeanFactory;
import com.briup.webServer.v2.util.ServerInfo;



public class ServerHandler2 extends Thread{
//	static Map<String,String> webClasses = new HashMap<String,String>();
//	static{
//		webClasses.put("test","com.briup.webServer.v2.Webclass.TestWebClass");
//		webClasses.put("login","com.briup.webServer.v2.Webclass.LoginWebClass");
//	}
	Socket socket = null;
	BufferedReader in = null;
	PrintStream out = null;
	
	String errorPage = ServerInfo.get("errorPage");
	
	public ServerHandler2(Socket socket) {
		this.socket = socket;
	}
	@Override 
	public void run() {
		try {
			in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			out = new PrintStream(socket.getOutputStream());
			Request request = new Request(in);
			Response response = new Response(out);
			
			String res = request.getResource();
			if (res.contains(".bean")) {
				//System.out.println("");
//xxx ——>(映射) 某个类的名字（全限定名）  反射 类的对象  实现指定好，能被客户端访问的类，必须包含每个方法，否则代码无法写成通用的
//定义接口， 写一个 doService();   
				//1.先拿到资源名 .之前的内容
				//2.从映射关系中，找到的全限定名
				//3.反射拿到类的对象
				//4.调用类中的doService();
				String key = res.substring(1, res.indexOf("."));
				System.out.println("资源名： "+key);
				
				WebClass wc = BeanFactory.getBean(key);
				
				if(wc != null){
					wc.doService(request, response);
				}else {
					response.doResponse(errorPage);
				}
				
			} else {
				response.doResponse(res);
			}
			socket.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
