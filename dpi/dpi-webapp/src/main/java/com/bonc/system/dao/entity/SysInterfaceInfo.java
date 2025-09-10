package com.bonc.system.dao.entity;

public class SysInterfaceInfo {
	
	private String clazz_name;
	private String method_name;
	
	private String module_name;
	private String func_code;
	private String func_name;
	private String point_code;
	
	private String is_decrypt_userid;
	private String is_write_log;

	public String getClazz_name() {
		return clazz_name;
	}

	public void setClazz_name(String clazz_name) {
		this.clazz_name = clazz_name;
	}

	public String getMethod_name() {
		return method_name;
	}

	public void setMethod_name(String method_name) {
		this.method_name = method_name;
	}

	public String getModule_name() {
		return module_name;
	}

	public void setModule_name(String module_name) {
		this.module_name = module_name;
	}

	public String getFunc_code() {
		return func_code;
	}

	public void setFunc_code(String func_code) {
		this.func_code = func_code;
	}

	public String getFunc_name() {
		return func_name;
	}

	public void setFunc_name(String func_name) {
		this.func_name = func_name;
	}

	public String getPoint_code() {
		return point_code;
	}

	public void setPoint_code(String point_code) {
		this.point_code = point_code;
	}

	public String getIs_decrypt_userid() {
		return is_decrypt_userid;
	}

	public void setIs_decrypt_userid(String is_decrypt_userid) {
		this.is_decrypt_userid = is_decrypt_userid;
	}

	public String getIs_write_log() {
		return is_write_log;
	}

	public void setIs_write_log(String is_write_log) {
		this.is_write_log = is_write_log;
	}
	
}