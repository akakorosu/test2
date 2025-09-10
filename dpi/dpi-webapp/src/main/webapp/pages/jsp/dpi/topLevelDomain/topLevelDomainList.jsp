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
<script src="<%=request.getContextPath()%>/pages/jsp/dpi/topLevelDomain/topLevelDomainList.js"></script>
</head>
<body>
	<div class="page-content clearfix">
		<div class="col-sm-12 grid-full">
			<!-- 查询div -->
			<%-- <div class="grid-search-div" id="gridSearch">
	            <button class="btn btn-warning" type="button" id="createPiBtn" >新建</button>
	            
				<!--批处理 -->
	           <jsp:include page="/pages/jsp/dpi/exportExcel/exportExcel.jsp">
					<jsp:param value="com.bonc.dpi.dao.entity.TopLevelDomain" name="entityName"/>
					<jsp:param value="topLevelDomainService" name="beanName"/>
					<jsp:param value="top_level_domain.xlsx" name="templateName"/>
					<jsp:param value="0|1|2|3|4|5" name="fieldNums"/>
				</jsp:include>			
			
			</div> --%>
			<!-- 列表div -->
			<input type="text" class="datepicker-input" placeholder="请选择时间" id="time1">
            <select id="auditStatus" class="searchField input-md" >
					<option value="3">稽核状态</option>
					<option value="1">已处理</option>
					<option value="2">无需处理</option>
					<option value="0">未处理</option>
				</select>
			<button class="btn btn-primary" type="button" id="search" >查询</button>
			<button class="btn btn-primary" type="button" id="clear" >清空</button>
			<button class="btn btn-primary" type="button" id="backto" >返回</button>
			<div id="gridContainer">
				<table id="grid-table"></table>
				<div id="grid-pager"></div>
			</div>
		</div>
	</div>
</body>

</html>