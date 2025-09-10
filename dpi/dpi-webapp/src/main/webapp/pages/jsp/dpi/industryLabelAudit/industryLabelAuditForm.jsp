<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="cxt" uri="http://www.bonc.com.cn/common/tag/cxt"%>
<!-- 编辑表单div -->

<div id="piForm">
	<input type="hidden" id="labellvlHidden" class="formField" name="id" >
	<input type="hidden" id="labelName1Hidden" class="formField" name="id" >
	<input type="hidden" id="labelId2Hidden" class="formField" name="id" >
	<input type="hidden" id="labelName2Hidden" class="formField" name="id" >
	<!-- <div class="clearfix formGroup" style="margin:0 0 20px 40px;">
		<input type="text" placeholder="产品名称" class="searchField input-md" id="name" name="name" style="width:100px;">
		<select name="" id="selectName" class="chosen-select" data-placeholder="产品ID" style="width:100px;">
		</select> 
		<button class="btn btn-primary" type="button" id="searchPiList">查询</button>
		<span style="color: #000;">产品名称：</span> <select
							style="width: 400px !important;" multiple=""
							class="chosen-select" id="form-field-select-4"
							data-placeholder="请选择指标名称">
							<option value="AL">北京</option>
							<option value="AK">天津</option>
						</select>
	</div> -->
	<%-- <div class="clearfix formGroup">
		<label class="col-sm-2 labelForm">域名：</label> 
		<div class="col-sm-4">
			<input value="${vo.host }" type="text" placeholder="域名"  class="formField required" id="host" name="host" readonly="readonly">
		</div>	
		<label class="col-sm-2 labelForm">title：</label> 
		<div class="col-sm-4">
			<input value="${vo.title }" type="text" placeholder="title" class="formField required" id="title" name="title" readonly="readonly"/>
		</div>
	</div> --%>
	<div class="clearfix formGroup">
		<label class="col-sm-2 labelForm">产品ID：</label> 
		<div class="col-sm-4">
<!-- 			<select name="" id="" class="chosen-select" data-placeholder="不限" style="width:70px;" readonly="readonly">
				<option value="0">全部</option>
				<option value="0"></option>
				<option value="1">1</option>
				<option value="2">2</option>
				<option value="3">3</option>
				<option value="4">4</option>
			</select> -->
			<input value="${vo.prodId }" type="text" placeholder="产品ID"   onlyUrl="/unidentifiedUrl/check,id,prodId"  class="formField required " id="prodId" name="prodId" readonly="readonly">
		</div>
		<label class="col-sm-2 labelForm">产品名称：</label>
		<div class="col-sm-4">
			<input value="${vo.prodName }" type="text" placeholder="产品名称" class="formField required " id="prodName" name="prodName" readonly="readonly">
		</div>
	</div>
	<div class="clearfix formGroup">
		<label class="col-sm-2 labelForm">产品标签：</label> 
		<div class="col-sm-4">
			<input value="${vo.labelId1 }" type="text" placeholder="产品标签"  class="formField required" id="labelId1" name="labelId1" readonly unselectable="on" readonly="readonly">
		</div>	
		<label class="col-sm-2 labelForm">标签名称：</label> 
		<div class="col-sm-4">
			<%-- <input value="${vo.prodLabelName }" type="text" placeholder="标签名称" class="formField required" id="labelName" name="labelName" readonly="readonly"/> --%>
		<input type="text" value="${vo.labelName1 }" placeholder="标签" class="formField required" id="labelName1" name="labelName1" readonly unselectable="on" onclick="sntest()">
		</div>
	</div>
	
	
	
<script src="<%=request.getContextPath()%>/pages/jsp/dpi/industryLabelAudit/industryLabelAuditForm.js"></script>
