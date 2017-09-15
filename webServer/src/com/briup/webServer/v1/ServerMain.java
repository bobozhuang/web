package com.briup.webServer.v1;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

import java.io.PrintStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.URL;
import java.sql.Time;

/**
 * 
 * @author 83822
 * @version 1.0.0 {@link Time}2017.8.22
 */
public class ServerMain {

	public static void main(String[] args) {
		// 1.声明所用对象
		// ServerSocket
		// Socket
		// BufferedReader
		// PrintStream 因为这个流是用来按照http响应规则返回数据给浏览器，http响应规则中可能
		// 既要写字符又要写字节，所以用这个流
		ServerSocket server = null;
		File file = null;
		BufferedReader br = null;
		PrintStream out = null;
		URL url = null;
		try {
			// 2. 赋值，为try语句块外的变量赋值
			server = new ServerSocket(9999);
			Socket client = server.accept();
			// 3. 处理请求，及即从socket中拿出浏览器按照http协议封装好的请求（字符串）关心请求行
			// 按空格拆分字符串
			// 拆出来的第一部分请求方式
			// 拆出来的第二部分资源路径

			System.out.println("进来了");
			br = new BufferedReader(new InputStreamReader(client.getInputStream()));
			String str1 = br.readLine();
			System.out.println(str1);

			String[] str2 = str1.split(" ");
			if (str2[0].equals("GET")) {
				out = new PrintStream(client.getOutputStream());
				if (str2[1].equals("/")) {
					file = new File(System.getProperty("user.dir") + "\\files\\index.html");
					sendFile(str2, file, out);
					// file = new File("src/files/index.html");
					// out = new PrintStream(client.getOutputStream());
					// out.print("你好啊");
				} else {
					file = new File(System.getProperty("user.dir") + str2[1]);
					if (file.exists()) {
					} else {
						file = new File(System.getProperty("user.dir") + "\\files\\error.html");
						sendFile(str2, file, out);

					}
				}

			} else if (str2[0].equals("POST")) {

			}

			System.out.println("出去了");
			// url = new URL(str1);
			//
			// System.out.println("URL is " + url.toString());
			// System.out.println("protocol is "+ url.getProtocol());
			// System.out.println("authority is "+ url.getAuthority());
			// System.out.println("file name is " + url.getFile());//文件名
			// System.out.println("host is " + url.getHost());
			// System.out.println("path is " + url.getPath());
			// System.out.println("port is " + url.getPort());
			// System.out.println("default port is "+ url.getDefaultPort());
			// System.out.println("query is " + url.getQuery());
			// System.out.println("ref is " + url.getRef());

			// 4 处理响应
			// 如果请求方式是 Get即代表没有请求体
			// 从请求行中找到要访问的文件
			// 从本地目录下查找（不是要便利整个文件系统，
			// 代表着我们要定义一个目录位置，此位置为数据仓库，专门用来存放客户端可能会访问的数据
			// 咱们暂定这个目录为“项目/files”

			// 是否有此文件。对于/资源特殊处理，代表访问默认(index.html)
			// 如果有文件，利用输出流，把数据拼成http响应格式的数据，返还给客户（数据找到了，响应码200）
			// 如果没有文件，返还error.html文件（代表比较友好的提示方式），也得按照http相应格式返还error.html

			// 如果是post方式，暂不处理
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	public static void IsOther(File f) {
		String FileName = f.toString();
		int point = FileName.lastIndexOf(".");
	}

	// 5.关闭资源
	// 什么时候关服务器，什么时候管客户端

	// a 让服务端源源不断处理请求（程序不停止运行）
	// b 让服务端同时可以处理多个请求（多线程）

	public static void sendFile(String[] str2, File f, PrintStream out) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(new FileInputStream(f)));
		out.println(str2[2] + " 200 " + "MyWebServer");
		out.flush();
		out.println("Content-Type:text/html;charset:UTF-8");
		out.println();
		out.flush();
		String t = "";
		while ((t = in.readLine()) != null) {
			System.out.println(t);
			out.println(t);
			out.flush();
		}
		out.close();
	}

}
