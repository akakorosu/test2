package com.bonc.common.utils;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
//import com.bonc.common.nlapInterface.TextSegmentClient;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 直接调用chartData5方法就行;
 * @author 李天福
 * @date: 上午11:06:28
 * @version 1.0
 */
public class EchartsUtils {

	//获取上个月;
	public static String getTime(){
		Calendar calendar = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		String time = sdf.format(calendar.getTime());
		return time;
	}
	public static String[] changeData2(List list){
		String data[] = new String[4];
		StringBuffer sbf_data = new StringBuffer("[");
		StringBuffer sbf_x = new StringBuffer("[");
		StringBuffer sbf_data_seven = new StringBuffer("[");
		StringBuffer sbf_data_seven_Z = new StringBuffer("[");
		Map map = new HashMap<>();
		if(list.size()>0){
			for (int i = 0; i < list.size(); i++) {
				map = (Map) list.get(i);
				if(i!=0){
					sbf_data.append(",");
					sbf_x.append(",");
					sbf_data_seven.append(",");
					sbf_data_seven_Z.append(",");
				}
				sbf_data.append("{").append("name:'").append(map.get("X")).append("',").append("value:").append(map.get("Y")).append("}");
				sbf_data_seven.append("{").append("name:'").append(map.get("X")).append("',").append("value:").append(map.get("Z")).append("}");
				sbf_x.append("'").append(map.get("X")).append("'");
				sbf_data_seven_Z.append(map.get("Z"));
			}
			sbf_data.append("]");
			sbf_x.append("]");
			sbf_data_seven.append("]");
			sbf_data_seven_Z.append("]");
		}else{
			sbf_data.append("'").append("0").append("'").append("]");
			sbf_x.append("'").append("'").append("]");
			sbf_data_seven.append("'").append("0").append("'").append("]").toString();
			sbf_data_seven_Z.append("'").append("0").append("'").append("]");
		}
		data[0] = sbf_data.toString();
		data[1] = sbf_x.toString();
		data[2] = sbf_data_seven.toString();
		data[3] = sbf_data_seven_Z.toString();
		return data;
	}
	public static String[] changeData6(List list){
		String data[] = new String[7];
		//今日
		StringBuffer sbf_data = new StringBuffer("[");
		//七天的
		StringBuffer sbf_data_seven = new StringBuffer("[");
		//30天的
		StringBuffer sbf_data_thirty = new StringBuffer("[");
		//X
		StringBuffer sbf_x = new StringBuffer("[");
		//Y
		StringBuffer sbf_y = new StringBuffer("[");
		//Z
		StringBuffer sbf_z = new StringBuffer("[");
		//W
		StringBuffer sbf_w = new StringBuffer("[");
		Map map = new HashMap<>();
		if(list.size()>0){
			for (int i = 0; i < list.size(); i++) {
				map = (Map) list.get(i);
				if(i!=0){
					sbf_data.append(",");
					sbf_data_seven.append(",");
					sbf_data_thirty.append(",");
					sbf_x.append(",");
					sbf_y.append(",");
					sbf_z.append(",");
					sbf_w.append(",");
				}
				sbf_data.append("{").append("name:'").append(map.get("X")).append("',").append("value:").append(map.get("Y")).append("}");
				sbf_data_seven.append("{").append("name:'").append(map.get("X")).append("',").append("value:").append(map.get("Z")).append("}");
				sbf_data_thirty.append("{").append("name:'").append(map.get("X")).append("',").append("value:").append(map.get("W")).append("}");
				sbf_x.append("'").append(map.get("X")).append("'");
				sbf_y.append(map.get("Y"));
				sbf_z.append(map.get("Z"));
				sbf_w.append(map.get("W"));
			}
			sbf_data.append("]");
			sbf_data_seven.append("]");
			sbf_data_thirty.append("]");
			sbf_x.append("]");
			sbf_y.append("]");
			sbf_z.append("]");
			sbf_w.append("]");
		}else{
			sbf_data.append("'").append("0").append("'").append("]").toString();
			sbf_data_seven.append("'").append("0").append("'").append("]").toString();
			sbf_data_thirty.append("'").append("0").append("'").append("]").toString();
			sbf_x.append("'").append("'").append("]");
			sbf_y.append("]");
			sbf_z.append("]");
			sbf_w.append("]");
		}
		data[0] = sbf_data.toString();
		data[1] = sbf_data_seven.toString();
		data[2] = sbf_data_thirty.toString();
		data[3] = sbf_x.toString();
		data[4] = sbf_y.toString();
		data[5] = sbf_z.toString();
		data[6] = sbf_w.toString();
		return data;
	}
	//单独value, 单独name,  value和name的;
	public static String[] changeData5(List list){
		System.out.println("-----------------------"+list.size());
		String data[] = new String[3];
		StringBuffer name = new StringBuffer("[");   //单独name,
		StringBuffer value = new StringBuffer("[");   //单独value,
		StringBuffer value_name = new StringBuffer("[");   //value,name;
		Map map = new HashMap<>();
		if(list.size()>0){
			for (int i = 0; i < list.size(); i++) {
				map = (Map) list.get(i);
				if(i!=0){
					name.append(",");
					value.append(",");
					value_name.append(",");
				}
				name.append("'").append(map.get("X")).append("'");
				value.append(map.get("Y"));
				value_name.append("{").append("name:'").append(map.get("X")).append("',").append("value:").append(map.get("Y")).append("}");
			}
			name.append("]");
			value.append("]");
			value_name.append("]");
		}else{
			name.append("'").append("'").append("]");
			value.append("0").append("]");
			value_name.append("'").append("'").append("]");
		}
		data[0] = name.toString();        //单独数字的.
		data[1] = value.toString();        //单独汉字的,
		data[2] = value_name.toString();   //七天的series里面的data,
		return data;
	}

	public static String[] changeData_jason_lee(List list){
		String data[] = new String[1];
		StringBuffer name = new StringBuffer("[");   //单独name,
		Map map = new HashMap<>();
		if(list.size()>0){
			for (int i = 0; i < list.size(); i++) {
				map = (Map) list.get(i);
				name.append("{").append("'").append("name").append("':").append("'").append(map.get("AREA_NAME")).append("'").append(",").append("'value':").append("[").append(map.get("LONGITUDE")).append(",").append(map.get("LATITUDE")).append("]},");
			}
			name.append("]");
		}else{
			name.append("'").append("'").append("]");
		}
		data[0] = name.toString().substring(0,name.toString().lastIndexOf(",")) + "]";        //单独数字的.
		return data;
	}

	//大屏
	public static String[] changeData5_jason(List list){
		String data[] = new String[5];
		StringBuffer X1 = new StringBuffer("[");   //单独name,
		StringBuffer Y1 = new StringBuffer("[");   //单独value,
		StringBuffer Y2 = new StringBuffer("[");   //value,name;
		StringBuffer Y3 = new StringBuffer("[");   //value,name;
		StringBuffer Y4 = new StringBuffer("[");   //value,name;
		StringBuffer Y5 = new StringBuffer("[");   //value,name;
		Map map = new HashMap<>();
		if(list.size()>0){
			for (int i = 0; i < list.size(); i++) {
				map = (Map) list.get(i);
				if(i!=0){
					X1.append(",");
					Y1.append(",");
					Y2.append(",");
					Y3.append(",");
					Y4.append(",");
					Y5.append(",");
				}
				X1.append("'").append(map.get("X1")).append("'");
				Y1.append(map.get("Y1"));
				Y2.append(map.get("Y2"));
				Y3.append(map.get("Y3"));
				Y4.append(map.get("Y4"));
			}
			X1.append("]");
			Y1.append("]");
			Y2.append("]");
			Y3.append("]");
			Y4.append("]");
		}else{
			X1.append("'").append("'").append("]");
			Y1.append("0").append("]");
			Y2.append("0").append("]");
			Y3.append("0").append("]");
			Y4.append("0").append("]");
		}
		data[0] = X1.toString();
		data[1] = Y1.toString();
		data[2] = Y2.toString();
		data[3] = Y3.toString();
		data[4] = Y4.toString();
		return data;
	}




	//雷达图;
	public static String[] changeData_radar(List list,String max){
		String data[] = new String[3];
		StringBuffer name = new StringBuffer("[");   //单独name,
		StringBuffer value = new StringBuffer("[");   //单独value,
		StringBuffer value_name = new StringBuffer("[");   //value,name;
		Map map = new HashMap<>();
		if(list.size()>0){
			for (int i = 0; i < list.size(); i++) {
				map = (Map) list.get(i);
				if(i!=0){
					name.append(",");
					value.append(",");
					value_name.append(",");
				}
				name.append("'").append(map.get("X")).append("'");
				value.append(map.get("Y"));
				value_name.append("{").append("text:'").append(map.get("X")).append("',").append("max:").append(max).append("}");
			}
			name.append("]");
			value.append("]");
			value_name.append("]");
		}else{
			name.append("'").append("'").append("]");
			value.append("0").append("]");
			value_name.append("'").append("'").append("]");
		}
		data[0] = name.toString();        //单独数字的.
		data[1] = value.toString();        //单独汉字的,
		data[2] = value_name.toString();   //七天的series里面的data,
		return data;
	}

	//年龄分布;
	public static String[] changeData6(List list,String mark){
		String data[] = new String[3];
		StringBuffer name = new StringBuffer("[");   //年龄分段;
		Map map = new HashMap<>();
		if(list.size()>0){
			for (int i = 0; i < list.size(); i++) {
				map = (Map) list.get(i);
				if(mark.equals(map.get("X"))){
					name.append("{").append("value:").append(map.get("Y")).append(",name:").append("'").append(map.get("Z")).append("'},");
					name.append("{").append("value:").append(map.get("N")).append(",name:").append("'").append("'").append("}");
				}
			}
			name.append("]");
		}else{
			name.append("'").append("'").append("]");
		}
		data[0] = name.toString();
		return data;
	}

	//单独统计男女的;
	public static String[] changeData7(List list){
		String data[] = new String[3];
		StringBuffer man = new StringBuffer("[");   //单独男,
		StringBuffer women = new StringBuffer("[");   //单独女,
		StringBuffer name = new StringBuffer("[");
		Map map = new HashMap<>();
		if(list.size()>0){
			for (int i = 0; i < list.size(); i++) {
				map = (Map) list.get(i);
				if(i!=0){
					man.append(",");
					women.append(",");
					name.append(",");
				}
				if(map.get("X2").equals("男")){
					man.append(map.get("Y2"));
				}else{
					women.append(map.get("Y2"));
				}
				name.append("'").append(map.get("X1")).append("'");
			}
			man.append("]");
			women.append("]");
			name.append("]");
		}else{
			man.append("0").append("]");
			women.append("0").append("]");
			name.append("'").append("'").append("]");
		}
		data[0] = man.toString();        //单独数字的.
		data[1] = women.toString();        //单独汉字的,
		data[2] = name.toString();
		return data;
	}

	public static String[] change(Map m,String[] desc){
		String data[] = new String[3];
		StringBuffer name = new StringBuffer("[");
		StringBuffer time = new StringBuffer("[");
		StringBuffer json = new StringBuffer("[");
		Map map = new HashMap<>();
		if(m.size()>0){
			for (int i = 0; i < m.size(); i++) {
				List list = (List) m.get("yq_"+i);
				if(i!=0){
					name.append(",");
					json.append(",");
				}
				name.append("'").append(desc[i]).append("'");
				json.append("{").append("name:'").append(desc[i]).append("',").append("type:'line',").append("data:[");
				Map ls = new HashMap<>();
				if(list.size()>0){
					for(int j = 0; j < list.size(); j++){
						ls = (Map) list.get(j);
						if(j!=0){
							json.append(",");
						}
						json.append(ls.get("Y"));
						//生成time
						if(i==0){
							if(j!=0){
								time.append(",");
							}
							time.append("'").append(ls.get("X")).append("'");
						}
					}
				}
				String s = "markPoint: {data: [{type: 'max', name: '最大值'},{type: 'min', name: '最小值'}]}";
				json.append("],").append(s).append("}");
			}
			name.append("]");
			time.append("]");
			json.append("]");
		}else{
			name.append("]");
			time.append("]");
			json.append("]");
		}
		data[0] = name.toString();
		data[1] = time.toString();
		data[2] = json.toString();
		return data;
	}
	public static List<String> getBetweenDates(String begin, String end) {
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
		Date startDate = new Date();
		Date endDate = new Date();
		try {
			startDate = sdf.parse(begin);
			endDate = sdf.parse(end);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		List<String> result = new ArrayList<String>();
		Calendar tempStart = Calendar.getInstance();
		tempStart.setTime(startDate);
		while(startDate.getTime()<=endDate.getTime()){
			Date date = tempStart.getTime();
			String str = sdf.format(date);
			result.add(str);
			tempStart.add(Calendar.DAY_OF_YEAR, 1);
			startDate = tempStart.getTime();
		}
		return result;
	}
	public static List<String> getBetweenDates2(String currentFlag, String end) {
		SimpleDateFormat sdf=new SimpleDateFormat("yyyyMMdd");
		Date endDate = new Date();
		try {
			endDate = sdf.parse(end);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		List<String> result = new ArrayList<String>();
		Calendar tempStart = Calendar.getInstance();

		int flag = Integer.parseInt(currentFlag);
		tempStart.setTime(endDate);
		for(int i=0;i<=flag;i++){
			Date date = tempStart.getTime();
			String str = sdf.format(date);
			result.add(str);
			tempStart.add(Calendar.DAY_OF_YEAR, -1);
			endDate = tempStart.getTime();
		}
		return result;
	}
//	public static void insertWords(String[] words){
//		TextSegmentClient client = TextSegmentClient.getInstance();
//		client.setDomain(TextUtil.domain);
//		for (String word : words){
//			client.addWord2LibraryJ(word, "n", 10000);
//
//		}
//	}
//
//	public static void delWords(String words){
//		TextSegmentClient client = TextSegmentClient.getInstance();
//		client.setDomain("192.168.0.18:5010");
//		client.removeWordFromLibrary(words);
//
//	}
	public static void runEs(String shpath) {
		try {
			Process ps = Runtime.getRuntime().exec(shpath);
			ps.waitFor();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public static void runEs2(String[] shpath) {
		try {
			Process ps = Runtime.getRuntime().exec(shpath);
			ps.waitFor();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
