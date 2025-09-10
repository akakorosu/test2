<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="cxt" uri="http://www.bonc.com.cn/common/tag/cxt"%>
<!-- 编辑表单div -->
<div id="cwlForm">
	<input type="hidden" class="formField" name="id" value="${vo.id}">
	<input type="hidden" class="formField" name="author" value="${vo.author}">
	<input type="hidden" class="formField" name="opTime" value="${vo.opTime}">
	<div class="clearfix formGroup">
	</div>
	<div class="clearfix formGroup">
		<label class="col-sm-2 labelForm">分类标签：</label> 
		<div class="col-sm-4">
			<input value="${vo.cataLabel}" type="hidden" placeholder="分类标签"  onlyUrl="/cataWordLtb/check,id,catawordparam,catalabelparam|联合主键重复"  class="formField required " id="labelCodeHidden_form" name="catalabelparam">
			<input value="${labelName}" type="text" placeholder="标签名称" onlyUrl="/cataWordLtb/check,id,catawordparam,catalabelparam|联合主键重复"  class="formField required " readonly id="labelNameOverView_form" name = "cataLabelName">
			<button class="btn btn-success" id="queryProdLabelCode" onclick="getLableCode_byForm()">查询</button>		
		</div>
		<label class="col-sm-2 labelForm">分类关键词：</label> 
		<div class="col-sm-4">
			<input value="${vo.cataWord}" type="text" placeholder="分类关键词" onlyUrl="/cataWordLtb/check,id,catawordparam,catalabelparam|联合主键重复" class="formField required" id="catawordparam" name="catawordparam">
		</div>
	</div>
</div>

<script src="<%=request.getContextPath()%>/pages/jsp/dpi/cataWordLtb/cataWordLtbForm.js"></script>
<%-- <script type="text/javascript" src="<%=request.getContextPath()%>/pages/jsp/dpi/industryOverview/industryOverview.js"></script> --%>
<script src="<%=request.getContextPath()%>/pages/jsp/dpi/dpiCommon.js"></script>