<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.bonc.com.cn/common/tag/cxt" prefix="cxt"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<cxt:commonLinks />

	<link rel="stylesheet" href="<%=request.getContextPath()%>/pages/css/bootstrap-multiselect.css" />
	
	<script type="text/javascript" src="<%=request.getContextPath()%>/pages/js/bootstrap-multiselect.js"></script>
<%-- 	<script type="text/javascript" src="<%=request.getContextPath()%>/pages/jsp/dpi/common/datepickerDpi.js"></script> --%>
	<script src="<%=request.getContextPath()%>/pages/jsp/dpi/cataWordLtb/cataWordLtbList.js"></script>
	<script src="<%=request.getContextPath()%>/pages/jsp/dpi/dpiCommon.js"></script>
	<%-- <script type="text/javascript" src="<%=request.getContextPath()%>/pages/jsp/dpi/industryOverview/industryOverview.js"></script> --%>
	<link rel="stylesheet" href="<%=request.getContextPath()%>/resource/ace/css/daterangepicker.css" />
	<script src="<%=request.getContextPath()%>/resource/ace/js/date-time/daterangepicker.js"></script>
</head>
<body>
	<div class="page-content clearfix">
		<div class="col-sm-12 grid-full">
			<!-- 查询div -->
			<div class="grid-search-div" id="gridSearch">
				<input type="hidden" placeholder="分类标签" class="searchField input-md" id="labelCodeHidden1" name="cataLabel">
				<input type="hidden" placeholder="标签编码" class="searchField input-md" id="labelCodeHidden" name="prodLabelCode">
				<input type="text" placeholder="标签名称" class="searchField input-md" id="labelNameOverView" name="labelName1" readonly unselectable="on" onclick="sntestprod()">
				<input type="text" placeholder="分类关键词" class="searchField input-md" id="cataWord" name="cataWord">
				<!-- <input type="text" placeholder="操作时间" class="searchField input-md datepicker-input" style="width:150px;" id="opTime" name="opTime"> -->
				<input type="text" placeholder="操作人" class="searchField input-md" id="author" name="author">
				<div hidden="hidden"></div>
				<input class="searchField input-md datepicker-input" placeholder="操作时间" type="text" name="opTime" id="opTime" style="width:180px;"/>
				<button class="btn btn-primary" type="button" id="searchCwlList">查询</button>
				<button class="btn btn-primary" type="button" id="clearList" onclick="clearGridSearch()">清空</button>
	            <button class="btn btn-warning" type="button" id="createCwlBtn" >新建</button>
	            
				<!--批处理 -->
	            <jsp:include page="/pages/jsp/frame/operationFlow.jsp">
					<jsp:param value="com.bonc.dpi.dao.entity.CataWordLtb" name="entityName"/>
					<jsp:param value="cataWordLtbService" name="beanName"/>
					<jsp:param value="dim_ia_cata_word_lib.xlsx" name="templateName"/>
					<jsp:param value="0,1,2,3,4" name="fieldNums"/>
					<jsp:param value="falseDataViewFromExcle" name="impCallbackFun"/>
				</jsp:include>			
			
			</div>
			<!-- 列表div -->
			<div id="gridContainer">
				<table id="grid-table"></table>
				<div id="grid-pager"></div>
			</div>
		</div>
	</div>
</body>

</html>