package com.bonc.dpi.dao.mapper;

import java.util.List;

import com.bonc.dpi.dao.entity.UnidentifiedUa;

public interface UnidentifiedUaMapper {
	/**
	 * 分页查询
	 * @param vo
	 * @return
	 */
	List<UnidentifiedUa> selectList (UnidentifiedUa vo);
}
