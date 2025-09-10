package com.bonc.dpi.dao.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import com.bonc.dpi.dao.entity.ContentLabel;
import com.bonc.dpi.dao.entity.OperationFlow;

public interface ContentLabelMapper {
	List<String> selectContentLabelName1();
	
	Integer selectDataNum(ContentLabel obj);
	
	List<ContentLabel> selectList (ContentLabel obj);
	
	Boolean insert(ContentLabel obj);
	
	Integer checkData(ContentLabel obj);
	
	ContentLabel selectById(String id);
	
	Boolean delete(ContentLabel obj);
	
	Boolean update(ContentLabel obj);
	Boolean updateContentLabelName(ContentLabel obj);
	
	Integer check(ContentLabel obj);
	List<ContentLabel>  selectLabelList(ContentLabel obj);
	
	ContentLabel selectVoByPrimaryKey(ContentLabel obj);
	void deleteVoByPrimaryKey(ContentLabel obj);
	/**
	 * 批量导入
	 * @param list
	 * @return
	 */
    Boolean insertVoPl(@Param("list")List<OperationFlow> list,@Param("database_type")String database_type);
    List<String> selectAllList();
}
