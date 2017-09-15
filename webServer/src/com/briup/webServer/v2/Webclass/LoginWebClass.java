package com.briup.webServer.v2.Webclass;

import com.briup.webServer.v2.request.Request;
import com.briup.webServer.v2.response.Response;

public class LoginWebClass implements WebClass{

	@Override
	public void doService(Request request, Response response) {
		// 比对用户名 密码
		String username = request.getParamenter("username");
		String passwd = request.getParamenter("password");
		if("bobo".equals(username)&&"bobo".equals(passwd)){
			response.doResponse("/loginSucc.html");
		}else{
			response.doResponse("loginFail.html");
		}
		
	}

}
