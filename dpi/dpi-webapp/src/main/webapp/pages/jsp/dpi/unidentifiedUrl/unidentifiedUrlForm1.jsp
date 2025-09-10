<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="cxt" uri="http://www.bonc.com.cn/common/tag/cxt"%>
<!-- 编辑表单div -->
<div id="piForm">
	<input type="hidden" class="formField" name="id" value="${vo.id}"> 
	<div class="clearfix formGroup" style="margin:0 0 20px 40px;">
		<input type="text" placeholder="产品名称" class="searchField input-md" id="name" name="name" style="width:100px;">
		<select name="" id="" class="chosen-select" data-placeholder="产品ID" style="width:100px;">
		</select> 
		<button class="btn btn-primary" type="button" id="searchPiList">查询</button>
	</div>
	
	<div class="clearfix formGroup">
		<label class="col-sm-2 labelForm">域名：</label> 
		<div class="col-sm-4">
			<input value="${vo.host }" type="text" placeholder="域名"  class="formField required" id="host" name="host" >
		</div>	
		<label class="col-sm-2 labelForm">title：</label> 
		<div class="col-sm-4">
			<input value="${vo.title }" type="text" placeholder="title" class="formField required" id="title" name="title" />
		</div>
	</div>
	<div class="clearfix formGroup">
		<label class="col-sm-2 labelForm">产品ID：</label> 
		<div class="col-sm-4">
			<input value="${vo.prodId }" type="text" placeholder="产品ID"  class="formField required " id="prodId" name="prodId"> 
		</div>
		<label class="col-sm-2 labelForm">产品名称：</label>
		<div class="col-sm-4">
			<input value="${vo.prodName }" type="text" placeholder="产品名称" class="formField required " id="prodName" name="prodName">
		</div>
	</div><div class="clearfix formGroup">
		<label class="col-sm-2 labelForm">产品标签：</label> 
		<div class="col-sm-4">
			<input value="${vo.prodLabel }" type="text" placeholder="产品标签"  class="formField required" id="prodLabel" name="prodLabel" >
		</div>	
		<label class="col-sm-2 labelForm">标签名称：</label> 
		<div class="col-sm-4">
			<input value="${vo.labelName }" type="text" placeholder="标签名称" class="formField required" id="labelName" name="labelName" />
		</div>
	</div>
<script src="<%=request.getContextPath()%>/pages/jsp/dpi/unidentifiedUrl/unidentifiedUrlForm.js"></script>
