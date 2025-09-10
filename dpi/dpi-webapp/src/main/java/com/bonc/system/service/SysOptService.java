package com.bonc.system.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bonc.dpi.config.Constant;
import com.bonc.dpi.dao.entity.ProductInfo;
import com.bonc.dpi.dao.mapper.ProductInfoMapper;
import com.bonc.dpi.utils.DpiUtils;
import com.bonc.system.dao.entity.SysInterfaceInfo;
import com.bonc.system.dao.mapper.SystemMapper;

@Service
@Transactional(rollbackFor = Exception.class)
public class SysOptService {
	
	protected final Logger logger = LoggerFactory.getLogger(SysOptService.class);

	@Resource
	private ProductInfoMapper mapper;
	
    @Resource
    private SystemMapper systemMapper;
    
    private volatile Map<String, SysInterfaceInfo> interfaceInfoCache;
	
    /**
     * 批量导入产品表数据，并对产品名称解密
     * @return
     */
	public Object insertRdVos() {
		long startTime = System.currentTimeMillis();
		this.systemMapper.delRdAllProd();
		List<ProductInfo> infos = this.mapper.getAllProd();
		int size1 = infos.size();
		int size2 = 0;
		List<ProductInfo> list = new ArrayList<>();
		for(ProductInfo vo : infos) {
			vo.setProdName(DpiUtils.strDecrypt(vo.getProdName()));
			size2++;
			list.add(vo);
			if(size2%10000 == 0 || size1 == size2) {
				this.systemMapper.insertRdVoBatch(list);
				logger.info("已导入dim_rd_ia_product_info表，记录条数：" + size2);
				try {
					Thread.sleep(5000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				list.clear();
			}
		}
		Map<String, String> resultObj = new HashMap<String, String>();
		resultObj.put("查询dim_ia_product_info表，记录条数：", size1+"");
		resultObj.put("导入dim_rd_ia_product_info表，记录条数：", size2+"");
		resultObj.put("耗时[S]：", (System.currentTimeMillis() - startTime)/1000 + "");
		return resultObj;
	}
	
    /**
     * 获取接口信息缓存
     * @return
     */
    public Map<String, SysInterfaceInfo> getInterfaceInfoCache() {
    	if(interfaceInfoCache == null) {
    		synchronized (this) {
    			if(interfaceInfoCache == null) {
    				List<SysInterfaceInfo> infos = this.systemMapper.getSysInterfaceInfo();
    				interfaceInfoCache = new HashMap<>();
    				for(SysInterfaceInfo info : infos) {
    					interfaceInfoCache.put(info.getClazz_name() + Constant._COMMON_STRING_SEG + info.getMethod_name(), info);
    				}
    			}
			}
    	}
    	return interfaceInfoCache;
    }
    
    /**
     * 根据参数获取接口信息
     * @param clazzName
     * @param methodName
     * @return
     */
    public SysInterfaceInfo getInterfaceInfo(String clazzName, String methodName) {
    	return this.getInterfaceInfoCache().get(clazzName + Constant._COMMON_STRING_SEG + methodName);
    }
	
}
