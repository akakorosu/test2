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
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bonc.common.des.DesUtil;
import com.bonc.common.utils.UUIDUtil;
import com.bonc.dpi.dao.entity.KeywordSearchRule;
import com.bonc.dpi.dao.entity.OperationFlow;
import com.bonc.dpi.dao.entity.OperationFlowExportReturn;
import com.bonc.dpi.dao.entity.OperationFlowImportReturn;
import com.bonc.dpi.dao.entity.ProductInfo;
import com.bonc.dpi.dao.entity.ProductLabel;
import com.bonc.dpi.dao.mapper.ProductInfoMapper;
import com.bonc.dpi.service.i.OperationFlowServiceI;
import com.bonc.dpi.utils.DpiUtils;
import com.bonc.login.action.LoginAction;
import com.bonc.system.dao.entity.SysUser;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;

/**
 * 产品表
 * dim_ia_product_info
 * @author BONC-XUXL
 *
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class ProductInfoService implements OperationFlowServiceI{
	
	
	@Resource
//	@Autowired
	private ProductInfoMapper mapper;
	
	@Resource
	private ProductLabelService productLabelService;
	
	private static SysUser user ;

	private static SimpleDateFormat sdf = DpiUtils.sdf;
	
	public static HashSet<String> hashSetInExcle_primaryKey = new HashSet<String>();//excle中的主键

	public static List<OperationFlow> invalidDataInExcle_repeatPrimaryKey = new ArrayList<OperationFlow>(); //excle中无效的数据--主键重复
	public static List<OperationFlow> invalidDataInExcle_empty = new ArrayList<OperationFlow>(); //excle中无效的数据--空值
	public static List<OperationFlow> invalidDataInExcle_tooLong = new ArrayList<OperationFlow>(); //excle中无效的数据--过长
	public static List<OperationFlow> invalidDataInExcle_notTrue_prodLable = new ArrayList<OperationFlow>(); //excle中无效的数据--关联表不真实存在
	
	public static List<OperationFlow> list_false_cache = new ArrayList<OperationFlow>(); //excle中在数据库中重复的缓存数据
	public static List<OperationFlow> list_true_cache = new ArrayList<OperationFlow>(); //excle中正确的缓存数据

	
	/**
	 * 分页查询
	 * @param
	 * @param page
	 * @param row
	 * @return
	 */
	public Page<ProductInfo> selectList(ProductInfo vo, Integer page, Integer row) {
		PageHelper.startPage(page, row);
		Page<ProductInfo> pageList = (Page<ProductInfo>) this.mapper.selectList(vo);
		
		
//		//加密
//		ProductInfo pi = new ProductInfo();
//		List<ProductInfo> ProductInfoListInDataBase= mapper.selectList(pi);
//		for (ProductInfo productInfo : ProductInfoListInDataBase) {
//			//查询条件处理prod_name
//			String prodName = productInfo.getProdName();
//			if(prodName!=null && !"".equals(prodName)){
//				String prodName_res = "";
//				//将字符换分词
//				for(int i=0;i<prodName.length();i++){
//					char str =prodName.charAt(i);//单个的字符
//					String s = String.valueOf(str);
//					s = DesUtil.encrypt(s);//加密
//					prodName_res = prodName_res+DpiUtils.PROD_NAME_SPLIT+s;
//				}
//				prodName_res = prodName_res.substring(1);//截去首字母~
//				productInfo.setProdName(prodName_res);
//				mapper.updateVo(productInfo);//单条加密
//			}
//		}
		
		//解密(查询productLabel)
		for(int i=0;i<pageList.size();i++){
			//解密
			ProductInfo pi= pageList.get(i);
			doDec(pi);
			
			//查询productLabel
			String labelCode = pi.getProdLabelCode();
			ProductLabel pl = productLabelService.selectVoByLabelCode(labelCode);
			if(pl!=null){
				String labelName1 = pl.getLabelName1();
				String labelName2 = pl.getLabelName2();
				pi.setLabelName1(labelName1);
				pi.setLabelName2(labelName2);
			}
		}
		return pageList;
	}

	private void doDec(ProductInfo pi) {
		String prodName_res = "";
		String prodName = pi.getProdName();
		String[] prodName_array = prodName.split(DpiUtils.PROD_NAME_SPLIT);
		for(int j=0;j<prodName_array.length;j++){
			String prodName_decrypt = DesUtil.decrypt(prodName_array[j]);//解密
			prodName_res = prodName_res+prodName_decrypt;
		}
		pi.setProdName(prodName_res);
		String iosProdName = pi.getIosProdName();
		if(!StringUtils.isEmpty(iosProdName)){
			String prodName_res1 = "";
			String[] iosProdName_array = iosProdName.split(DpiUtils.PROD_NAME_SPLIT);
			for(int j=0;j<iosProdName_array.length;j++){
				String prodName_decrypt = DesUtil.decrypt(iosProdName_array[j]);//解密
				prodName_res1 = prodName_res1+prodName_decrypt;
			}
			pi.setIosProdName(prodName_res1);
			
		}
	}
	
	/**
	 * 产品名称加密(弃用)
	 * @param pageList
	 */
	public void prodName_jm(Page<ProductInfo> pageList){
		
		List<ProductInfo> list = new ArrayList<ProductInfo>();
		//加密
		ProductInfo pi = new ProductInfo();
		List<ProductInfo> ProductInfoListInDataBase= mapper.selectList(pi);
		for (ProductInfo productInfo : ProductInfoListInDataBase) {
			//查询条件处理prod_name
			String prodName = productInfo.getProdName();
			if(prodName!=null && !"".equals(prodName)){
				String prodName_res = "";
				//将字符换分词
				for(int i=0;i<prodName.length();i++){
					char str =prodName.charAt(i);//单个的字符
					String s = String.valueOf(str);
					s = DesUtil.encrypt(s);//加密
					prodName_res = prodName_res+DpiUtils.PROD_NAME_SPLIT+s;
				}
				prodName_res = prodName_res.substring(1);//截去首字母~
				productInfo.setProdName(prodName_res);
				list.add(productInfo);//批量加密list
//				mapper.updateVo(productInfo);//单条加密
			}
		}
		Integer PL_NUMBERS_ONCE = 100;//每一百行加密
		//500条一组插入数据库
		for(int j=0;j<(list.size()/PL_NUMBERS_ONCE+1);j++){
			if(list.size()>0 && list!=null){
				//500条一组插入数据库
				List<ProductInfo> list_true_insert = new ArrayList<ProductInfo>(); //要插入的500条数
				list_true_insert.clear();
				if(j ==list.size()/PL_NUMBERS_ONCE){
					for(int i=(j*PL_NUMBERS_ONCE);i<(list.size());i++){
						list_true_insert.add(list.get(i));
					}
				}else{
					for(int i=(j*PL_NUMBERS_ONCE);i<((j+1)*PL_NUMBERS_ONCE);i++){
						list_true_insert.add(list.get(i));
					}
				}
				mapper.updateVoPl(list_true_insert);//批量加密
			}
		}
	}
	
	/**
	 * 查询(根据主键)
	 * @param
	 * @return
	 */
	public ProductInfo selectVoByPrimaryKey(ProductInfo voParam){
		return mapper.selectVoByPrimaryKey(voParam);
	}
	public List<ProductInfo> selectRuleList(ProductInfo voParam){
		return mapper.selectRuleList(voParam);
	}
	public Object updateUrlPath(ProductInfo voParam){
		return mapper.updateUrlPath(voParam);
	}
	public List<ProductInfo> getAllProd(){
		return mapper.getAllProd();
	}
	public Integer delAllProd(){
		return mapper.delAllProd();
	}
	public Object updateUrlParameter(ProductInfo voParam){
		return mapper.updateUrlParameter(voParam);
	}
	public Object updateUrlFuzzyRule(ProductInfo voParam){
		return mapper.updateUrlFuzzyRule(voParam);
	}
	public Object updateDomainRule(ProductInfo voParam){
		return mapper.updateDomainRule(voParam);
	}
	public Object updateIpPort(ProductInfo voParam){
		return mapper.updateIpPort(voParam);
	}
	public Object updateGroupClass(ProductInfo voParam){
		return mapper.updateGroupClass(voParam);
	}
	public Object updateUseragentKey(ProductInfo voParam){
		return mapper.updateUseragentKey(voParam);
	}
	public Object updateUseragentRule(ProductInfo voParam){
		return mapper.updateUseragentRule(voParam);
	}
	public Object updateDimIaKeywordSearchRule(ProductInfo voParam){
		return mapper.updateDimIaKeywordSearchRule(voParam);
	}
	
	/**
	 * 删除(根据主键)
	 * @param
	 * @return
	 */
	public Boolean deleteVoByPrimaryKey(ProductInfo voParam){
		return mapper.deleteVoByPrimaryKey(voParam);
	}
	public Boolean deleteVoByPrimaryKeyJm(ProductInfo voParam){
		return mapper.deleteVoByPrimaryKeyJm(voParam);
	}
	public Boolean deleteVoByUrlPath(ProductInfo voParam){
		return mapper.deleteVoByUrlPath(voParam);
	}
	public Boolean deleteVoByUrlParameter(ProductInfo voParam){
		return mapper.deleteVoByUrlParameter(voParam);
	}
	public Boolean deleteVoByFuzzyRule(ProductInfo voParam){
		return mapper.deleteVoByFuzzyRule(voParam);
	}
	public Boolean deleteVoByDomainRule(ProductInfo voParam){
		return mapper.deleteVoByDomainRule(voParam);
	}
	public Boolean deleteVoByIpPort(ProductInfo voParam){
		return mapper.deleteVoByIpPort(voParam);
	}
	public Boolean deleteVoByGroupClass(ProductInfo voParam){
		return mapper.deleteVoByGroupClass(voParam);
	}
	public Boolean deleteVoByUseragentKey(ProductInfo voParam){
		return mapper.deleteVoByUseragentKey(voParam);
	}
	
	public Boolean deleteVoByUseragentRule(ProductInfo voParam){
		return mapper.deleteVoByUseragentRule(voParam);
	}
	
	
	
	/**
	 * 添加
	 * @param vo
	 */
	public Boolean insertVo(ProductInfo vo){
		return mapper.insertVo(vo);
	}
	public Boolean insertVoJm(ProductInfo vo){
		return mapper.insertVoJm(vo);
	}
	
	/**
	 * 修改
	 * @param vo
	 * @return 
	 */
	public Boolean updateVo(ProductInfo vo){
		return mapper.updateVo(vo);
	}
	public Boolean updateVoJm(ProductInfo vo){
		return mapper.updateVoJm(vo);
	}
	
	/**
	 * 批量修改
	 * @param list
	 * @return
	 */
	public Boolean updateVoPl(List<ProductInfo> list){
		Boolean bl = mapper.updateVoPl(list);
		return bl;
	}
	
	
	/**
	 * 去重过滤
	 * @param vo
	 * @return
	 */
	public Boolean selectCheck(ProductInfo vo) {
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
	public Boolean prodIdCheck(ProductInfo vo){
		Integer i = this.mapper.prodIdCheck(vo);
		if(i > 0) {
			return true;
		}
		return false;
	}
	
	/**
	 * 根据产品Id模糊查询
	 * @param
	 * @return
	 */
	public List<ProductInfo> getProdIdListByProdId(String prodId)throws Exception {
		return mapper.getProdIdListByProdId(prodId);
	}
	
	/**
	 * 根据产品名称模糊查询
	 * @param prodName
	 * @return
	 */
	public List<ProductInfo> getProdIdListByProdName(String prodName)throws Exception {
		return mapper.getProdIdListByProdName(prodName);
	}
	
	/**
	 * 获取产品id最大的
	 * @param prodId_head :例如prodId='W12233' 则prodId_head='W'
	 * @return
	 */
	public String getProdId_Max(String prodId_head)throws Exception {
		return mapper.getProdId_Max(prodId_head);
	}
	

	
	/**
	 * 批量导入
	 */
	@Override
	public OperationFlowImportReturn doImport(List<OperationFlow> list,String type) throws Exception {
		
		clearCache();//清空缓存
		
		OperationFlowImportReturn ofir = new OperationFlowImportReturn();//提示语对象
		ofir.setFalseType("1");//默认有问题
		ofir.setActionUrl(DpiUtils.url_falseDataViewFromExcle_productInfo);//excle批量导入问题数据页面反回路径
		
		Boolean bl_excle_self = true;
		
		//1.检查excle中自身数据有效性
		dataValidityInExcle(list);
		//excle中产品id不在产品表中的数据条数
		if(invalidDataInExcle_notTrue_prodLable.size()>0){
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
			Map<String,List<OperationFlow>> map = filterList_CompareDataBase(list,type);
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
			//将没有问题的数据插入到数据库，有问题的数据返回到前台
			/*if(true){
				//数据完全没问题
				if(list_true.size()>0 && list_true!=null){
					
					if(bl_compareToDataBase){
						ofir.setFalseType("2");//数据完全没问题
					}
					insertListCache(list_true);
				}
			}*/
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
		invalidDataInExcle_notTrue_prodLable.clear();//清空缓存
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
			Boolean bl_notTrue_prodLable = true;//数据有效--关联表不真实存在
			
			ProductInfo vo = (ProductInfo) of;
			String id = vo.getId();
			String prodId =vo.getProdId();//prod_id,产品ID
			String prodName =vo.getProdName();//prod_name,产品名称
			String prodLabelCode =vo.getProdLabelCode();//prod_label_code,标签ID
			String addLabelList =vo.getAddLabelList();//add_label_list,附属标签列表
			String fatherProdId =vo.getFatherProdId();//father_prod_id,父产品ID
			String prodTypeId =vo.getProdTypeId();//prod_type_id,产品归类ID 同产品ID
			String prodTypeName =vo.getProdTypeName();//prod_type_name,产品归类名称  默认归类为产品名称 建立产品间的关系 归属、关联性，默认归类为自己，有特殊需求的手工处理，如果微信公众平台、微信支付归类应该都为微信
			String prodCatagory =vo.getProdCatagory();//prod_catagory,产品类别
			String isMainProd =vo.getIsMainProd();//is_main_prod,是否主产品
			String isApp =vo.getIsApp();//is_app,是否APP
			String isOperator =vo.getIsOperator();//is_operator,是否运营商自有业务：0-非运营商 1-移动自有 2-联通自有 3-电信自有
			String spId =vo.getSpId();//sp_id,spid
			String spName =vo.getSpName();//sp_name,spname
			String shortName =vo.getShortName();//short_name,公司简称
			String labelCheck =vo.getLabelCheck();//label_check，标签是否稽核
			String author = vo.getAuthor();//作者
			//String opTime = vo.getOpTime();//操作时间
			
			//非空校验
//			if("".equals(prodId) || prodId==null){
//				bl_empty = false;
//			}
			if("".equals(prodName) || prodName==null){
				bl_empty = false;
			}
			if("".equals(prodLabelCode) || prodLabelCode==null){
				bl_empty = false;
			}
			if("".equals(isOperator) || isOperator==null){
				bl_empty = false;
			}
			if("".equals(labelCheck) || labelCheck==null){
				bl_empty = false;
			}
			//长度验证
			if(prodId.length()>20){
				bl_tooLong = false;
			}
			if(prodName.length()>2000){
				bl_tooLong = false;
			}
			if(prodLabelCode.length()>50){
				bl_tooLong = false;
			}
			if(addLabelList.length()>400){
				bl_tooLong = false;
			}
			if(fatherProdId.length()>400){
				bl_tooLong = false;
			}
			if(prodTypeId.length()>20){
				bl_tooLong = false;
			}
			if(prodTypeName.length()>400){
				bl_tooLong = false;
			}
			if(prodCatagory.length()>401){
				bl_tooLong = false;
			}
			if(isMainProd.length()>5){
				bl_tooLong = false;
			}
			if(isApp.length()>5){
				bl_tooLong = false;
			}
			if(isOperator.length()>1){
				bl_tooLong = false;
			}
			if(spId.length()>10){
				bl_tooLong = false;
			}
			if(spName.length()>400){
				bl_tooLong = false;
			}
			if(shortName.length()>400){
				bl_tooLong = false;
			}			
			if(author.length()>400){
				bl_tooLong = false;
			}
			/*if(opTime.length()>400){
				bl_tooLong = false;
			}*/
			
			//产品标签逻辑验证
			ProductLabel productLabel = new ProductLabel();
			
			if(!bl_empty){
				invalidDataInExcle_empty.add(of);
			}
			if(!bl_tooLong){
				invalidDataInExcle_tooLong.add(of);
			}
			if(!bl_notTrue_prodLable){
				invalidDataInExcle_notTrue_prodLable.add(of);
			}
			
			//主键没有重复则插入缓存中
			if(!hashSetInExcle_primaryKey.contains(prodName)){
				hashSetInExcle_primaryKey.add(prodName);
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
	public Map<String,List<OperationFlow>> filterList_CompareDataBase(List<OperationFlow> list,String type) throws Exception {
		
		user = LoginAction.getLoginUser();
		List<OperationFlow> list_true = new ArrayList<OperationFlow>(); //有效数据
		List<OperationFlow> list_false = new ArrayList<OperationFlow>(); //无效数
		Map<String,List<OperationFlow>> map = new HashMap<String,List<OperationFlow>>();
		String prodId_max = mapper.getProdId_Max(DpiUtils.PRODID_HEAD);//PRODID_HEAD是产品ID首字母
		for (OperationFlow operationFlow : list) {
			ProductInfo vo = (ProductInfo) operationFlow;
			String prodName = vo.getProdName();//产品名称
			ProductInfo voParam = new ProductInfo();
			if("1".equals(type)){
				voParam.setProdName(DpiUtils.strEncrypt(prodName));//加密
			}else if("2".equals(type)){
				voParam.setProdName(prodName);//加密
			}
			ProductInfo productInfo = mapper.selectVoByPrimaryKey(voParam);//根据产品名称查找
			
			if("1".equals(type)){
				//补全信息
				String prodIdNew = DpiUtils.getProdId_next(prodId_max);//自动生成新产品ID
				prodId_max = prodIdNew;//将最大值赋值给新的
				vo.setProdId(prodIdNew);
				vo.setProdName(DpiUtils.strEncrypt(vo.getProdName()));//加密
				
				String isMainProd = vo.getIsMainProd();//is_main_prod,是否主产品
				String isApp = vo.getIsApp();//is_app,是否APP
				if(!"".equals(isMainProd) && isMainProd!=null){
					vo.setIsMainProd("1");//是否主产品--默认值
				}
				if(!"".equals(isApp) && isApp!=null){
					vo.setIsApp("1");//是否APP--默认值
				}
//				vo.setAuthor(user.getLoginId());
//				vo.setOpTime(DpiUtils.sdf.format(new Date()));//设置时间
//				String UUID = UUIDUtil.createUUID();//uuid
//				vo.setId(UUID);
			}else if("2".equals(type)){
			}

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


			if(productInfo != null){
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
				ProductInfo vo = (ProductInfo) of;
				String prodName = vo.getProdName();
				ProductInfo voParam = new ProductInfo();//参数
				voParam.setProdName(DpiUtils.strEncrypt(prodName));
				mapper.deleteVoByPrimaryKey(voParam);
				vo.setProdName(DpiUtils.strEncrypt(prodName));
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
		/*for(int j=0;j<(list.size()/PL_NUMBERS_ONCE+1);j++){
			List<OperationFlow> list_true_insert = DpiUtils.getPlInsertDataOnce(list, j);
			if(list_true_insert.size()>0 && list_true_insert!=null){
				
				for(OperationFlow a:list_true_insert){
					ProductInfo vo = (ProductInfo) a;
					vo.setProdName(DpiUtils.strDecrypt(vo.getProdName()));//strDecrypt
				}
				
				mapper.insertVoPlJm(list_true_insert);
			}
		}*/
	}
	public void insertListCacheJm(List<ProductInfo> list )throws Exception {
		Integer PL_NUMBERS_ONCE = DpiUtils.PL_NUMBERS_ONCE;
		//1000条一组插入数据库
		//int a=1;
		for(int j=0;j<(list.size()/PL_NUMBERS_ONCE+1);j++){
			List<ProductInfo> list_true_insert = DpiUtils.getPlInsertDataOnceJm(list, j);
			if(list_true_insert.size()>0 && list_true_insert!=null){
				//if(a==1){
					//list_true_insert=list_true_insert.subList(0, 2);
					mapper.insertVoPlJm(list_true_insert);
				//}
				//a++;
				
			}
		}
		/*for(int i=0;i<list.size();i++){
			
			mapper.insertVoPlJmByone(list.get(i));
			
			
		}*/
		
	}
		
	
	/**
	 * 批量导出
	 */
	@Override
	public OperationFlowExportReturn doExport(OperationFlow o) throws Exception {
		ProductInfo vo = (ProductInfo) o;
		if(StringUtils.isNotBlank(vo.getOpTime())) {
			String[] arr=vo.getOpTime().split("-");
			vo.setStartTime(arr[0].trim());
			vo.setEndTime(arr[1].trim());
		}
		List<ProductInfo> list = this.mapper.selectList(vo);
		//产品之间有~线时，将密文变成明文
		/*if(vo.getProdName().contains("|")&&vo.getExportTypeProdExcel()=="1"){
		for(ProductInfo item:list){
			this.doDec(item);
		}
		}*/
		if(vo.getExportTypeProdExcel()=="1"){
			for(ProductInfo item:list){
				//item.setIosProdName(DesUtil.decrypt(item.getIosProdName()));
				this.doDec(item);
			}
		}
		
		OperationFlowExportReturn ofer = new OperationFlowExportReturn();
		ofer.setExcelName("dim_ia_product_info.xlsx");
		ofer.setTxtName("dim_ia_product_info.txt");
		ofer.setList(list);
		return ofer;
	}

	public List<ProductInfo> selectList(ProductInfo vo) {
		List<ProductInfo> list = this.mapper.selectList(vo);
		for (ProductInfo productInfo : list) {
			this.doDec(productInfo);
		}
		return list;
	}
	
	/**
	 * 批量导出数据条数
	 */
	@Override
	public Integer exportDataNum(OperationFlow o,String type) throws Exception {
		ProductInfo vo = (ProductInfo) o;
		if(StringUtils.isNotBlank(vo.getOpTime())) {
			String[] arr=vo.getOpTime().split("-");
			vo.setStartTime(arr[0].trim());
			vo.setEndTime(arr[1].trim());
		}
		Integer num = this.mapper.selectDataNum(vo);
		
		return num;
	}
}
