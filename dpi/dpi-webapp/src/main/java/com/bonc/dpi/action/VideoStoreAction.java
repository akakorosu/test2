package com.bonc.dpi.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import com.bonc.dpi.entity.VideoCategoryInfo;
import com.bonc.dpi.entity.VideoLabel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.bonc.common.cst.CST;
import com.bonc.dpi.entity.VideoWebInfo;
import com.bonc.dpi.service.VideoStoreService;
import com.bonc.system.dao.entity.SysUser;





import javax.servlet.http.HttpServletRequest;
@Controller
public class VideoStoreAction {

	private final static Logger logger = LoggerFactory.getLogger(VideoStoreAction.class);

	@Resource
	private VideoStoreService videoStoreService;

	@RequestMapping(value = "/video/initparam",method=RequestMethod.POST, produces="application/json;charset=UTF-8")
	@ResponseBody
	public Map<String, Object> initparam() {
		Map<String, Object> resultmap = new HashMap<String, Object>();
		List<VideoWebInfo> weblist = videoStoreService.getWeblist();
		List<VideoCategoryInfo> categorylist = videoStoreService.getCategorylist();
		resultmap.put("weblist", weblist);
		resultmap.put("categorylist", categorylist);
		return resultmap;
	}

	@RequestMapping(value = "/video/labellist")
	@ResponseBody
	public Map<String, Object> labellist(@RequestParam(value="videotypename")String videotypename) {
		Map<String , Object> params = new HashMap<String, Object>();
		Map<String, Object> resultmap = new HashMap<String, Object>();
		params.put("video_type_name",videotypename);
		List<VideoLabel> labellist = videoStoreService.getLabellist(params);
		resultmap.put("labellist", labellist);
		return resultmap;
	}

	@RequestMapping(value = "/video/getExportLogNum")
	@ResponseBody
	public Map<String, Object> getExportLogNum(@RequestParam(value="lablename")String lablename) {
		Map<String, Object> resultmap = new HashMap<String, Object>();
		Integer count = videoStoreService.getExportLogNum(lablename);
		resultmap.put("count", count);
		return resultmap;
	}
	@RequestMapping(value = "/video/getVideoTotalnum")
	@ResponseBody
	public Map<String, Object> getVideoTotalnum(@RequestParam(value="net_id",required = false)String net_id,
			@RequestParam(value="video_name",required = false)String video_name,
			@RequestParam(value="video_type",required = false)String video_type,
			@RequestParam(value="video_label",required = false)String video_label,
			@RequestParam(value="director",required = false)String director,
			@RequestParam(value="actor",required = false)String actor,
			@RequestParam(value="spot",required = false)String spot,
			@RequestParam(value="language",required = false)String language) {
		System.out.println(video_name);
		Map<String, Object> resultmap = new HashMap<String, Object>();
		int totalnum = videoStoreService.getVideoAmount(net_id,video_name,video_type,director,actor,spot,language,video_label);
		resultmap.put("totalnum", totalnum);
		return resultmap;
	}

	@RequestMapping(value = "/video/getVideoList")
	@ResponseBody
	public Map<String, Object> getVideoList(@RequestParam(value="pagenum",required=false, defaultValue="1")String pagenum,
			@RequestParam(value="net_id",required = false)String net_id,
			@RequestParam(value="video_name",required = false)String video_name,
			@RequestParam(value="video_type",required = false)String video_type,
			@RequestParam(value="director",required = false)String director,
			@RequestParam(value="actor",required = false)String actor,
			@RequestParam(value="spot",required = false)String spot,
			@RequestParam(value="language",required = false)String language,
			@RequestParam(value="video_label",required = false)String video_label){
		Map<String, Object> resultmap = new HashMap<String, Object>();
		try {
			resultmap = videoStoreService.getVideoContentList(pagenum, net_id, video_name, video_type, director, actor, spot, language, video_label);
			System.out.println("resultmap:"+resultmap);
			int totalNum = (int) resultmap.get("totalnum");
			if(totalNum == 0) {
				resultmap.put("resultCode", "2");
				resultmap.put("resultMsg", "查询无数据！");
				logger.info("查询无数据！");
			}else {
				resultmap.put("resultCode", "1");
				resultmap.put("resultMsg", "查询成功！");
				logger.info("查询成功!");
			}
		} catch (Exception e) {
			resultmap.put("resultCode", "2");
			resultmap.put("resultMsg", "查询异常！");
			e.printStackTrace();
		}
		return resultmap;
	}


	@RequestMapping(value = "/video/exportJob")
	@ResponseBody
	public Map<String, Object> exportJob (
			@RequestParam(value="net_id",required = false)String net_id,
			@RequestParam(value="lablename",required = false)String lablename,
			@RequestParam(value="video_type",required = false)String video_type,
			@RequestParam(value="video_label",required = false)String video_label,
			@RequestParam(value="director",required = false)String director,
			@RequestParam(value="actor",required = false)String actor,
			@RequestParam(value="spot",required = false)String spot) {
		Map<String, Object> resultmap = new HashMap<String, Object>();
		String resultMsg = "";
		String resultCode = "";
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
		SysUser user = (SysUser) request.getSession().getAttribute(CST.SESSION_SYS_USER_INFO);
		String user_id = user.getUserId();
		resultmap = videoStoreService.exportVideoJob(lablename,user_id, net_id, video_type, video_label, director, actor, spot);
		videoStoreService.executeExportJob();
		return resultmap;
	}

	@Scheduled(cron="0 0 12 * * ?")
	public void exportJob () {
		logger.info("=============定时导出功能开始============");
		videoStoreService.executeExportJob();
		logger.info("=============定时导出功能结束============");
	}

}
