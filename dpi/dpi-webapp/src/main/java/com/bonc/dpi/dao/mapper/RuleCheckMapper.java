package com.bonc.dpi.dao.mapper;

import java.util.List;

import com.bonc.dpi.dao.entity.RuleCheck;

public interface RuleCheckMapper {
	List<RuleCheck> selectList (RuleCheck vo);
}
