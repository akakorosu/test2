<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="cxt" uri="http://www.bonc.com.cn/common/tag/cxt"%>
<!-- 编辑表单div -->
<div id="drForm">
	<input type="hidden" class="formField" name="author" value="${vo.author}">
	<input type="hidden" class="formField" name="opTime" value="${vo.opTime}">
	<input type="hidden" class="formField" name="domainCodeOld" value="${vo.domainCode}">
	<div class="clearfix formGroup">
	</div>
	<div class="clearfix formGroup">
		<label class="col-sm-2 labelForm">域名编码：</label>
		<div class="col-sm-4">
			<input value="${vo.domainCode }" type="text" placeholder="域名编码" onlyUrl="/domainRule/check,domainCode,domainCodeOld|主键重复" class="formField required isDomain" id="domainCode" name="domainCode" />
		</div>
		<label class="col-sm-2 labelForm">产品名称：</label> 
		<div class="col-sm-4">
			<input value="${vo.prodId }" type="hidden" placeholder="产品ID"  class="formField required" id="prodid" name="prodId">
			<input value="${vo.prodName }" type="text" placeholder="产品名称"   class="formField required" id="prodNameNew" name="prodName">
			<button class="btn btn-warning"  onclick="getProdIdList('','','prodName')">查询</button>
		</div>
	</div>
<!-- 	<div class="clearfix formGroup"> -->
<!-- 		<label class="col-sm-2 labelForm">是否有效：</label> -->
<!-- 		<div class="col-sm-4"> -->
<%-- 	    	<c:choose> --%>
<%-- 		    	<c:when test="${'1' eq vo.isValid}"> --%>
<!-- 					<input type="radio" value="1" checked class="formField required" name="isValid"/><span>有效</span> -->
<!-- 					<input type="radio" value="0" class="formField required" name="isValid"/><span>无效</span> -->
<%-- 		    	</c:when> --%>
<%-- 		    	<c:when test="${'0' eq vo.isValid}"> --%>
<!-- 					<input type="radio" value="1" class="formField required" name="isValid"/><span>有效</span> -->
<!-- 					<input type="radio" value="0" checked class="formField required" name="isValid"/><span>无效</span> -->
<%-- 		    	</c:when> --%>
<%-- 		    	<c:otherwise> --%>
<!-- 					<input type="radio" value="1" checked class="formField required" name="isValid"/><span>有效</span> -->
<!-- 					<input type="radio" value="0" class="formField required" name="isValid"/><span>无效</span> -->
<%-- 		    	</c:otherwise> --%>
<%-- 	    	</c:choose>		 --%>
<!--     	</div> -->
<!--    	</div> -->
</div>

<script src="<%=request.getContextPath()%>/pages/jsp/dpi/productInfo/productInfoForm.js"></script>
<%-- <script src="<%=request.getContextPath()%>/pages/jsp/dpi/cataWordLtb/cataWordLtbForm.js"></script> --%>
<script src="<%=request.getContextPath()%>/pages/jsp/dpi/dpiCommon.js"></script>