<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.bonc.com.cn/common/tag/cxt" prefix="cxt"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<cxt:commonLinks />

	<link rel="stylesheet" href="<%=request.getContextPath()%>/pages/css/bootstrap-multiselect.css" />
	
	<script type="text/javascript" src="<%=request.getContextPath()%>/pages/js/bootstrap-multiselect.js"></script>
	<%-- <script type="text/javascript" src="<%=request.getContextPath()%>/pages/jsp/dpi/common/datepickerDpi.js"></script> --%>
	<script src="<%=request.getContextPath()%>/pages/jsp/dpi/productInfo/productInfoList.js"></script>
	<%-- <script type="text/javascript" src="<%=request.getContextPath()%>/pages/jsp/dpi/industryOverview/industryOverview.js"></script> --%>
	<script src="<%=request.getContextPath()%>/pages/jsp/dpi/dpiCommon.js"></script>
	<link rel="stylesheet" href="<%=request.getContextPath()%>/resource/ace/css/daterangepicker.css" />
	<script src="<%=request.getContextPath()%>/resource/ace/js/date-time/daterangepicker.js"></script>

</head>
<body>
	<div class="page-content clearfix">
		<div class="col-sm-12 grid-full">
			<!-- 查询div -->
			<div class="grid-search-div" id="gridSearch">
				<input type="text" placeholder="产品Id" class="searchField input-md" id="prodId" name="prodId">
				
				<input type="hidden" placeholder="标签编码" class="searchField input-md" id="labelCodeHidden" name="prodLabelCode">
				<input type="text" placeholder="标签名称" class="searchField input-md" id="labelNameOverView" name="labelName1" readonly unselectable="on" onclick="sntestprod()">
				<!-- <input type="text" placeholder="操作时间" class="searchField input-md datepicker-input" style="width:100px;" id="opTime" name="opTime">	 -->
				<input type="text" placeholder="操作人" class="searchField input-md" id="author" name="author">
				
				<div hidden="hidden"></div>
				<input class="searchField input-md datepicker-input" placeholder="操作时间" type="text" name="opTime" id="opTime" style="width:180px;"/>
<!-- 				<input type="text" placeholder="二级标签名称" class="searchField input-md" id="labelName2" name="labelName2"> -->
                <input type="text" placeholder="产品名称" class="searchField input-md" id="prodName" name="prodName">
                <select name="batchFlag" id="exportProdType" class="chosen-select searchField" data-placeholder="不限" style="width:150px;">
				<option value="0">批量模糊匹配</option>
				<option value="1">批量精准匹配</option>
			    </select>
				<button class="btn btn-primary" type="button" id="searchPiList">查询</button>
				<button class="btn btn-primary" type="button" id="clearList" onclick="clearGridSearch()">清空</button>
	            
				<!--批处理 -->
<%-- 	            <jsp:include page="/pages/jsp/frame/operationFlow.jsp"> --%>
<%-- 					<jsp:param value="com.bonc.dpi.dao.entity.ProductInfo" name="entityName"/> --%>
<%-- 					<jsp:param value="productInfoService" name="beanName"/> --%>
<%-- 					<jsp:param value="product_info.xlsx" name="templateName"/> --%>
<%-- 					<jsp:param value="0,1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17" name="fieldNums"/> --%>
<%-- 					<jsp:param value="1,1,1,1,1" name="isShow"/> --%>
<%-- 					<jsp:param value="falseDataViewFromExcle" name="impCallbackFun"/> --%>
<%-- 				</jsp:include> --%>
					<%-- 	第二个为0--%>
	            <jsp:include page="/pages/jsp/frame/operationFlow.jsp">
					<jsp:param value="com.bonc.dpi.dao.entity.DimIaProductInfoDomain" name="entityName"/>
					<jsp:param value="dimIaProductInfoService" name="beanName"/>
					<jsp:param value="dim_ia_product_info_domain.xlsx" name="templateName"/>
					<jsp:param value="0,1,2,4,7,8,9,10,11" name="fieldNums"/>
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