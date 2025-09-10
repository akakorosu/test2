package com.bonc.dpi.dao.entity;

/**
 * 分类词库
 * dim_ia_cata_word_ltb
 * @author BONC-XUXL
 *
 */
public class CataWordLtb extends OperationFlow{
	private String id;//
	private String cataLabel;//cata_label,分类标签
	private String cataWord;//cata_word,分类关键词
	private String author;//作者
	private String opTime;//op_time,操作时间
	
	
	private String cataLabelName;//标签名称
	private String cataLabelName1;//一级标签名称
	private String cataLabelName2;//二级标签名称
	
	private String catalabelparam;//参数，用于测试主键重复
	private String catawordparam;//参数，用于测试主键重复
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
	public String getCatalabelparam() {
		return catalabelparam;
	}
	public void setCatalabelparam(String catalabelparam) {
		this.catalabelparam = catalabelparam;
	}
	public String getCataLabel() {
		return cataLabel;
	}
	public void setCataLabel(String cataLabel) {
		this.cataLabel = cataLabel;
	}
	public String getCataWord() {
		return cataWord;
	}
	public void setCataWord(String cataWord) {
		this.cataWord = cataWord;
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
	public String getCataLabelName() {
		return cataLabelName;
	}
	public void setCataLabelName(String cataLabelName) {
		this.cataLabelName = cataLabelName;
	}
	public String getCataLabelName1() {
		return cataLabelName1;
	}
	public void setCataLabelName1(String cataLabelName1) {
		this.cataLabelName1 = cataLabelName1;
	}
	public String getCataLabelName2() {
		return cataLabelName2;
	}
	public void setCataLabelName2(String cataLabelName2) {
		this.cataLabelName2 = cataLabelName2;
	}
	public String getCatawordparam() {
		return catawordparam;
	}
	public void setCatawordparam(String catawordparam) {
		this.catawordparam = catawordparam;
	}
}
