package com.bonc.dpi.service.i;

import java.util.List;

import com.bonc.dpi.dao.entity.OperationFlow;
import com.bonc.dpi.dao.entity.OperationFlowExportReturn;
import com.bonc.dpi.dao.entity.OperationFlowImportReturn;

public interface OperationFlowServiceI {
	
	/**
	 * 导出，返回为查询的结果list
	 * @param o
	 * @return
	 * @throws Exception
	 */
	OperationFlowExportReturn doExport(OperationFlow o) throws Exception;
	
	/**
	 * 导入，返回为导入失败的list
	 * @param list
	 * @param type（1：excel，2：txt）
	 * @return
	 * @throws Exception
	 */
	OperationFlowImportReturn doImport(List<OperationFlow> list,String type) throws Exception;
	
	/**
	 * 批量导出数据条数
	 * @param o
	 * @param type（1：excel，2：txt）
	 * @return
	 * @throws Exception
	 */
	Integer exportDataNum(OperationFlow o,String type) throws Exception;
}
