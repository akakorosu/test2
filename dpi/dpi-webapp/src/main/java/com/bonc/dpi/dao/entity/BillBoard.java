package com.bonc.dpi.dao.entity;

public class BillBoard {
    private String id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    private String keyWord;
    private String area;
    private String userTotal;
    private String dateId;
    private String user;
    private String prodid;
    private String prodName;

    private String contentLabelCode;
    private String contentLabelName;
    private String contentLabelLevel;
    private String contentLabelCode1;
    private String contentLabelName1;
    private String contentLabelCode2;
    private String contentLabelName2;
    private String keyWordCode;

    private String cityId;
    private String monthId;
    private String queryMapFlag = "0";
    private String enddate;
    private String startdate;

    public String getEnddate() {
        return enddate;
    }

    public void setEnddate(String enddate) {
        this.enddate = enddate;
    }

    public String getStartdate() {
        return startdate;
    }

    public void setStartdate(String startdate) {
        this.startdate = startdate;
    }

    public BillBoard() {
    	super();
    }

//    private String contentLabelCode3;
//    private String contentLabelName3;
//    private String contentLabelCode4;
//    private String contentLabelName4;
//    private String contentLabelCode5;
//    private String contentLabelName5;
//    private String contentLabelCode6;
//    private String contentLabelName6;


    public BillBoard(String cityId, String monthId) {
		super();
		this.cityId = cityId;
		this.monthId = monthId;
	}

	public String getKeyWordCode() {
        return keyWordCode;
    }

    public void setKeyWordCode(String keyWordCode) {
        this.keyWordCode = keyWordCode;
    }

    public String getKeyWord() {
        return keyWord;
    }

    public void setKeyWord(String keyWord) {
        this.keyWord = keyWord;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getUserTotal() {
        return userTotal;
    }

    public void setUserTotal(String userTotal) {
        this.userTotal = userTotal;
    }

    public String getDateId() {
        return dateId;
    }

    public void setDateId(String dateId) {
        this.dateId = dateId;
    }

    public String getProdid() {
        return prodid;
    }

    public void setProdid(String prodid) {
        this.prodid = prodid;
    }

    public String getProdName() {
        return prodName;
    }

    public void setProdName(String prodName) {
        this.prodName = prodName;
    }

    public String getContentLabelCode() {
        return contentLabelCode;
    }

    public void setContentLabelCode(String contentLabelCode) {
        this.contentLabelCode = contentLabelCode;
    }

    public String getContentLabelName() {
        return contentLabelName;
    }

    public void setContentLabelName(String contentLabelName) {
        this.contentLabelName = contentLabelName;
    }

    public String getContentLabelLevel() {
        return contentLabelLevel;
    }

    public void setContentLabelLevel(String contentLabelLevel) {
        this.contentLabelLevel = contentLabelLevel;
    }

    public String getContentLabelCode1() {
        return contentLabelCode1;
    }

    public void setContentLabelCode1(String contentLabelCode1) {
        this.contentLabelCode1 = contentLabelCode1;
    }

    public String getContentLabelName1() {
        return contentLabelName1;
    }

    public void setContentLabelName1(String contentLabelName1) {
        this.contentLabelName1 = contentLabelName1;
    }

    public String getContentLabelCode2() {
        return contentLabelCode2;
    }

    public void setContentLabelCode2(String contentLabelCode2) {
        this.contentLabelCode2 = contentLabelCode2;
    }

    public String getContentLabelName2() {
        return contentLabelName2;
    }

    public void setContentLabelName2(String contentLabelName2) {
        this.contentLabelName2 = contentLabelName2;
    }

	public String getCityId() {
		return cityId;
	}

	public void setCityId(String cityId) {
		this.cityId = cityId;
	}

	public String getMonthId() {
		return monthId;
	}

	public void setMonthId(String monthId) {
		this.monthId = monthId;
	}

	public String getQueryMapFlag() {
		return queryMapFlag;
	}

	public void setQueryMapFlag(String queryMapFlag) {
		this.queryMapFlag = queryMapFlag;
	}

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    @Override
	public String toString() {
		return "BillBoard [keyWord=" + keyWord + ", area=" + area + ", userTotal=" + userTotal + ", dateId=" + dateId
				+ ", prodid=" + prodid + ", prodName=" + prodName + ", contentLabelCode=" + contentLabelCode
				+ ", contentLabelName=" + contentLabelName + ", contentLabelLevel=" + contentLabelLevel
				+ ", contentLabelCode1=" + contentLabelCode1 + ", contentLabelName1=" + contentLabelName1
				+ ", contentLabelCode2=" + contentLabelCode2 + ", contentLabelName2=" + contentLabelName2
				+ ", keyWordCode=" + keyWordCode + ", cityId=" + cityId + ", monthId=" + monthId + "]";
	}

}
