package com.bonc.dpi.service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bonc.common.utils.UUIDUtil;
import com.bonc.dpi.dao.entity.OperationFlow;
import com.bonc.dpi.dao.entity.OperationFlowExportReturn;
import com.bonc.dpi.dao.entity.OperationFlowImportReturn;
import com.bonc.dpi.dao.entity.ProductInfo;
import com.bonc.dpi.dao.entity.StopWordLib;
import com.bonc.dpi.dao.entity.UrlParameter;
import com.bonc.dpi.dao.mapper.UrlParameterMapper;
import com.bonc.dpi.service.i.OperationFlowServiceI;
import com.bonc.dpi.utils.DpiUtils;
import com.bonc.login.action.LoginAction;
import com.bonc.system.dao.entity.SysUser;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;

/**
 * 
 * @author BONC-XUXL
 *
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class UrlParameterService implements OperationFlowServiceI{

	@Autowired
	private UrlParameterMapper mapper;
	
	@Resource
	private ProductInfoService piService;
	
	private static SimpleDateFormat sdf = DpiUtils.sdf;
	private static SysUser user ;
	
	public static HashSet<String> hashSetInExcle_primaryKey = new HashSet<String>();//excle中的主键

	public static List<OperationFlow> invalidDataInExcle_repeatPrimaryKey = new ArrayList<OperationFlow>(); //excle中无效的数据--主键重复
	public static List<OperationFlow> invalidDataInExcle_empty = new ArrayList<OperationFlow>(); //excle中无效的数据--空值
	public static List<OperationFlow> invalidDataInExcle_tooLong = new ArrayList<OperationFlow>(); //excle中无效的数据--过长
	public static List<OperationFlow> invalidDataInExcle_notTrue_prodId = new ArrayList<OperationFlow>(); //excle中无效的数据--关联表不真实存在
	
	public static List<OperationFlow> list_false_cache = new ArrayList<OperationFlow>(); //excle中在数据库中重复的缓存数据
	public static List<OperationFlow> list_true_cache = new ArrayList<OperationFlow>(); //excle中正确的缓存数据

	
	/**
	 * 分页查询
	 * @param ksr
	 * @param page
	 * @param row
	 * @return
	 */
	public Page<UrlParameter> selectList(UrlParameter ksr, Integer page, Integer row) {
		PageHelper.startPage(page, row);
		Page<UrlParameter> pageList = (Page<UrlParameter>) this.mapper.selectList(ksr);
		for (UrlParameter urlParameter : pageList) {
			ProductInfo piParam = new ProductInfo();
			String prodId = urlParameter.getProdId();
			piParam.setProdId(prodId);
			ProductInfo voPi = piService.selectVoByPrimaryKey(piParam);
			if(voPi!=null){
				urlParameter.setProdName(DpiUtils.strDecrypt(voPi.getProdName()));
			}
		}
		
		return pageList;
	}
	
	/**
	 * 根据id查询对象
	 * @param id
	 * @return
	 */
	public UrlParameter selectVoById(String id){
		return mapper.selectVoById(id);
	}
	
	/**
	 * 添加
	 * @param vo
	 */
	public UrlParameter insertVo(UrlParameter vo){
		Boolean bl = mapper.insertVo(vo);
		return vo;
	}
	
	/**
	 * 修改
	 * @param vo
	 * @return 
	 */
	public UrlParameter updateVo(UrlParameter vo){
		Boolean bl = mapper.updateVo(vo);
		return vo;
	}
	
	/**
	 * 删除
	 * @param id
	 */
	public Boolean deleteVoById(String id){
		Boolean bl = mapper.deleteVoById(id);
		return bl;
	}
	
	/**
	 * 去重过滤
	 * @param vo
	 * @return
	 */
	public Boolean selectCheck(UrlParameter vo) {
		Integer i = this.mapper.selectCheck(vo);
		if(i > 0) {
			return false;
		}
		return true;
	}
	
	/**
	 * 不可为空，prod_id必须在产品表里
	 * @return
	 */
	public Boolean prodIdCheck(UrlParameter vo){
		Integer i = this.mapper.prodIdCheck(vo);
		if(i > 0) {
			return true;
		}
		return false;
	}
	
	
	
	
	
	
	/**
	 * 批量导入
	 */
	@Override
	public OperationFlowImportReturn doImport(List<OperationFlow> list,String type) throws Exception {
		
		clearCache();//清空缓存
		
		OperationFlowImportReturn ofir = new OperationFlowImportReturn();//提示语对象
		ofir.setFalseType("1");//默认有问题
		ofir.setActionUrl(DpiUtils.url_falseDataViewFromExcle_urlParameter);//excle批量导入问题数据页面反回路径
		
		Boolean bl_excle_self = true;
		
		//1.检查excle中自身数据有效性
		dataValidityInExcle(list);
		//excle中产品id不在产品表中的数据条数
		if(invalidDataInExcle_notTrue_prodId.size()>0){
			bl_excle_self = false;
		}
		//excle中为空数据条数
		if(invalidDataInExcle_empty.size()>0){
			bl_excle_self = false;
		}
		//excle中数据过长数据条数
		if(invalidDataInExcle_tooLong.size()>0){
			bl_excle_self = false;
		}
		//excle中主键是否有重复数据
		if(invalidDataInExcle_repeatPrimaryKey.size()>0){
			bl_excle_self = false;
		}
		
		//2.检查与对比数据库中是否有重复（如果excle中自身没问题了）
		if(bl_excle_self){
			Boolean bl_compareToDataBase = true;//与数据库比较标识
			Map<String,List<OperationFlow>> map = filterList_CompareDataBase(list);
			List<OperationFlow> list_true = map.get("list_true");//正确数据插入数据库中
			List<OperationFlow> list_false = map.get("list_false");//错误数据返回到前台页面
			list_false_cache = list_false;
			list_true_cache = list_true;
			
			if(list_false!=null && list_false.size()>0){
				ofir.setErrorList(list_false);
				bl_compareToDataBase = false;
			}
			
			//如果都没问题，则直接插入库中
			if(bl_compareToDataBase){
				//数据完全没问题
				if(list_true.size()>0 && list_true!=null){
					ofir.setFalseType("2");//数据完全没问题
					insertListCache(list_true);
				}
			}
		}
		
		return ofir;
	}
	
	/**
	 * 清空内存
	 * @throws Exception
	 */
	public void clearCache()throws Exception {
		
		hashSetInExcle_primaryKey.clear();//清空缓存
		
		invalidDataInExcle_repeatPrimaryKey.clear();//清空缓存
		invalidDataInExcle_notTrue_prodId.clear();//清空缓存
		invalidDataInExcle_empty.clear();//清空缓存
		invalidDataInExcle_tooLong.clear();//清空缓存
		
		list_true_cache.clear();//清空缓存
		list_false_cache.clear();//清空缓存
	}
	
	/**
	 * 批量导入数据有效性验证
	 * @param list
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unused")
	public void dataValidityInExcle(List<OperationFlow> list) throws Exception {
		
		for (OperationFlow of : list) {
			
			Boolean bl_empty = true;//数据有效--空值
			Boolean bl_tooLong = true;//数据有效--过长
			Boolean bl_notTrue_prodId = true;//数据有效--关联表不真实存在
			
			UrlParameter vo = (UrlParameter) of;
			String host = vo.getHost();//域名host
			String parameterCode = vo.getParameterCode();//参数名PARAMETER_CODE
			String parameterValue =vo.getParameterValue();//参数值PARAMETER_VALUE
			String prodId = vo.getProdId();//产品IDPROD_ID
			String regexpRule = vo.getRegexpRule();//匹配规则REGEXP
			String comments = vo.getComments();//备注COMMENTS
			String urlExample = vo.getUrlExample();//URL样例URL_EXAMPLE
			String author = vo.getAuthor();//作者
			String opTime = vo.getOpTime();//操作时间
			String id = vo.getId();//序号
			
			//非空校验
			if("".equals(host) || host==null){
				bl_empty = false;
			}
			if("".equals(prodId) || prodId==null){
				bl_empty = false;
			}
			if("".equals(parameterCode) || parameterCode==null){
				bl_empty = false;
			}
			if("".equals(parameterValue) || parameterValue==null){
				bl_empty = false;
			}			
			if("".equals(regexpRule) || regexpRule==null){
				bl_empty = false;
			}

			//长度验证
			if(host.length()>200){
				bl_tooLong = false;
			}
			if(parameterCode.length()>100){
				bl_tooLong = false;
			}
			if(parameterValue.length()>100){
				bl_tooLong = false;
			}
			if(prodId.length()>100){
				bl_tooLong = false;
			}
			if(regexpRule.length()>500){
				bl_tooLong = false;
			}
			if(comments.length()>2000){
				bl_tooLong = false;
			}	
			if(urlExample.length()>2000){
				bl_tooLong = false;
			}	
			
			//产品id逻辑验证
			ProductInfo pi = new ProductInfo();//new条件
			pi.setProdId(prodId);
			bl_notTrue_prodId = piService.prodIdCheck(pi);//prod_id必须在产品表里
			
			if(!bl_empty){
				invalidDataInExcle_empty.add(of);
			}
			if(!bl_tooLong){
				invalidDataInExcle_tooLong.add(of);
			}
			if(!bl_notTrue_prodId){
				invalidDataInExcle_notTrue_prodId.add(of);
			}
			
			//主键没有重复则插入缓存中
			if(!hashSetInExcle_primaryKey.contains(host+"~"+regexpRule)){
				hashSetInExcle_primaryKey.add(host+"~"+regexpRule);
			}
			else{
				invalidDataInExcle_repeatPrimaryKey.add(of);//重复主键
			}
		}
	}
	
	
	/**
	 * 过滤list数据,与数据库比较，筛选出主键重复的
	 * @param list
	 * @return
	 */
	public Map<String,List<OperationFlow>> filterList_CompareDataBase(List<OperationFlow> list) throws Exception {
		
		user = LoginAction.getLoginUser();
		List<OperationFlow> list_true = new ArrayList<OperationFlow>(); //有效数据
		List<OperationFlow> list_false = new ArrayList<OperationFlow>(); //无效数
		Map<String,List<OperationFlow>> map = new HashMap<String,List<OperationFlow>>();
		for (OperationFlow operationFlow : list) {

			UrlParameter vo = (UrlParameter) operationFlow;
			UrlParameter voParam = DpiUtils.getVoParam_UrlParameter(vo);//参数
			UrlParameter urlParameter= mapper.selectVoByPrimaryKey(voParam);//根据主键查找
			
			//补全信息
			if(StringUtils.isBlank(vo.getId())){
				String UUID = UUIDUtil.createUUID();//
				vo.setId(UUID);
			}
			if(StringUtils.isBlank(vo.getAuthor())){
				vo.setAuthor(user.getLoginId());
			}
			if(StringUtils.isBlank(vo.getOpTime())){
				vo.setOpTime(sdf.format(new Date()));
			}


//			vo.setId(UUID);
//			vo.setAuthor(user.getLoginId());//登陆名
//			vo.setOpTime(sdf.format(new Date()));//设置时间
			if(urlParameter != null){
				list_false.add(operationFlow);//无效数据（主键重复等原因）
			}else{
				list_true.add(operationFlow);//有效数据
			}
		}
		map.put("list_true", list_true);
		map.put("list_false", list_false);
		
		return map;
	}
	
	/**
	 * 将主键重复数据插入库中
	 * 1.type  1:舍弃，2：更新
	 * @param type
	 * @return
	 * @throws Exception
	 */
	public Boolean insertListFalseCache(String type)throws Exception {
		Boolean bl = false;
		//type  1:舍弃
		if("1".equals(type)){
			//1.正确的数据（在之前校验时候并没有插入库，和错误数据一起插入）
			if(list_true_cache.size()>0 && list_true_cache!=null){
				insertListCache(list_true_cache);
			}
		}
		//type 2：更新
		else if("2".equals(type)){
			//2.1先删除库中的相同主键数据
			for (OperationFlow of : list_false_cache){
				UrlParameter vo = (UrlParameter) of;
				UrlParameter voParam = DpiUtils.getVoParam_UrlParameter(vo);//参数
				mapper.deleteVoByPrimaryKey(voParam);
			}
			
			//2.2.将重复的数据插入
			if(list_false_cache.size()>0 && list_false_cache!=null){
				insertListCache(list_false_cache);
			}
			
			//2.3.正确的数据（在之前校验时候并没有插入库，和错误数据一起插入）
			if(list_true_cache.size()>0 && list_true_cache!=null){
				insertListCache(list_true_cache);
			}
		}
		bl = true;
		
		return bl;
	}
	
	/**
	 * 将缓存的集合插入库
	 */
	public void insertListCache(List<OperationFlow> list )throws Exception {
		Integer PL_NUMBERS_ONCE = DpiUtils.PL_NUMBERS_ONCE;
		//500条一组插入数据库
		for(int j=0;j<(list.size()/PL_NUMBERS_ONCE+1);j++){
			List<OperationFlow> list_true_insert = DpiUtils.getPlInsertDataOnce(list, j);
			if(list_true_insert.size()>0 && list_true_insert!=null){
				mapper.insertVoPl(list_true_insert);
			}
		}
	}
	
	/**
	 * 批量导出
	 */
	@Override
	public OperationFlowExportReturn doExport(OperationFlow o) throws Exception {
		UrlParameter vo = (UrlParameter) o;
		if(StringUtils.isNotBlank(vo.getOpTime())) {
			String[] arr=vo.getOpTime().split("-");
			vo.setStartTime(arr[0].trim());
			vo.setEndTime(arr[1].trim());
		}
		List<UrlParameter> list = this.mapper.selectList(vo);
		OperationFlowExportReturn ofer = new OperationFlowExportReturn();
		ofer.setExcelName("dim_ia_url_parameter.xlsx");
		ofer.setTxtName("dim_ia_url_parameter_all.txt");
		ofer.setNewTxtName("dim_ia_url_parameter.txt");
		ofer.setList(list);
		return ofer;
	}	
	
	/**
	 * 批量导出数据条数
	 */
	@Override
	public Integer exportDataNum(OperationFlow o,String type) throws Exception {
		UrlParameter vo = (UrlParameter) o;
		if(StringUtils.isNotBlank(vo.getOpTime())) {
			String[] arr=vo.getOpTime().split("-");
			vo.setStartTime(arr[0].trim());
			vo.setEndTime(arr[1].trim());
		}
		Integer num = this.mapper.selectDataNum(vo);
		
		return num;
	}
}
