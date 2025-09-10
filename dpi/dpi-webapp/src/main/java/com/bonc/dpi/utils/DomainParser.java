//package com.bonc.urlparser.common;
//
//import java.util.Collection;
//import java.util.regex.Matcher;
//import java.util.regex.Pattern;
//
//import org.apache.commons.lang.StringUtils;
//
//import com.bonc.urlparser.entity.Domain;
//import com.bonc.urlparser.entity.IP;
//import com.bonc.urlparser.entity.Parameter;
//import com.bonc.urlparser.entity.Path;
//import com.bonc.util.RegExUtils;
//import com.bonc.util.redis.RedisUtils;
//
//public class DomainParser {
//
//	public static Domain getDomain(String dictType, String host, String url, String ipPort, String countFlag, String hostSuffix, String domainHostEndFilter) throws Exception {
//		// 获取redis对象
////		Jedis redis  = CodisUtils.getJedis();
//		//根据url获取host
////		String host = ParserUtils.getURLHost(url);
//		//根据gprsType获取Gprs(从redis读取)
////		Gprs gprs = (Gprs) RedisUtils.getObject(redis, "flowTypeOld_", gprsType);
//		Domain resultDomain = null;
//		//一、url解析
//		if (!StringUtils.isEmpty(url)) {
//			// 获取host对应的Path列表
//			Collection<Path> pathlist = StringUtils.equals(dictType, "redis")?RedisUtils.getList("urlPath_list_"+host, new Path()):DimIAInfo.pathMap.get(host);
//			// 获取host对应的Parameter列表
//			Collection<Parameter> paralist = StringUtils.equals(dictType, "redis")?RedisUtils.getList("urlParameterOld_list_"+host, new Parameter()):DimIAInfo.parameterMap.get(host);
//			// 1. Path解析
//			if (pathlist!=null && pathlist.size() > 0) {
//				for (Path path : pathlist) {
//					if (path.getIS_VALID().equals("1")) {
//						Matcher m = path.getREGEXP().matcher(url);
//						if (m.find()) {
//							resultDomain = new Domain();
//							//TODO 待添加字段(add root_domain,domain_code(host))
//							resultDomain.setDomain_code(host);
//							resultDomain.setProduct_id(path.getPRODUCT_ID());
//							resultDomain.setDomain_type("path");
//							resultDomain.setRoot_domain(ParserUtils.getRootdomain(host));
//							//TotalCountToRedisService.TotalCountToRedis(dictType, "dim_ia_url_path", path.getREGEXP().toString());
////							MapUtils.putKey(countMap, "dim_ia_url_path", path.getREGEXP().toString(), countFlag);
//							path.setTOTAL_COUNT(path.getTOTAL_COUNT() + 1);
//							break;
//						}
//					}
//				}
//			}
//			//2. parameter解析
//			else if (paralist != null && paralist.size() > 0) {
//				for (Parameter para : paralist) {
////					// 这种情况，暂时不会有
////					if (para.getIF_NEED_REGEXP().equals("1") && para.getIF_NEED_KEYVALUE().equals("1")) {
////						Matcher m = para.getREGEXP().matcher(url);
////						if (m.find()) {
////							if (url.contains(para.getPARAMETER_CODE() + "=" + para.getPARAMETER_VALUE())) {
////								// ua.setUaProdId(para.getPRODUCT_ID());
////								// return ua;
////							}
////						}
////					// 目前都是这种情况
////					} else if (para.getIF_NEED_REGEXP().equals("1")) {
////					} else if (para.getIF_NEED_KEYVALUE().equals("1")) {
////
////					}
//					if (para.getIS_VALID().equals("1")) {
//						Matcher m = para.getREGEXP().matcher(url);
//						if (m.find()) {
//							resultDomain = new Domain();
//							resultDomain.setDomain_code(host);
//							resultDomain.setProduct_id(para.getPRODUCT_ID());
//							resultDomain.setDomain_type("parameter");
//							resultDomain.setRoot_domain(ParserUtils.getRootdomain(host));
//							//TotalCountToRedisService.TotalCountToRedis(dictType, "dim_ia_url_parameter", para.getREGEXP().toString());
////							MapUtils.putKey(countMap, "dim_ia_url_parameter", para.getREGEXP().toString(), countFlag);
//							para.setTOTAL_COUNT(para.getTOTAL_COUNT() + 1);
//							break;
//						}
//					}
//				}
//			}
//		}
//		//三、domain解析
//		if (!StringUtils.isEmpty(url) && resultDomain == null) {
//			resultDomain = doDomainFilter(host.toLowerCase(), domainHostEndFilter);
//			if(resultDomain != null) {
//				return resultDomain;
//			}
//			//用解析出来的host在域名规则表获取domain（从redis读取）
//			resultDomain = StringUtils.equals(dictType, "redis")?(Domain) RedisUtils.getObject("domainAllOld_", host.toLowerCase()):DimIAInfo.domainMap.get(host.toLowerCase());
//			if (resultDomain == null) {
//				//用正则表达式获取domain
//				//int i = 0;
//				//因为需要while循环所有需要一个domain中间变量
//				Domain domain = null;
//				
//				int count = countInnerStr(host, "\\.");
//				String domain_code = host;
//				
//				for(int i=0; i<count; i++) {
//					domain_code = domain_code.substring(domain_code.indexOf(".")+1);
//					if(DimIAInfo.hostSuffixs.toString().contains("|"+domain_code+"|") || domain_code.indexOf(".")==-1) {
//						break;
//					}
//					domain = StringUtils.equals(dictType, "redis")?(Domain) RedisUtils.getObject("domainAllOld_", domain_code):DimIAInfo.domainMap.get(domain_code);
//					// 
//					if(domain != null) {
//						resultDomain = domain;
//						break;
//					}
//				}
//				
//				/*
//				do {
//					//要解析的级别
//					++i;
//					//按正则表达式，和要解析的级别获取domain_code
//					String domain_code = ParserUtils.getDomainCode(host, i, hostSuffix).toLowerCase();
//					if (!"".equals(domain_code) && domain_code != null) {
//						//按domain_code获取domain(从redis读取)
//						domain = StringUtils.equals(dictType, "redis")?(Domain) RedisUtils.getObject("domainAllOld_", domain_code):DimIAInfo.domainMap.get(domain_code);
//					} else {
//						domain = null;
//					}
//					if (domain != null) {
//						resultDomain = domain;
//						resultDomain.setDomain_type("domain");
//						//TotalCountToRedisService.TotalCountToRedis(dictType, "dim_ia_domain_rule", domain.getDomain_code());
//					}
//				} while (domain != null && "1".equals(domain.getHas_child()));//如果domain不为空并且has_child为1继续循环
//				*/
//				
//				if(resultDomain != null) 
//					resultDomain.setTOTAL_COUNT(resultDomain.getTOTAL_COUNT() + 1);
////					MapUtils.putKey(countMap, "dim_ia_domain_rule", resultDomain.getDomain_code(), countFlag);
//				
//			}else {
//				resultDomain.setTOTAL_COUNT(resultDomain.getTOTAL_COUNT() + 1);
////				MapUtils.putKey(countMap, "dim_ia_domain_rule", resultDomain.getDomain_code(), countFlag);
//			}
//		}
//		//二、ipPort解析
//		if (!StringUtils.isEmpty(ipPort) && resultDomain == null) {
//			//3.在domain_all表里， 用ip地址获取domain
////					resultDomain = (Domain) RedisUtils.getObject(redis, "domainAllOld_", ipPort);
////					if (resultDomain == null) {
////						
////					}
//			//4.在ipPort表里，用ipPort获取resultDomain(从redis读取)
//			IP ip = StringUtils.equals(dictType, "redis")?(IP) RedisUtils.getObject("ipPortDynamicOld_", ipPort):DimIAInfo.ipMap.get(ipPort);
//			if (ip != null) {
//				resultDomain = new Domain();
//				resultDomain.setDomain_code(ip.getDomain_code());
//				resultDomain.setDomain_type("ipport");
//				resultDomain.setProduct_id(ip.getProd_id());
//				resultDomain.setParent_domain("");
//				resultDomain.setLabel_code(ip.getDomain_label_code());
//				resultDomain.setRoot_domain(ip.getIp());
//				resultDomain.setIsDynamic("1");
//				resultDomain.setProject_id(ParserUtils.getProjectId(dictType, ip.getProd_id()));
//				resultDomain.setProj_sub_id(ip.getProj_sub_id());
//				//TotalCountToRedisService.TotalCountToRedis(dictType, "dim_ia_ip_port_dynamic", ip.getDomain_code());
////						MapUtils.putKey(countMap, "dim_ia_ip_port_dynamic", ip.getDomain_code(), countFlag);
//				ip.setTOTAL_COUNT(ip.getTOTAL_COUNT() + 1);
//			}
//		}
////		CodisUtils.closeJedis(redis);
//		if(resultDomain == null) {
//			return new Domain();
//		}
//		return resultDomain;
//	}
//	
//	private static Domain doDomainFilter(String host, String domainHostEndFilter) {
//		Boolean bl = true;
//		// 如果域名不符合规范bl为false
//		if(!RegExUtils.doRegExHost(host)) {
//			bl = false;
//		}
//		// 如果域名符合规范
//		if(bl) {
//			// 获取不配置的不规则后缀
//			String[] filters = domainHostEndFilter.split(",", -1);
//			for (String filter : filters) {
//				// 如果是不规则的后缀，则bl为false
//				if(host.endsWith(filter)) {
//					bl = false;
//					break;
//				}
//			}
//		}
//		// 如果bl为true则可以继续解析
//		if(bl) {
//			return null;
//		// 如果bl为false则返回不规则的Domain对象
//		} else {
//			Domain domain = new Domain();
//			domain.setDomain_code(host);
//			domain.setProduct_id("D1");
//			domain.setDomain_type("域名不规范");
//			return domain;
//		}
//	}
//	
//	/**
//	 * 计算域名.数量
//	 * @param str
//	 * @param patternStr
//	 * @return
//	 */
//	public static int countInnerStr(String str, String patternStr) {
//		int count = 0;
//		final Pattern r = Pattern.compile(patternStr);
//		final Matcher m = r.matcher(str);
//
//		while (m.find()) {
//			count++;
//		}
//		return count;
//	}
//}
