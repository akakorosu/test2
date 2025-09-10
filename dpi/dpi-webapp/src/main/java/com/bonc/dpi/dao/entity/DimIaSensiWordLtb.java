package com.bonc.dpi.dao.entity;
/**
 * 
 * @author dragon
 *
 */
public class DimIaSensiWordLtb  extends OperationFlow{
	private String id = null;
	private String sensiWord = null;
	private String author = null;
	private String opTime = null;
	private String startTime;
	private String endTime;

	@Override
	public String toString() {
		return "DimIaSensiWordLtb  id=" + id + ", sensiWord=" + sensiWord + ", author=" + author + ", opTime=" + opTime;	
	}

	
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



	public String getSensiWord() {
		return sensiWord;
	}



	public void setSensiWord(String sensiWord) {
		this.sensiWord = sensiWord;
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
