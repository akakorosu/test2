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
<link rel="stylesheet" href="<%=request.getContextPath()%>/pages/css/bootstrap-multiselect.css" />
<link rel="stylesheet" href="<%=request.getContextPath()%>/pages/css/common.css" />
<link rel="stylesheet" href="<%=request.getContextPath()%>/pages/css/operateCenter.css" />
<script type="text/javascript" src="<%=request.getContextPath()%>/pages/js/echarts.js"></script>
<script src="<%=request.getContextPath()%>/pages/jsp/dpi/unidentifiedUa/unidentifiedUaList.js"></script>
</head>
<body>
	<div class="page-content clearfix">
		<div class="col-sm-12 grid-full">
		<label for="">稽核状态：</label> 
			
			<select name="" id="status" class="chosen-select" data-placeholder="不限" style="width:170px;">
				<option value="2">全部</option>
				<option value="1">已稽核</option>
				<option value="0">未稽核</option>
				
			</select>
		<button class="btn btn-primary" type="button" id="backto" >返回</button>
			<div id="gridContainer">
				<table id="grid-table"></table>
				<div id="grid-pager"></div>
			</div>
		</div>
	</div>
</body>

</html>