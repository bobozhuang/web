package com.briup.webServer.v2.Webclass;

import com.briup.webServer.v2.request.Request;
import com.briup.webServer.v2.response.Response;

public class TestWebClass implements WebClass{
	@Override
	public void doService(Request request, Response response) {
		System.out.println("此类被访问到");
	}
}
