package com.bonc.dpi.service;

import java.io.File;
import java.io.IOException;
import java.util.*;

import javax.annotation.Resource;

import com.bonc.dpi.entity.*;
import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.bonc.dpi.dao.mapper.VideoStoreMapper;
//import com.sun.javafx.image.IntToBytePixelConverter;

@Service
public class VideoStoreService {

	private final static Logger logger = LoggerFactory.getLogger(VideoStoreService.class);

	@Resource
	private VideoStoreMapper videoStoreMapper;

	public List<VideoWebInfo> getWeblist(){
		List<VideoWebInfo> weblist = videoStoreMapper.selectWeblist();
		return weblist;
	}

	public int getVideoAmount(String net_id,String video_name,String video_type,String director,String actor,String spot,String language,String video_label) {
		Map<String , Object> params = new HashMap<String, Object>();
		params.put("net_id", net_id);
		params.put("video_name", video_name);
		params.put("video_type", video_type);
		params.put("director", director);
		params.put("actor", actor);
		params.put("spot", spot);
		params.put("language", language);
		params.put("video_label", video_label);
		int totalnum = videoStoreMapper.getVideoAmount(params);
		return totalnum;
	}

	public Map<String, Object> getVideoContentList(String pagenum, String net_id, String video_name, String video_type, String director, String actor, String spot, String language, String video_label){
		Map<String, Object> resultmap = new HashMap<String, Object>();
		int totalnum = getVideoAmount(net_id,video_name,video_type,director,actor,spot,language,video_label);
		Map<String , Object> params = new HashMap<String, Object>();
		params.put("net_id", net_id);
		params.put("video_name", video_name);
		params.put("video_type", video_type);
		params.put("video_label", video_label);
		params.put("director", director);
		params.put("actor", actor);
		params.put("spot", spot);
		params.put("language", language);
		int limitnum = 20;//每页显示条数
		int totalpages = (int)Math.ceil(totalnum/(limitnum*1.0));//总页数
		if(Integer.parseInt(pagenum) < 1) {
			pagenum = "1";
		}
		if(Integer.parseInt(pagenum) > totalpages) {
			pagenum =  Integer.toString(totalpages);
		}
		int startrow = 0;//开始行数
		if(Integer.parseInt(pagenum)>1) {
			startrow = (Integer.parseInt(pagenum) - 1) * limitnum;
		}
		params.put("startrow", startrow);
		params.put("limitnum", limitnum);
		System.out.println(params);
		List<VideoContent> videolist = videoStoreMapper.getVideoContent(params);
		resultmap.put("videolist", videolist);
		resultmap.put("totalpages", totalpages);
		resultmap.put("totalnum", totalnum);
		return resultmap;
	}

//	public Map<String, Object> exportVideoJob(String lablename,String user_id, String net_id, String video_name, String video_type,String video_label, String director, String actor, String spot, String language, String export_data) {
//		Map<String, Object> resultmap = new HashMap<String, Object>();
//		String op_time = DateUtil.getToday(DateUtil.DEFAULT_FORMAT_T);
//		Random r = new Random(1);
//		int ran = r.nextInt(100);
//		String task_id = user_id + op_time + ran;
//		Map<String , Object> params = new HashMap<String, Object>();
//		params.put("lablename", lablename);
//		params.put("task_id", task_id);
//		params.put("user_id", user_id);
//		params.put("op_time", op_time);
//		params.put("net_id", net_id);
//		params.put("video_name", video_name);
//		params.put("video_type", video_type);
//		params.put("video_label", video_label);
//		params.put("director", director);
//		params.put("actor", actor);
//		params.put("spot", spot);
//		params.put("language", language);
//		//同一个人相同的查询条件只能导入一次
//		int lognum = videoStoreMapper.getExportLog(params);
//		logger.info("lognum :" + lognum);
//		int exportnum = 0;
//		if(lognum == 0) {
//			exportnum = videoStoreMapper.exportData(params);
//			//插入条件查询日志表
//			videoStoreMapper.insertExportLog(params);
//			logger.info("视频库导入==="+ "操作人：" +user_id+" ,导入条数："+ exportnum);
//		}else {
//			logger.info("======重复条件禁止导入====");
//			logger.info("禁止条件：" + user_id + "、"+net_id + "、"+ video_name + "、"+ video_type + "、"+video_label+"、"+ director + "、"+ actor + "、"+ spot + "、"+ language);
//		}
//		List<VideoContent> videolist = videoStoreMapper.getVideoContentNotLimited(params);
////		System.out.println(JSON.toJSONString(videolist));
//		resultmap.put("exportnum", exportnum);
//		resultmap.put("lognum", lognum);
//		resultmap.put("task_id", task_id);
//		File file = new File("E://"+task_id+".txt");
//		try {
//			FileUtils.writeStringToFile(file, task_id+"\r\n\r\n"+"标签名:"+lablename+"\r\n\r\n"+"|video_name|director|video_rating|video_tag|video_type|video_label|actor|release_date|view_counts|spot|net_name|video_id|"+"\r\n"+Json2ArrayList(videolist));
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//		return resultmap;
//
//	}

	public Map<String, Object> exportVideoJob(String lablename,String user_id, String net_id, String video_type,String video_label, String director, String actor, String spot) {
		Map<String, Object> resultmap = new HashMap<String, Object>();
		Map<String , Object> param = new HashMap<String, Object>();
		param.put("flag_id", "");
		int i = videoStoreMapper.countVideoQueryList();
		String video_hj_id = "";
		if(i==0){
			video_hj_id = "hj_00000";
		}else {
			List<VideoQuery> videoQueries = videoStoreMapper.selectVideoQueryList(param);
			VideoQuery videoQuery = videoQueries.get(videoQueries.size() - 1);
			String vid = videoQuery.getVideo_hj_id();
			String s = vid.split("_")[1];
			int num = Integer.parseInt(s)+1;
			video_hj_id = "hj_"+String.format("%05d", num);
		}
		Map<String , Object> params = new HashMap<String, Object>();
		params.put("video_hj_id", video_hj_id);
		params.put("label_name", lablename);
		params.put("area_desc", spot);
		params.put("opr_id", user_id);
		params.put("net_id", net_id);
		params.put("video_label_id", video_label);
		params.put("actor", director);
		params.put("performer", actor);
		params.put("video_type", video_type);
		params.put("flag_id", "1");
		int result = videoStoreMapper.insertVideoQuery(params);
		if(result > 0) {
			resultmap.put("resultMsg", "导出成功！");
			resultmap.put("resultCode", 1);
		}else {
			resultmap.put("resultMsg", "导出失败！");
			resultmap.put("resultCode", 0);
		}
		return resultmap;

	}

	public void executeExportJob() {
		Map<String , Object> param = new HashMap<String, Object>();
		param.put("flag_id", "1");
		List<VideoQuery> videoQueries = videoStoreMapper.selectVideoQueryList(param);
		int i = videoStoreMapper.deleteVideoLabelDm();
		for(VideoQuery videoQuery : videoQueries){
			Map<String , Object> params = new HashMap<String, Object>();
			String act = videoQuery.getActor();
			String video_hj_id = videoQuery.getVideo_hj_id();
			String area_desc = videoQuery.getArea_desc();
			String label_name = videoQuery.getLabel_name();
			String nid = videoQuery.getNet_id();
			String performer = videoQuery.getPerformer();
			String video_label_id = videoQuery.getVideo_label_id();
			String videotype = videoQuery.getVideo_type();
			params.put("net_id", nid);
			params.put("video_type", videotype);
			params.put("video_label", video_label_id);
			params.put("director", act);
			params.put("actor", performer);
			params.put("spot", area_desc);
			int totalnum = getVideoAmount(nid,null,videotype,act,performer,area_desc,null,video_label_id);
			if(totalnum!=0) {
				params.put("startrow", 0);
				params.put("limitnum", totalnum);
				List<VideoContent> videolist = videoStoreMapper.getVideoContent(params);
				List<VideoQuery> videoQueryList = new ArrayList<>();
				for (VideoContent videoContent : videolist) {
					String video_id = videoContent.getVideo_id();
					VideoQuery vq = new VideoQuery();
					vq.setVideo_id(video_id);
					vq.setVideo_hj_id(video_hj_id);
					vq.setNet_id(nid);
					vq.setLabel_name(label_name);
					videoQueryList.add(vq);
				}
				int res = videoStoreMapper.insertVideoLabelDm(videoQueryList);
				if(res>0) {
					logger.info("视频内容库-视频集数据插入成功！");
				}else{
					logger.info("视频内容库-视频集数据插入失败！");
				}
			}
		}
	}

	//	public String Json2Csv(String json)  {//转前台json为字符串
////		JSONObject json = new JSONObject();
//		 ArrayList<HashMap> videoList = JSON.parseObject(json, new TypeReference<ArrayList<HashMap>>(){});
//		StringBuffer buffer = new StringBuffer();
//		System.out.println(videoList);
//		for(HashMap<String,Object> map:videoList) {
//			buffer.append("|");
//			for (Map.Entry<String, Object> entry : map.entrySet()) {
//				buffer.append(entry.getValue()+"|");
//			}
//			buffer.append("\r\n");
//		}
//		return buffer.toString();
//	}
	public String Json2ArrayList(List<VideoContent> videoList)  {//转ArrayList为字符串
		StringBuffer buffer = new StringBuffer();

		for(VideoContent video:videoList) {
//			System.out.println(video.getDirector());
			buffer.append("|"+video.getVideo_name()+"|");
			buffer.append(video.getDirector()+"|");
			buffer.append(video.getVideo_rating()+"|");
//			buffer.append(video.getLanguage()+"|");
			buffer.append(video.getVideo_tag()+"|");
			buffer.append(video.getVideo_type()+"|");
			buffer.append(video.getActor()+"|");
			buffer.append(video.getRelease_date()+"|");
			buffer.append(video.getView_counts()+"|");
			buffer.append(video.getSpot()+"|");
			buffer.append(video.getNet_name()+"|");
			buffer.append(video.getVideo_id()+"|");
			buffer.append("\r\n");
		}
		return buffer.toString();
	}

	public List<VideoCategoryInfo> getCategorylist() {
		List<VideoCategoryInfo> categorylist = videoStoreMapper.selectCategorylist();
		return categorylist;
	}

	public List<VideoLabel> getLabellist(Map<String , Object> params) {
		List<VideoLabel> labellist = videoStoreMapper.selectLabellist(params);
		return labellist;
	}

	public Integer getExportLogNum(String lablename) {
		Map<String , Object> params = new HashMap<String, Object>();
		params.put("lablename",lablename);
		return videoStoreMapper.getExportLogNum(params);
	}
}
