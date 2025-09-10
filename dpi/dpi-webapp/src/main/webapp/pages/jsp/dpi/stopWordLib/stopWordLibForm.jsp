<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="cxt" uri="http://www.bonc.com.cn/common/tag/cxt"%>
<!-- 编辑表单div -->
<div id="swlForm">
	<div class="clearfix formGroup">
		<p style="color: red">提示：停用关键词不能重复</p>
	</div>
	<input type="hidden" class="formField" name="id" value="${vo.id}">
	<input type="hidden" class="formField" name="author" value="${vo.author}">
	<input type="hidden" class="formField" name="opTime" value="${vo.opTime}">
	<div class="clearfix formGroup">
	</div>
	<div class="clearfix formGroup">
		<label class="col-sm-2 labelForm">停用关键词：</label> 
		<div class="col-sm-4">
			<input value="${vo.stopWord }" maxlength="200" type="text"  style="width: 500px;" placeholder="停用关键词"  class="formField required" onlyUrl="/stopWordLib/check,stopWord,id|主键重复" id="stopWord" name="stopWord">
		</div>
	</div>
</div>

<script src="<%=request.getContextPath()%>/pages/jsp/dpi/stopWordLib/stopWordLibForm.js"></script>
<script src="<%=request.getContextPath()%>/pages/jsp/dpi/dpiCommon.js"></script>