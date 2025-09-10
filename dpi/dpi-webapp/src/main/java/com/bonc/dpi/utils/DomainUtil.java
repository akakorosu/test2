package com.bonc.dpi.utils;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DomainUtil {

	private static String isStartWithImg = "^(img|img[0-9][0-9])(\\.[a-zA-Z0-9][-a-zA-Z0-9]{0,62})*$";
	private static String isStartWithNumber = "^[0-9]+(\\.[a-zA-Z0-9][-a-zA-Z0-9]{0,62})*$";
	private static String isIp = "((1[0-9][0-9]\\.)|(2[0-4][0-9]\\.)|(25[0-5]\\.)|([1-9][0-9]\\.)|([0-9]\\.)){3}((1[0-9][0-9])|(2[0-4][0-9])|(25[0-5])|([1-9][0-9])|([0-9]))";
	private static String isIpPort = "((1[0-9][0-9]\\.)|(2[0-4][0-9]\\.)|(25[0-5]\\.)|([1-9][0-9]\\.)|([0-9]\\.)){3}((1[0-9][0-9])|(2[0-4][0-9])|(25[0-5])|([1-9][0-9])|([0-9])):[0-9]{2,5}";
	private static String isRepeat = "(\\w)\\1{2,}.*";
	private static String isStartWithWWW = "^www.*";
	private static List<String> logList = new ArrayList<String>();
	
	//根据参数获取多级域名
	public String getReDomain(String ReFirst,String url) {
		Pattern pattern  = Pattern.compile(ReFirst, Pattern.CASE_INSENSITIVE);
		String result = url;
		try {
			Matcher matcher = pattern.matcher(url);
			matcher.find();
			result = matcher.group();
		} catch (Exception e) {
			System.out.println("[getRE_FIRSTDomain ERROR]====>");
			e.printStackTrace();
		}
		return result;
	}

	public static void main(String[] args) {
		String suffix = getSuffix();
		List<String> domainRuleList = ReadWriteTxt.readText("C:\\Users\\Administrator\\Desktop\\domain_un_count.txt",null);
		List<String> shouldSave = new LinkedList<String>();
		List<String> shouldDelete = new LinkedList<String>();
		List<String> shouldSaveOther = new LinkedList<String>();
		List<String> rule = new LinkedList<String>();
		rule.add(isStartWithNumber);rule.add(isStartWithImg);rule.add(isRepeat);
		for (String s : domainRuleList) {
			Pattern isIpPattern = Pattern.compile(isIp);
			Matcher isIpMatcher = isIpPattern.matcher(s);
			if (isIpMatcher.matches()) {
				shouldSave.add(s);
			} else {
				Pattern isIpPortPattern = Pattern.compile(isIpPort);
				Matcher isIpPortPatternMatcher = isIpPortPattern.matcher(s);
				if (isIpPortPatternMatcher.matches()) {
					shouldSave.add(s);
				} else {
					Pattern isStartWithWWWPattern = Pattern.compile(isStartWithWWW);
					Matcher isStartWithWWWMatcher = isStartWithWWWPattern.matcher(s);
					if (isStartWithWWWMatcher.matches()) {
						shouldSave.add(s);
					} else {
						getResult( s, 0, shouldSave,  shouldDelete,rule);
					}
				}
			}
		}
		shouldSaveOther.addAll(shouldSave);
		ReadWriteTxt.writeToFile2("shouldDelete.txt", "C:\\Users\\Administrator\\Desktop\\", shouldDelete);
		logList.add("-----------------------------------------------");

		Set<String> rootDomainCode = getRootDomainCode(shouldDelete, suffix);
		int count = rootDomainCode.size();
		rootDomainCode.addAll(shouldSave);
		List<String> root = new LinkedList<String>();
		root.addAll(rootDomainCode);

		logList.add("-----------------------------------------------");
		logList.add("总数  = " + domainRuleList.size());
		logList.add("删除的总数  = " + shouldDelete.size());
		logList.add("应该保留的总数  = " + shouldSave.size());
		logList.add("筛选应该删除的域名保留的根域名  = " + count);
		logList.add("保留的根域名与应该保留的重复的数量  = " + (shouldSave.size() + count - rootDomainCode.size()));
		logList.add("新增域名数量  = " + (rootDomainCode.size() - shouldSave.size()));
		logList.add("合并后保留域名数量  = " + root.size());
		logList.add("-----------------------------------------------");

		ReadWriteTxt.writeToFile2("shouldSave.txt", "C:\\Users\\Administrator\\Desktop\\", root);
		logList.add("-----------------------------------------------");
		logList.add("清洗完毕");
		ReadWriteTxt.writeToFile2("log.txt", "C:\\Users\\Administrator\\Desktop\\", logList);

		System.out.println("-----------------------------------------------");
		System.out.println("清洗完毕");
		logList = new ArrayList<String>();
		System.out.println("日志清空");
	}

	//获取根域名，suffix后缀表
	public static Set<String> getRootDomainCode(List<String> shouldDelete, String suffix) {
		Set<String> root = new LinkedHashSet<String>();
		Pattern pattern = Pattern.compile(suffix, Pattern.CASE_INSENSITIVE);
		for (String s : shouldDelete) {
			String []value = s.split("	");
			try {
				Matcher matcher = pattern.matcher(value[0]);
				matcher.find();
				root.add(matcher.group()+"	"+value[1]);
			} catch (Exception e) {
				System.out.println("解析根域名错误，错误元域名为: " + s);
			}
		}
		return root;
	}
	
	//拼接域名正则表达式
	public static String getSuffix() {
		List<String> suffixList = ReadWriteTxt.readText("C:\\Users\\Administrator\\Desktop\\dim_ia_domain_suffix.txt","gbk");
		String result = "";
		for (int i = 0; i < suffixList.size(); i++) {
			String value = suffixList.get(i);
			if (i == suffixList.size() - 1) {
				result += value.substring(1, value.length());
			} else {
				result += value.substring(1, value.length()) + "|";
			}
		}
		String ReSecond = "(\\w*\\.?){1}\\.(" + result + ")$";
		return ReSecond;
	}
	
	//定义正则表达式，根据表达式判断数据是否删除
	public static void getResult(String s, int count, List<String> shouidSave, List<String> shouidDelete,
			List<String> rules) {
		if (rules.size() > count) {
			Pattern p = Pattern.compile(rules.get(count));
			Matcher m = p.matcher(s);
			if (m.matches()) {
				shouidDelete.add(s);
			} else {
				if (rules.size() - 1 == count) {
					shouidSave.add(s);
				} else {
					getResult(s, count + 1, shouidSave, shouidDelete, rules);
				}
			}
		}
	}

}
