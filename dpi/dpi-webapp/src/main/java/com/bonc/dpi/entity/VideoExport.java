package com.bonc.dpi.entity;
/**
 * 视频级内容表
 * @author sy
 *
 */

public class VideoExport {
	
	private String task_id;//任务id
	
	private String video_id;//视频id
	
	private String user_id;//用户id
	
	private String net_id;//网站id
	
	private String op_time;//操作时间

	public String getTask_id() {
		return task_id;
	}

	public String getVideo_id() {
		return video_id;
	}

	public String getUser_id() {
		return user_id;
	}

	public String getNet_id() {
		return net_id;
	}

	public String getOp_time() {
		return op_time;
	}

	public void setTask_id(String task_id) {
		this.task_id = task_id;
	}

	public void setVideo_id(String video_id) {
		this.video_id = video_id;
	}

	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}

	public void setNet_id(String net_id) {
		this.net_id = net_id;
	}

	public void setOp_time(String op_time) {
		this.op_time = op_time;
	}
	
	
	

}
