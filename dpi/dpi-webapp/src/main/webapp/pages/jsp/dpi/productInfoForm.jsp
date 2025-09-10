<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="cxt" uri="http://www.bonc.com.cn/common/tag/cxt"%>
<link rel="stylesheet" href="<%=request.getContextPath()%>/pages/css/jquery-ui-1.10.0.custom.min.css">
<script src="<%=request.getContextPath()%>/pages/js/jquery-ui-1.10.0.custom.min.js"></script>
<div id="productInfoForm">
	<div class="clearfix formGroup">
		<label class="col-sm-3 labelForm">产品名称：</label>
		<div class="col-sm-9">
			<input type="text" placeholder="输入产品名称"  class="formField required"
				 class="formField required" id="pName" name="pName" value="${vo.pName}" />
		</div>
	</div>
</div>
<%-- <script src="<%=request.getContextPath()%>/pages/jsp/dpi/productInfoForm.js"></script> --%>
