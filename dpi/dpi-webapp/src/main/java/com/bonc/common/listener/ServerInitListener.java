package com.bonc.common.listener;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import com.bonc.common.service.activity.listener.task.SpringContextHolder;
import com.bonc.system.dao.entity.SysCode;
import com.bonc.system.service.SysCodeService;
import com.bonc.system.service.SysCodeUtils;

public class ServerInitListener implements ServletContextListener {

	public void contextDestroyed(ServletContextEvent arg0) {
		// TODO Auto-generated method stub

	}

	/**
	 * 容器初始化
	 */
	public void contextInitialized(ServletContextEvent sce) {
//		RoleService roleService = SpringContextHolder.getBean(RoleService.class);
//		roleService.synchronizationMenuTree();
		Properties props = new Properties();
		InputStream inputStream = null;
		try {
			inputStream = getClass().getResourceAsStream("/sysConfig.properties");
			props.load(inputStream);
		} catch (IOException ex) {
			ex.printStackTrace();
		}
		SysCodeService sysCodeService = SpringContextHolder.getBean(SysCodeService.class);
		List<SysCode> sysCodeListTree = sysCodeService.selectTree(null);
		Map<String, SysCode> sysCodeMap = sysCodeService.selectMap(null);
		SysCodeUtils.SYS_CODE_LIST_TREE = sysCodeListTree;
		SysCodeUtils.SYS_CODE_MAP = sysCodeMap;
	}

}
