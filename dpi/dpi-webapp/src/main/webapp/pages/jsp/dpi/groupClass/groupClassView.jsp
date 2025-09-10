<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="cxt" uri="http://www.bonc.com.cn/common/tag/cxt"%>
<!-- 编辑表单div -->
<div id="upForm">
	<div class="clearfix formGroup">
		<label class="col-sm-2 labelForm">集团大类：</label> 
		<div class="col-sm-4">
			<input value="${vo.classCode }" type="text" readonly="readonly" />
		</div>
		<label class="col-sm-2 labelForm">大类名称：</label> 
		<div class="col-sm-4">
			<input value="${vo.className }" type="text" readonly="readonly" />
		</div>
	</div>
	<div class="clearfix formGroup">
		<label class="col-sm-2 labelForm">集团小类：</label> 
		<div class="col-sm-4">
			<input value="${vo.subClassCode }" type="text" readonly="readonly" />
		</div>
		<label class="col-sm-2 labelForm">小类名称：</label>
		<div class="col-sm-4">
			<input value="${vo.subClassName }" type="text" readonly="readonly" />
		</div>
	</div>
	<div class="clearfix formGroup">
		<label class="col-sm-2 labelForm">产品名称：</label> 
		<div class="col-sm-4">
			<input value="${vo.prodName }" type="text" readonly="readonly" />
		</div>		
	</div>	
</div>

