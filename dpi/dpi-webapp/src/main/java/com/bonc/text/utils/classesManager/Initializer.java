package com.bonc.text.utils.classesManager;

import com.bonc.common.utils.PropertiesLoader;

public class Initializer {
	
	static {
		System.out.println("################## 加载so文件开始2 #########################");
		PropertiesLoader pl = new PropertiesLoader("sysConfig.properties");
		//System.out.println(System.getProperty("java.library.path"));
		// 这个方法so文件名称前面需要加lib，例如：libjiami.so
		// System.loadLibrary("jiami");
		System.load(pl.getString("so.path"));
		System.out.println("################## 加载so文件结束2 #########################");
	}

	public static native String getClassLoader2();

	// -Djava.library.path=/home/sn/my/test-so/
	public static void main(String[] args) {
		System.out.println(Initializer.getClassLoader2());
	}
}
