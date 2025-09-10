<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="cxt" uri="http://www.bonc.com.cn/common/tag/cxt"%>
<!-- 编辑表单div -->
<div id="dmDtarRuleBaseForm">
	<div class="clearfix formGroup">
		<label class="col-sm-2 labelForm">名称：</label> 
		<div class="col-sm-4">
			<input maxlength="15" value="${vo.tarBaseName }" onlyUrl="/dmdtarrulebase/check,id|已有此名称偏好标签" type="text" placeholder="名称" class="formField required" id="tarBaseName" name="tarBaseName" >
			<input type="hidden" id="id" name="id" value="${vo.id }" class="formField">
		</div>
		<label class="col-sm-2 labelForm">类别：</label> 
		<div class="col-sm-4">
			<cxt:CodeSelectTlb value="${vo.tarBaseType }" name="tarBaseType" codeType="phgztype" defaultyn="n" cssClass="formField"></cxt:CodeSelectTlb>
		</div>
	</div>
	<div class="clearfix formGroup">
		<label class="col-sm-2 labelForm">分类：</label> 
		<div class="col-sm-4">
			<input maxlength="15" value="${vo.tarBaseClass }" type="text" placeholder="分类" class="formField required" id="tarBaseClass" name="tarBaseClass" >
		</div>
		<label class="col-sm-2 labelForm"></label> 
		<div class="col-sm-4">
		</div>
	</div>
	<div class="clearfix formGroup">
		<label class="col-sm-2 labelForm">定义：</label> 
		<div class="col-sm-8">
			<textarea name="tarBaseDefine" id="tarBaseDefine" class="autosize-transition form-control formField" maxlength="100" style="width: 600px;height:70px;">${vo.tarBaseDefine }</textarea>
		</div>
	</div>
</div>
<script src="<%=request.getContextPath()%>/pages/jsp/dpi/dmDtarRuleBase/dmDtarRuleBaseForm.js"></script>
<script src="<%=request.getContextPath()%>/pages/jsp/dpi/dpiCommon.js"></script>