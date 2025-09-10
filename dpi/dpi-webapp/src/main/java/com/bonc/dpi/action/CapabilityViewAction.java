package com.bonc.dpi.action;

import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.bonc.common.cst.CST;
import com.bonc.common.utils.Ajax;
import com.bonc.common.utils.PageJqGrid;
import com.bonc.dpi.dao.entity.CapabilityView;
import com.bonc.dpi.dao.entity.ProductFbDescInfo;
import com.bonc.dpi.service.CapabilityViewService;
import com.bonc.dpi.utils.DpiUtils;
import com.bonc.login.action.LoginAction;
import com.bonc.system.dao.entity.SysUser;
import com.github.pagehelper.Page;

@Controller
@RequestMapping(value = "/capability")
public class CapabilityViewAction {
	
	@Autowired
    CapabilityViewService capabilityViewService;
	
	@RequestMapping(value = "/selectpagelist")
	@ResponseBody
	public PageJqGrid<CapabilityView> selectPageList(CapabilityView vo, Integer page, Integer rows) throws Exception {

		

		Page<CapabilityView> pageList = this.capabilityViewService.selectList(vo, page, rows);
		PageJqGrid<CapabilityView> pageJqGrid = new PageJqGrid<CapabilityView>(pageList);
		return pageJqGrid;
	}
	private static final Logger logger=LoggerFactory.getLogger(CapabilityViewAction.class);
	/**
	 * 获取分类数据
	 * @param obj
	 * @return
	 */
	@RequestMapping(value = "/initMap",method=RequestMethod.POST, produces="application/json;charset=UTF-8")
	@ResponseBody
	public String initMap() {
		try {
			this.capabilityViewService.initProdCache();
			return Ajax.responseString(CST.RES_SECCESS, "");
		} catch (Exception e) {
			logger.error(e.getMessage());
			return Ajax.responseString(CST.RES_EXCEPTION, e.getMessage());
		}
	}
	
	@RequestMapping(value = "/getLabelNum",method=RequestMethod.POST, produces="application/json;charset=UTF-8")
	@ResponseBody
	public String getLabelNum() {
		try {
			Map<String, String> result=capabilityViewService.getLabelNum();
			return Ajax.responseString(CST.RES_SECCESS, result);
		} catch (Exception e) {
			logger.error(e.getMessage());
			return Ajax.responseString(CST.RES_EXCEPTION, e.getMessage());
		}
	}
	@RequestMapping(value = "/initDirector",method=RequestMethod.GET, produces="application/json;charset=UTF-8")
	@ResponseBody
	public String initDirector(CapabilityView vo) {
		try {
			
			List<CapabilityView> result=capabilityViewService.getListByPage(vo);
			int count =capabilityViewService.countAllDir(vo);
			
			return Ajax.responseString(CST.RES_SECCESS,count+"", result);
		} catch (Exception e) {
			logger.error(e.getMessage());
			
			return Ajax.responseString(CST.RES_EXCEPTION, e.getMessage());
		}
	}
	@RequestMapping(value = "/getDireDetail",method=RequestMethod.GET, produces="application/json;charset=UTF-8")
	@ResponseBody
	public String getDireDetail(CapabilityView vo) {
		try {
			
			//List<CapabilityView> result=capabilityViewService.getListByPage(vo);
			CapabilityView count =capabilityViewService.getDireDetail(vo);
			
			return Ajax.responseString(CST.RES_SECCESS,count);
		} catch (Exception e) {
			logger.error(e.getMessage());
			
			return Ajax.responseString(CST.RES_EXCEPTION, e.getMessage());
		}
	}
	@RequestMapping(value = "/getLabelTree",method=RequestMethod.GET, produces="application/json;charset=UTF-8")
	@ResponseBody
	public String getLabelTree() {
		try {
			String aa="{\"name\":\"冰桶挑战\",\"children\":[{\"name\":\"刘作虎\"},{\"name\":\"刘作虎\"}]}";
			 JSONObject jsonObject = JSONObject.parseObject(aa);
		      
			
			return Ajax.responseString(CST.RES_SECCESS, "",jsonObject);
		} catch (Exception e) {
			logger.error(e.getMessage());
			return Ajax.responseString(CST.RES_EXCEPTION, e.getMessage());
		}
	}
	@RequestMapping(value = "/getAllAct",method=RequestMethod.POST, produces="application/json;charset=UTF-8")
	@ResponseBody
	public String getAllAct() {
		try {
			List<CapabilityView> lvl2=capabilityViewService.getAllAct();
		      
			
			return Ajax.responseString(CST.RES_SECCESS,lvl2);
		} catch (Exception e) {
			logger.error(e.getMessage());
			return Ajax.responseString(CST.RES_EXCEPTION, e.getMessage());
		}
	}
	@RequestMapping(value = "/getInitLabel",method=RequestMethod.POST, produces="application/json;charset=UTF-8")
	@ResponseBody
	public String getInitLabel() {
		try {
			Map<String, List<CapabilityView>> map=new HashMap<>();
			List<CapabilityView> shipin=capabilityViewService.getAllLabelIn("AN00");
			List<CapabilityView> dianshang=capabilityViewService.getAllLabelIn("BT00");
			List<CapabilityView> xiaoshuo=capabilityViewService.getAllLabelIn("AP00");
		    map.put("shipin", shipin);
		    map.put("dianshang", dianshang);
		    map.put("xiaoshuo", xiaoshuo);
			
			return Ajax.responseString(CST.RES_SECCESS,map);
		} catch (Exception e) {
			logger.error(e.getMessage());
			return Ajax.responseString(CST.RES_EXCEPTION, e.getMessage());
		}
	}
	@RequestMapping(value = "/updateAct",method=RequestMethod.POST, produces="application/json;charset=UTF-8")
	@ResponseBody
	public String updateAct(CapabilityView vo) {
		try {
			List<CapabilityView> lvl2=capabilityViewService.updateAct(vo);
		      
			
			return Ajax.responseString(CST.RES_SECCESS,lvl2);
		} catch (Exception e) {
			logger.error(e.getMessage());
			return Ajax.responseString(CST.RES_EXCEPTION, e.getMessage());
		}
	}
	@RequestMapping(value = "/getModelInfo",method=RequestMethod.POST, produces="application/json;charset=UTF-8")
	@ResponseBody
	public String getModelInfo() {
		try {
			Map<String, List<CapabilityView>> result=new HashMap<>();
			List<String> resultList=capabilityViewService.getModelWorkLvl1();
			for(String item: resultList){
				if(!StringUtils.isEmpty(item)){
					List<CapabilityView> lvl2=capabilityViewService.getWorkLvl2(item);
					result.put(item, lvl2);
					
				}
				
				
			}
			List<CapabilityView> lvl3=capabilityViewService.getChild();
			result.put("child", lvl3);
			return Ajax.responseString(CST.RES_SECCESS, result);
		} catch (Exception e) {
			logger.error(e.getMessage());
			return Ajax.responseString(CST.RES_EXCEPTION, e.getMessage());
		}
	}
	
	@RequestMapping(value = "/getLabelLvl",method=RequestMethod.POST, produces="application/json;charset=UTF-8")
	@ResponseBody
	public String getLabelLvl() {
		try {
			Map<String, List<CapabilityView>> resultmap=new HashMap<>();
			
			// sleep45秒...good luck!!!
//			if(prodCache.size()==0){
//				Thread.sleep(45504);
//			}
			
			//先得到6大分类
			List<String> result=capabilityViewService.getLabelLvl1();

			for(int i=0;i<result.size();i++){
				List<CapabilityView> relist=new ArrayList<>();
				String type=result.get(i);
				List<CapabilityView> lvl2=capabilityViewService.getLabelLvl2(type);
				for(int j=0;j<lvl2.size();j++){//循环一级标签
					CapabilityView capabilityView = lvl2.get(j);
					String type2=capabilityView.getLabelName1();
					List<CapabilityView> lvl3=capabilityViewService.getLabelLvl3(type,type2);
					String name2="";
					String code2="";
					String search2="";
					for(int n=0;n<lvl3.size();n++){
						if(!StringUtils.isEmpty(lvl3.get(n).getLabelName2())){
							name2+=lvl3.get(n).getLabelName2()+";";
							code2+=lvl3.get(n).getLabelCode2()+";";
							search2+=this.capabilityViewService.getProdCacheValue(lvl3.get(n).getLabelCode2())+"~~~";
						}
					}
					CapabilityView aa=new CapabilityView();
					aa.setLabelName(type); //设置六大类
					aa.setLabelCode1(capabilityView.getLabelCode1());  //设置一级code
					if(!StringUtils.isEmpty(this.capabilityViewService.getProdCacheValue(capabilityView.getLabelCode1()))){
						aa.setProdSearch1(this.capabilityViewService.getProdCacheValue(capabilityView.getLabelCode1()));
					}
					aa.setLabelName1(type2);
					aa.setLabelCode2(code2);  //设置二级code
					aa.setLabelName2(name2);
					if(!StringUtils.isEmpty(search2)){
						aa.setProdSearch2(search2);
					}
					relist.add(aa);
					
				}
				resultmap.put(result.get(i),relist );
				
			}
			return Ajax.responseString(CST.RES_SECCESS, resultmap);
		} catch (Exception e) {
			logger.error(e.getMessage());
			return Ajax.responseString(CST.RES_EXCEPTION, e.getMessage());
		}
	}
	
	@RequestMapping(value = "/showForm")
	@Deprecated
	public String showForm(HttpServletRequest request ,CapabilityView vo) throws Exception{
		String prodName=vo.getProdName();
		String flag=vo.getLabelLvl();
		if(!StringUtils.isEmpty(prodName)){
			vo.setProdName("%"+DpiUtils.strEncrypt(prodName)+"%");
		}
		if("1".equals(flag)){
			vo.setLabelCode1("%"+vo.getLabelName()+"%");
		}
		if("2".equals(flag)){
			vo.setLabelCode2("%"+vo.getLabelName()+"%");
		}
		List<CapabilityView> lvl2=capabilityViewService.getLabelProd(vo);
		for(CapabilityView item : lvl2){
			item.setProdName(DpiUtils.strDecrypt(item.getProdName()));// 解密
			
		}
		request.setAttribute("list", lvl2);
		
		return "pages/jsp/dpi/capabilityPreview/capabilityViewProd";
		
		
	}
	
	@RequestMapping(value = "/showTable")
	public String showTable(HttpServletRequest request) throws Exception{
        String labelLvl="";
        String labelName="";
        String prodName = "";
        try {
        	labelLvl = URLDecoder.decode(request.getParameter("labelLvl"), "utf-8");
        	labelName = URLDecoder.decode(request.getParameter("labelName"), "utf-8");
        	prodName = URLDecoder.decode(request.getParameter("prodName"), "utf-8");
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
        
        request.setAttribute("labelLvl", labelLvl);
        request.setAttribute("labelName", labelName);
        request.setAttribute("prodName", prodName);
		return "pages/jsp/dpi/capabilityPreview/capabilityViewProdTable";
	}
	
	@RequestMapping(value = "/toCapabilityPreview")
	public String toCapabilityPreview(HttpServletRequest request) throws Exception{
		return "pages/jsp/dpi/capabilityPreview/capabilityPreview";
	}
	
	/**
	 * 分页查询
	 * 
	 * @param ksr
	 * @param page
	 * @param rows
	 * @return
	 */
	@RequestMapping(value = "/selectProductFbDescInfoList")
	@ResponseBody
	public PageJqGrid<ProductFbDescInfo> selectProductFbDescInfoList(ProductFbDescInfo vo, Integer page, Integer rows, String sord, String sidx) throws Exception {
		Page<ProductFbDescInfo> pageList = this.capabilityViewService.selectProductFbDescInfoList(vo, page, rows, sord, sidx);
		PageJqGrid<ProductFbDescInfo> pageJqGrid = new PageJqGrid<ProductFbDescInfo>(pageList);
		return pageJqGrid;
	}
	
	@RequestMapping(value = "/showLabel")
	public String showLabel(HttpServletRequest request ,CapabilityView vo) throws Exception{
		return "pages/jsp/dpi/capabilityPreview/capabilityPreviewModal";
	}
	
	@RequestMapping(value = "/getContentKu",method=RequestMethod.POST, produces="application/json;charset=UTF-8")
	@ResponseBody
	public String getContentKu() {
		try {
			Map<String, List<CapabilityView>> resultmap=new HashMap<>();
			List<CapabilityView> lvl2=capabilityViewService.getContentKuLabel();
			for(int i=0;i<lvl2.size();i++){
				String code=lvl2.get(i).getContentLabelCode1();
				if(code.equalsIgnoreCase("AN00")){
					lvl2.get(i).setContentLabelCode1("视频");
				}
				if(code.equalsIgnoreCase("BT00")){
					lvl2.get(i).setContentLabelCode1("购物");
				}
				if(code.equalsIgnoreCase("AP00")){
					lvl2.get(i).setContentLabelCode1("小说");
				}
				if(code.equalsIgnoreCase("音乐待定")){
					lvl2.get(i).setContentLabelCode1("小说");
				}
			}
			List<CapabilityView> ProdNameShiPin=capabilityViewService.getContentKuProdName("G0004"); //视频
			for(CapabilityView cap:ProdNameShiPin){
				if(!StringUtils.isEmpty(cap.getProdName())){
					cap.setProdName(DpiUtils.strDecrypt(cap.getProdName()));// 解密
					cap.setContentLabelCode1("视频");
				}
			}
			List<CapabilityView> ProdNameXiaoShuo=capabilityViewService.getContentKuProdName("G0009"); //小说
			for(CapabilityView cap1:ProdNameXiaoShuo){
				if(!StringUtils.isEmpty(cap1.getProdName())){
					cap1.setProdName(DpiUtils.strDecrypt(cap1.getProdName()));// 解密
					cap1.setContentLabelCode1("小说");
				}
			}
			List<CapabilityView> ProdNameDianShang=capabilityViewService.getContentKuProdName("G0014"); //电商
			for(CapabilityView cap2:ProdNameDianShang){
				if(!StringUtils.isEmpty(cap2.getProdName())){
					cap2.setProdName(DpiUtils.strDecrypt(cap2.getProdName()));// 解密
					cap2.setContentLabelCode1("电商");
				}
			}
			//属性
			List<CapabilityView> attrshipin=capabilityViewService.getContentKuAttr("视频"); //视频
			List<CapabilityView> attrxiaoshuo=capabilityViewService.getContentKuAttr("小说"); //小说
			List<CapabilityView> attrdianshang=capabilityViewService.getContentKuAttr("电商"); //电商
			
			
			resultmap.put("LabelNum", lvl2);
			resultmap.put("shipin", ProdNameShiPin);
			resultmap.put("xiaoshuo", ProdNameXiaoShuo);
			resultmap.put("dianshang", ProdNameDianShang);
			resultmap.put("attrshipin", attrshipin);
			resultmap.put("attrxiaoshuo", attrxiaoshuo);
			resultmap.put("attrdianshang", attrdianshang);
				
			return Ajax.responseString(CST.RES_SECCESS, resultmap);
		} catch (Exception e) {
			logger.error(e.getMessage());
			return Ajax.responseString(CST.RES_EXCEPTION, e.getMessage());
		}
	}
	@RequestMapping(value = "/getActInfo",method=RequestMethod.POST, produces="application/json;charset=UTF-8")
	@ResponseBody
	public String getActInfo() {
		try {
			Map<String, List<CapabilityView>> result=new HashMap<>();
			List<String> resultList=capabilityViewService.getModelWorkLvl1();
			for(String item: resultList){
				if(!StringUtils.isEmpty(item)){
					List<CapabilityView> lvl2=capabilityViewService.getWorkLvl2(item);
					result.put(item, lvl2);
					
				}
				
				
			}
			List<CapabilityView> lvl3=capabilityViewService.getChild();
			result.put("child", lvl3);
			return Ajax.responseString(CST.RES_SECCESS, result);
		} catch (Exception e) {
			logger.error(e.getMessage());
			return Ajax.responseString(CST.RES_EXCEPTION, e.getMessage());
		}
	}
	
	@RequestMapping(value = "/publishAuth",method=RequestMethod.POST, produces="application/json;charset=UTF-8")
	@ResponseBody
	public String publishAuth() {
		String publishAF = "";
		SysUser sysUser = null;
		try {
			sysUser = LoginAction.getLoginUser();
			String loginId = sysUser.getLoginId();
			int publishAuthFlag = capabilityViewService.getpublishAuth(loginId);
			if(publishAuthFlag > 0) {
				publishAF = "1";
			}else {
				publishAF = "0";
			}
//			sb.append("，操作人：" + (sysUser == null ? "null": sysUser.getLoginId()));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return  Ajax.responseString(publishAF);
	}
	
	//修改发布状态
	@RequestMapping(value = "/publishById",method=RequestMethod.POST, produces="application/json;charset=UTF-8")
	@ResponseBody
	public Boolean publishById(String prodId,String fbFlag) throws Exception {
		ProductFbDescInfo voParam = new ProductFbDescInfo();
		voParam.setProdId(prodId);
		if("1".equals(fbFlag)) {
			fbFlag = "0";
		}else{
			fbFlag = "1";
		}
		voParam.setFbFlag(fbFlag);
		Boolean publish = capabilityViewService.publishById(voParam);
		return publish ;
	}
	
	
}
