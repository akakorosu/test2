package com.bonc.dpi.entity;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class MarketingHost {
    private String domain_code;
    private String prod_id;
    private String prod_name;

    public String getProd_id() {
        return prod_id;
    }

    public void setProd_id(String prod_id) {
        this.prod_id = prod_id;
    }

    public String getDomain_code() {
        return domain_code;
    }

    public void setDomain_code(String domain_code) {
        this.domain_code = domain_code;
    }
    public String getProd_name() {
        return prod_name;
    }

    public void setProd_name(String prod_name) {
        this.prod_name = prod_name;
    }
    public HashMap parameterMap(){
        HashMap map=new HashMap();
        map.put("prod_type_code","");
        map.put("prod_type_name","");
        map.put("host_rule",getDomain_code());
        map.put("prod_code",getProd_id());
        map.put("prod_name",getProd_name());
        map.put("rule_type",1);
        map.put("insert_date",DateUtil.formatDate(new Date()));
        return map;
    }
}
