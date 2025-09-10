package com.bonc.dpi.dao.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * 共用实体类
 * @author BONC-XUXL
 *
 */
public class BaseEntity implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String author;//作者
	private Date opTime;//操作时间
	private String isValid = "1";//是否有效
	private Integer totalCount;//访问次数
	
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public Date getOpTime() {
		return opTime;
	}
	public void setOpTime(Date opTime) {
		this.opTime = opTime;
	}
	public String getIsValid() {
		return isValid;
	}
	public void setIsValid(String isValid) {
		this.isValid = isValid;
	}
	public Integer getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(Integer totalCount) {
		this.totalCount = totalCount;
	}

	
}
