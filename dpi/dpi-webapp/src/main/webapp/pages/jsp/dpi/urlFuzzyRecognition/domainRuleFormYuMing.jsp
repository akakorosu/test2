<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="cxt" uri="http://www.bonc.com.cn/common/tag/cxt"%>
<!-- 编辑表单div -->
<script src="<%=request.getContextPath()%>/pages/jsp/dpi/urlFuzzyRecognition/domainRuleFormYuMing.js"></script>
<div id="drForm">
	
	<div class="clearfix formGroup">
	<p style="color: red">提示：域名编码从url截取</p>
	</div>
	<div class="clearfix formGroup">
		<label class="col-sm-2 labelForm">域名编码：</label>
		<div class="col-sm-4">
			<input  type="text" placeholder="域名编码"  class="formField required" id="domainCode" name="domainCode" />
		</div>
		<label class="col-sm-2 labelForm">产品名称：</label> 
		<div class="col-sm-4">
			<input  type="hidden" placeholder="产品ID"  class="formField required" id="prodId" name="prodId">
			<input  type="text" placeholder="产品名称"   class="formField required" id="prodName" name="prodName">
			<button class="btn btn-warning"  onclick="getProdIdList('','','prodName')">查询</button>
		</div>
	</div>
	<div class="clearfix formGroup">
		<label class="col-sm-2 labelForm">标签code：</label>
		<div class="col-sm-4">
			<input  type="text" placeholder="标签code"  class="formField required" readonly unselectable="on" id="labelCode" name="labelCode" />
		</div>
		<label class="col-sm-2 labelForm">标签名称：</label> 
		<div class="col-sm-4">
			<input  type="text" placeholder="标签名称"   class="formField required" readonly unselectable="on" id="labelName" name="labelName" onclick="sntest()">
			
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



