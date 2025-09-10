package com.bonc.dpi.entity;

import java.util.HashMap;
import java.util.Map;

/**
 * 场景
 * @author sy
 *
 */
public class MarketingScene {
    private String id;
    private String scene_code;
    private String scene_text;
    private String app_codes;
    private String app_name;
    private String video_name;
    private String video_code;
    private String keys_strs;
    private String state_name;
    private String state_code;
    private String operator_code;
    private String operator_name;
    private String type_code;
    private String type_name;
    private String start_date;
    private String end_date;
    private String rule_code;
    private String rule_type;
    private String flow_max;
    private String go_times;
    private String flow_time_circle;
    private String busi_up_date;
    private String busi_down_date;
    private String busi_code;

    public Map<String,Object> getMap(){
        Map<String , Object> params = new HashMap<String, Object>();
        params.put("scene_code", this.getScene_code());
        params.put("scene_text", this.getScene_text());
        params.put("app_codes", this.getApp_codes());
        params.put("app_name", this.getApp_name());
        params.put("video_code", this.getVideo_code());
        params.put("video_name", this.getVideo_name());
        params.put("keys_strs", this.getKeys_strs());
        params.put("state_code", this.getState_code());
        params.put("state_name", this.getState_name());
        params.put("operator_code", this.getOperator_code());
        params.put("operator_name", this.getOperator_name());
        params.put("type_code", this.getType_code());
        params.put("type_name", this.getType_name());
        params.put("end_date", this.getEnd_date());
        params.put("start_date", this.getStart_date());
        params.put("rule_code", this.getRule_code());
        params.put("rule_type", this.getRule_type());
        params.put("flow_max", this.getFlow_max());
        params.put("go_times", this.getGo_times());
        params.put("flow_time_circle", this.getFlow_time_circle());
        params.put("busi_up_date", this.getBusi_up_date());
        params.put("busi_down_date", this.getBusi_down_date());
        return params;
    };
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getBusi_code() {
        return busi_code;
    }

    public void setBusi_code(String busi_code) {
        this.busi_code = busi_code;
    }
    public String getRule_type() {
        return rule_type;
    }

    public void setRule_type(String rule_type) {
        this.rule_type = rule_type;
    }

    public String getFlow_max() {
        return flow_max;
    }

    public void setFlow_max(String flow_max) {
        this.flow_max = flow_max;
    }

    public String getGo_times() {
        return go_times;
    }

    public void setGo_times(String go_times) {
        this.go_times = go_times;
    }

    public String getFlow_time_circle() {
        return flow_time_circle;
    }

    public void setFlow_time_circle(String flow_time_circle) {
        this.flow_time_circle = flow_time_circle;
    }

    public String getBusi_up_date() {
        return busi_up_date;
    }

    public void setBusi_up_date(String busi_up_date) {
        this.busi_up_date = busi_up_date;
    }

    public String getBusi_down_date() {
        return busi_down_date;
    }

    public void setBusi_down_date(String busi_down_date) {
        this.busi_down_date = busi_down_date;
    }

    public String getScene_code() {
        return scene_code;
    }

    public void setScene_code(String scene_code) {
        this.scene_code = scene_code;
    }

    public String getScene_text() {
        return scene_text;
    }

    public void setScene_text(String scene_text) {
        this.scene_text = scene_text;
    }

    public String getApp_codes() {
        return app_codes;
    }

    public void setApp_codes(String app_codes) {
        this.app_codes = app_codes;
    }

    public String getApp_name() {
        return app_name;
    }

    public void setApp_name(String app_name) {
        this.app_name = app_name;
    }

    public String getVideo_name() {
        return video_name;
    }

    public void setVideo_name(String video_name) {
        this.video_name = video_name;
    }

    public String getVideo_code() {
        return video_code;
    }

    public void setVideo_code(String video_code) {
        this.video_code = video_code;
    }

    public String getKeys_strs() {
        return keys_strs;
    }

    public void setKeys_strs(String keys_strs) {
        this.keys_strs = keys_strs;
    }

    public String getState_name() {
        return state_name;
    }

    public void setState_name(String state_name) {
        this.state_name = state_name;
    }

    public String getState_code() {
        return state_code;
    }

    public void setState_code(String state_code) {
        this.state_code = state_code;
    }

    public String getOperator_code() {
        return operator_code;
    }

    public void setOperator_code(String operator_code) {
        this.operator_code = operator_code;
    }

    public String getOperator_name() {
        return operator_name;
    }

    public void setOperator_name(String operator_name) {
        this.operator_name = operator_name;
    }

    public String getType_code() {
        return type_code;
    }

    public void setType_code(String type_code) {
        this.type_code = type_code;
    }

    public String getType_name() {
        return type_name;
    }

    public void setType_name(String type_name) {
        this.type_name = type_name;
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

    public String getRule_code() {
        return rule_code;
    }

    public void setRule_code(String rule_code) {
        this.rule_code = rule_code;
    }
}
