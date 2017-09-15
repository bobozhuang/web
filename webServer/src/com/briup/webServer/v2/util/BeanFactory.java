package com.briup.webServer.v2.util;
/**
 * 
 * @author 83822
 * 1.读取配置文件，将配置文件中所描述的，进行实例化
 * 2.提供生产方法，传入字符串 拿出对应的对象
 */

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

import com.briup.webServer.v2.Webclass.WebClass;

public class BeanFactory {
	//Properties 类表示了一个持久的属性集。Properties 可保存在流中或从流中加载。属性列表中每个键及其对应值都是一个字符串。 
	static Properties beans = new Properties();
	static Map<String , WebClass> objects = new HashMap<String , WebClass>();
	static{
		try {
			//类镜像。获取一个数据流（输入流）   类所在未知的输入流
			beans.load(BeanFactory.class.getResourceAsStream("beans.properties"));
			Set<Object> keys = beans.keySet();
			Iterator<Object> it = keys.iterator();
			while(it.hasNext()){
				String key = (String)it.next();
				String className = beans.getProperty(key);
				Object o = Class.forName(className).newInstance();
				objects.put(key,(WebClass)o);
			}
			
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		
	}
	
	public static WebClass getBean(String beanName){
		return objects.get(beanName);
	}
	
	
}
