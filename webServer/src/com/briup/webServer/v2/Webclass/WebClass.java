package com.briup.webServer.v2.Webclass;

import com.briup.webServer.v2.request.Request;
import com.briup.webServer.v2.response.Response;
/**
 * 
 * @author 83822
 *用来规定 能被客户端访问到的服务端的类，必须重写的方法
 */
public interface WebClass {
	public void doService(Request request , Response response);
}
