package com.bonc.dpi.dao.entity;

/**
 * 停用词库
 * dim_ia_stop_word_lib
 * @author BONC-XUXL
 *
 */
public class StopWordLib extends OperationFlow{
	private String id;//
	private String stopWord;//stop_word,分类关键词
	private String author;//作者
	private String opTime;//op_time,操作时间
	private String startTime;
	private String endTime;
	
	
	public String getStartTime() {
		return startTime;
	}
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}
	public String getEndTime() {
		return endTime;
	}
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
	public String getStopWord() {
		return stopWord;
	}
	public void setStopWord(String stopWord) {
		this.stopWord = stopWord;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public String getOpTime() {
		return opTime;
	}
	public void setOpTime(String opTime) {
		this.opTime = opTime;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}

}
