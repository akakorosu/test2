<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.bonc.com.cn/common/tag/cxt" prefix="cxt"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<cxt:commonLinks />
 <link rel="stylesheet" href="<%=request.getContextPath()%>/pages/css/bootstrap-multiselect.css" />
 <link rel="stylesheet" href="<%=request.getContextPath()%>/pages/css/common.css" />
 <link rel="stylesheet" href="<%=request.getContextPath()%>/pages/css/operateCenter.css" />
 <script type="text/javascript" src="<%=request.getContextPath()%>/pages/js/bootstrap-multiselect.js"></script>
<script src="<%=request.getContextPath()%>/pages/jsp/dpi/ruleCheck/ruleCheck.js"></script>
<script src="<%=request.getContextPath()%>/pages/jsp/dpi/dpiCommon.js"></script>
</head>
<body>
	<div class="page-content clearfix">
		<div class="col-sm-12 grid-full">
			<!-- 查询div -->
			<div class="grid-search-div" id="gridSearch">
				<input type="text" placeholder="规则ID" class="searchField input-md" id="id" name="id">
				<input type="text" placeholder="解析URL" class="searchField input-md" id="url" name="url">
				<input type="text" class="form-control datepicker-input " id='time' placeholder="操作时间" style='width:150px;display:inline-block;'>
				<button class="btn btn-primary" type="button" id="searchDrList">查询</button>
				<button class="btn btn-primary" type="button" id="clearList">清空</button>	
				<button class="btn btn-primary" type="button" id="backto">返回</button>
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