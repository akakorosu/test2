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

import com.bonc.common.utils.UUIDUtil;
import com.bonc.dpi.dao.entity.DomainRule;
import com.bonc.dpi.dao.entity.OperationFlow;
import com.bonc.dpi.dao.entity.OperationFlowExportReturn;
import com.bonc.dpi.dao.entity.OperationFlowImportReturn;
import com.bonc.dpi.dao.entity.ProductInfo;
import com.bonc.dpi.dao.mapper.DomainRuleMapper;
import com.bonc.dpi.service.i.OperationFlowServiceI;
import com.bonc.dpi.utils.DpiUtils;
import com.bonc.login.action.LoginAction;
import com.bonc.system.dao.entity.SysUser;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;

@Service
@Transactional(rollbackFor = Exception.class)
public class DomainRuleService implements OperationFlowServiceI{
	
	@Resource
//	@Autowired
	private DomainRuleMapper mapper;
	
	@Resource
	private ProductInfoService piService;
	
	private static SimpleDateFormat sdf = DpiUtils.sdf;
	private static SysUser user ;
	
	public static HashSet<String> hashSetInDataBase_primaryKey = new HashSet<String>();//数据库中的主键
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
	public Page<DomainRule> selectList(DomainRule ksr, Integer page, Integer row) {
		PageHelper.startPage(page, row);
		Page<DomainRule> pageList = (Page<DomainRule>) this.mapper.selectList(ksr);
		for (DomainRule domainRule : pageList) {
			ProductInfo piParam = new ProductInfo();
			String prodId = domainRule.getProdId();
			piParam.setProdId(prodId);
			ProductInfo voPi = piService.selectVoByPrimaryKey(piParam);
			if(voPi!=null){
				domainRule.setProdName(DpiUtils.strDecrypt(voPi.getProdName()));
			}
		}
		return pageList;
	}
	
	/**
	 * 查询VO(根据主键)
	 * @param
	 * @return
	 */
	public DomainRule selectVoByPrimaryKey(DomainRule voParam){
		DomainRule vo = mapper.selectVoByPrimaryKey(voParam);
		return vo;
	}
	
	/**
	 * 删除VO(根据主键)
	 * @param
	 * @return
	 */
	public Boolean deleteVoByPrimaryKey(DomainRule voParam){
		return mapper.deleteVoByPrimaryKey(voParam);
	}
	
	
	/**
	 * 添加
	 * @param
	 */
	public Boolean insertVo(DomainRule voParam){
		return mapper.insertVo(voParam);
	}
	
	/**
	 * 修改
	 * @param
	 * @return 
	 */
	public Boolean updateVo(DomainRule voParam){
		return mapper.updateVo(voParam);
	}
	
	
	/**
	 * 批量导入
	 */
	@Override
	public OperationFlowImportReturn doImport(List<OperationFlow> list,String type) throws Exception {
		
		clearCache();//清空缓存
		
		OperationFlowImportReturn ofir = new OperationFlowImportReturn();//提示语对象
		ofir.setFalseType("1");//默认有问题
		ofir.setActionUrl(DpiUtils.url_falseDataViewFromExcle_domainRule);//excle批量导入问题数据页面反回路径
		
		Boolean bl_excle_self = true;//excle自身问题
		
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
//		invalidDataInExcle_repeatPrimaryKey = DpiUtils.checkRepeatInExcleDomainRule(list);//2检查excle中主键是否有重复数据
		//excle中主键是否有重复数据
		if(invalidDataInExcle_repeatPrimaryKey.size()>0){
			bl_excle_self = false;
		}
		
		//2.检查与对比数据库中是否有重复（如果excle中自身没问题了）
		if(bl_excle_self){
			Boolean bl_compareToDataBase = true;//与数据库比较标识
//			DomainRule dr = new DomainRule();
//			List<DomainRule> domainRuleListInDataBase= mapper.selectList(dr);//批量入库前库中的数据
//			for (DomainRule vo : domainRuleListInDataBase) {
//				String domainCode = vo.getDomainCode();//域名编码
//				hashSetInDataBase_primaryKey.add(domainCode);//主键校验存入upHashSet中
//			}
//			Map<String,List<OperationFlow>> map = DpiUtils.filterListDomainRule(list,hashSetInDataBase_primaryKey);//数据过滤
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
					insertListCache(list_true);   //一会恢复过来
					
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
		
//		hashSetInDataBase_primaryKey.clear();//清空缓存
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
			
			DomainRule vo = (DomainRule) of;
			String domainCode=vo.getDomainCode(); //域名编码
			String prodId=vo.getProdId();//产品ID
			String author=vo.getAuthor();//操作人
			String opTime=vo.getOpTime(); //操作时间
			
			//非空校验
			if("".equals(domainCode) || domainCode==null){
				bl_empty = false;
			}
			if("".equals(prodId) || prodId==null){
				bl_empty = false;
			}
			//长度验证
			if(domainCode.length()>200){
				bl_tooLong = false;
			}
			if(prodId.length()>100){
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
			
			
			//没有重复则插入缓存中
			if(!hashSetInExcle_primaryKey.contains(domainCode)){
				hashSetInExcle_primaryKey.add(domainCode);
			}
			else{
				invalidDataInExcle_repeatPrimaryKey.add(of);
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
			DomainRule vo = (DomainRule) operationFlow;
			DomainRule voParam = new DomainRule();
			voParam.setDomainCode(vo.getDomainCode());
			DomainRule domainRule= mapper.selectVoByPrimaryKey(voParam);//根据主键查找
			
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

//			vo.setAuthor(user.getLoginId());
//			vo.setOpTime(sdf.format(new Date()));//设置时间
//			String UUID = UUIDUtil.createUUID();//
//			vo.setId(UUID);
			if(domainRule != null){
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
			for (OperationFlow of : list_false_cache) {
				DomainRule vo = (DomainRule) of;
				String domainCode = vo.getDomainCode();
				DomainRule voParam = new DomainRule();
				voParam.setDomainCode(domainCode);
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
		DomainRule vo = (DomainRule) o;
		if(StringUtils.isNotBlank(vo.getOpTime())) {
			String[] arr=vo.getOpTime().split("-");
			vo.setStartTime(arr[0].trim());
			vo.setEndTime(arr[1].trim());
		}
		List<DomainRule> list = this.mapper.selectList(vo);
		OperationFlowExportReturn ofer = new OperationFlowExportReturn();
		ofer.setExcelName("dim_ia_domain_rule.xlsx");
		ofer.setTxtName("dim_ia_domain_rule_all.txt");
		ofer.setNewTxtName("dim_ia_domain_rule.txt");
		ofer.setList(list);
		return ofer;
	}
	
	/**
	 * 批量导出数据条数
	 */
	@Override
	public Integer exportDataNum(OperationFlow o,String type) throws Exception {
		DomainRule vo = (DomainRule) o;
		if(StringUtils.isNotBlank(vo.getOpTime())) {
			String[] arr=vo.getOpTime().split("-");
			vo.setStartTime(arr[0].trim());
			vo.setEndTime(arr[1].trim());
		}
		Integer num = this.mapper.selectDataNum(vo);
		
		return num;
	}
}
