package com.bonc.dpi.action;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bonc.common.cst.CST;
import com.bonc.common.utils.Ajax;
import com.bonc.dpi.dao.entity.IndustryGroupPic;

import com.bonc.dpi.service.IndustryGroupPicService;
import com.bonc.dpi.service.PublicCPeriodService;
import com.bonc.dpi.utils.DpiUtils;

@Controller
@RequestMapping(value = "/industrygrouppic")
public class IndustryPicGroupAction {
	// 获取 时间
	@Resource
	PublicCPeriodService dateUtil;
	@Resource
	private IndustryGroupPicService service;
	private static final Logger logger = LoggerFactory.getLogger(IndustryPicGroupAction.class);
	DecimalFormat df = new DecimalFormat("0.00");

	/**
	 * 获取行业占比 以及 男女比例
	 * 
	 * @param LABLE
	 * @return
	 */

	@RequestMapping(value = "/selectProdTypePer", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String select(String dateId, String areaId, String sex) throws Exception {
		IndustryGroupPic industryGroupPic = new IndustryGroupPic();
		IndustryGroupPic temp1 = new IndustryGroupPic();
		industryGroupPic.setDateId(dateId);
		industryGroupPic.setAreaId(areaId);
		industryGroupPic.setSex(sex);
		String date = dateUtil.selectDateId("1005");
		if (dateId.equals("0")) {
			industryGroupPic.setDateId(date);
		} else {
			industryGroupPic.setDateId(dateId);
		}
		int manCount = 0;
		int womanCount = 0;
		temp1.setSex("1");
		temp1.setAreaId(areaId);
		temp1.setDateId(industryGroupPic.getDateId());
		manCount = service.selectCount(temp1);
		temp1.setSex("2");
		womanCount = service.selectCount(temp1);
		
		List<IndustryGroupPic> industryGroupList = new ArrayList<>();
		industryGroupList = service.selectProdTypePer(industryGroupPic);
		Map<String, String> result = new HashMap<String, String>();
		
		//男女比例
		if((manCount+womanCount)==0){
			result.put("manPercent", df.format(0));
			result.put("womanPercent", df.format(0));
		}else{
			result.put("manPercent", df.format((double)manCount* 100/(manCount+womanCount)));
			result.put("womanPercent", df.format((double)womanCount* 100/(manCount+womanCount)));
		}
		
		// 1~10
		int i = 0;
		int j = 0;
		for (IndustryGroupPic temp : industryGroupList) {
			if (temp.getSex().equals("1")) {
				if (i == 5) {
					break;
				}
				result.put("data" + (i + 1), temp.getName());
				result.put("data" + (i + 6), df.format(Double.valueOf(temp.getPercent()) * 100) + "%");
				i++;

			}
		}
		for (IndustryGroupPic temp : industryGroupList) {
			if (temp.getSex().equals("2")) {
				if (j == 5) {
					break;
				}
				result.put("data" + (j + 21), temp.getName());
				result.put("data" + (j + 26), df.format(Double.valueOf(temp.getPercent()) * 100) + "%");
				j++;
			}
		}

		return Ajax.responseString(CST.RES_SECCESS, result);

	}

	/**
	 * 获取用户群体画像APP排行
	 * 
	 * @param LABLE
	 * @return
	 */

	@RequestMapping(value = "/selectTopAppdate", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String selectApp(String dateId, String areaId, String sex) throws Exception {
		IndustryGroupPic industryGroupPic = new IndustryGroupPic();
		industryGroupPic.setDateId(dateId);
		industryGroupPic.setAreaId(areaId);
		industryGroupPic.setSex(sex);
		String date = dateUtil.selectDateId("1005");
		if (dateId.equals("0")) {
			industryGroupPic.setDateId(date);
		} else {
			industryGroupPic.setDateId(dateId);
		}

		List<IndustryGroupPic> industryGroupList = new ArrayList<>();
		industryGroupList = service.selectTopAppdate(industryGroupPic);

		Map<String, String> result = new HashMap<String, String>();

		int i = 0;
		int j = 0;
		for (IndustryGroupPic temp : industryGroupList) {
			// 10~20
			if (temp.getSex().equals("1")) {
				result.put("data" + (i + 11), DpiUtils.strDecrypt(temp.getName()));
				result.put("data" + (i + 16), df.format(Double.valueOf(temp.getPercent()) * 100) + "%");
				i++;
			}
			// 30~40
			if (temp.getSex().equals("2")) {
				result.put("data" + (j + 31), DpiUtils.strDecrypt(temp.getName()));
				result.put("data" + (j + 36), df.format(Double.valueOf(temp.getPercent()) * 100) + "%");
				j++;
			}
		}
		result.put("date", industryGroupPic.getDateId());
		return Ajax.responseString(CST.RES_SECCESS, result);

	}

	/**
	 * 获取 年龄分布
	 * 
	 * @param LABLE
	 * @return
	 */

	@RequestMapping(value = "/selectAgeEcharts", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String selectAgeEcharts(String dateId, String areaId, String sex) throws Exception {
		IndustryGroupPic industryGroupPic = new IndustryGroupPic();
		String date = dateUtil.selectDateId("1005");
		if (dateId.equals("0")) {
			industryGroupPic.setDateId(date);
		} else {
			industryGroupPic.setDateId(dateId);
		}
		industryGroupPic.setAreaId(areaId);
		industryGroupPic.setSex(sex);

		List<IndustryGroupPic> industryGroupList = new ArrayList<>();
		industryGroupList = service.selectAgeEcharts(industryGroupPic);
		Map<String, String> result = new HashMap<String, String>();
		for (int i = 0; i < 7; i++) {
			for (IndustryGroupPic temp : industryGroupList) {
				if (temp.getAge().equals("" + i)) {
					result.put("age" + i, temp.getAgeCount());
				}
			}
		}
		result.put("date", industryGroupPic.getDateId());
		return Ajax.responseString(CST.RES_SECCESS, result);

	}

	/**
	 * 柱状图点击事件 左侧刷新
	 * 
	 * @param LABLE
	 * @return
	 */
	@RequestMapping(value = "/ageEchartsClickLeft", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String ageEchartsClickLeft(String dateId, String areaId, String sex, String age) throws Exception {

		IndustryGroupPic industryGroupPic = new IndustryGroupPic();
		industryGroupPic.setAreaId(areaId);
		industryGroupPic.setSex(sex);
		String date = dateUtil.selectDateId("1005");
		if (dateId.equals("0")) {
			industryGroupPic.setDateId(date);
		} else {
			industryGroupPic.setDateId(dateId);
		}
		Map<String, String> result = new HashMap<String, String>();
		if (age.endsWith("以上") || age.endsWith("以下")) {
			result.put("ageDistribution", age + "年龄段占比");
		} else {
			result.put("ageDistribution", age + "岁年龄段占比");
		}
		
		switch (age) {
		case "18岁以下":
			industryGroupPic.setAge("0");
			break;
		case "18-24":
			industryGroupPic.setAge("1");
			break;
		case "25-34":
			industryGroupPic.setAge("2");
			break;
		case "35-44":
			industryGroupPic.setAge("3");
			break;
		case "45-54":
			industryGroupPic.setAge("4");
			break;
		case "55-64":
			industryGroupPic.setAge("5");
			break;
		case "65岁以上":
			industryGroupPic.setAge("6"); 
			break;
		}

		result.put("age", industryGroupPic.getAge());
		List<IndustryGroupPic> industryGroupList = new ArrayList<>();
		//只查询了行业数量占比  最下面的
		industryGroupList = service.ageEchartsClickLeft(industryGroupPic);
	
		int i = 0;
		for (IndustryGroupPic temp : industryGroupList) {

			result.put("a" + i, temp.getName());
			result.put("agePer" + i, df.format(Double.valueOf(temp.getPercent()) * 100) + "%");
			i++;
		}

		
		return Ajax.responseString(CST.RES_SECCESS, result);

	}

	/**
	 * 柱状图点击事件 右侧刷新
	 * 
	 * @param LABLE
	 * @return
	 */
	@RequestMapping(value = "/ageEchartsClickRight", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String ageEchartsClickRight(String dateId, String areaId, String sex, String age) throws Exception {

		IndustryGroupPic industryGroupPic = new IndustryGroupPic();
		industryGroupPic.setAreaId(areaId);
		industryGroupPic.setSex(sex);
		String date = dateUtil.selectDateId("1005");
		if (dateId.equals("0")) {
			industryGroupPic.setDateId(date);
		} else {
			industryGroupPic.setDateId(dateId);
		}
		
		Map<String, String> result = new HashMap<String, String>();
		if (age.endsWith("以上") || age.endsWith("以下")) {
			result.put("ageDistribution", age + "年龄段占比");
		} else {
			result.put("ageDistribution", age + "岁年龄段占比");
		}
		
		switch (age) {
		case "18岁以下":
			industryGroupPic.setAge("0");
			break;
		case "18-24":
			industryGroupPic.setAge("1");
			break;
		case "25-34":
			industryGroupPic.setAge("2");
			break;
		case "35-44":
			industryGroupPic.setAge("3");
			break;
		case "45-54":
			industryGroupPic.setAge("4");
			break;
		case "55-64":
			industryGroupPic.setAge("5");
			break;
		case "65岁以上":
			industryGroupPic.setAge("6");
			break;
		}

		result.put("age", industryGroupPic.getAge());
		List<IndustryGroupPic> industryGroupList = new ArrayList<>();
		industryGroupList = service.ageEchartsClickRight(industryGroupPic);

		
		int i = 0;
		for (IndustryGroupPic temp : industryGroupList) {
			result.put("app" + i, DpiUtils.strDecrypt(temp.getName()));
			result.put("appPer" + i, df.format(Double.valueOf(temp.getPercent()) * 100) + "%");
			i++;
		}

		return Ajax.responseString(CST.RES_SECCESS, result);

	}
}
