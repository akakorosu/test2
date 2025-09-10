package com.bonc.dpi.dao.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.bonc.dpi.dao.entity.ProductInfo;
import com.bonc.dpi.dao.entity.UnidentifiedUrl;

public interface UnidentifiedUrlMapper {

	/**
	 * 分页查询
	 * @param vo
	 * @return
	 */
	List<UnidentifiedUrl> selectList (UnidentifiedUrl vo);
	
	/**
	 * 根据id查对象
	 * @param id
	 * @return
	 */
	UnidentifiedUrl selectVoById(@Param("host1")String host1,@Param("time")String time);
	/**
	 * 插入域名表
	 * @param vo
	 * @return
	 */
	Boolean insertDomainRule(UnidentifiedUrl vo);
	/**
	 * 更改稽核状态
	 */
	void updateAuditStatus(UnidentifiedUrl vo);
	/**
	 * 修改
	 * @param vo
	 * @return
	 */
	Boolean updateVo(UnidentifiedUrl vo);

	Integer selectCheck(UnidentifiedUrl vo);
	List<Map<String, Object>> selectProdId(String prodName);
	UnidentifiedUrl selectInfoByProdId(String prodId);
	UnidentifiedUrl getprodCatagoryName(String prodCatagory);
	ProductInfo selectInfoByProdName(String prodName);
	int onlyUpdateCheck(String host);
	int insertDomainRuleNew(UnidentifiedUrl vo);
	int updateDomainRuleNew(UnidentifiedUrl vo);
	int checkData(UnidentifiedUrl vo);
	//
	int batchUpdateEmptyCheck(Map<String, Object> params);
	int batchUpdateCheck(Map<String, Object> params);
	int batchInsertDomainRule(Map<String, Object> params);
	List<Map<String, Object>> getAllProdName();
	List<Map<String, Object>> getAllProdId();
	int alterAndInsert(UnidentifiedUrl vo);
	int alterOnlyUpdate(UnidentifiedUrl vo);
	int insertExport(UnidentifiedUrl vo);
	List<UnidentifiedUrl> comLikeSearch(@Param(value = "prodName")String prodName);
	int alterProdInfo(UnidentifiedUrl vo);
	int alterInsertDomain(UnidentifiedUrl vo);
}
