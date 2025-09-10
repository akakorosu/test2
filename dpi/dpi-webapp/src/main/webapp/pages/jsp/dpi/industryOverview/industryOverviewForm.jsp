<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="cxt" uri="http://www.bonc.com.cn/common/tag/cxt"%>
<!-- 编辑表单div -->

<div id="piForm">
	<!-- 列表div -->
	<input type="hidden" id="cityHidden" class="formField" name="id" value="${vo.city}">
	<input type="hidden" id="labelName1Hidden" class="formField" name="id" value="${vo.labelName1}">
	<input type="hidden" id="labelName2Hidden" class="formField" name="id" value="${vo.labelName2}">
			<div id="gridContainer">
				<table id="grid-table"></table>
				<div id="grid-pager"></div>
				
			</div>
	</div>
	
	
	
<script src="<%=request.getContextPath()%>/pages/jsp/dpi/industryOverview/industryOverviewForm.js"></script>
