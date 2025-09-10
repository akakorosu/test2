<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="cxt" uri="http://www.bonc.com.cn/common/tag/cxt"%>
<!-- 编辑表单div -->
<style>
#divFastSearch1>div{
	padding-left:12px;
    line-height: 30px;
    border-bottom: 1px dashed #ccc;
}
</style>
<div id="piForm" style="position:relative;">
	<input type="hidden" class="formField" id="labelCodeHidden" name="orgLabelCode" value="${vo.prodLabelCode}"> 
	<input value="${vo.prodLabelCode }" type="hidden" placeholder="产品标签"  class="formField required" id="prodLabel" name="prodLabel" >
	<input value="${vo.prodCatagory }" type="hidden" placeholder="产品标签"  class="formField required" id="prodCatagory" name="prodCatagory" >
	<div class="clearfix formGroup">
	<p style="color: red">提示：产品id为空时，为新增产品</p>
	</div>
	<div class="clearfix formGroup" style="margin:0 0 20px 40px;position:absolute;width:570px;z-index:1;">
	    <label class="col-sm-2 labelForm">搜索产品</label> <!-- 新加的 -->
	    <input class="width200" maxlength="40" type="text" id="likeSearch11"  style="width:380px" >
	  <!--   <input placeholder="快速搜索" id="inputFastSearch"> -->
	  
	    <!-- <button class="btn btn-primary" type="button"><i class="fa fa-search inputFastSearchIcon"></i>查询</button>-->
	    <button class="btn btn-primary" type="button" id="searchPiList1">确定</button>
	    <label class="col-sm-2 labelForm"></label> <!-- 新加的 -->
		<div id="divFastSearch1" style="width:380px;max-height:240px;overflow:auto;background:#fff;">
		</div>
		<!-- <input type="text" placeholder="产品名称" class="searchField input-md" id="name" name="name" style="width:100px;"> -->
		<!-- <select name="" id="selectName" class="chosen-select" data-placeholder="产品ID" style="width:100px;">
		</select>  -->
		
		<!-- <span style="color: #000;">产品名称：</span> <select
							style="width: 400px !important;" multiple=""
							class="chosen-select" id="form-field-select-4"
							data-placeholder="请选择指标名称">
							<option value="AL">北京</option>
							<option value="AK">天津</option>
						</select> -->
	</div>
	<div class="clearfix formGroup" style="margin-top:66px;">
		<label class="col-sm-2 labelForm">域名：</label> 
		<div class="col-sm-4">
			<input value="${vo.host }" type="text" placeholder="域名"  class="formField required" id="host" name="host" readonly="readonly">
		</div>	
		<label class="col-sm-2 labelForm">title：</label> 
		<div class="col-sm-4">
			<input value="${vo.title }" type="text" placeholder="title" class="formField required" id="title" name="title" readonly="readonly"/>
		</div>
	</div>
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
			<input value="${vo.prodName }" type="text" placeholder="产品名称" class="formField required " id="prodName" name="prodName" >
		</div>
	</div><div class="clearfix formGroup">
		<label class="col-sm-2 labelForm">产品类型：</label> 
		<div class="col-sm-4">
			<input value="${vo.prodCatagoryName }" type="text" placeholder="产品类型"  class="formField required" id="prodCatagoryName" name="prodCatagoryName" readonly  unselectable="on" onclick="sntest13()">
		</div>	
		<label class="col-sm-2 labelForm">标签名称：</label> 
		<div class="col-sm-4">
			<%-- <input value="${vo.prodLabelName }" type="text" placeholder="标签名称" class="formField required" id="labelName" name="labelName" readonly="readonly"/> --%>
		<input type="text" value="${vo.prodLabelName }" placeholder="标签" class="formField required" id="labelName" name="labelName" readonly unselectable="on" onclick="sntest12()">
		</div>
	</div>
<script src="<%=request.getContextPath()%>/pages/jsp/dpi/unidentifiedUrl/unidentifiedUrlForm.js"></script>
