package com.briup.webServer.v2.util;

import java.io.IOException;
import java.util.Properties;

public class ServerInfo {
	static Properties infos = new Properties();
	static{
		try {
			infos.load(ServerInfo.class.getResourceAsStream("infos.properties"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static String get(String name){
		return infos.getProperty(name);
	}
	
}
