package com.bonc.dpi.dao.mapper;

import java.util.List;
import com.bonc.dpi.dao.entity.ApplicationRate;
import org.apache.ibatis.annotations.SelectProvider;

public interface ApplicationRateMapper {
	@SelectProvider(type = ApplicationRateMapperSql.class, method = "selectApplicationData")
	List<ApplicationRate> selectApplicationData(ApplicationRate vo);

	@SelectProvider(type = ApplicationRateMapperSql.class, method = "selectList")
	List<ApplicationRate> selectList(ApplicationRate vo);


	@SelectProvider(type = ApplicationRateMapperSql.class, method = "selectExportList")
	List<ApplicationRate> selectExportList(ApplicationRate vo);
	
}
