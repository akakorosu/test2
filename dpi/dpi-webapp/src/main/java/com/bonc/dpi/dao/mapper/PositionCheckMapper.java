package com.bonc.dpi.dao.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.bonc.dpi.dao.entity.OperationFlow;
import com.bonc.dpi.dao.entity.PositionCheck;


public interface PositionCheckMapper {

	/**
	 * 查询数据条数
	 * @param vo
	 * @return
	 */
	Integer selectDataNum(PositionCheck vo);

	/**
	 * 分页查询
	 * @param vo
	 * @return
	 */
	List<PositionCheck> selectList(PositionCheck vo);

	Boolean deleteById(String id);

	Boolean insert(PositionCheck vo);

	PositionCheck selectById(String id);

	int selectMaxNum();

	PositionCheck selectVoByPrimaryKey(PositionCheck obj);

	void deleteVoByPrimaryKey(PositionCheck voParam);
	/**
	 * 批量导入
	 * @param list
	 * @return
	 */
	Boolean insertVoPl(@Param("list")List<OperationFlow> list,@Param("database_type")String database_type);
}
