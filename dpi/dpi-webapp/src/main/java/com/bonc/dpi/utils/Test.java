package com.bonc.dpi.utils;

import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Test {
	private static String isIp = "((1[0-9][0-9]\\.)|(2[0-4][0-9]\\.)|(25[0-5]\\.)|([1-9][0-9]\\.)|([0-9]\\.)){3}((1[0-9][0-9])|(2[0-4][0-9])|(25[0-5])|([1-9][0-9])|([0-9]))";
	private static String isIpPort = "((1[0-9][0-9]\\.)|(2[0-4][0-9]\\.)|(25[0-5]\\.)|([1-9][0-9]\\.)|([0-9]\\.)){3}((1[0-9][0-9])|(2[0-4][0-9])|(25[0-5])|([1-9][0-9])|([0-9])):[0-9]{2,5}";
	public static void main(String[] args) {
		String suffix =DomainUtil.getSuffix();
		List<String> list2 = ReadWriteTxt.readText("C:\\Users\\Administrator\\Desktop\\1.txt",null);
		List<String> list3 = new LinkedList<String>();
		Set<String> root = new LinkedHashSet<String>();
		for (String s : list2) {
				String[] value = s.split("	");
				Pattern isIpPattern = Pattern.compile(isIp);
				Matcher isIpMatcher = isIpPattern.matcher(value[0]);
				if (isIpMatcher.matches()) {
					root.add(s);
				} else {
					Pattern isIpPortPattern = Pattern.compile(isIpPort);
					Matcher isIpPortPatternMatcher = isIpPortPattern.matcher(value[0]);
					if (isIpPortPatternMatcher.matches()) {
						root.add(s);
					}else{
						list3.add(s);
					}
				}
			
		}
		root.addAll(DomainUtil.getRootDomainCode(list3, suffix));
		List<String> list = new LinkedList<String>();
		list.addAll(root);
		ReadWriteTxt.writeToFile2("RootDomainCode.txt", "C:\\Users\\Administrator\\Desktop\\", list);
		System.out.println("ok");
	}
}
