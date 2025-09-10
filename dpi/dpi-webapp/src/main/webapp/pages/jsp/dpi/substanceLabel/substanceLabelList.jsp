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
<script src="<%=request.getContextPath()%>/pages/jsp/dpi/substanceLabel/substanceLabelList.js"></script>
</head>
<body>
	<div class="page-content clearfix">
		<div class="col-sm-12 grid-full">
		<!-- 查询div -->
			<div class="grid-search-div" id="gridSearch">
				<!-- <input type="text" placeholder="标签" class="searchField input-md" id="labelName" name="labelName" readonly unselectable="on" onclick="sntest1()"> -->
				<select id="bigTable" class="searchField input-md">
					<option value="0">映射结果</option>
					<option value="1">编码映射</option>
				
					
				</select>
				<select id="contentStatus" class="searchField input-md">
					<!-- <option value="0">视频</option>
					<option value="1">电商</option>
					<option value="2">小说</option>
					<option value="3">音乐</option> -->
					
				</select>
				<select id="labelLvl" class="searchField input-md">
					<option value="0">标签层级</option>
					<option value="4">标签一级</option>
					<option value="8">标签二级</option>
					<option value="12">标签三级</option>
					<option value="16">标签四级</option>
					<option value="20">标签五级</option>
					<option value="24">标签六级</option>
				</select>
				<select id="prodName" class="searchField input-md">
					<option value="0">产品名称</option>
					
					
				</select>
				<select id="status" class="searchField input-md">
					<option value="2">稽核状态</option>
					<option value="1">已稽核</option>
					<option value="0">未稽核</option>
					
				</select>
				<button class="btn btn-primary" type="button" id="searchPiList">查询</button>
				<button class="btn btn-primary" type="button" id="clearList" >清空</button>
				<!-- <button class="btn btn-primary" type="button" id="batch" >批量稽核</button> -->
				<button class="btn btn-primary" type="button" id="backto" >返回</button>
				<button class="btn btn-warning" type="button" id="addNew" >新增</button>	
			</div>
			<div id="gridContainer">
				<table id="grid-table"></table>
				<div id="grid-pager"></div>
			</div>
		</div>
	</div>
</body>

</html>