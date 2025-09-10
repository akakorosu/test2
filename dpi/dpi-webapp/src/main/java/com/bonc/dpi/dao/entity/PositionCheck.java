package com.bonc.dpi.dao.entity;

public class PositionCheck extends OperationFlow{
  
    private String prodName;
    private String url;
    private String ruleUrl;
    private String host;
    private String matchType;
    
    private String longitudeRule; //经度规则
    private String getIndexLong;   // 匹配位置
    private String tLongResult;   // 匹配结果
    private String latitudeRule;  //维度规则
    private String getIndexLati;
    private String tLatiResult;
    private String system;
    private String groupType;
    private String author;
    private String opTime;
    
    
    private String ruleType;
   
    private String groupName;
    private String longResult;
    private String latiResult;
    private String startTime;
    private String endTime;
    private String num;
    private String state;
    private String prodid;
    
    
  

	public String getRuleUrl() {
		return ruleUrl;
	}

	public void setRuleUrl(String ruleUrl) {
		this.ruleUrl = ruleUrl;
	}

	public String getLongitudeRule() {
		return longitudeRule;
	}

	public void setLongitudeRule(String longitudeRule) {
		this.longitudeRule = longitudeRule;
	}

	public String getGetIndexLong() {
		return getIndexLong;
	}

	public void setGetIndexLong(String getIndexLong) {
		this.getIndexLong = getIndexLong;
	}

	public String gettLongResult() {
		return tLongResult;
	}

	public void settLongResult(String tLongResult) {
		this.tLongResult = tLongResult;
	}

	public String getLatitudeRule() {
		return latitudeRule;
	}

	public void setLatitudeRule(String latitudeRule) {
		this.latitudeRule = latitudeRule;
	}

	public String getGetIndexLati() {
		return getIndexLati;
	}

	public void setGetIndexLati(String getIndexLati) {
		this.getIndexLati = getIndexLati;
	}

	public String gettLatiResult() {
		return tLatiResult;
	}

	public void settLatiResult(String tLatiResult) {
		this.tLatiResult = tLatiResult;
	}

	public String getSystem() {
		return system;
	}

	public void setSystem(String system) {
		this.system = system;
	}

	public String getLongResult() {
		return longResult;
	}

	public void setLongResult(String longResult) {
		this.longResult = longResult;
	}

	public String getLatiResult() {
		return latiResult;
	}

	public void setLatiResult(String latiResult) {
		this.latiResult = latiResult;
	}



    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
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

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public String getMatchType() {
        return matchType;
    }

    public void setMatchType(String matchType) {
        this.matchType = matchType;
    }

    public String getRuleType() {
        return ruleType;
    }

    public void setRuleType(String ruleType) {
        this.ruleType = ruleType;
    }

    

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    

    

    public String getGroupType() {
        return groupType;
    }

    public void setGroupType(String groupType) {
        this.groupType = groupType;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
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

    

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    @Override
    public String toString() {
        return "DepthRuleCheck{" +
                "prodid='" + prodid + '\'' +
                ", prodName='" + prodName + '\'' +
                ", matchType='" + matchType + '\'' +
                ", ruleType='" + ruleType + '\'' +
              
                ", url='" + url + '\'' +
               
               
                ", groupType='" + groupType + '\'' +
                ", groupName='" + groupName + '\'' +
               
                ", author='" + author + '\'' +
                ", opTime='" + opTime + '\'' +
                ", host='" + host + '\'' +
                
                ", state='" + state + '\'' +
               
                ", startTime='" + startTime + '\'' +
                ", endTime='" + endTime + '\'' +
                ", num='" + num + '\'' +
                '}';
    }
}
