<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.bonc.com.cn/common/tag/cxt" prefix="cxt"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<cxt:commonLinks />
	<link rel="stylesheet" href="<%=request.getContextPath()%>/pages/css/bootstrap-multiselect.css" />
	<script type="text/javascript" src="<%=request.getContextPath()%>/pages/js/bootstrap-multiselect.js"></script>
	<script src="<%=request.getContextPath()%>/pages/jsp/dpi/dpiCommon.js"></script>
	<link rel="stylesheet" href="<%=request.getContextPath()%>/resource/ace/css/daterangepicker.css" />
	<script src="<%=request.getContextPath()%>/resource/ace/js/date-time/daterangepicker.js"></script>

<script type="text/javascript">
var queryLabelLvl = '<%=request.getAttribute("labelLvl")%>'
var queryLabelName = '<%=request.getAttribute("labelName")%>'
var queryProdName = '<%=request.getAttribute("prodName")%>'
</script>

<script src="<%=request.getContextPath()%>/pages/jsp/dpi/capabilityPreview/capabilityViewProdTable.js"></script>
</head>
<body>
	<div class="page-content clearfix">
		<div class="col-sm-12 grid-full">
			<!-- 查询div -->
			<div class="grid-search-div" id="gridSearch">
				<!-- 
				<input type="text" placeholder="产品Id" class="searchField input-md" id="prodId" name="prodId">
				<input type="hidden" placeholder="标签编码" class="searchField input-md" id="labelCodeHidden" name="prodLabelCode">
				<input type="text" placeholder="标签名称" class="searchField input-md" id="labelNameOverView" name="labelName1" readonly unselectable="on" onclick="sntestprod()">
				<input type="text" placeholder="操作人" class="searchField input-md" id="author" name="author">
				<div hidden="hidden"></div>
				<input class="searchField input-md datepicker-input" placeholder="操作时间" type="text" name="opTime" id="opTime" style="width:180px;"/>
                <input type="text" placeholder="产品名称" class="searchField input-md" id="prodName" name="prodName">
                <select name="batchFlag" id="exportProdType" class="chosen-select searchField" data-placeholder="不限" style="width:150px;">
				<option value="0">批量模糊匹配</option>
				<option value="1">批量精准匹配</option>
			    </select>
			     -->
			     
			    <input type="text" class="searchField input-md" placeholder="账期" id="queryTime" style="width:150px;">
			    <select name="" class="chosen-select searchField" id="cityId" style="width: 150px;">
        		</select>
        		<select name="fbFlag" id="fbFlag" class="chosen-select searchField" data-placeholder="" style="width:150px;">
				<option value="-1">是否发布</option>
				<option value="1">已发布</option>
				<option value="0">未发布</option>
			    </select>
			    
				<button class="btn btn-primary" type="button" id="searchPiList">查询</button>
				<button class="btn btn-primary" type="button" id="backButton" onclick="backButton()">返回</button>

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