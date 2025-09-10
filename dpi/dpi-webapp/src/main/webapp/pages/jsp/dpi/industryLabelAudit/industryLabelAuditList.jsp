<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.bonc.com.cn/common/tag/cxt" prefix="cxt"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<cxt:commonLinks />
<script type="text/javascript">
 var time = "<%=request.getParameter("time")%>";
</script>
<script src="<%=request.getContextPath()%>/pages/jsp/dpi/industryLabelAudit/industryLabelAuditList.js"></script>
<%-- <script src="<%=request.getContextPath()%>/pages/jsp/system/sysuser/sysUser.js"></script> --%>

</head>
<style>
	
</style>
<body>
	<div class="page-content clearfix">
		<div class="col-sm-12 grid-full">
			<!-- 查询div -->
			<div class="grid-search-div" id="gridSearch">
				<input type="text" placeholder="标签" class="searchField input-md" id="labelName" name="labelName" readonly unselectable="on" onclick="sntest1()">
				<select id="auditStatus" class="searchField input-md">
					<option value="0">稽核状态</option>
					<option value="1">已稽核</option>
					<option value="2">未稽核</option>
					
				</select>
				<select id="top" class="searchField input-md">
					<option value="0">TOP</option>
					<option value="1">10</option>
					<option value="2">30</option>
					<option value="3">50</option>
					<option value="4">100</option>
				</select>
				<button class="btn btn-primary" type="button" id="searchPiList">查询</button>
				<button class="btn btn-primary" type="button" id="clearList" >清空</button>
				<button class="btn btn-primary" type="button" id="batch" >批量稽核</button>
				<button class="btn btn-primary" type="button" id="backto" >返回</button>	
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