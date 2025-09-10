package com.bonc.dpi.entity;

import java.util.HashMap;
import java.util.Map;

public class MarketBushiCondition {
    private String condition_id;
    private String busi_code;
    private String condition_type;
    private String send_num;
    private String send_rate_unit;
    private String send_rate_value;
    private String field_name;
    private String sum_field_unit;
    private String sum_field_value;
    private String sum_time_unit;
    private String sum_time_value;
    private String status;
    private String send_start_date;
    private String send_end_date;
    private String start_date;
    private String end_date;
    public String redisString(){
        return condition_id+"|"+busi_code+"|"+condition_type+"|"+send_num+"|"+send_rate_unit+"|"+send_rate_value+"|"+field_name+"|"+sum_field_unit+"|"+sum_field_value+"|"+sum_time_unit+"|"+sum_time_value+"|"+status+"|"+send_start_date+"|"+send_end_date+"|"+start_date+"|"+end_date;
    }

    public Map redisMap(){
        Map mapbushi = new HashMap();
        mapbushi.put(getCondition_id(),redisString());
        return mapbushi;
    }
    public String getCondition_id() {
        return condition_id;
    }

    public void setCondition_id(String condition_id) {
        this.condition_id = condition_id;
    }

    public String getBusi_code() {
        return busi_code;
    }

    public void setBusi_code(String busi_code) {
        this.busi_code = busi_code;
    }

    public String getCondition_type() {
        return condition_type;
    }

    public void setCondition_type(String condition_type) {
        this.condition_type = condition_type;
    }

    public String getSend_num() {
        return send_num;
    }

    public void setSend_num(String send_num) {
        this.send_num = send_num;
    }

    public String getSend_rate_unit() {
        return send_rate_unit;
    }

    public void setSend_rate_unit(String send_rate_unit) {
        this.send_rate_unit = send_rate_unit;
    }

    public String getSend_rate_value() {
        return send_rate_value;
    }

    public void setSend_rate_value(String send_rate_value) {
        this.send_rate_value = send_rate_value;
    }

    public String getField_name() {
        return field_name;
    }

    public void setField_name(String field_name) {
        this.field_name = field_name;
    }

    public String getSum_field_unit() {
        return sum_field_unit;
    }

    public void setSum_field_unit(String sum_field_unit) {
        this.sum_field_unit = sum_field_unit;
    }

    public String getSum_field_value() {
        return sum_field_value;
    }

    public void setSum_field_value(String sum_field_value) {
        this.sum_field_value = sum_field_value;
    }

    public String getSum_time_unit() {
        return sum_time_unit;
    }

    public void setSum_time_unit(String sum_time_unit) {
        this.sum_time_unit = sum_time_unit;
    }

    public String getSum_time_value() {
        return sum_time_value;
    }

    public void setSum_time_value(String sum_time_value) {
        this.sum_time_value = sum_time_value;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getSend_start_date() {
        return send_start_date;
    }

    public void setSend_start_date(String send_start_date) {
        this.send_start_date = send_start_date;
    }

    public String getSend_end_date() {
        return send_end_date;
    }

    public void setSend_end_date(String send_end_date) {
        this.send_end_date = send_end_date;
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
