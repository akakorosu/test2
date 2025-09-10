package com.bonc.dpi.action;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bonc.dpi.dao.entity.DmDevVideoInfo;
import com.bonc.dpi.service.DmDevVideoInfoService;

@Controller
@RequestMapping(value = "/dmdevvideoinfo")
public class DmDevVideoInfoAction {
	
	@Resource
	private DmDevVideoInfoService dmDevVideoInfoService;

	/**
	 * 分页查询
	 * @param sysUser
	 * @param page
	 * @param rows
	 * @return
	 */
	@RequestMapping(value = "/selectlist")
	@ResponseBody
	public List<DmDevVideoInfo> selectList(DmDevVideoInfo vo) {
		List<DmDevVideoInfo> list = this.dmDevVideoInfoService.selectList(vo);
		return list;
	}
}