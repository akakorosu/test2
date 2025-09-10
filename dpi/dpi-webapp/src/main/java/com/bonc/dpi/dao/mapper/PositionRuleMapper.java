package com.bonc.dpi.dao.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.bonc.dpi.dao.entity.PositionRule;

/**
 * 
 * 位置规则接口
 * dim_ia_position_rule
 *
 *
 * @author zhouping
 * @since 2019年11月13日
 */
public interface PositionRuleMapper {
	
	List<PositionRule> selectListSimple(PositionRule vo);
	
	
	/**
	 * 查询数据条数
	 * @param vo
	 * @return
	 */
	Integer selectDataNum (PositionRule vo);
	
	/**
	 * 分页查询
	 * @param vo
	 * @return
	 */
	List<PositionRule> selectList (PositionRule vo);
	
	/**
	 * 根据id查对象
	 * @param id
	 * @return
	 */
	PositionRule selectVoById(String id);
	
	/**
	 * 添加
	 * @param vo
	 * @return
	 */
	Boolean insertVo(PositionRule vo);

	/**
	 * 修改
	 * @param vo
	 * @return
	 */
	Boolean updateVo(PositionRule vo);

	/**
	 * 删除
	 * @param id
	 * @return
	 */
	Boolean deleteVoById(String id);
	
	/**
	 * 删除(根据主键)
	 * @param id
	 * @return
	 */
	Boolean deleteVoByPrimaryKey(PositionRule vo);
	
	/**
	 * 查询(根据主键)
	 * @param id
	 * @return
	 */
	PositionRule selectVoByPrimaryKey(PositionRule vo);
	
	
	/**
	 * 重复校验
	 * @param vo
	 * @return
	 */
	Integer checkRepeat(PositionRule vo);
	
	
	/**
	 * 查询num最大值
	 * @return
	 */
	Integer selectMaxNum();

	/**
	 * 批量导入
	 * @param list
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	Boolean insertVoPl(@Param("map")Map map);
	
	/**
	 * 校验groupType是否为空
	 * @param vo
	 * @return
	 */
	Integer checkGroupType(PositionRule vo);
	
	
	/**
	 * 校验groupName是否为空
	 * @param vo
	 * @return
	 */
	Integer checkGroupName(PositionRule vo);
	
	
}
