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
	<script src="<%=request.getContextPath()%>/pages/jsp/dpi/productLabel/productLabelList.js"></script>
	<script src="<%=request.getContextPath()%>/pages/jsp/dpi/dpiCommon.js"></script>
	<link rel="stylesheet" href="<%=request.getContextPath()%>/resource/ace/css/daterangepicker.css" />
	<script src="<%=request.getContextPath()%>/resource/ace/js/date-time/daterangepicker.js"></script>
</head>
<body>
	<div class="page-content clearfix">
		<div class="col-sm-12 grid-full">
			<!-- 查询div -->
			<div class="grid-search-div" id="gridSearch">
				<select placeholder="分类名称"  style='width:150px;'  class="searchField formField" id="label_Name" name="labelName">
					<option disabled="disabled">分类名称</option>
					<option selected disabled style="display: none;">全部</option>		
					<option>休闲娱乐</option>
					<option>生活类</option>
					<option>行业类</option>
					<option>工具类</option>
					<option>个性偏好</option>
					<option>其他</option>   
           	    </select>
			    <input type="text" placeholder="标签编码" class="searchField input-md" id="label_code" name='labelCode'>
				<input type="text" placeholder="标签"  style='width:150px;' id="label" class="searchField formField"  name="label" readOnly="readonly" onclick="select_label()">
				<!-- <input type="text" placeholder="操作时间" class="searchField input-md datepicker-input" style="width:150px;" id="opTime" name="opTime"> -->
				<input type="text" placeholder="操作人" class="searchField input-md" id="author" name="author">
				
				<div hidden="hidden"></div>
				<input class="searchField input-md datepicker-input" placeholder="操作时间" type="text" name="opTime" id="opTime" style="width:180px;"/>
				<button class="btn btn-primary" type="button" id="searchPiList">查询</button>
				<button class="btn btn-primary" type="button" id="clearRuleSearch" >清空</button>
	            
				<!--批处理 -->
	            <jsp:include page="/pages/jsp/frame/operationFlow.jsp">
	            	<jsp:param value="1,0,1,1,1" name="isShow"/>
					<jsp:param value="com.bonc.dpi.dao.entity.ProductLabel" name="entityName"/>
					<jsp:param value="productLabelService" name="beanName"/>
					<jsp:param value="product_label.xlsx" name="templateName"/>
					<jsp:param value="0,1,2,3,4,5,6,7,8,9,10" name="fieldNums"/>
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