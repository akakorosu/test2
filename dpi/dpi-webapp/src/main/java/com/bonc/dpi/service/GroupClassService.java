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
import com.bonc.dpi.dao.entity.ContentLabel;
import com.bonc.dpi.dao.entity.DimIaIpPortDynamic;
import com.bonc.dpi.dao.entity.DomainFuzzyRule;
import com.bonc.dpi.dao.entity.DomainRule;
import com.bonc.dpi.dao.entity.GroupClass;
import com.bonc.dpi.dao.entity.OperationFlow;
import com.bonc.dpi.dao.entity.OperationFlowExportReturn;
import com.bonc.dpi.dao.entity.OperationFlowImportReturn;
import com.bonc.dpi.dao.entity.ProductInfo;
import com.bonc.dpi.dao.mapper.GroupClassMapper;
import com.bonc.dpi.service.i.OperationFlowServiceI;
import com.bonc.dpi.utils.DpiUtils;
import com.bonc.login.action.LoginAction;
import com.bonc.system.dao.entity.SysUser;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;

@Service
@Transactional(rollbackFor = Exception.class)
public class GroupClassService implements OperationFlowServiceI {

	@Resource
	// @Autowired
	private GroupClassMapper mapper;
	private static HashSet<String> hashSetInDataBase = new HashSet<String>();// 数据库中的数据
	
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
	 * 
	 * @param
	 * @param page
	 * @param row
	 * @return
	 */
	public Page<GroupClass> selectList(GroupClass vo, Integer page, Integer row) {
		PageHelper.startPage(page, row);
		Page<GroupClass> pageList = (Page<GroupClass>) this.mapper.selectList(vo);
		if(StringUtils.isBlank(vo.getProdName())) {
			for (GroupClass group : pageList) {
				ProductInfo piParam = new ProductInfo();
				String prodid = group.getProdid();
				piParam.setProdId(prodid);
				ProductInfo voPi = piService.selectVoByPrimaryKey(piParam);
				if(voPi!=null){
					group.setProdName(DpiUtils.strDecrypt(voPi.getProdName()));
				}
			}
		}else {
			for (GroupClass group : pageList) {				
				group.setProdName(DpiUtils.strDecrypt(group.getProdName()));
			}
		}
		
		return pageList;
	}

	/**
	 * 根据id查询对象
	 * 
	 * @param id
	 * @return
	 */
	public GroupClass selectVoById(String id) {
		GroupClass gc=mapper.selectVoById(id);
		gc.setProdName(DpiUtils.strDecrypt(gc.getProdName()));
		return gc;
	}

	/**
	 * 根据id查询对象
	 * 
	 * @param id
	 * @return
	 */
	public GroupClass selectVoByIdWithProdName(String id) {
		return mapper.selectVoByIdWithProdName(id);
	}

	/**
	 * 添加
	 * 
	 * @param vo
	 */
	public GroupClass insertVo(GroupClass vo) {
		Boolean bl = mapper.insertVo(vo);
		return vo;
	}

	/**
	 * 修改
	 * 
	 * @param vo
	 * @return
	 */
	public GroupClass updateVo(GroupClass vo) {
		Boolean bl = mapper.updateVo(vo);
		return vo;
	}

	/**
	 * 验证数据的唯一性
	 * 
	 * @param vo
	 * @return
	 */
	public int checkUnique(GroupClass vo) {

		return mapper.checkUnique(vo);
	}

	/**
	 * 删除
	 * 
	 * @param id
	 */
	public Boolean deleteVoById(String id) {
		Boolean bl = mapper.deleteVoById(id);
		return bl;
	}

	/**
	 * 去重过滤
	 * 
	 * @param vo
	 * @return
	 */
	public Boolean selectCheck(GroupClass vo) {
		Integer i = this.mapper.selectCheck(vo);
		if(i > 0) {
			return false;
		}
		return true;
	}

	/**
	 * 批量导入
	 */
	@Override
	public OperationFlowImportReturn doImport(List<OperationFlow> list,String type) throws Exception {
		
		clearCache();//清空缓存
		
		OperationFlowImportReturn ofir = new OperationFlowImportReturn();//提示语对象
		ofir.setFalseType("1");//默认有问题
		ofir.setActionUrl(DpiUtils.url_falseDataViewFromExcle_groupClass);//excle批量导入问题数据页面反回路径
		
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
			
			GroupClass vo = (GroupClass) of;
			String classCode=vo.getClassCode();
			String className=vo.getClassName();
			String subClassCode=vo.getSubClassCode();
			String subClassName=vo.getSubClassName();
			String prodid=vo.getProdid();
			String author = vo.getAuthor();//作者
			String opTime = vo.getOpTime();//操作时间
			
			//非空校验
			if("".equals(classCode) || classCode==null){
				bl_empty = false;
			}
			if("".equals(className) || className==null){
				bl_empty = false;
			}
			if("".equals(subClassCode) || subClassCode==null){
				bl_empty = false;
			}
			if("".equals(subClassName) || subClassName==null){
				bl_empty = false;
			}
			if("".equals(prodid) || prodid==null){
				bl_empty = false;
			}
			//长度验证
			if(classCode.length()>10){
				bl_tooLong = false;
			}
			if(className.length()>100){
				bl_tooLong = false;
			}
			if(subClassCode.length()>10){
				bl_tooLong = false;
			}
			if(subClassName.length()>100){
				bl_tooLong = false;
			}	
			if(prodid.length()>100){
				bl_tooLong = false;
			}
			
			//产品id逻辑验证
			ProductInfo pi = new ProductInfo();//new条件
			pi.setProdId(prodid);
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
			if(!hashSetInExcle_primaryKey.contains(classCode+"~"+subClassCode+"~"+prodid)){
				hashSetInExcle_primaryKey.add(classCode+"~"+subClassCode+"~"+prodid);
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

			GroupClass vo = (GroupClass) operationFlow;
			GroupClass groupClass= mapper.selectVoByPrimaryKey(vo);//根据主键查找
			
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


//			vo.setAuthor(user.getUserName());//用户名称
//			vo.setAuthor(user.getLoginId());//登陆名
//			vo.setOpTime(sdf.format(new Date()));//设置时间
//			vo.setId(UUID);
			if(groupClass != null){
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
				GroupClass vo = (GroupClass) of;
				mapper.deleteVoByPrimaryKey(vo);
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
		GroupClass vo = (GroupClass) o;
		if(StringUtils.isNotBlank(vo.getOpTime())) {
			String[] arr=vo.getOpTime().split("-");
			vo.setStartTime(arr[0].trim());
			vo.setEndTime(arr[1].trim());
		}
		List<GroupClass> list = mapper.selectList(vo);
		OperationFlowExportReturn ofer = new OperationFlowExportReturn();
		ofer.setExcelName("dim_ia_group_class.xlsx");
		ofer.setTxtName("dim_ia_group_class.txt");
		ofer.setList(list);
		System.out.println(list);
		return ofer;
	}

	/**
	 * 根据产品Id模糊查询
	 * 
	 * @param
	 * @return
	 */
	public List<GroupClass> getProdIdListByProdId(String prodId) throws Exception {
		GroupClass pc=new GroupClass();
		pc.setProdid(prodId);
		return mapper.getProdIdListByProdId(pc);
	}

	/**
	 * 根据产品名称模糊查询
	 * 
	 * @param prodName
	 * @return
	 */
	public List<GroupClass> getProdIdListByProdName(String prodName) throws Exception {
		GroupClass pc=new GroupClass();
		pc.setProdName(prodName);
		return mapper.getProdIdListByProdName(pc);
	}
	@Override
	public Integer exportDataNum(OperationFlow o,String type) throws Exception {
		GroupClass vo = (GroupClass) o;
		if(StringUtils.isNotBlank(vo.getOpTime())) {
			String[] arr=vo.getOpTime().split("-");
			vo.setStartTime(arr[0].trim());
			vo.setEndTime(arr[1].trim());
		}
		Integer num = mapper.selectDataNum(vo);
		
		return num;
	}
}