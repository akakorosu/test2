package com.bonc.system.dao.mapper;

import java.util.List;

import com.bonc.dpi.dao.entity.ProductInfo;
import com.bonc.system.dao.entity.SysInterfaceInfo;

public interface SystemMapper {

	public List<SysInterfaceInfo> getSysInterfaceInfo();
	
	public int delRdAllProd();
	
	public int insertRdVoBatch(List<ProductInfo> list);
	
}
