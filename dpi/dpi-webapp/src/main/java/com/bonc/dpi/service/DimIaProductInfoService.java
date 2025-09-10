package com.bonc.dpi.service;

import java.text.SimpleDateFormat;
import java.util.*;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bonc.common.utils.UUIDUtil;
import com.bonc.dpi.dao.entity.DimIaIpPortDynamic;
import com.bonc.dpi.dao.entity.DimIaProductInfoDomain;
import com.bonc.dpi.dao.entity.DomainRule;
import com.bonc.dpi.dao.entity.OperationFlow;
import com.bonc.dpi.dao.entity.OperationFlowExportReturn;
import com.bonc.dpi.dao.entity.OperationFlowImportReturn;
import com.bonc.dpi.dao.entity.ProductInfo;
import com.bonc.dpi.dao.entity.ProductLabel;
import com.bonc.dpi.dao.mapper.DimIaIpPortDynamicMapper;
import com.bonc.dpi.dao.mapper.DomainRuleMapper;
import com.bonc.dpi.dao.mapper.ProductInfoMapper;
import com.bonc.dpi.service.i.OperationFlowServiceI;
import com.bonc.dpi.utils.DpiUtils;
import com.bonc.login.action.LoginAction;
import com.bonc.system.dao.entity.SysUser;

/**
 * 产品表
 * dim_ia_product_info
 * 新模板用
 * @author BONC-XUXL
 *
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class DimIaProductInfoService implements OperationFlowServiceI{
	
	@Resource
//	@Autowired
	private ProductInfoMapper mapper;
	
	@Resource
	private ProductLabelService productLabelService;
	
	@Resource
	private DomainRuleService domainRuleService;
	
	@Resource
	private DomainRuleMapper domainRuleMapper;//域名的Mapper
	
	@Resource
	private DimIaIpPortDynamicService dimIaIpPortDynamicService;
	
	@Resource
	private DimIaIpPortDynamicMapper dimIaIpPortDynamicMapper;//ip的Mapper
	
	private static SysUser user ;

	private static SimpleDateFormat sdf = DpiUtils.sdf;
	
	public static HashSet<String> hashSetInExcle_primaryKey_domainCode = new HashSet<String>();//excle中的主键domainCode
	public static HashSet<String> hashSetInExcle_primaryKey_prodName = new HashSet<String>();//excle中的主键prodName
	
	public static List<OperationFlow> invalidDataInExcle_repeatPrimaryKey_prodName = new ArrayList<OperationFlow>(); //excle中无效的数据--主键重复
	public static List<OperationFlow> invalidDataInExcle_repeatPrimaryKey_domainCode = new ArrayList<OperationFlow>(); //excle中无效的数据--主键重复

	public static List<OperationFlow> invalidDataInExcle_empty = new ArrayList<OperationFlow>(); //excle中无效的数据--空值
	public static List<OperationFlow> invalidDataInExcle_tooLong = new ArrayList<OperationFlow>(); //excle中无效的数据--过长
	public static List<OperationFlow> invalidDataInExcle_notTrue_prodLabelCode = new ArrayList<OperationFlow>(); //excle中无效的数据--关联表不真实存在
	
	public static List<OperationFlow> list_false_cache_productInfo = new ArrayList<OperationFlow>(); //excle中在数据库中重复的缓存数据
	public static List<OperationFlow> list_true_cache_productInfo = new ArrayList<OperationFlow>(); //excle中正确的缓存数据
	public static List<OperationFlow> list_false_cache_domainRule = new ArrayList<OperationFlow>(); //excle中在数据库中重复的缓存数据
	public static List<OperationFlow> list_true_cache_domainRule = new ArrayList<OperationFlow>(); //excle中正确的缓存数据
	public static List<OperationFlow> list_false_cache_ipPort = new ArrayList<OperationFlow>(); //excle中在数据库中重复的缓存数据
	public static List<OperationFlow> list_true_cache_ipPort = new ArrayList<OperationFlow>(); //excle中正确的缓存数据

	public static String prodFlag;
	/**
	 * 清空内存
	 * @throws Exception
	 */
	public void clearCache()throws Exception {
		
		hashSetInExcle_primaryKey_domainCode.clear();//清空缓存
		hashSetInExcle_primaryKey_prodName.clear();//清空缓存
		
		invalidDataInExcle_repeatPrimaryKey_prodName.clear();//清空缓存
		invalidDataInExcle_repeatPrimaryKey_domainCode.clear();//清空缓存
		
		invalidDataInExcle_notTrue_prodLabelCode.clear();//清空缓存
		invalidDataInExcle_empty.clear();//清空缓存
		invalidDataInExcle_tooLong.clear();//清空缓存
		
		list_false_cache_productInfo.clear();//清空缓存
		list_true_cache_productInfo.clear();//清空缓存
		list_false_cache_domainRule.clear();//清空缓存
		list_true_cache_domainRule.clear();//清空缓存
		list_false_cache_ipPort.clear();//清空缓存
		list_true_cache_ipPort.clear();//清空缓存
	}
	
	
	/**
	 * 批量导入
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public OperationFlowImportReturn doImport(List<OperationFlow> list,String type) throws Exception {
		
		clearCache();//清空缓存
		prodFlag=type;
		OperationFlowImportReturn ofir = new OperationFlowImportReturn();//提示语对象
		ofir.setFalseType("1");//默认有问题
		ofir.setActionUrl(DpiUtils.url_falseDataViewFromExcle_productInfo_domain);//excle批量导入问题数据页面反回路径
		
		Boolean bl_excle_self = true;
		
		//1.检查excle中自身数据有效性
		dataValidityInExcle(list);
		//excle中产品id不在产品表中的数据条数
		if(invalidDataInExcle_notTrue_prodLabelCode.size()>0){
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
		if(invalidDataInExcle_repeatPrimaryKey_prodName.size()>0){
			bl_excle_self = false;
		}
		//excle中主键是否有重复数据
		if(invalidDataInExcle_repeatPrimaryKey_domainCode.size()>0){
			bl_excle_self = false;
		}
		
		//2.检查与对比数据库中是否有重复（如果excle中自身没问题了）
		if(bl_excle_self){
			Boolean bl_compareToDataBase = true;//与数据库比较标识
			
			List<OperationFlow> list_productInfo = new ArrayList<OperationFlow>();//1.进行产品表比较-------将list转换为产品表对应的实体类
			List<OperationFlow> list_domainRule = new ArrayList<OperationFlow>();//2.进行域名表比较-------将list转换为域名表对应的实体类
			List<OperationFlow> list_ipPort = new ArrayList<OperationFlow>();//2.进行ip表比较-------将list转换为ip表对应的实体类
			String prodId_max = mapper.getProdId_Max(DpiUtils.PRODID_HEAD);//PRODID_HEAD是产品ID首字母
			HashMap map_prodName_prodId = new HashMap();//产品名称，产品id的缓存map
			for (OperationFlow operationFlow : list) {
				DimIaProductInfoDomain dipid = (DimIaProductInfoDomain)operationFlow;
				String domainCode =dipid.getDomainCode();//domain_code,域名编码
				String prodName =dipid.getProdName();//prod_name,产品名称
				String prodLabelCode =dipid.getProdLabelCode();//prod_label_code,标签ID
				String author =dipid.getAuthor();//author,操作人
				String opTime=dipid.getOpTime();//时间
				String prodCatagory =dipid.getProdCatagory();//prodCatagory,产品类型
				String rowNum = dipid.getRowNum();//行号
				String prodDesc=dipid.getProdDesc();
				String iosProdName=dipid.getIosProdName();
				String isBoce=dipid.getIsBoce();
				String boceDate=dipid.getBoceDate();
				
				ProductInfo pi = new ProductInfo();
				if(!hashSetInExcle_primaryKey_prodName.contains(prodName)){
					hashSetInExcle_primaryKey_prodName.add(prodName);
					String prodIdNew = DpiUtils.getProdId_next(prodId_max);//自动生成新产品ID
					prodId_max = prodIdNew;//将最大值赋值给新的
					
					pi.setProdName(prodName);
					pi.setProdLabelCode(prodLabelCode);
					pi.setRowNum(rowNum);
					pi.setProdId(prodIdNew);
					pi.setAuthor(author);
					pi.setOpTime(opTime);
					pi.setProdCatagory(prodCatagory);
					pi.setProdDesc(prodDesc);
					pi.setIosProdName(DpiUtils.strEncrypt(iosProdName));
					if(isBoce==null ||"null".equals(isBoce) || "".equals(isBoce)){
						pi.setIsBonc("0");
					}else{
						pi.setIsBonc(isBoce);
					}
					
					pi.setBoceDate(boceDate);
					list_productInfo.add(pi);
					map_prodName_prodId.put(prodName, prodIdNew);//将产品名称，产品id以map缓存
				}
				boolean b_isIp = DpiUtils.isIp(domainCode);//true是纯ip
				boolean b_isIpPort = DpiUtils.isIpPort(domainCode);//true是ipPort
				if(b_isIp){
					DimIaIpPortDynamic dimIaIpPortDynamic = new DimIaIpPortDynamic();
					dimIaIpPortDynamic.setIpPort(domainCode+":80");
					dimIaIpPortDynamic.setIp(domainCode);
					dimIaIpPortDynamic.setPort("80");//默认补全80
					dimIaIpPortDynamic.setRowNum(rowNum);
					String prodId = (String)map_prodName_prodId.get(prodName);//由于map_prodName_prodId中一定会存在key（prodName）、上一步先存起来的，所以不用验证
					dimIaIpPortDynamic.setProdid(prodId);
					dimIaIpPortDynamic.setProdName(prodName);
					dimIaIpPortDynamic.setAuthor(author);
					dimIaIpPortDynamic.setOpTime(opTime);
					list_ipPort.add(dimIaIpPortDynamic);
				}
				else if(b_isIpPort){
					String[] ipPortArray = domainCode.split(":");
					DimIaIpPortDynamic dimIaIpPortDynamic = new DimIaIpPortDynamic();
					dimIaIpPortDynamic.setIpPort(domainCode);
					dimIaIpPortDynamic.setIp(ipPortArray[0]);
					dimIaIpPortDynamic.setPort(ipPortArray[1]);
					String prodId = (String)map_prodName_prodId.get(prodName);//由于map_prodName_prodId中一定会存在key（prodName）、上一步先存起来的，所以不用验证
					dimIaIpPortDynamic.setProdid(prodId);
					dimIaIpPortDynamic.setProdName(prodName);
					dimIaIpPortDynamic.setAuthor(author);
					dimIaIpPortDynamic.setOpTime(opTime);
					list_ipPort.add(dimIaIpPortDynamic);
				}
				else{
					DomainRule domainRule = new DomainRule();
					domainRule.setDomainCode(domainCode);
					domainRule.setRowNum(rowNum);
					String prodId = (String)map_prodName_prodId.get(prodName);//由于map_prodName_prodId中一定会存在key（prodName）、上一步先存起来的，所以不用验证
					domainRule.setProdId(prodId);
					domainRule.setProdName(prodName);
					domainRule.setAuthor(author);
					domainRule.setOpTime(opTime);
					list_domainRule.add(domainRule);
				}
			}
			
			Map<String,List<OperationFlow>> map_productInfo = filterList_CompareDataBase_productInfo(list_productInfo);
			list_true_cache_productInfo = map_productInfo.get("list_true");//正确数据插入数据库中
			list_false_cache_productInfo = map_productInfo.get("list_false");//错误数据返回到前台页面
			
			Map<String,List<OperationFlow>> map_domainRule = filterList_CompareDataBase_domainRule(list_domainRule);
			list_true_cache_domainRule = map_domainRule.get("list_true");//正确数据插入数据库中
			list_false_cache_domainRule = map_domainRule.get("list_false");//错误数据返回到前台页面
			
			Map<String,List<OperationFlow>> map_ipPort = filterList_CompareDataBase_ipPort(list_ipPort);
			list_true_cache_ipPort = map_ipPort.get("list_true");//正确数据插入数据库中
			list_false_cache_ipPort = map_ipPort.get("list_false");//错误数据返回到前台页面
			
			if(list_false_cache_productInfo!=null && list_false_cache_productInfo.size()>0){
				ofir.setErrorList(list_false_cache_productInfo);
				bl_compareToDataBase = false;
			}
			
			if(list_false_cache_domainRule!=null && list_false_cache_domainRule.size()>0){
				ofir.setErrorList(list_false_cache_productInfo);
				bl_compareToDataBase = false;
			}
			if(list_false_cache_ipPort!=null && list_false_cache_ipPort.size()>0){
				ofir.setErrorList(list_false_cache_ipPort);
				bl_compareToDataBase = false;
			}
			
			//2.进行域名表比较
			
			//如果都没问题，则直接插入库中
			if(bl_compareToDataBase){
				//数据完全没问题
				if(list_true_cache_productInfo.size()>0 && list_true_cache_productInfo!=null){
					ofir.setFalseType("2");//数据完全没问题
					insertListCache(list_true_cache_productInfo,"dim_ia_product_info");
				}
				//数据完全没问题
				if(list_true_cache_domainRule.size()>0 && list_true_cache_domainRule!=null){
					ofir.setFalseType("2");//数据完全没问题
					insertListCache(list_true_cache_domainRule,"dim_ia_domain_rule");
				}
				//数据完全没问题
				if(list_true_cache_ipPort.size()>0 && list_true_cache_ipPort!=null){
					ofir.setFalseType("2");//数据完全没问题
					insertListCache(list_true_cache_ipPort,"dim_ia_ip_port_dynamic");
				}	
			}
		}
		
		return ofir;
	}
	
	
	/**
	 * 批量导入数据有效性验证
	 * @param list
	 * @return
	 * @throws Exception
	 */
	public void dataValidityInExcle(List<OperationFlow> list) throws Exception {
		
		for (OperationFlow of : list) {
			
			Boolean bl_empty = true;//数据有效--空值
			Boolean bl_tooLong = true;//数据有效--过长
			Boolean bl_notTrue_prodLabelCode = true;//数据有效--关联表不真实存在
			
			DimIaProductInfoDomain vo = (DimIaProductInfoDomain) of;
			String domainCode =vo.getDomainCode();//domain_code,域名编码
			String prodName =vo.getProdName();//prod_name,产品名称
			String prodLabelCode =vo.getProdLabelCode();//prod_label_code,标签ID
			//String opTime = vo.getOpTime();//操作时间
			
			//非空校验
//			if("".equals(domainCode) || domainCode==null){
//				bl_empty = false;
//			}
			if("".equals(prodName) || prodName==null){
				bl_empty = false;
			}
			if("".equals(prodLabelCode) || prodLabelCode==null){
				bl_empty = false;
			}
			
			//长度验证
			if(domainCode.length()>200){
				bl_tooLong = false;
			}
			if(prodName.length()>1000){
				bl_tooLong = false;
			}
			if(prodLabelCode.length()>50){
				bl_tooLong = false;
			}
			/*if(opTime.length()>400){
				bl_tooLong = false;
			}*/
			
			//产品标签逻辑验证
			ProductLabel productLabel = productLabelService.selectVoByLabelCode(prodLabelCode);
			if(productLabel == null){
				bl_notTrue_prodLabelCode = false;
			}
			
			if(!bl_empty){
				invalidDataInExcle_empty.add(of);
			}
			if(!bl_tooLong){
				invalidDataInExcle_tooLong.add(of);
			}
			if(!bl_notTrue_prodLabelCode){
				invalidDataInExcle_notTrue_prodLabelCode.add(of);
			}
			
			//主键没有重复则插入缓存中domainCode
			if(!hashSetInExcle_primaryKey_domainCode.contains(domainCode)||StringUtils.isBlank(domainCode)){
				hashSetInExcle_primaryKey_domainCode.add(domainCode);
			}else{
				invalidDataInExcle_repeatPrimaryKey_domainCode.add(of);//重复主键domainCode
			}

		}
	}
	

	/**
	 * 过滤list数据,与数据库比较，筛选出主键重复的
	 * @param list
	 * @return
	 */
	public Map<String,List<OperationFlow>> filterList_CompareDataBase_productInfo(List<OperationFlow> list) throws Exception {
		
		user = LoginAction.getLoginUser();
		List<OperationFlow> list_true = new ArrayList<OperationFlow>(); //有效数据
		List<OperationFlow> list_false = new ArrayList<OperationFlow>(); //无效数
		Map<String,List<OperationFlow>> map = new HashMap<String,List<OperationFlow>>();
		for (OperationFlow operationFlow : list) {
			ProductInfo vo = (ProductInfo) operationFlow;
			
			String prodName = vo.getProdName();//产品名称
			ProductInfo voParam = new ProductInfo();
			voParam.setProdName(DpiUtils.strEncrypt(prodName));//加密
			ProductInfo ProductInfo= mapper.selectVoByPrimaryKey(voParam);//根据产品名称查找
			vo.setProdName(DpiUtils.strEncrypt(vo.getProdName()));//加密
			
			vo.setAddLabelList("");
			vo.setFatherProdId("");
			vo.setProdTypeId("");
			vo.setProdTypeName("");
			//vo.setProdCatagory("");
			vo.setIsMainProd("1");//是否主产品--默认值
			vo.setIsApp("1");//是否APP--默认值
			vo.setIsOperator("0");//is_operator,是否运营商自有业务：0-非运营商 1-移动自有 2-联通自有 3-电信自有
			vo.setSpId("");
			vo.setSpName("");
			vo.setShortName("");
			
			vo.setLabelCheck("0");//标签是否稽核

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


//			if(vo.getAuthor()==null){
//				vo.setAuthor(user.getLoginId());
//			}
			//vo.setAuthor(user.getLoginId());
//			vo.setOpTime(DpiUtils.sdf.format(new Date()));//设置时间
//			String UUID = UUIDUtil.createUUID();//uuid
//			vo.setId(UUID);
			if(ProductInfo != null){
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
	 * 过滤list数据,与数据库比较，筛选出主键重复的
	 * @param list
	 * @return
	 */
	@SuppressWarnings("unused")
	public Map<String,List<OperationFlow>> filterList_CompareDataBase_domainRule(List<OperationFlow> list) throws Exception {
		
		user = LoginAction.getLoginUser();
		List<OperationFlow> list_true = new ArrayList<OperationFlow>(); //有效数据
		List<OperationFlow> list_false = new ArrayList<OperationFlow>(); //无效数
		Map<String,List<OperationFlow>> map = new HashMap<String,List<OperationFlow>>();
		for (OperationFlow operationFlow : list) {
			DomainRule vo = (DomainRule) operationFlow;
			DomainRule voParam = new DomainRule();
			voParam.setDomainCode(vo.getDomainCode());
			DomainRule domainRule= domainRuleService.selectVoByPrimaryKey(voParam);//根据主键查找
			String prodIdOld = "";
			String prodNameOld =  "";
			if(domainRule!=null){
				prodIdOld = domainRule.getProdId();
			}
			//查找产品名称
			ProductInfo voParam_productInfo = new ProductInfo();
			if(prodIdOld!=null && !"".equals(prodIdOld)){
				voParam_productInfo.setProdId(prodIdOld);
				ProductInfo productInfo= mapper.selectVoByPrimaryKey(voParam_productInfo);//根据产品id查找
				if(productInfo!=null){
					prodNameOld = productInfo.getProdName();
					prodNameOld = DpiUtils.strDecrypt(prodNameOld);
				}	
			}
			
			//补全信息  txt2    excel 1
			if("1".equals(prodFlag)){
				
				if(StringUtils.isBlank(vo.getAuthor())){
					vo.setAuthor(user.getLoginId());
				}
				if(StringUtils.isBlank(vo.getOpTime())){
					vo.setOpTime(sdf.format(new Date()));
				}
				
				
			}
//			vo.setAuthor(user.getLoginId());
//			vo.setOpTime(DpiUtils.sdf.format(new Date()));//设置时间
			String UUID = UUIDUtil.createUUID();//
			vo.setId(UUID);
			if(domainRule != null){
				//vo.setProdId(prodIdOld);//用于错误数据返回页面显示
			    //vo.setProdName(prodNameOld);//用于错误数据返回页面显示
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
	 * 过滤list数据,与数据库比较，筛选出主键重复的
	 * @param list
	 * @return
	 */
	@SuppressWarnings("unused")
	public Map<String,List<OperationFlow>> filterList_CompareDataBase_ipPort(List<OperationFlow> list) throws Exception {
		
		user = LoginAction.getLoginUser();
		List<OperationFlow> list_true = new ArrayList<OperationFlow>(); //有效数据
		List<OperationFlow> list_false = new ArrayList<OperationFlow>(); //无效数
		Map<String,List<OperationFlow>> map = new HashMap<String,List<OperationFlow>>();
		for (OperationFlow operationFlow : list) {
			DimIaIpPortDynamic vo = (DimIaIpPortDynamic) operationFlow;
			DimIaIpPortDynamic voParam = new DimIaIpPortDynamic();
			voParam.setIpPort(vo.getIpPort());
			DimIaIpPortDynamic dimIaIpPortDynamic= dimIaIpPortDynamicMapper.selectVoByPrimaryKey(voParam);//根据主键查找
			String prodIdOld = "";
			String prodNameOld =  "";
			if(dimIaIpPortDynamic!=null){
				prodIdOld = dimIaIpPortDynamic.getProdid();
			}
			//查找产品名称
			ProductInfo voParam_productInfo = new ProductInfo();
			if(prodIdOld!=null && !"".equals(prodIdOld)){
				voParam_productInfo.setProdId(prodIdOld);
				ProductInfo productInfo= mapper.selectVoByPrimaryKey(voParam_productInfo);//根据产品id查找
				if(productInfo!=null){
					prodNameOld = productInfo.getProdName();
					prodNameOld = DpiUtils.strDecrypt(prodNameOld);
				}
			}
			
			//补全信息  txt2    excel 1
			if("1".equals(prodFlag)){
				
				if(StringUtils.isBlank(vo.getAuthor())){
					vo.setAuthor(user.getLoginId());
				}
				if(StringUtils.isBlank(vo.getOpTime())){
					vo.setOpTime(sdf.format(new Date()));
				}
				
				
			}
			//vo.setAuthor(user.getLoginId());
			//vo.setOpTime(DpiUtils.sdf.format(new Date()));//设置时间
			String UUID = UUIDUtil.createUUID();//
			vo.setId(UUID);
			if(dimIaIpPortDynamic != null){
				//vo.setProdid(prodIdOld);//用于错误数据返回页面显示
				//vo.setProdName(prodNameOld);//用于错误数据返回页面显示
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
	 * 将缓存的集合插入库
	 */
	public void insertListCache(List<OperationFlow> list ,String tableName)throws Exception {
		Integer PL_NUMBERS_ONCE = DpiUtils.PL_NUMBERS_ONCE;
		//500条一组插入数据库
		for(int j=0;j<(list.size()/PL_NUMBERS_ONCE+1);j++){
			List<OperationFlow> list_true_insert = DpiUtils.getPlInsertDataOnce(list, j);
			if(list_true_insert.size()>0 && list_true_insert!=null){
				if("dim_ia_product_info".equals(tableName)){
					mapper.insertVoPl(list_true_insert);//产品表插入数据
				}else if("dim_ia_domain_rule".equals(tableName)){
					List<OperationFlow> domain_list=new ArrayList<OperationFlow>();
					for(OperationFlow obj:list_true_insert){
						DomainRule vo = (DomainRule)obj;
						if(StringUtils.isNotBlank(vo.getDomainCode())){
							domain_list.add(obj);
						}
					}
					if(domain_list.size()>0){
						domainRuleMapper.insertVoPl(domain_list);//域名表插入数据
					}

				}
				else if("dim_ia_ip_port_dynamic".equals(tableName)){
					dimIaIpPortDynamicMapper.insertVoPl(list_true_insert);//ip表插入数据
				}
			}
		}
	}
	
	
	/**
	 * 批量导出
	 */
	@Override
	public OperationFlowExportReturn doExport(OperationFlow o) throws Exception {
		ProductInfo vo = (ProductInfo) o;
		List<ProductInfo> list = this.mapper.selectList(vo);
		OperationFlowExportReturn ofer = new OperationFlowExportReturn();
		ofer.setExcelName("dim_ia_product_info.xlsx");
		ofer.setTxtName("dim_ia_product_info.txt");
		ofer.setList(list);
		return ofer;
	}
	
	/**
	 * 批量导出数据条数
	 */
	@Override
	public Integer exportDataNum(OperationFlow o,String type) throws Exception {
		DomainRule vo = (DomainRule) o;
		Integer num = 0;
		
		return num;
	}
	/**
	 * 将主键重复数据插入库中
	 * 1.type  1:舍弃，2：更新
	 * @param type
	 * @return
	 * @throws Exception
	 */
	public Boolean insertListFalseCacheDomain(String type)throws Exception {
		Boolean bl = false;
		//type  1:舍弃
		if("1".equals(type)){
			//1.正确的数据（在之前校验时候并没有插入库，和错误数据一起插入）
			/*if(list_true_cache_domainRule.size()>0 && list_true_cache_domainRule!=null){
				insertListCache(list_true_cache_domainRule);
			}*/
			if(list_true_cache_productInfo.size()>0 && list_true_cache_productInfo!=null){
			    for(int i=0;i<list_false_cache_domainRule.size();i++){
			    	DomainRule vo=(DomainRule) list_false_cache_domainRule.get(i);
			    	String name=vo.getProdName();
			    	for(int j=0;j<list_true_cache_productInfo.size();j++){
			    		ProductInfo prod = (ProductInfo) list_true_cache_productInfo.get(j);
			    		String prodName=prod.getProdName();
			    		
			    		prodName=DpiUtils.strDecrypt(prodName);
			    		if(name.equals(prodName)){
			    			list_true_cache_productInfo.remove(j);
			    		}
			    	}
			    	
			    }
				insertListCache(list_true_cache_productInfo,"dim_ia_product_info");
			}
			//数据完全没问题
			if(list_true_cache_domainRule.size()>0 && list_true_cache_domainRule!=null){
				
				insertListCache(list_true_cache_domainRule,"dim_ia_domain_rule");
			}
		}
		//type 2：更新
		else if("2".equals(type)){
			//2.1先删除库中的相同主键数据
			for (OperationFlow of : list_false_cache_domainRule){
				DomainRule vo = (DomainRule) of;
				String domainCode=vo.getDomainCode();
				//存到域名历史表
				mapper.saveVoByPrimaryKeyDomain(domainCode);
				mapper.deleteVoByPrimaryKeyDomain(domainCode);
				
			}
			
			//2.2.将重复的数据插入
			if(list_false_cache_domainRule.size()>0 && list_false_cache_domainRule!=null){
				
				insertListCache(list_false_cache_domainRule,"dim_ia_domain_rule");
			}
            
			//2.3.正确的数据（在之前校验时候并没有插入库，和错误数据一起插入）
			if(list_true_cache_domainRule.size()>0 && list_true_cache_domainRule!=null){
				insertListCache(list_true_cache_domainRule,"dim_ia_domain_rule");
			}
			if(list_true_cache_productInfo.size()>0 && list_true_cache_productInfo!=null){
				 /*for(int i=0;i<list_false_cache_domainRule.size();i++){
				    	DomainRule vo=(DomainRule) list_false_cache_domainRule.get(i);
				    	String name=vo.getProdName();
				    	for(int j=0;j<list_true_cache_productInfo.size();j++){
				    		ProductInfo prod = (ProductInfo) list_true_cache_productInfo.get(j);
				    		String prodName=prod.getProdName();
				    		
				    		prodName=DpiUtils.strDecrypt(prodName);
				    		if(name.equals(prodName)){
				    			list_true_cache_productInfo.remove(j);
				    		}
				    	}
				    	
				    }*/
				insertListCache(list_true_cache_productInfo,"dim_ia_product_info");
			}
		}
		bl = true;
		
		return bl;
	}
	public Boolean insertListFalseCacheIp(String type)throws Exception {
		Boolean bl = false;
		//type  1:舍弃
		if("1".equals(type)){
			//1.正确的数据（在之前校验时候并没有插入库，和错误数据一起插入）
			/*if(list_true_cache_domainRule.size()>0 && list_true_cache_domainRule!=null){
				insertListCache(list_true_cache_domainRule);
			}*/
			if(list_true_cache_productInfo.size()>0 && list_true_cache_productInfo!=null){
				 for(int i=0;i<list_false_cache_ipPort.size();i++){
					 DimIaIpPortDynamic vo=(DimIaIpPortDynamic) list_false_cache_ipPort.get(i);
				    	String name=vo.getProdName();
				    	for(int j=0;j<list_true_cache_productInfo.size();j++){
				    		ProductInfo prod = (ProductInfo) list_true_cache_productInfo.get(j);
				    		String prodName=prod.getProdName();
				    		
				    		prodName=DpiUtils.strDecrypt(prodName);
				    		if(name.equals(prodName)){
				    			list_true_cache_productInfo.remove(j);
				    		}
				    	}
				    	
				    }
				insertListCache(list_true_cache_productInfo,"dim_ia_product_info");
			}
			if(list_true_cache_ipPort.size()>0 && list_true_cache_ipPort!=null){
				//ofir.setFalseType("2");//数据完全没问题
				insertListCache(list_true_cache_ipPort,"dim_ia_ip_port_dynamic");
			}
		}
		//type 2：更新
		else if("2".equals(type)){
			//2.1先删除库中的相同主键数据
			for (OperationFlow of : list_false_cache_ipPort){
				DimIaIpPortDynamic vo = (DimIaIpPortDynamic) of;
				String ipPort=vo.getIpPort();
				//存到域名历史表
				mapper.saveVoByPrimaryKeyIp(ipPort);
				mapper.deleteVoByPrimaryKeyIp(ipPort);
				
			}
			
			//2.2.将重复的数据插入
			if(list_false_cache_ipPort.size()>0 && list_false_cache_ipPort!=null){
				
				insertListCache(list_false_cache_ipPort,"dim_ia_ip_port_dynamic");
			}
            
			//2.3.正确的数据（在之前校验时候并没有插入库，和错误数据一起插入）
			if(list_true_cache_ipPort.size()>0 && list_true_cache_ipPort!=null){
				insertListCache(list_true_cache_ipPort,"dim_ia_ip_port_dynamic");
			}
			if(list_true_cache_productInfo.size()>0 && list_true_cache_productInfo!=null){
				 /*for(int i=0;i<list_false_cache_ipPort.size();i++){
					 DimIaIpPortDynamic vo=(DimIaIpPortDynamic) list_false_cache_ipPort.get(i);
				    	String name=vo.getProdName();
				    	for(int j=0;j<list_true_cache_productInfo.size();j++){
				    		ProductInfo prod = (ProductInfo) list_true_cache_productInfo.get(j);
				    		String prodName=prod.getProdName();
				    		
				    		prodName=DpiUtils.strDecrypt(prodName);
				    		if(name.equals(prodName)){
				    			list_true_cache_productInfo.remove(j);
				    		}
				    	}
				    	
				    }*/
				insertListCache(list_true_cache_productInfo,"dim_ia_product_info");
			}
		}
		bl = true;
		
		return bl;
	}
	/**
	 * 将缓存的集合插入库
	 */
	/*public void insertListCache(List<OperationFlow> list )throws Exception {
		Integer PL_NUMBERS_ONCE = DpiUtils.PL_NUMBERS_ONCE;
		//500条一组插入数据库
		for(int j=0;j<(list.size()/PL_NUMBERS_ONCE+1);j++){
			List<OperationFlow> list_true_insert = DpiUtils.getPlInsertDataOnce(list, j);
			if(list_true_insert.size()>0 && list_true_insert!=null){
				mapper.insertVoPl(list_true_insert);
			}
		}
	}*/
}
