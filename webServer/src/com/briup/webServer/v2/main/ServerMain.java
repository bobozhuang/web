package com.briup.webServer.v2.main;

import java.net.ServerSocket;
import java.net.Socket;

import com.briup.webServer.v2.util.ServerInfo;

public class ServerMain {

	public static void main(String[] args) {

		ServerSocket server = null;
		Socket socket = null;
		try {
			server = new ServerSocket(Integer.parseInt(ServerInfo.get("port")));
			while (true) {
				socket = server.accept();
				ServerHandler2 handler = new ServerHandler2(socket);
				System.out.println("handler线程开启");
				handler.start();
				
			}	
		} catch (Exception e) {
			e.printStackTrace();
		}
	
	}

}
