package com.bonc.activiti.dao.entity;

public class FlowResource {
    private String appid;

    private String deployid;

    private String processid;

    private String processname;

    private String appclassname;

    private String memo;

    public String getAppid() {
        return appid;
    }

    public void setAppid(String appid) {
        this.appid = appid == null ? null : appid.trim();
    }

    public String getDeployid() {
        return deployid;
    }

    public void setDeployid(String deployid) {
        this.deployid = deployid == null ? null : deployid.trim();
    }

    public String getProcessid() {
        return processid;
    }

    public void setProcessid(String processid) {
        this.processid = processid == null ? null : processid.trim();
    }

    public String getProcessname() {
        return processname;
    }

    public void setProcessname(String processname) {
        this.processname = processname == null ? null : processname.trim();
    }

    public String getAppclassname() {
        return appclassname;
    }

    public void setAppclassname(String appclassname) {
        this.appclassname = appclassname == null ? null : appclassname.trim();
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo == null ? null : memo.trim();
    }
}