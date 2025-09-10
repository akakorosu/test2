package com.bonc.login.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bonc.login.dao.mapper.IndexMapper;
import com.bonc.workbench.dao.mapper.WorkOrderInfoMapper;
/**
 * @author 祝正佳
 *
 */
@Service
public class IndexService {
	@Autowired
	private IndexMapper indexDao;
	@Autowired 
	private WorkOrderInfoMapper wordOrderDao;
	public Integer selectBackLogCount(String userId){
		Integer result = wordOrderDao.selectBackLogCount(userId);
		if(null!=result){
			return result;
		}else{
			return 0;
		}
	}

}
