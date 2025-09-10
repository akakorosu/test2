package com.bonc.dpi.service;

import com.bonc.common.des.DesUtil;
import com.bonc.common.utils.UUIDUtil;
import com.bonc.dpi.dao.entity.*;
import com.bonc.dpi.dao.mapper.DepthRuleCheckMapper;
import com.bonc.dpi.service.i.OperationFlowServiceI;
import com.bonc.dpi.utils.DpiUtils;
import com.bonc.login.action.LoginAction;
import com.bonc.system.dao.entity.SysUser;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.net.URLDecoder;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


@Service
@Transactional(rollbackFor = Exception.class)
public class DepthRuleCheckService implements OperationFlowServiceI{


	@Resource
	private DepthRuleCheckMapper mapper;


	private static SimpleDateFormat sdf = DpiUtils.sdf;
	private static SysUser user ;

	public static HashSet<String> hashSetInDataBase_primaryKey = new HashSet<String>();//数据库中的主键
	public static HashSet<String> hashSetInExcle_primaryKey = new HashSet<String>();//excle中的主键

	public static List<OperationFlow> invalidDataInExcle_repeatPrimaryKey = new ArrayList<OperationFlow>(); //excle中无效的数据--主键重复
	public static List<OperationFlow> invalidDataInExcle_empty = new ArrayList<OperationFlow>(); //excle中无效的数据--空值
	public static List<OperationFlow> invalidDataInExcle_tooLong = new ArrayList<OperationFlow>(); //excle中无效的数据--过长

	public static List<OperationFlow> list_false_cache = new ArrayList<OperationFlow>(); //excle中在数据库中重复的缓存数据
	public static List<OperationFlow> list_true_cache = new ArrayList<OperationFlow>(); //excle中正确的缓存数据

	/**
	 * 分页查询
	 * @param
	 * @param page
	 * @param row
	 * @return
	 */
	public Page<DepthRuleCheck> selectList(DepthRuleCheck depthRuleCheck, Integer page, Integer row) {
		PageHelper.startPage(page, row);
		// 查询条件处理prod_name
		String prodName = depthRuleCheck.getProdName();
		if (StringUtils.isNotBlank(prodName)) {
			String prodName_res = DpiUtils.strEncrypt(prodName);// 字符串分词加密
			depthRuleCheck.setProdName(prodName_res);
		}
		Page<DepthRuleCheck> pageList = (Page<DepthRuleCheck>) this.mapper.selectList(depthRuleCheck);
		for(DepthRuleCheck vo:pageList){
			if(StringUtils.isNotBlank(vo.getProdName())){
				vo.setProdName(DpiUtils.strDecrypt(vo.getProdName()));
			}
		}

		return pageList;
	}
	public Boolean audit(String id){
		DepthRuleCheck vo=mapper.selectById(id);
		vo.setNum(mapper.selectMaxNum()+1+"");
		vo.setAuthor(user.getLoginId());
		vo.setOpTime(sdf.format(new Date()));
		mapper.deleteById(id);
		return mapper.insert(vo);
	}
	public Boolean delete(String id){
		return mapper.deleteById(id);
	}

	/**
	 * 批量导入
	 */
	@Override
	public OperationFlowImportReturn doImport(List<OperationFlow> list,String type) throws Exception {
		clearCache();//清空缓存

		OperationFlowImportReturn ofir = new OperationFlowImportReturn();//提示语对象
		ofir.setFalseType("1");//默认有问题
		ofir.setActionUrl(DpiUtils.url_falseDataViewFromExcle_DepthRuleCheck);//excle批量导入问题数据页面反回路径

		Boolean bl_excle_self = true;//excle自身问题

		//1.检查excle中自身数据有效性
		dataValidityInExcle(list);
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

//		hashSetInDataBase_primaryKey.clear();//清空缓存
		hashSetInExcle_primaryKey.clear();//清空缓存

		invalidDataInExcle_repeatPrimaryKey.clear();//清空缓存
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

			DepthRuleCheck vo = (DepthRuleCheck) of;
			String url=vo.getUrl();
			String parseRule=vo.getParseRule();
			String prodId=vo.getProdid();
			String matchType=vo.getMatchType();
            String ruleType=vo.getRuleType();
			String channelName=vo.getChannelName();
			String groupType=vo.getGroupType();
			String author=vo.getAuthor();//作者
			String opTime=vo.getOpTime();//op_time,操作时间


			//非空校验
			if("".equals(url) || url==null){
				bl_empty = false;
			}
			if("".equals(parseRule) || parseRule==null){
				bl_empty = false;
			}
            if("".equals(prodId) || prodId==null){
                bl_empty = false;
            }
            if("".equals(matchType) || matchType==null){
                bl_empty = false;
            }
            if("".equals(channelName) || channelName==null){
                bl_empty = false;
            }
            if("".equals(ruleType) || ruleType==null){
                bl_empty = false;
            }
            if("".equals(groupType) || groupType==null){
                bl_empty = false;
            }

			//长度验证
            if(prodId.length()>100){
                bl_tooLong = false;
            }
			if(StringUtils.isNotBlank(vo.getProdName())){
				if(DpiUtils.strEncrypt(vo.getProdName()).length()>500){
					bl_tooLong = false;
				}
			}
            if(matchType.length()>10){
                bl_tooLong = false;
            }
            if(matchType.length()>10){
                bl_tooLong = false;
            }
            if(ruleType.length()>10){
                bl_tooLong = false;
            }
            if(channelName.length()>100){
                bl_tooLong = false;
            }
            if(vo.getUrl().length()>2000){
				bl_tooLong = false;
			}
			if(parseRule.length()>500){
				bl_tooLong = false;
			}

			if(vo.getTestResult().length()>100){
				bl_tooLong = false;
			}
			if(groupType.length()>100){
				bl_tooLong = false;
			}
			if(vo.getGroupName().length()>100){
				bl_tooLong = false;
			}
			if(vo.getCrawlerUrl().length()>2000){
				bl_tooLong = false;
			}
			if(author.length()>100){
				bl_tooLong = false;
			}
			if(opTime.length()>8){
				bl_tooLong = false;
			}

			if(!bl_empty){
				invalidDataInExcle_empty.add(of);
			}
			if(!bl_tooLong){
				invalidDataInExcle_tooLong.add(of);
			}

			//没有重复则插入缓存中
			if(!hashSetInExcle_primaryKey.contains(url+"~"+parseRule)){
				hashSetInExcle_primaryKey.add(url+"~"+parseRule);
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
			DepthRuleCheck vo = (DepthRuleCheck) operationFlow;
			DepthRuleCheck voParam = new DepthRuleCheck();
			voParam.setUrl(vo.getUrl());
			voParam.setParseRule(vo.getParseRule());
			DepthRuleCheck depthRuleCheck= mapper.selectVoByPrimaryKey(voParam);//根据主键查找

			if(StringUtils.isNotBlank(vo.getUrl())){
				vo.setHost(vo.getUrl().split("/")[0]);
			}

			Matcher m = Pattern.compile(vo.getParseRule()).matcher(vo.getUrl());
			if(m.find()){
				vo.setParseResult(m.group(Integer.parseInt(vo.getGetIndex())));
				vo.setParseResultDec(URLDecoder.decode(vo.getParseResult(), "UTF-8"));
			}

			if(StringUtils.isNotBlank(vo.getProdName())){
				vo.setProdName(DpiUtils.strEncrypt(vo.getProdName()));
			}
			vo.setState("0");
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

			if(depthRuleCheck != null){
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
				DepthRuleCheck vo = (DepthRuleCheck) of;
				DepthRuleCheck voParam = new DepthRuleCheck();
				voParam.setUrl(vo.getUrl());
				voParam.setParseRule(vo.getParseRule());
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
				mapper.insertVoPl(list_true_insert,DpiUtils.DATABASE_TYPE);
			}
		}
	}


	/**
	 * 批量导出
	 */
	@Override
	public OperationFlowExportReturn doExport(OperationFlow o) throws Exception {
		DepthRuleCheck vo = (DepthRuleCheck) o;
		if(StringUtils.isNotBlank(vo.getOpTime())) {
			String[] arr=vo.getOpTime().split("-");
			vo.setStartTime(arr[0].trim());
			vo.setEndTime(arr[1].trim());
		}
		List<DepthRuleCheck> list = this.mapper.selectList(vo);
		OperationFlowExportReturn ofer = new OperationFlowExportReturn();
		ofer.setExcelName("dim_ia_depth_rule_check.xlsx");
		ofer.setTxtName("dim_ia_depth_rule_check.txt");
		ofer.setList(list);
		return ofer;
	}

	/**
	 * 批量导出数据条数
	 */
	@Override
	public Integer exportDataNum(OperationFlow o,String type) throws Exception {
		DepthRuleCheck vo = (DepthRuleCheck) o;
		if(StringUtils.isNotBlank(vo.getOpTime())) {
			String[] arr=vo.getOpTime().split("-");
			vo.setStartTime(arr[0].trim());
			vo.setEndTime(arr[1].trim());
		}
		Integer num = this.mapper.selectDataNum(vo);

		return num;
	}
}
