package com.bonc.dpi.dao.entity;

/**
 * 分类词库
 * dim_ia_cata_word_ltb
 * @author BONC-XUXL
 *
 */
public class CapabilityView extends OperationFlow{
	private String id;//
	private String labelName;//6大分类
	private String labelName1;//一级分类
	private String labelName2;//二级分类
	private String labelCode1;//一级分类code
	private String labelCode2;//二级分类code
	private String contentLabelCode1;
	private String allNum;
	private String oneNum;
	private String twoNum;
	private String threeNum;
	private String fourNum;
	private String fiveNum;
	private String prodName;
	private String contentType;
	private String value;
	private String lvl1;
	private String lvl2;
	private String modelName;
	private String modelInfo;
	private String labelLvl;
	private String prodLabelCode;
	private String prodSearch1;
	private String prodSearch2;
	private String profName1;
	private String profName2;
	private String bak;
	private String childSch;
	private String bigType;
	
	private String chineseName;
	private String foreignName;
	private String anotherName;
	private String gender;  //性别
	private String birth;
	private String death;
	private String nativePlace;
	private String nationality;
	private String height;
	private String weight;
	private String school;
	private String nation;
	private String brithPalce;
	private String bloodType;
	private String job;
	private String representativeWorks;
	private String brokeragefirm;
	private String spouse;
	private String constellation;
	private String achievement;
	private String total;
	//private String nativePlace;
	private String nowPage;
	private String pageSize;
	private String district; //地区
	private String startNum;
	private String endNum;
	private String year;
	private String workName;
	private String duty;
	private String type;
	
	private String contentLabelCode;
	private String contentLabelName;
	//private String contentLabelCode1;
	private String contentLabelName1;
	private String contentLabelCode2;
	private String contentLabelName2;
	private String contentLabelCode3;
	private String contentLabelName3;
	private String contentLabelCode4;
	private String contentLabelName4;
	private String contentLabelCode5;
	private String contentLabelName5;
	private String contentLabelCode6;
	private String contentLabelName6;
	
	
	
	
	
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
	public String getContentLabelCode3() {
		return contentLabelCode3;
	}
	public void setContentLabelCode3(String contentLabelCode3) {
		this.contentLabelCode3 = contentLabelCode3;
	}
	public String getContentLabelName3() {
		return contentLabelName3;
	}
	public void setContentLabelName3(String contentLabelName3) {
		this.contentLabelName3 = contentLabelName3;
	}
	public String getContentLabelCode4() {
		return contentLabelCode4;
	}
	public void setContentLabelCode4(String contentLabelCode4) {
		this.contentLabelCode4 = contentLabelCode4;
	}
	public String getContentLabelName4() {
		return contentLabelName4;
	}
	public void setContentLabelName4(String contentLabelName4) {
		this.contentLabelName4 = contentLabelName4;
	}
	public String getContentLabelCode5() {
		return contentLabelCode5;
	}
	public void setContentLabelCode5(String contentLabelCode5) {
		this.contentLabelCode5 = contentLabelCode5;
	}
	public String getContentLabelName5() {
		return contentLabelName5;
	}
	public void setContentLabelName5(String contentLabelName5) {
		this.contentLabelName5 = contentLabelName5;
	}
	public String getContentLabelCode6() {
		return contentLabelCode6;
	}
	public void setContentLabelCode6(String contentLabelCode6) {
		this.contentLabelCode6 = contentLabelCode6;
	}
	public String getContentLabelName6() {
		return contentLabelName6;
	}
	public void setContentLabelName6(String contentLabelName6) {
		this.contentLabelName6 = contentLabelName6;
	}
	public String getYear() {
		return year;
	}
	public void setYear(String year) {
		this.year = year;
	}
	public String getWorkName() {
		return workName;
	}
	public void setWorkName(String workName) {
		this.workName = workName;
	}
	public String getDuty() {
		return duty;
	}
	public void setDuty(String duty) {
		this.duty = duty;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getStartNum() {
		return startNum;
	}
	public void setStartNum(String startNum) {
		this.startNum = startNum;
	}
	public String getEndNum() {
		return endNum;
	}
	public void setEndNum(String endNum) {
		this.endNum = endNum;
	}
	public String getDistrict() {
		return district;
	}
	public void setDistrict(String district) {
		this.district = district;
	}
	public String getTotal() {
		return total;
	}
	public void setTotal(String total) {
		this.total = total;
	}
	public String getNowPage() {
		return nowPage;
	}
	public void setNowPage(String nowPage) {
		this.nowPage = nowPage;
	}
	public String getPageSize() {
		return pageSize;
	}
	public void setPageSize(String pageSize) {
		this.pageSize = pageSize;
	}
	public String getAnotherName() {
		return anotherName;
	}
	public void setAnotherName(String anotherName) {
		this.anotherName = anotherName;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getBirth() {
		return birth;
	}
	public void setBirth(String birth) {
		this.birth = birth;
	}
	public String getDeath() {
		return death;
	}
	public void setDeath(String death) {
		this.death = death;
	}
	public String getNativePlace() {
		return nativePlace;
	}
	public void setNativePlace(String nativePlace) {
		this.nativePlace = nativePlace;
	}
	public String getNationality() {
		return nationality;
	}
	public void setNationality(String nationality) {
		this.nationality = nationality;
	}
	public String getHeight() {
		return height;
	}
	public void setHeight(String height) {
		this.height = height;
	}
	public String getWeight() {
		return weight;
	}
	public void setWeight(String weight) {
		this.weight = weight;
	}
	public String getSchool() {
		return school;
	}
	public void setSchool(String school) {
		this.school = school;
	}
	public String getNation() {
		return nation;
	}
	public void setNation(String nation) {
		this.nation = nation;
	}
	public String getBrithPalce() {
		return brithPalce;
	}
	public void setBrithPalce(String brithPalce) {
		this.brithPalce = brithPalce;
	}
	public String getBloodType() {
		return bloodType;
	}
	public void setBloodType(String bloodType) {
		this.bloodType = bloodType;
	}
	public String getJob() {
		return job;
	}
	public void setJob(String job) {
		this.job = job;
	}
	public String getRepresentativeWorks() {
		return representativeWorks;
	}
	public void setRepresentativeWorks(String representativeWorks) {
		this.representativeWorks = representativeWorks;
	}
	public String getBrokeragefirm() {
		return brokeragefirm;
	}
	public void setBrokeragefirm(String brokeragefirm) {
		this.brokeragefirm = brokeragefirm;
	}
	public String getSpouse() {
		return spouse;
	}
	public void setSpouse(String spouse) {
		this.spouse = spouse;
	}
	public String getConstellation() {
		return constellation;
	}
	public void setConstellation(String constellation) {
		this.constellation = constellation;
	}
	public String getAchievement() {
		return achievement;
	}
	public void setAchievement(String achievement) {
		this.achievement = achievement;
	}
	public String getChineseName() {
		return chineseName;
	}
	public void setChineseName(String chineseName) {
		this.chineseName = chineseName;
	}
	public String getForeignName() {
		return foreignName;
	}
	public void setForeignName(String foreignName) {
		this.foreignName = foreignName;
	}
	public String getBigType() {
		return bigType;
	}
	public void setBigType(String bigType) {
		this.bigType = bigType;
	}
	public String getChildSch() {
		return childSch;
	}
	public void setChildSch(String childSch) {
		this.childSch = childSch;
	}
	public String getProfName1() {
		return profName1;
	}
	public void setProfName1(String profName1) {
		this.profName1 = profName1;
	}
	public String getProfName2() {
		return profName2;
	}
	public void setProfName2(String profName2) {
		this.profName2 = profName2;
	}
	public String getBak() {
		return bak;
	}
	public void setBak(String bak) {
		this.bak = bak;
	}
	public String getProdSearch1() {
		return prodSearch1;
	}
	public void setProdSearch1(String prodSearch1) {
		this.prodSearch1 = prodSearch1;
	}
	public String getProdSearch2() {
		return prodSearch2;
	}
	public void setProdSearch2(String prodSearch2) {
		this.prodSearch2 = prodSearch2;
	}
	public String getProdLabelCode() {
		return prodLabelCode;
	}
	public void setProdLabelCode(String prodLabelCode) {
		this.prodLabelCode = prodLabelCode;
	}
	public String getLabelLvl() {
		return labelLvl;
	}
	public void setLabelLvl(String labelLvl) {
		this.labelLvl = labelLvl;
	}
	public String getLvl1() {
		return lvl1;
	}
	public void setLvl1(String lvl1) {
		this.lvl1 = lvl1;
	}
	public String getLvl2() {
		return lvl2;
	}
	public void setLvl2(String lvl2) {
		this.lvl2 = lvl2;
	}
	public String getModelName() {
		return modelName;
	}
	public void setModelName(String modelName) {
		this.modelName = modelName;
	}
	public String getModelInfo() {
		return modelInfo;
	}
	public void setModelInfo(String modelInfo) {
		this.modelInfo = modelInfo;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public String getContentType() {
		return contentType;
	}
	public void setContentType(String contentType) {
		this.contentType = contentType;
	}
	public String getProdName() {
		return prodName;
	}
	public void setProdName(String prodName) {
		this.prodName = prodName;
	}
	public String getContentLabelCode1() {
		return contentLabelCode1;
	}
	public void setContentLabelCode1(String contentLabelCode1) {
		this.contentLabelCode1 = contentLabelCode1;
	}
	public String getAllNum() {
		return allNum;
	}
	public void setAllNum(String allNum) {
		this.allNum = allNum;
	}
	public String getOneNum() {
		return oneNum;
	}
	public void setOneNum(String oneNum) {
		this.oneNum = oneNum;
	}
	public String getTwoNum() {
		return twoNum;
	}
	public void setTwoNum(String twoNum) {
		this.twoNum = twoNum;
	}
	public String getThreeNum() {
		return threeNum;
	}
	public void setThreeNum(String threeNum) {
		this.threeNum = threeNum;
	}
	public String getFourNum() {
		return fourNum;
	}
	public void setFourNum(String fourNum) {
		this.fourNum = fourNum;
	}
	
	public String getFiveNum() {
		return fiveNum;
	}
	public void setFiveNum(String fiveNum) {
		this.fiveNum = fiveNum;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getLabelName() {
		return labelName;
	}
	public void setLabelName(String labelName) {
		this.labelName = labelName;
	}
	public String getLabelName1() {
		return labelName1;
	}
	public void setLabelName1(String labelName1) {
		this.labelName1 = labelName1;
	}
	public String getLabelName2() {
		return labelName2;
	}
	public void setLabelName2(String labelName2) {
		this.labelName2 = labelName2;
	}
	public String getLabelCode1() {
		return labelCode1;
	}
	public void setLabelCode1(String labelCode1) {
		this.labelCode1 = labelCode1;
	}
	public String getLabelCode2() {
		return labelCode2;
	}
	public void setLabelCode2(String labelCode2) {
		this.labelCode2 = labelCode2;
	}
	
	
	
	
	
	
}
