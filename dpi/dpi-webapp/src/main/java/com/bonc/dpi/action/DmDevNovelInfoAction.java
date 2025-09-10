package com.bonc.dpi.action;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bonc.dpi.dao.entity.DmDevNovelInfo;
import com.bonc.dpi.service.DmDevNovelInfoService;

@Controller
@RequestMapping(value = "/dmdevnovelinfo")
public class DmDevNovelInfoAction {
	
	@Resource
	private DmDevNovelInfoService dmDevNovelInfoService;

	/**
	 * 分页查询
	 * @param sysUser
	 * @param page
	 * @param rows
	 * @return
	 */
	@RequestMapping(value = "/selectlist")
	@ResponseBody
	public List<DmDevNovelInfo> selectList(DmDevNovelInfo vo) {
		List<DmDevNovelInfo> list = this.dmDevNovelInfoService.selectList(vo);
		return list;
	}
}