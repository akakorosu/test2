package com.bonc.dpi.dao.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.bonc.dpi.dao.entity.TopLevelDomain;

public interface TopLevelDomainMapper {
	
	/**
	 * 查询数量
	 * @param vo
	 * @return
	 */
	Integer selectDataNum (TopLevelDomain vo);
	/**
	 * 分页查询
	 * @param vo
	 * @return
	 */
	List<TopLevelDomain> selectList (TopLevelDomain vo);
	/**
	 * 查询没有产品ID
	 * @param vo
	 * @return
	 */
	List<TopLevelDomain> selectListNotProdId (TopLevelDomain vo);
	
	
	/**
	 * 根据id查对象
	 * @param id
	 * @return
	 */
	TopLevelDomain selectVoById(@Param("host")String host,@Param("monthId")String monthId);
	
	/**
	 * 添加
	 * @param vo
	 * @return
	 */
	Boolean insertVo(TopLevelDomain vo);

	/**
	 * 修改
	 * @param vo
	 * @return
	 */
	Boolean updateVo(TopLevelDomain vo);
	
	int updateTopDomain(TopLevelDomain vo);
	
	int topLvlDomainCheck2(TopLevelDomain vo);
	
	int updateDomainCode(TopLevelDomain vo);
	
	int checkDomainCode(TopLevelDomain vo);
	
	int updateOrInsertDomain(TopLevelDomain vo);

	/**
	 * 删除
	 * @param id
	 * @return
	 */
	Boolean deleteVoById(@Param("id")String id,@Param("time")String time);
	
	/**
	 * 重复校验
	 * @param vo
	 * @return
	 */
	Integer selectCheck(TopLevelDomain vo);
}
