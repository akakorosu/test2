<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.bonc.com.cn/common/tag/cxt" prefix="cxt"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<cxt:commonLinks />
</head>
<body>
	<div class="page-content clearfix">
		<div class="col-sm-12 grid-full">
			<!-- 查询div -->
			<div class="grid-search-div" id="gridSearch">
				<input type="text" placeholder="角色名称" class="searchField input-md" id="roleName" name="roleName">
				<button class="btn btn-primary" type="button" id="searchBtn">查询</button>
				<button class="btn btn-primary" type="button" id="clearBtn" onclick="topclear('gridSearch')">清空</button>
	            <button class="btn btn-warning" type="button" id="createBtn">新建</button>
			</div>
			<!-- 列表div -->
			<div id="gridContainer">
				<table id="grid-table"></table>
				<div id="grid-pager"></div>
			</div>
		</div>
	</div>
</body>
<script src="<%=request.getContextPath()%>/pages/jsp/system/sysrole/sysRole.js"></script>
</html>