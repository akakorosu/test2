package com.bonc.dpi.service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bonc.common.utils.UUIDUtil;
import com.bonc.dpi.dao.entity.KeywordGroup;
import com.bonc.dpi.dao.entity.OperationFlow;
import com.bonc.dpi.dao.entity.OperationFlowExportReturn;
import com.bonc.dpi.dao.entity.OperationFlowImportReturn;
import com.bonc.dpi.dao.entity.PositionRule;
import com.bonc.dpi.dao.mapper.PositionRuleMapper;
import com.bonc.dpi.service.i.OperationFlowServiceI;
import com.bonc.dpi.utils.DpiUtils;
import com.bonc.login.action.LoginAction;
import com.bonc.system.dao.entity.SysUser;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;

/**
 * 关键字规则Service
 * dim_ia_keyword_search_rule
 * @author BONC-XUXL
 *
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class PositionRuleService implements OperationFlowServiceI{
	
	@Autowired
	private PositionRuleMapper mapper;
	
	@Autowired
	private KeywordGroupService kgService;
	
	
	private static SimpleDateFormat sdf = DpiUtils.sdf;
	private static SysUser user ;

	
	public static HashSet<String> hashSetInExcle_primaryKey = new HashSet<String>();//excle中的主键

	public static List<OperationFlow> invalidDataInExcle_repeatPrimaryKey = new ArrayList<OperationFlow>(); //excle中无效的数据--主键重复
	public static List<OperationFlow> invalidDataInExcle_empty = new ArrayList<OperationFlow>(); //excle中无效的数据--空值
	public static List<OperationFlow> invalidDataInExcle_tooLong = new ArrayList<OperationFlow>(); //excle中无效的数据--过长
	public static List<OperationFlow> invalidDataInExcle_notTrue_prodId = new ArrayList<OperationFlow>(); //excle中无效的数据--关联表不真实存在
	public static List<OperationFlow> invalidDataInExcle_notTrue_groupType = new ArrayList<OperationFlow>(); //excle中无效的数据--关联表不真实存在

	
	public static List<OperationFlow> list_false_cache = new ArrayList<OperationFlow>(); //excle中在数据库中重复的缓存数据
	public static List<OperationFlow> list_true_cache = new ArrayList<OperationFlow>(); //excle中正确的缓存数据
	
	
	public List<PositionRule> selectListSimple(PositionRule pos) {
		List<PositionRule> list = this.mapper.selectListSimple(pos);
		return list;
	}

	/**
	 * 分页查询
	 * @param pos
	 * @param page
	 * @param row
	 * @return
	 */
	public Page<PositionRule> selectList(PositionRule pos, Integer page, Integer row) {
		PageHelper.startPage(page, row);
		Page<PositionRule> pageList = (Page<PositionRule>) this.mapper.selectList(pos);
		
		for (PositionRule positionRule : pageList) {
			KeywordGroup kgParam = new KeywordGroup();
			kgParam.setGroupType(positionRule.getGroupType());
			KeywordGroup voKg = kgService.selectVoByPrimaryKey(kgParam);
			if(voKg!=null){
				positionRule.setGroupName(voKg.getGroupName());
			}
		}
		
		return pageList;
	}
	
	/**
	 * 根据id查询对象
	 * @param id
	 * @return
	 */
	public PositionRule selectVoById(String id){
		return mapper.selectVoById(id);
	}
	
	/**
	 * 添加
	 * @param vo
	 */
	public Boolean insertVo(PositionRule vo){
		Integer num=mapper.selectMaxNum();
		
		vo.setNum(num+1+"");
		return mapper.insertVo(vo);
	}
	
	/**
	 * 修改
	 * @param vo
	 * @return 
	 */
	public Boolean updateVo(PositionRule vo){
		return mapper.updateVo(vo);
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
	public Boolean checkRepeat(PositionRule vo) {
		Integer i = this.mapper.checkRepeat(vo);//验证主键重复
		
		if(i > 0) {
			return false;
		}
		return true;
	}
	
	
	/**
	 * 校验groupType是否为空
	 * @param vo
	 * @return
	 */
	public Boolean checkGroupType(PositionRule vo) {
		Integer i = this.mapper.checkGroupType(vo);
		if(i < 1) {
			return false;
		}
		return true;
	}
	
	/**
	 * 校验groupName是否为空
	 * @param vo
	 * @return
	 */
	public Boolean checkGroupName(PositionRule vo) {
		Integer i = this.mapper.checkGroupName(vo);
		if(i < 1) {
			return false;
		}
		return true;
	}
	
	
	
	
	/**
	 * 清空内存
	 * @throws Exception
	 */
	public void clearCache()throws Exception {
		
		hashSetInExcle_primaryKey.clear();//清空缓存
		
		invalidDataInExcle_repeatPrimaryKey.clear();//清空缓存
		invalidDataInExcle_empty.clear();//清空缓存
		invalidDataInExcle_tooLong.clear();//清空缓存
		
		invalidDataInExcle_notTrue_prodId.clear();//清空缓存
		invalidDataInExcle_notTrue_groupType.clear();//清空缓存
		
		list_true_cache.clear();//清空缓存
		list_false_cache.clear();//清空缓存
	}
	
	
	/**
	 * 批量导入
	 */
	@Override
	public OperationFlowImportReturn doImport(List<OperationFlow> list,String type) throws Exception {
		
		clearCache();//清空缓存
		OperationFlowImportReturn ofir = new OperationFlowImportReturn();//提示语对象
		ofir.setFalseType("1");//默认有问题
		ofir.setActionUrl(DpiUtils.url_falseDataViewFromExcle_positionRule);//excle批量导入问题数据页面反回路径
		
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
			Map<String,List<OperationFlow>> map = filterList_CompareDataBase(list);//更改
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
					insertListCache(list_true,type);
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
			Boolean bl_notTrue_prodId = true;//数据有效--关联表不真实存在，产品表
			Boolean bl_notTrue_groupType = true;//数据有效--关联表不真实存在，应用分组编码
			
			
			PositionRule vo = (PositionRule) of;
			
			String url = vo.getUrl();// URL
			String host=vo.getHost();// 域名
			String matchType=vo.getMatchType();// 匹配类型

			String longitudeRule = vo.getLongitudeRule();// 经度截取规则规则
			String getIndexLong = String.valueOf(vo.getGetIndexLong());// 经度截取序号
			String latitudeRule = vo.getLatitudeRule();// 纬度截取规则
			String getIndexLati = String.valueOf(vo.getGetIndexLati());// 纬度截取序号
			String systemRule = vo.getSystemRule();// 系统截取规则
			String getIndexSys = String.valueOf(vo.getGetIndexSys());// 系统截取序号
			String system = vo.getSystem();// 定位系统
			String groupType = vo.getGroupType();// 应用分组编码
			
		
			String author=vo.getAuthor();//作者
			String opTime=vo.getOpTime();//操作时间
			String isValid=vo.getIsValid();//是否有效
			
			String num=vo.getNum();
			
			
			//非空校验
			boolean not_ip = !StringUtils.equals("ip", matchType);
			
			if("".equals(url) || url==null){
				bl_empty = false;
			}
			if("".equals(host)&&not_ip || host==null&&not_ip){
				bl_empty = false;
			}
			if("".equals(matchType) || matchType==null){
				bl_empty = false;
			}
			if("".equals(longitudeRule) || longitudeRule==null){
				bl_empty = false;
			}
			if("".equals(latitudeRule) || latitudeRule==null){
				bl_empty = false;
			}
			

			//长度验证
			if(url.length()>2000){
				bl_tooLong = false;
			}
			if(host.length()>200){
				bl_tooLong = false;
			}
			if(matchType.length()>10){
				bl_tooLong = false;
			}

			if(longitudeRule.length()>200){
				bl_tooLong = false;
			}
			if(getIndexLong.length()>10){
				bl_tooLong = false;
			}	
			if(latitudeRule.length()>200){
				bl_tooLong = false;
			}
			if(getIndexLati.length()>10){
				bl_tooLong = false;
			}			
			if(systemRule.length()>500){
				bl_tooLong = false;
			}
			if(getIndexSys.length()>10){
				bl_tooLong = false;
			}
			if(system.length()>10){
				bl_tooLong = false;
			}
			
			if(groupType.length()>100){
				bl_tooLong = false;
			}	

			if(author.length()>20){
				bl_tooLong = false;
			}	
			if(opTime.length()>8){
				bl_tooLong = false;
			}
			if(isValid.length()>1){
				bl_tooLong = false;
			}
			if(num.length()>20){
				bl_tooLong = false;
			}	
			
			//应用分组校验
			KeywordGroup KeywordGroupParam = new KeywordGroup();
			KeywordGroupParam.setGroupType(groupType);
			KeywordGroup KeywordGroup = kgService.selectVoByPrimaryKey(KeywordGroupParam);
			if(KeywordGroup == null){
				bl_notTrue_groupType = false;
			}
			
			if(!bl_empty){
				invalidDataInExcle_empty.add(of);
			}
			if(!bl_tooLong){
				invalidDataInExcle_tooLong.add(of);
			}
			if(!bl_notTrue_prodId){
				invalidDataInExcle_notTrue_prodId.add(of);
			}
			if(!bl_notTrue_groupType){
				invalidDataInExcle_notTrue_groupType.add(of);
			}
			
			//主键没有重复则插入缓存中
			if(!hashSetInExcle_primaryKey.contains(host+"~"+longitudeRule+"~"+latitudeRule)){
				hashSetInExcle_primaryKey.add(host+"~"+longitudeRule+"~"+latitudeRule);
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

			PositionRule vo = (PositionRule) operationFlow;
			PositionRule voParam = new PositionRule();
			voParam.setHost(vo.getHost());
			PositionRule positionRule= mapper.selectVoByPrimaryKey(voParam);//根据主键查找
			
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

			if(positionRule != null){
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
				PositionRule vo = (PositionRule) of;
				PositionRule voParam = new PositionRule();
				voParam.setHost(vo.getHost());
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
	public void insertListCache(List<OperationFlow> list ,String type)throws Exception {
		Integer PL_NUMBERS_ONCE = DpiUtils.PL_NUMBERS_ONCE;
		//500条一组插入数据库
		if("2".equals(type)){
			for(int j=0;j<(list.size()/PL_NUMBERS_ONCE+1);j++){
				List<OperationFlow> list_true_insert = DpiUtils.getPlInsertDataOnce(list, j);
				if(list_true_insert.size()>0 && list_true_insert!=null){
					Integer maxNum=mapper.selectMaxNum();
					if(maxNum==null) {
						maxNum = 0;
					}
					Map map=new HashMap();
					map.put("list",list_true_insert);
					map.put("type",type);
					map.put("maxNum",maxNum);
					map.put("database_type",DpiUtils.DATABASE_TYPE);
					mapper.insertVoPl(map);
				}
			}
		}else{
			for(int j=0;j<(list.size()/PL_NUMBERS_ONCE+1);j++){
				List<OperationFlow> list_true_insert = DpiUtils.getPlInsertDataOnce(list, j);
				if(list_true_insert.size()>0 && list_true_insert!=null){
					Integer maxNum=mapper.selectMaxNum();
					if(maxNum==null) {
						maxNum = 0;
					}
					Map map=new HashMap();
					map.put("list",list_true_insert);
					map.put("maxNum",maxNum);
					map.put("type",type);
					map.put("database_type",DpiUtils.DATABASE_TYPE);
					mapper.insertVoPl(map);
				}
			}
		}

	}
	public void insertListCache(List<OperationFlow> list )throws Exception {
		Integer PL_NUMBERS_ONCE = DpiUtils.PL_NUMBERS_ONCE;
		//500条一组插入数据库

		for(int j=0;j<(list.size()/PL_NUMBERS_ONCE+1);j++){
			List<OperationFlow> list_true_insert = DpiUtils.getPlInsertDataOnce(list, j);
			if(list_true_insert.size()>0 && list_true_insert!=null){
				Integer maxNum=mapper.selectMaxNum();
				Map map=new HashMap();
				map.put("list",list_true_insert);
				map.put("maxNum",maxNum);
				map.put("database_type",DpiUtils.DATABASE_TYPE);
				mapper.insertVoPl(map);
			}
		}
	}
	
	/**
	 * 批量导出
	 */
	@Override
	public OperationFlowExportReturn doExport(OperationFlow o) throws Exception {
		PositionRule vo = (PositionRule) o;
		if(StringUtils.isNotBlank(vo.getOpTime())) {
			String[] arr=vo.getOpTime().split("-");
			vo.setStartTime(arr[0].trim());
			vo.setEndTime(arr[1].trim());
		}
		List<PositionRule> list = this.mapper.selectList(vo);
		OperationFlowExportReturn ofer = new OperationFlowExportReturn();
		ofer.setExcelName("dim_ia_position_rule.xlsx");
		ofer.setTxtName("dim_ia_position_rule_all.txt");
		ofer.setNewTxtName("dim_ia_position_rule.txt");
		ofer.setList(list);
		return ofer;
	}	
	
	/**
	 * 批量导出数据条数
	 */
	@Override
	public Integer exportDataNum(OperationFlow o,String type) throws Exception {
		PositionRule vo = (PositionRule) o;
		if(StringUtils.isNotBlank(vo.getOpTime())) {
			String[] arr=vo.getOpTime().split("-");
			vo.setStartTime(arr[0].trim());
			vo.setEndTime(arr[1].trim());
		}
		Integer num = this.mapper.selectDataNum(vo);
		
		return num;
	}
}
