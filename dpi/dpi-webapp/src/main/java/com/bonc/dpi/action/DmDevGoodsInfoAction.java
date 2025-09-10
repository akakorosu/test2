package com.bonc.dpi.action;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bonc.dpi.dao.entity.DmDevGoodsInfo;
import com.bonc.dpi.service.DmDevGoodsInfoService;

@Controller
@RequestMapping(value = "/dmdevgoodsinfo")
public class DmDevGoodsInfoAction {
	
	@Resource
	private DmDevGoodsInfoService dmDevGoodsInfoService;

	/**
	 * 分页查询
	 * @param sysUser
	 * @param page
	 * @param rows
	 * @return
	 */
	@RequestMapping(value = "/selectlist")
	@ResponseBody
	public List<DmDevGoodsInfo> selectList(DmDevGoodsInfo vo) {
		List<DmDevGoodsInfo> list = this.dmDevGoodsInfoService.selectList(vo);
		return list;
	}
}