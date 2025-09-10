package com.bonc.dpi.dao.mapper;

import java.util.List;
import java.util.Map;

import com.bonc.dpi.entity.*;
import org.apache.ibatis.annotations.Param;

public interface VideoStoreMapper {

	List<VideoWebInfo> selectWeblist();

	List<VideoContent> getVideoContent(Map<String, Object> map);

	List<VideoContent> getVideoContentNotLimited(Map<String, Object> map);

	List<VideoLabel> selectLabellist(Map<String, Object> map);

	int getVideoAmount(Map<String, Object> map);

	int exportData(Map<String, Object> map);

	int insertVideoQuery(Map<String, Object> map);

	int insertVideoLabelDm(@Param("videoQueries") List<VideoQuery> videoQueries);

	int countVideoQueryList();

	int deleteVideoLabelDm();

	List<VideoQuery> selectVideoQueryList(Map<String, Object> map);

	VideoQuery getExportTask(Map<String, Object> map);

	int delExportTask(Map<String, Object> map);

	int getExportLogNum(Map<String, Object> map);

    List<VideoCategoryInfo> selectCategorylist();

}
