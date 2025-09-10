<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="cxt" uri="http://www.bonc.com.cn/common/tag/cxt"%>
<!-- 编辑表单div -->
<div id="piForm">
    <input type="hidden" class="formField" name="id" value="${vo.id}"> 
	<input type="hidden" class="formField" name="author" value="${vo.author}">
	<div class="clearfix formGroup">
	<p style="color: red">提示：集团大类，集团小类，产品ID联合主键不能重复</p>
	</div>
	<div class="clearfix formGroup">
		<label class="col-sm-2 labelForm">集团大类：</label> 
		<div class="col-sm-4">
			<input value="${vo.classCode }" type="text" placeholder="集团大类" onlyUrl="/groupClass/check,id,classCode,subClassCode,prodid|联合校验重复!" class="formField required" id="classCode" name="classCode" />
		</div>
		<label class="col-sm-2 labelForm">大类名称：</label> 
		<div class="col-sm-4">
			<input value="${vo.className }" type="text" placeholder="大类名称"  class="formField required " id="className" name="className">
		</div>
	</div>
	<div class="clearfix formGroup">
		<label class="col-sm-2 labelForm">集团小类：</label>
		<div class="col-sm-4">
			<input value="${vo.subClassCode }" type="text" placeholder="集团小类" onlyUrl="/groupClass/check,id,classCode,subClassCode,prodid|联合校验重复!" class="formField required " id="subClassCode" name="subClassCode">
		</div>
		<label class="col-sm-2 labelForm">小类名称：</label> 
		<div class="col-sm-4">
			<input value="${vo.subClassName }" type="text" placeholder="小类名称" class="formField required " id="subClassName" name="subClassName">
		</div>		
	</div>
	<div class="clearfix formGroup">	
		<label class="col-sm-2 labelForm">产品名称：</label> 
		<div class="col-sm-4">
			<input  value="${vo.prodName }" type="text" placeholder="产品名称"  onlyUrl="/ipport/checkId,id,prodName|产品不存在!" class="formField" id="prodName" name="prodName" />
			<button class="btn btn-warning" id="checkParseRuleAndriod" onclick="getProdIdList11('','','prodName')">查询</button>		
		</div>
		<label class="col-sm-2 labelForm">产品ID：</label> 
		<div class="col-sm-4">
			<input value="${vo.prodid }" type="text" placeholder="产品ID" onlyUrl="/groupClass/check,id,classCode,subClassCode,prodid|联合校验重复!" class="formField required" id="prodid" name="prodid"  readonly/>
		</div> 
	</div>
	
<script src="<%=request.getContextPath()%>/pages/jsp/dpi/groupClass/groupClassForm.js"></script>
<script src="<%=request.getContextPath()%>/pages/jsp/dpi/dpiCommon.js"></script>