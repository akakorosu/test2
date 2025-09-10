<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="cxt" uri="http://www.bonc.com.cn/common/tag/cxt"%>
<!-- 编辑表单div -->
<div id="kgForm">
	<input type="hidden" class="formField" name="id" value="${vo.id}">
	<input type="hidden" class="formField" name="author" value="${vo.author}">
	<input type="hidden" class="formField" name="opTime" value="${vo.opTime}">
	<div class="clearfix formGroup">
	<p style="color: red">提示：分组编码不能重复</p>
	</div>
	<div class="clearfix formGroup">
		<label class="col-sm-2 labelForm">分组编码：</label> 
		<div class="col-sm-4">
			<input value="${vo.groupType }" type="text" placeholder="分组编码" onlyUrl="/keywordGroup/check,id,groupType" class="formField required" id="groupType" name="groupType" />
		</div>
		<label class="col-sm-2 labelForm">分组名称：</label> 
		<div class="col-sm-4">
			<input value="${vo.groupName }" type="text" placeholder="分组名称"  class="formField required" id="groupName" name="groupName">
		</div>
	</div>
	<div class="clearfix formGroup">
		<label class="col-sm-2 labelForm">分组级别：</label> 
		<div class="col-sm-4">
			<input value="${vo.groupLevel }" type="text" placeholder="分组级别" class="formField required " id="groupLevel" name="groupLevel">
		</div>
	</div>
</div>

<script src="<%=request.getContextPath()%>/pages/jsp/dpi/keywordGroup/keywordGroupForm.js"></script>
<script src="<%=request.getContextPath()%>/pages/jsp/dpi/dpiCommon.js"></script>