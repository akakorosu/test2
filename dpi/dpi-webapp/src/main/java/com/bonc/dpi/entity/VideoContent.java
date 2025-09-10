package com.bonc.dpi.entity;
/**
 * 视频库内容
 * @author sy
 *
 */

public class VideoContent {
	
	private String video_id;//视频id

	private String app_id;//网站id

	private String net_name;//网址名称
	
	private String video_name;//视频名称
	
	private String video_type;//视频分类	
	
	private String director;//导演
	
	private String actor;//演员
	
	private String spot;//区域
	
	private String language;//语言
	
	private String video_tag;//视频标签
	
	private String release_date;//上映日期
	
	private String video_rating;//评分
	
	private String view_counts;//浏览量

	
	public String getVideo_tag() {
		return video_tag;
	}

	public String getRelease_date() {
		return release_date;
	}

	public String getVideo_rating() {
		return video_rating;
	}

	public String getView_counts() {
		return view_counts;
	}


	public String getNet_name() {
		return net_name;
	}

	public String getVideo_name() {
		return video_name;
	}

	public String getVideo_type() {
		return video_type;
	}

	public String getDirector() {
		return director;
	}

	public String getActor() {
		return actor;
	}

	public String getSpot() {
		return spot;
	}

	public String getLanguage() {
		return language;
	}

	public void setNet_name(String net_name) {
		this.net_name = net_name;
	}

	public void setVideo_name(String video_name) {
		this.video_name = video_name;
	}

	public void setVideo_type(String video_type) {
		this.video_type = video_type;
	}

	public void setDirector(String director) {
		this.director = director;
	}

	public void setActor(String actor) {
		this.actor = actor;
	}

	public void setSpot(String spot) {
		this.spot = spot;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	public void setView_counts(String view_counts) {
		this.view_counts = view_counts;
	}

	public void setVideo_rating(String video_rating) {
		this.video_rating = video_rating;
	}

	public void setRelease_date(String release_date) {
		this.release_date = release_date;
	}

	public void setVideo_tag(String video_tag) {
		this.video_tag = video_tag;
	}
	
	public String getVideo_id() {
		return video_id;
	}

	public void setVideo_id(String video_id) {
		this.video_id = video_id;
	}

	public String getApp_id() {
		return app_id;
	}

	public void setApp_id(String app_id) {
		this.app_id = app_id;
	}

	@Override
	public String toString() {
		return "VideoContent{" +
				"video_id='" + video_id + '\'' +
				", app_id='" + app_id + '\'' +
				", net_name='" + net_name + '\'' +
				", video_name='" + video_name + '\'' +
				", video_type='" + video_type + '\'' +
				", director='" + director + '\'' +
				", actor='" + actor + '\'' +
				", spot='" + spot + '\'' +
				", language='" + language + '\'' +
				", video_tag='" + video_tag + '\'' +
				", release_date='" + release_date + '\'' +
				", video_rating='" + video_rating + '\'' +
				", view_counts='" + view_counts + '\'' +
				'}';
	}
}
