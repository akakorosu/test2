package com.bonc.dpi.entity;

import java.util.HashMap;
import java.util.Map;

public class MarketBushi {
    private String market_id;
    private String account_id;
    private String busi_code;
    private String busi_name;
    private String type_list;
    private String rule_list;
    private String keyword_hostlist;
    private String status;
    private String start_date;
    private String end_date;

    public String redisString(){
        return market_id+"|"+account_id+"|"+busi_code+"|"+busi_name+"|"+type_list+"|"+rule_list+"|"+keyword_hostlist+"|"+status+"|"+start_date+"|"+end_date;
    }

    public Map redisMap(){
        Map mapbushi = new HashMap();
        mapbushi.put(getMarket_id(),redisString());
        return mapbushi;
    }
    public String getMarket_id() {
        return market_id;
    }

    public void setMarket_id(String market_id) {
        this.market_id = market_id;
    }

    public String getAccount_id() {
        return account_id;
    }

    public void setAccount_id(String account_id) {
        this.account_id = account_id;
    }

    public String getBusi_code() {
        return busi_code;
    }

    public void setBusi_code(String busi_code) {
        this.busi_code = busi_code;
    }

    public String getBusi_name() {
        return busi_name;
    }

    public void setBusi_name(String busi_name) {
        this.busi_name = busi_name;
    }

    public String getType_list() {
        return type_list;
    }

    public void setType_list(String type_list) {
        this.type_list = type_list;
    }

    public String getRule_list() {
        return rule_list;
    }

    public void setRule_list(String rule_list) {
        this.rule_list = rule_list;
    }

    public String getKeyword_hostlist() {
        return keyword_hostlist;
    }

    public void setKeyword_hostlist(String keyword_hostlist) {
        this.keyword_hostlist = keyword_hostlist;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStart_date() {
        return start_date;
    }

    public void setStart_date(String start_date) {
        this.start_date = start_date;
    }

    public String getEnd_date() {
        return end_date;
    }

    public void setEnd_date(String end_date) {
        this.end_date = end_date;
    }
}
