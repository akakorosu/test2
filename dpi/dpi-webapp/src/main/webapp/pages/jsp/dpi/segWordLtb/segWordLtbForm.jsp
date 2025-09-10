<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="cxt" uri="http://www.bonc.com.cn/common/tag/cxt"%>
<!-- 编辑表单div -->
<div id="piForm">
    <input type="hidden" class="formField" name="id" value="${vo.id}"> 
	<input type="hidden" class="formField" name="author" value="${vo.author}">
	<input type="hidden" class="formField" name="opTime" value="${vo.opTime}">
	<div class="clearfix formGroup">
	<p style="color: red">提示：分词不能重复</p>
	</div>
	<div class="clearfix formGroup">
		<label class="col-sm-2 labelForm">分词：</label> 
		<div class="col-sm-4">
			<input value="${vo.segWord }" style='width:500px;' type="text" placeholder="分词" onlyUrl="/segWordLtb/check,id,segWord|分词重复!"  class="formField required" id="segWord" name="segWord" >
		</div>	
	</div>
<script src="<%=request.getContextPath()%>/pages/jsp/dpi/dpiCommon.js"></script>