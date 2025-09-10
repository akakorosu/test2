package com.bonc.dpi.utils;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.lang.StringUtils;

public class FileUtils {
	
	public static List<List<String>> createList(InputStream inputStream, String split) {
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(inputStream,"utf-8")); //后添加的utf-8
			String line;
			List<List<String>> list = new ArrayList<List<String>>();
			while ((line = br.readLine()) != null) {
				List<String> sList = Arrays.asList(line.split(split, -1));
				list.add(sList);
			}
			return list;
		} catch (IOException e) {
			e.printStackTrace();
			throw new RuntimeException("");
		}
	}

	/**
	 * 创建文件
	 * @param filePath
	 * @param list
	 */
	public static void createFile(String filePath, List<List<String>> list) {
		try {
			FileOutputStream out = new FileOutputStream(new File(filePath));
			BufferedOutputStream buff = new BufferedOutputStream(out);
			for (List<String> l : list) {
				buff.write((new StringBuilder(StringUtils.join(l.toArray(), "~")).append("\r\n")).toString().getBytes());
			}
			buff.flush();
			buff.close();
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("创建临时文件错误：" + filePath);
		}
	}
	
	/**
	 * 判断文件（目录）是否存在
	 * @param path
	 * @return
	 */
	public static Boolean existsPath(String path) {
		File dir = new File(path);
		return dir.exists();
	}
	
	/**
	 * 创建文件（目录）
	 * @param path
	 * @return
	 */
	public static Boolean mkdirsPath(String path) {
		return new File(path).mkdirs();
	}
}
