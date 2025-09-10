package com.bonc.dpi.dao.entity;

/**
 * 分词词库统计情况
 * dm_d_lexicon_info
 * @author BONC-XUXL
 *
 */
public class DmDLexiconInfo {
	
	private String dateId ;//date_Id 帐期
	private String activeSensitive ;//active_sensitive 活跃敏感词
	private String addSensitive ;//add_sensitive 新增敏感词
	private String activeWords ;//active_words 活跃分词
	private String addWords ;//add_words 新增分词
	private String activeWordsType ;//active_words_type 活跃分类词库
	private String addWordsType ;//add_words_type 新增分类词库
	
	private String activeStop ;//active_words_type 活跃停用词词库
	private String addStop ;//add_words_type 新增停用词词库
	
	public String getDateId() {
		return dateId;
	}
	public void setDateId(String dateId) {
		this.dateId = dateId;
	}
	public String getActiveSensitive() {
		return activeSensitive;
	}
	public void setActiveSensitive(String activeSensitive) {
		this.activeSensitive = activeSensitive;
	}
	public String getAddSensitive() {
		return addSensitive;
	}
	public void setAddSensitive(String addSensitive) {
		this.addSensitive = addSensitive;
	}
	public String getActiveWords() {
		return activeWords;
	}
	public void setActiveWords(String activeWords) {
		this.activeWords = activeWords;
	}
	public String getAddWords() {
		return addWords;
	}
	public void setAddWords(String addWords) {
		this.addWords = addWords;
	}
	public String getActiveWordsType() {
		return activeWordsType;
	}
	public void setActiveWordsType(String activeWordsType) {
		this.activeWordsType = activeWordsType;
	}
	public String getAddWordsType() {
		return addWordsType;
	}
	public void setAddWordsType(String addWordsType) {
		this.addWordsType = addWordsType;
	}
	public String getActiveStop() {
		return activeStop;
	}
	public void setActiveStop(String activeStop) {
		this.activeStop = activeStop;
	}
	public String getAddStop() {
		return addStop;
	}
	public void setAddStop(String addStop) {
		this.addStop = addStop;
	}
	
}