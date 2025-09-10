package com.bonc.common.utils;

import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

public class PropertiesLoader implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private Properties props = new Properties();
	
	public String getString(String str) {
		String string = props.getProperty(str);
		return string;
	}
	
	public String getString(String str, String defaultValue) {
		String string = props.getProperty(str);
		if(string == null) {
			return defaultValue;
		}
		return string;
	}
	
	public Integer getInteger(String str) {
		String string = props.getProperty(str);
		return Integer.valueOf(string);
	}
	
	public String setString(String key, String value) {
		String string = (String) props.setProperty(key, value);
		return string;
	}
	
	public Map<String, String> getMapByPrefix(String prefix) {
		Map<String, String> map = new HashMap<String, String>();
		Set<Object> set = props.keySet();
		for (Object object : set) {
			String key = (String) object;
			if(key.startsWith(prefix)) {
				map.put(key, props.getProperty(key));
			}
		}
		return map;
	}
	
	public PropertiesLoader(String name) {
		try {
			// 加载配置的配置文件
//			InputStream is = PropertiesLoader.class.getClassLoader().getResourceAsStream("1.properties");
//			Properties p = new Properties();
//			p.load(is);
			// 加载配置文件
			InputStream inputStream = PropertiesLoader.class.getClassLoader().getResourceAsStream(name);
			props.load(inputStream);
		} catch (IOException e) {
			e.printStackTrace();
			throw new RuntimeException("读取配置文件失败！");
		}
	}

	public Properties getProps() {
		return props;
	}

	public void setProps(Properties props) {
		this.props = props;
	}
}
