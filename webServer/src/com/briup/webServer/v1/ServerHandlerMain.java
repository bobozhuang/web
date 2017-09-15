package com.briup.webServer.v1;

import java.net.ServerSocket;
import java.net.Socket;

public class ServerHandlerMain {
	public static void main(String[] args) {
		ServerSocket  server = null;
		Socket socket = null;
		try {
			server = new ServerSocket(8090);
			while (true) {
				socket = server.accept();
				ServerHandler handler = new ServerHandler(socket);
				System.out.println("handler线程开启");
				handler.start();
				
			}	
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
