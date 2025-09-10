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
				<input type="text" placeholder="姓名" class="searchField input-md" id="userName" name="userName" onclick="userNameClick()">
				<input type="text" placeholder="联系方式" class="searchField input-md" id="userMobile" name="userMobile">
				<select class="searchField input-md" name="roleId" id="roleId">
					<option value=""> --- 选择角色 --- </option>
				</select>
				<button class="btn btn-primary" type="button" id="searchUserList">查询</button>
				<button class="btn btn-primary" type="button" id="clearUserList" onclick="topclear('gridSearch')">清空</button>
				<button  class="btn btn-warning" type="button" id="createUserBtn">新建</button>
	         <%--<button top-do-role="add" class="btn btn-warning" type="button" id="createUserBtn">新建</button>--%>
	            
				<jsp:include page="/pages/jsp/frame/operationFlow.jsp">
					<jsp:param value="com.bonc.system.dao.entity.SysUser" name="entityName"/>
					<jsp:param value="sysUserService" name="beanName"/>
					<jsp:param value="sys_user.xlsx" name="templateName"/>
					<jsp:param value="3,1" name="fieldNums"/>
					<jsp:param value="1,1,1,1,1" name="isShow"/>
					<jsp:param value="impCallbackFun" name="impCallbackFun"/>
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
<script src="<%=request.getContextPath()%>/pages/jsp/system/sysuser/sysUser.js"></script>
<script type="text/javascript">
</script>
</html>