<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.bonc.com.cn/common/tag/cxt" prefix="cxt"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<cxt:commonLinks />
<script type="text/javascript">
 var time = "<%=request.getParameter("time")%>";
 var typeId = "<%=request.getParameter("typeId")%>";
</script>
<script src="<%=request.getContextPath()%>/pages/jsp/dpi/unidentifiedUrl/unidentifiedUrlList.js"></script>
</head>
<body>
	
	<div class="page-content clearfix">
		<div class="col-sm-12 grid-full">
			<!-- 查询div -->
			<div class="grid-search-div" id="gridSearch">
				
	            
				<!--批处理 -->
	           <%--  <jsp:include page="/pages/jsp/frame/operationFlow.jsp">
					<jsp:param value="com.bonc.dpi.dao.entity.ProductInfo" name="entityName"/>
					<jsp:param value="productInfoService" name="beanName"/>
					<jsp:param value="product_info.xlsx" name="templateName"/>
					<jsp:param value="product_info.txt" name="txtName"/>
					<jsp:param value="0|1|2|3|4|5|6|7|8|9|10|11|12|13|14|15|16|17|18|19" name="fieldNums"/>
				</jsp:include>	 --%>		
			
			</div>
			<!-- 列表div -->
			<label for="">稽核状态：</label> 
			
			<select name="" id="status" class="chosen-select" data-placeholder="不限" style="width:170px;">
				<option value="2">全部</option>
				<option value="1">已稽核</option>
				<option value="0">未稽核</option>
				
			</select>
			<button  class="btn btn-primary" id="batch">批量稽核</button>
			<button class="btn btn-primary" type="button" id="backto" >返回</button>
			<div id="gridContainer">
				<table id="grid-table"></table>
				<div id="grid-pager"></div>
			</div>
		</div>
	</div>
</body>

</html>