package com.bonc.dpi.action;

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
import com.bonc.dpi.dao.entity.IndustryPic;
import com.bonc.dpi.service.IndustryPicService;
import com.bonc.dpi.service.PublicCPeriodService;

/**
 * 行业画像
 * @author dragon
 *
 */
// TODO: 增加区域选择条件
@Controller
@RequestMapping(value = "/industrypic")
public class IndustryPicAction {
	//获取 时间
	@Resource
	PublicCPeriodService dateUtil;
	@Resource
	private IndustryPicService service;
	private static final Logger logger = LoggerFactory.getLogger(IndustryPicAction.class);
	
	/**
	 * 获取APP排行
	 * @param LABLE
	 * @return
	 */
	@RequestMapping(value="/selectapptop10",method=RequestMethod.POST, produces="application/json;charset=UTF-8")
	@ResponseBody
	public String select(String label,String num,String dateId) {
		try {
			IndustryPic industryPic = new IndustryPic();
			if(num.equals("1")) {		
				industryPic.setLabelName1(label);
			}else if(num.equals("2")){
				industryPic.setLabelName2(label);
			}
			industryPic.setDateId(dateId);
			List<IndustryPic> result =  service.select(industryPic);
						
			return Ajax.responseString(CST.RES_SECCESS, result);
		} catch (Exception e) {
			Map<String,String> resultError = new HashMap<String, String>();
			logger.error(e.getMessage());
			return Ajax.responseString(CST.RES_EXCEPTION, resultError);
		}
	}
		
	/**
	 * 获取年龄性别比例
	 * @param LABLE
	 * @return
	 */
	@RequestMapping(value="/selectage",method=RequestMethod.POST, produces="application/json;charset=UTF-8")
	@ResponseBody
	public String selectAge(String label,String num,String dateId) {
		try {
			IndustryPic industryPic = new IndustryPic();
			if(num.equals("1")) {		
				industryPic.setLabelName1(label);
			}else if(num.equals("2")){
				industryPic.setLabelName2(label);
			}
			industryPic.setDateId(dateId);
			IndustryPic result=service.selectAgeAndSex(num,industryPic);
			return Ajax.responseString(CST.RES_SECCESS, result);
		} catch (Exception e) {
			logger.error(e.getMessage());
			return Ajax.responseString(CST.RES_EXCEPTION, e.getMessage());
		}
	}

	/**
	 * 获取地图数据(引用行业总览中方法)
	 * @return
	 */
	@RequestMapping(value = "/getMapDataList",method=RequestMethod.POST, produces="application/json;charset=UTF-8")
	@ResponseBody
	public String getMapDataList(String label,String num,String dateId) {
		try {
			IndustryPic industryPic = new IndustryPic();
			if(num.equals("1")) {		
				industryPic.setLabelName1(label);
			}else if(num.equals("2")){
				industryPic.setLabelName2(label);
			}
			industryPic.setDateId(dateId);
			List<IndustryPic> result=service.selectMapDataList(num,industryPic);
			return Ajax.responseString(CST.RES_SECCESS, result);
		} catch (Exception e) {
			logger.error(e.getMessage());
			return Ajax.responseString(CST.RES_EXCEPTION, e.getMessage());
		}
	}
	
}
