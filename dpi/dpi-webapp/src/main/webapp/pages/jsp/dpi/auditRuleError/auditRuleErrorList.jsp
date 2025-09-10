<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.bonc.com.cn/common/tag/cxt" prefix="cxt"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<cxt:commonLinks />
<script type="text/javascript">
 var time = "<%=request.getParameter("time")%>";
</script>
<script src="<%=request.getContextPath()%>/pages/jsp/dpi/auditRuleError/auditRuleErrorList.js"></script>
</head>
<body>
	<div class="page-content clearfix">
		<div class="col-sm-12 grid-full">
            <!-- 查询div -->
            <div class="grid-search-div" id="gridSearch">
			<label for="">稽核对象：</label> 
			<!-- <input type="text" placeholder="稽核对象" class="searchField input-md" id="objId" name="objId"> -->
			<select name="" id="tableName1" class="chosen-select" data-placeholder="不限" style="width:170px;">
				<option value="0">全部</option>
				
			</select>
		<!-- <button class="btn btn-primary" type="button" id="searchUpList">查询</button> -->
		<button class="btn btn-primary" type="button" id="backto" >返回</button>	
        <button class="btn btn-warning" type="button" id="export"  onclick="exportExcel()">导出Excel</button>
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