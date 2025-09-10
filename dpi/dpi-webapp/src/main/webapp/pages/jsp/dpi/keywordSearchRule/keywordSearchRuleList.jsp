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
	<script src="<%=request.getContextPath()%>/pages/jsp/dpi/keywordSearchRule/keywordSearchRuleList.js"></script>
	<script src="<%=request.getContextPath()%>/pages/jsp/dpi/dpiCommon.js"></script>
	<link rel="stylesheet" href="<%=request.getContextPath()%>/resource/ace/css/daterangepicker.css" />
	<script src="<%=request.getContextPath()%>/resource/ace/js/date-time/daterangepicker.js"></script>
</head>
<body>
	<div class="page-content clearfix">
		<div class="col-sm-12 grid-full">
			<!-- 查询div -->
			<div class="grid-search-div" id="gridSearch">
<!-- 				<input style="width: 75px;" type="text" placeholder="NUM" class="searchField input-md" id="num" name="num"> -->
<!-- 				<input style="width: 60px;" type="text" placeholder="域名" class="searchField input-md" id="host" name="host"> -->
<!-- 				<input style="width: 90px;" type="text" placeholder="产品名称" class="searchField input-md" id="prodName" name="prodName"> -->
<!-- 				<input style="width: 90px;" type="text" placeholder="动作目录" class="searchField input-md" id="channelName" name="channelName"> -->
<!-- 				<input style="width: 90px;" type="text" placeholder="应用分组" class="searchField input-md" id="groupName" name="groupName"> -->
<!-- 				<input style="width: 90px;" type="text" placeholder="操作时间" class="searchField input-md datepicker-input"  id="opTime" name="opTime"> -->
				
				<input style="width: 120px;" type="text" placeholder="NUM" class="searchField input-mini" id="num" name="num">
				<input style="width: 120px;" type="text" placeholder="域名" class="searchField input-mini" id="host" name="host">
				<input style="width: 120px;" type="text" placeholder="产品名称" class="searchField input-mini" id="prodName" name="prodName">
				<input style="width: 120px;" type="text" placeholder="动作目录名称" class="searchField input-mini" id="channelName" name="channelName">
				<input style="width: 120px;" type="text" placeholder="应用分组名称" class="searchField input-mini" id="groupName" name="groupName">
				<!-- <input style="width: 120px;" type="text" placeholder="操作时间" class="searchField input-md datepicker-input"  id="opTime" > -->
				<input type="text" style="width: 120px;"  placeholder="操作人" class="searchField input-mini" id="author" name="author">
				<select name="batchFlag" id="exportProdType" class="chosen-select searchField" data-placeholder="不限" style="width:120px;">
					<option value="0">批量模糊匹配</option>
					<option value="1">批量精准匹配</option>
			    </select>
				<div hidden="hidden"></div>
				<input class="searchField input-md datepicker-input" placeholder="操作时间" type="text" name="opTime" id="opTime" style="width:150px;"/>
				<button class="btn btn-primary" type="button" id="searchKsrList">查询</button>
				<button class="btn btn-primary" type="button" id="clearList" onclick="topclear('gridSearch')">清空</button>
	            <button class="btn btn-warning" type="button" id="createKsrBtn" >新建</button>
				<button class="btn btn-warning" type="button" id="ruleCheck" >规则校验</button>
				<!--批处理 -->
	            <jsp:include page="/pages/jsp/frame/operationFlow.jsp">
					<jsp:param value="com.bonc.dpi.dao.entity.KeywordSearchRule" name="entityName"/>
					<jsp:param value="keywordSearchRuleService" name="beanName"/>
					<jsp:param value="dim_ia_keyword_search_rule.xlsx" name="templateName"/>
					<jsp:param value="0,1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16" name="fieldNums"/>
					<jsp:param value="1,2,3,4,5,6,7,8,9,10,16,15" name="textFieldNums"/>
					<jsp:param value="1,1,1,1,1" name="isShow"/>
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