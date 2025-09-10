package com.bonc.dpi.service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bonc.common.utils.UUIDUtil;
import com.bonc.dpi.dao.entity.ContentLabel;
import com.bonc.dpi.dao.entity.DimIaIpPortDynamic;
import com.bonc.dpi.dao.entity.DomainRule;
import com.bonc.dpi.dao.entity.OperationFlow;
import com.bonc.dpi.dao.entity.OperationFlowExportReturn;
import com.bonc.dpi.dao.entity.OperationFlowImportReturn;
import com.bonc.dpi.dao.entity.ProductInfo;
import com.bonc.dpi.dao.mapper.ContentLabelMapper;
import com.bonc.dpi.service.i.OperationFlowServiceI;
import com.bonc.dpi.utils.DpiUtils;
import com.bonc.login.action.LoginAction;
import com.bonc.system.dao.entity.SysUser;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;

@Service
@Transactional(rollbackFor = Exception.class)
public class ContentLabelService implements OperationFlowServiceI{
	@Autowired
	ContentLabelMapper contentLabelMapper;
	private static HashSet<String> hashSetInDataBase = new HashSet<String>();//数据库中的数据
	private static SimpleDateFormat sdf = DpiUtils.sdf;
	private static SysUser user ;	
	public static HashSet<String> hashSetInExcle_primaryKey = new HashSet<String>();//excle中的主键
	public static List<OperationFlow> invalidDataInExcle_repeatPrimaryKey = new ArrayList<OperationFlow>(); //excle中无效的数据--主键重复
	public static List<OperationFlow> invalidDataInExcle_empty = new ArrayList<OperationFlow>(); //excle中无效的数据--空值
	public static List<OperationFlow> invalidDataInExcle_tooLong = new ArrayList<OperationFlow>(); //excle中无效的数据--过长
	public static List<OperationFlow> invalidDataInExcle_notTrue_prodId = new ArrayList<OperationFlow>(); //excle中无效的数据--关联表不真实存在	
	public static List<OperationFlow> list_false_cache = new ArrayList<OperationFlow>(); //excle中在数据库中重复的缓存数据
	public static List<OperationFlow> list_true_cache = new ArrayList<OperationFlow>(); //excle中正确的缓存数据
	
	public List<String> selectContentLabelName1() {
		return contentLabelMapper.selectContentLabelName1();
	}
	/**
	 * 分页查询
	 * @param
	 * @param page
	 * @param row
	 * @return
	 */
	public Page<ContentLabel> selectList(ContentLabel obj, Integer page, Integer row) {
		PageHelper.startPage(page, row);
		Page<ContentLabel> pageList = (Page<ContentLabel>) contentLabelMapper.selectList(obj);
		return pageList;
	}
	
	/**
	 * 根据id查询对象
	 * @param id
	 * @return
	 */
	public ContentLabel selectById(String id){
		ContentLabel objr=contentLabelMapper.selectById(id);
		return objr;
	}
	
	/**
	 * 添加
	 * @param
	 */
	public ContentLabel insert(ContentLabel obj){
		Boolean bl = contentLabelMapper.insert(obj);
		return obj;
	}
	
	public int checkData(ContentLabel obj) {
		return contentLabelMapper.checkData(obj);
	}
	
	/**
	 * 修改
	 * @param
	 * @return 
	 */
	public ContentLabel update(ContentLabel obj){
		Boolean bl = contentLabelMapper.update(obj);
		Boolean bll = contentLabelMapper.updateContentLabelName(obj);
		return obj;
	}
	
	/**
	 * 删除
	 * @param
	 */
	public Boolean deleteById(ContentLabel obj){
		Boolean bl = contentLabelMapper.delete(obj);
		return bl;
	}
	
	/**
	 * 去重过滤
	 * @param vo
	 * @return
	 */
	public Boolean check(ContentLabel vo) {
		Integer i = contentLabelMapper.check(vo);
		if(i > 0) {
			return false;
		}
		return true;
	}

	public List<ContentLabel> selectByLabelCode(String contentLabelName){
		ContentLabel obj=new ContentLabel();
		obj.setContentLabelName(contentLabelName);
		return contentLabelMapper.selectList(obj);
	}
	public List<ContentLabel> selectLabelList(ContentLabel obj){
		return contentLabelMapper.selectLabelList(obj);
	}
	
	public List<ContentLabel> selectList(ContentLabel obj) {
		List<ContentLabel> list = this.contentLabelMapper.selectList(obj);
		return list;
	}
	
	/**
	 * 批量导入
	 */
	@Override
	public OperationFlowImportReturn doImport(List<OperationFlow> list,String type) throws Exception {
		
		clearCache();//清空缓存
		
		OperationFlowImportReturn ofir = new OperationFlowImportReturn();//提示语对象
		ofir.setFalseType("1");//默认有问题
		ofir.setActionUrl(DpiUtils.url_falseDataViewFromExcle_contentLabel);//excle批量导入问题数据页面反回路径
		
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
		
		//生成编码
		list=generatedContentLabelCode(list);
		
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
	private List<OperationFlow> generatedContentLabelCode(List<OperationFlow> list){
		char[] arr = new char[26];
		for (int i = 0; i < 26; i++) {
			arr[i] = (char) (65 + i);
		}
		Random random = new Random();
		List<ContentLabel> checkList=new ArrayList<ContentLabel>(); 
		for (OperationFlow vo : list) {
			ContentLabel obj = (ContentLabel) vo;
//			String contentLabelCode1 ="";
//			String contentLabelCode2 ="";
//			String contentLabelCode3 ="";
//			String contentLabelCode4 ="";
//			String contentLabelCode5 ="";
//			String contentLabelCode6 ="";
//			int level=Integer.parseInt(obj.getContentLabelLevel());
//			do {
//				for(int i=1;i<=level;i++) {
//					if(i==1) {
//						contentLabelCode1=getCode(arr,random,"");
//						obj.setContentLabelCode1(contentLabelCode1);
//						obj.setContentLabelCode(contentLabelCode1);
//					}
//					if(i==2) {
//						contentLabelCode2=getCode(arr,random,obj.getContentLabelCode1());
//						obj.setContentLabelCode2(contentLabelCode2);
//						obj.setContentLabelCode(contentLabelCode2);
//					}
//					if(i==3) {
//						contentLabelCode3=getCode(arr,random,obj.getContentLabelCode2());
//						obj.setContentLabelCode3(contentLabelCode3);
//						obj.setContentLabelCode(contentLabelCode3);
//					}
//					if(i==4) {
//						contentLabelCode4=getCode(arr,random,obj.getContentLabelCode3());
//						obj.setContentLabelCode4(contentLabelCode4);
//						obj.setContentLabelCode(contentLabelCode4);
//					}
//					if(i==5) {
//						contentLabelCode5=getCode(arr,random,obj.getContentLabelCode4());
//						obj.setContentLabelCode5(contentLabelCode5);
//						obj.setContentLabelCode(contentLabelCode5);
//					}
//					if(i==6) {
//						contentLabelCode6=getCode(arr,random,obj.getContentLabelCode5());
//						obj.setContentLabelCode6(contentLabelCode6);
//						obj.setContentLabelCode(contentLabelCode6);
//					}
//				}
//			}while(checkList.contains(obj));
			checkList.add(obj);
		}
		return list;
	}
	private String getCode(char[] arr,Random random,String code){					
		return code+arr[random.nextInt(26)]+arr[random.nextInt(26)]+random.nextInt(10)+random.nextInt(10);
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
			
			ContentLabel vo = (ContentLabel) of;
			String contentLabelCode = vo.getContentLabelCode();
			String contentLabelName = vo.getContentLabelName();
			String contentLabelLevel = vo.getContentLabelLevel();
			String contentLabelCode1 = vo.getContentLabelCode1();
			String contentLabelName1 = vo.getContentLabelName1();
			String contentLabelCode2 = vo.getContentLabelCode2();
			String contentLabelName2 = vo.getContentLabelName2();
			String contentLabelCode3 = vo.getContentLabelCode3();
			String contentLabelName3 = vo.getContentLabelName3();
			String contentLabelCode4 = vo.getContentLabelCode4();
			String contentLabelName4 = vo.getContentLabelName4();
			String contentLabelCode5 = vo.getContentLabelCode5();
			String contentLabelName5 = vo.getContentLabelName5();
			String contentLabelCode6 = vo.getContentLabelCode6();
			String contentLabelName6 = vo.getContentLabelName6();
			String author = vo.getAuthor();//作者
			String opTime = vo.getOpTime();//操作时间
			
			//非空校验			
			if("".equals(contentLabelName) || contentLabelName==null){
				bl_empty = false;
			}
			if("".equals(contentLabelLevel) || contentLabelLevel==null){
				bl_empty = false;
			}		
			if("".equals(contentLabelName1) || contentLabelName1==null){
				bl_empty = false;
			}
			if("".equals(contentLabelCode) || contentLabelCode==null){
				bl_empty = false;
			}
			if("".equals(contentLabelCode1) || contentLabelCode1==null){
				bl_empty = false;
			}
			//长度验证
			if(contentLabelCode.length()>100){
				bl_tooLong = false;
			}
			if(contentLabelName.length()>100){
				bl_tooLong = false;
			}
			if(contentLabelLevel.length()>10){
				bl_tooLong = false;
			}
			if(contentLabelCode1.length()>30){
				bl_tooLong = false;
			}
			if(contentLabelName1.length()>100){
				bl_tooLong = false;
			}
			if(contentLabelCode2.length()>100){
				bl_tooLong = false;
			}
			if(contentLabelName2.length()>30){
				bl_tooLong = false;
			}
			if(contentLabelCode3.length()>30){
				bl_tooLong = false;
			}
			if(contentLabelName3.length()>100){
				bl_tooLong = false;
			}
			if(contentLabelCode4.length()>100){
				bl_tooLong = false;
			}
			if(contentLabelName4.length()>30){
				bl_tooLong = false;
			}if(contentLabelCode5.length()>30){
				bl_tooLong = false;
			}
			if(contentLabelName5.length()>100){
				bl_tooLong = false;
			}
			if(contentLabelCode6.length()>100){
				bl_tooLong = false;
			}
			if(contentLabelName6.length()>30){
				bl_tooLong = false;
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
			
			String str=contentLabelName+'~'+contentLabelLevel+"~"+contentLabelName1+"~"+contentLabelName2+"~"+contentLabelName3
						+"~"+contentLabelName4+"~"+contentLabelName5+"~"+contentLabelName6;
			//数据没有重复则插入缓存中
			if(!hashSetInExcle_primaryKey.contains(str)){
				hashSetInExcle_primaryKey.add(str);
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
			ContentLabel vo = (ContentLabel) operationFlow;
			ContentLabel contentLabel= contentLabelMapper.selectVoByPrimaryKey(vo);//根据主键查找
			
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
			if(contentLabel != null){
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
				ContentLabel vo = (ContentLabel) of;
				contentLabelMapper.deleteVoByPrimaryKey(vo);
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
				contentLabelMapper.insertVoPl(list_true_insert,DpiUtils.DATABASE_TYPE);
			}
		}
	}
	
	/**
	 * 批量导出
	 */
	@Override
	public OperationFlowExportReturn doExport(OperationFlow o) throws Exception {
		ContentLabel vo = (ContentLabel) o;
		if(StringUtils.isNotBlank(vo.getOpTime())) {
			String[] arr=vo.getOpTime().split("-");
			vo.setStartTime(arr[0].trim());
			vo.setEndTime(arr[1].trim());
		}
		List<ContentLabel> list = contentLabelMapper.selectList(vo);
		OperationFlowExportReturn ofer = new OperationFlowExportReturn();
		ofer.setExcelName("dim_ia_content_label.xlsx");
		ofer.setTxtName("dim_ia_content_label.txt");
		ofer.setList(list);
		return ofer;
	}
	/**
	 * 批量导出数据条数
	 */
	@Override
	public Integer exportDataNum(OperationFlow o,String type) throws Exception {
		ContentLabel vo = (ContentLabel) o;
		if(StringUtils.isNotBlank(vo.getOpTime())) {
			String[] arr=vo.getOpTime().split("-");
			vo.setStartTime(arr[0].trim());
			vo.setEndTime(arr[1].trim());
		}
		Integer num = contentLabelMapper.selectDataNum(vo);
		
		return num;
	}
}
