package com.briup.webServer.v1;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerMain1 {

	public static void main(String[] args) {
		ServerSocket  server = null;
		Socket socket = null;
		BufferedReader in = null;
		//服务端相应即可能返还字符，也可能返还字节
		PrintStream out = null;
		//服务器上读取硬盘上的数据用
		FileInputStream fis = null;
		String method = "";
		String resource = "";
		String storage = "files";
		try {
			//分析资源
			server = new ServerSocket(8090);
			while (true) {
				socket = server.accept();
				in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
				out = new PrintStream(socket.getOutputStream());
				String requestLine = in.readLine();
				
				if (!"".equals(requestLine) && requestLine != null) {
					
					String[] infos = requestLine.split(" ");
					method = infos[0];
					resource = infos[1];//get方式传参时
					//如果有参数，为 资源名?key=value&key=value
					// 假定 资源名是 xxx.bean
					if (resource.contains(".bean")) {
						if (method.toUpperCase().equals("GET")) {
							
						} else if (method.toUpperCase().equals("POST")) {

						}		
					}
					else{
					
						if (resource.equals("/")) {
							resource = "/index.html";

						}
						// 响应行 版本号 响应码 自定义消息（给客户端看）
						String responseLine = "HTTP/1.1 200 OK";
						File file = new File(storage + resource);
						if (!file.exists()) {
							file = new File(storage + "/error.html");
							responseLine = "HTTP/1.1 404 NOTFOUND";
						}
						// 文件存在统一处理
						fis = new FileInputStream(file);
						byte[] temp = new byte[1024];

						// 响应头
						// 空行
						out.println(responseLine);
						out.println();
						// 发文件，并不是文件内容
						while (fis.read(temp) != -1) {
							out.write(temp);
							out.flush();
						}
						out.close();// 文件输出完毕关闭输出流
					}
				}
				socket.close();// http协议是一种无状态的，每次请求之间没有必然的联系
			}	
		} catch (Exception e) {
			// TODO: handle exception
		}

	}

}