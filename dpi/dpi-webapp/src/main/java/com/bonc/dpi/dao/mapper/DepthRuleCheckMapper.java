package com.bonc.dpi.dao.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.bonc.dpi.dao.entity.DepthRuleCheck;
import com.bonc.dpi.dao.entity.OperationFlow;


public interface DepthRuleCheckMapper {

	/**
	 * 查询数据条数
	 * @param vo
	 * @return
	 */
	Integer selectDataNum(DepthRuleCheck vo);

	/**
	 * 分页查询
	 * @param vo
	 * @return
	 */
	List<DepthRuleCheck> selectList(DepthRuleCheck vo);

	Boolean deleteById(String id);

	Boolean insert(DepthRuleCheck vo);

	DepthRuleCheck selectById(String id);

	int selectMaxNum();

	DepthRuleCheck selectVoByPrimaryKey(DepthRuleCheck obj);

	void deleteVoByPrimaryKey(DepthRuleCheck voParam);
	/**
	 * 批量导入
	 * @param list
	 * @return
	 */
	Boolean insertVoPl(@Param("list")List<OperationFlow> list,@Param("database_type")String database_type);
}
