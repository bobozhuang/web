package com.briup.webServer.v2.Webclass;

import com.briup.webServer.v2.request.Request;
import com.briup.webServer.v2.response.Response;

public class RegisterWebClass implements WebClass{

	@Override
	public void doService(Request request, Response response) {

		// 将用户名 密码保存，存到哪里呢？
		String username = request.getParamenter("username");
		String passwd = request.getParamenter("password");
		
			response.doResponse("/RegisterSucc.html");
	}

}
