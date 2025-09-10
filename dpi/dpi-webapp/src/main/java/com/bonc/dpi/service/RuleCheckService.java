package com.bonc.dpi.service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.bonc.dpi.dao.entity.RuleCheck;
import com.bonc.dpi.dao.mapper.RuleCheckMapper;
import com.bonc.login.action.LoginAction;
import com.bonc.system.dao.entity.SysUser;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;

@Service
@Transactional(rollbackFor = Exception.class)
public class RuleCheckService {
	
	@Resource
//	@Autowired
	private RuleCheckMapper mapper;
	
	/**
	 * 分页查询
	 * @param ksr
	 * @param page
	 * @param row
	 * @return
	 */
	public Page<RuleCheck> selectList(RuleCheck vo, Integer page, Integer row) {
		PageHelper.startPage(page, row);
		Page<RuleCheck> pageList = (Page<RuleCheck>) mapper.selectList(vo);
		return pageList;
	}
}
