package com.briup.webServer.v2.response;

import java.io.File;
import java.io.FileInputStream;
import java.io.PrintStream;

import com.briup.webServer.v2.util.ServerInfo;

public class Response {
	PrintStream out = null;
	FileInputStream fis= null;
	String storage = ServerInfo.get("storage");
	String indexPage = ServerInfo.get("indexPage");
	String errorPage = ServerInfo.get("errorPage");
	boolean flag = true;
	
	public Response(PrintStream out) {
		super();
		this.out = out;
	}
	
	public void doResponse(String resource) {
		try {
			
			if (resource.equals("/")) {
				resource = indexPage;
				
			} 
			String responseLine = "HTTP/1.1 200 OK";
			File file = new File(storage+resource);
			System.out.println("拼出了路径："+file);
			if (!file.exists()) {
				file = new File(storage+errorPage);
				System.out.println("应该不存在："+file);
				responseLine = "HTTP/1.1 404 NotFound";
				
			}
			fis = new FileInputStream(file);
			byte[] temp = new byte[1024];
			out.println(responseLine);
			out.println();
			int len = 0;
			while((len = fis.read(temp))!=-1){
				out.write(temp,0,len);
				System.out.println("输出可能几次才能出完！");
				//System.out.println(new String(temp));
				out.flush();
			
			}
			out.close();
		
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
