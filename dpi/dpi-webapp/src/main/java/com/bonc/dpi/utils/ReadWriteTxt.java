package com.bonc.dpi.utils;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

public class ReadWriteTxt {
	
	public static void writeToFile2(String fileName,String path,List<String> list){    
		try {
			File file = new File(path+fileName);
			//文件不存在时候，主动穿件文件。
			if(!file.exists()){
				file.createNewFile();
			}
			FileWriter fw = new FileWriter(file,false);
			BufferedWriter bw = new BufferedWriter(fw);
			for(String s : list){
				bw.write(s);
				bw.write("\r\n");
			}
			bw.close();
			fw.close();
		} catch (Exception e) {
		}
	}

	public static List<String> readText(String path,String encoding) {
		List<String> stringList = new LinkedList<String>();
		File file1 = new File(path);
		FileInputStream fis = null;
		InputStreamReader isr = null;
		BufferedReader br = null;
		try {
			fis = new FileInputStream(file1);
			if(StringUtils.isBlank(encoding)){
				isr = new InputStreamReader(fis);
			}else{
				isr = new InputStreamReader(fis,encoding);
			}
			br = new BufferedReader(isr);
			String lineTxt = null;
			while ((lineTxt = br.readLine()) != null) {
				stringList.add(lineTxt);
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (br != null) {
				try {
					br.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if (isr != null) {
				try {
					isr.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if (fis != null) {
				try {
					fis.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return stringList;
	}
}
