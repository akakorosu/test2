<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="cxt" uri="http://www.bonc.com.cn/common/tag/cxt"%>
<!-- 编辑表单div -->
<div id="upForm">

	<input type="hidden" class="formField" name="id" value="${vo.id}">
	<input type="hidden" class="formField" name="author" value="${vo.author}">
	<input type="hidden" class="formField" name="opTime" value="${vo.opTime}">
	<input type="hidden" class="formField" name="isValid" value="${vo.isValid}">
	<input type="hidden" class="formField" name="labelCode" id="labelCode" >
	<input type="hidden" class="formField" name="prodCatagoryCode" id="prodCatagoryCode" >
	<div class="clearfix formGroup">
	<p style="color: red">提示：截取规则从url截取</p>
	</div>
	<div class="clearfix formGroup">
		<label class="col-sm-2 labelForm">截取规则：</label> 
		<div class="col-sm-4">
			<input value="${vo.parseRule }" type="text" placeholder="截取规则"  class="formField required" id="parseRule" name="parseRule" />
			<button class="btn btn-success" id="checkDomainRule" onclick="checkDomainRule()">搜索域名</button>
		</div>
		<label class="col-sm-2 labelForm">产品类别：</label> 
		<div class="col-sm-4">
			<input value="${vo.prodCatagoryName }" type="text" placeholder="产品类别"  class="formField required" id="prodCatagoryName" readonly unselectable="on" name="prodCatagory" onclick="sntest4()"/>
		</div>
	</div>
	<%-- <div class="clearfix formGroup">		
		<label class="col-sm-2 labelForm">产品ID：</label>
		<div class="col-sm-4">
			<input value="${vo.prodId }" type="text" placeholder="产品ID"  class="formField required" id="prodId" name="prodId" />
		</div>
		<label class="col-sm-2 labelForm">产品名称：</label> 
		<div class="col-sm-4">
			<input value="${vo.prodName }" type="text" placeholder="产品名称"  class="formField required" id="prodName" name="prodName" />
		</div>
	</div> --%>
	<div class="clearfix formGroup">	
		<label class="col-sm-2 labelForm">产品ID：</label> 
		<div class="col-sm-4">
			<input value="${vo.prodId }" type="text" placeholder="产品ID" onlyUrl="/uakey/check,id,prodId,uaKey|联合校验重复!" class="formField " id="prodid" name="prodid" readonly unselectable="on"/>
			<!-- <button class="btn btn-warning" id="checkParseRuleAndriod1" onclick="getProdIdList1('','','prodId')">查询</button> -->		
		</div>
		<label class="col-sm-2 labelForm">产品名称：</label>
		<div class="col-sm-4">
			<input value="${vo.prodName }" type="text" placeholder="产品名称" class="formField required" id="prodName" name="prodName"  />
			<button class="btn btn-warning" id="checkParseRuleAn" onclick="getProdIdList1('','','prodName')">查询</button>
		</div>
	</div>
	<div class="clearfix formGroup">
		<label class="col-sm-2 labelForm">标签名称：</label> 
		<div class="col-sm-4">
			<input value="${vo.labelCode }" type="text" placeholder="标签名称"  class="formField required" id="labelName2" readonly unselectable="on" name="labelName" onclick="sntest3()"/>
		</div>
		<label class="col-sm-2 labelForm">URL样例：</label> 
		<div class="col-sm-4">
			<input value="${url }" type="text" placeholder="URL样例"  class="formField required" id="urlExample" name="urlExample"  readonly/>
		</div>
	</div>
	<%-- <label class="col-sm-2 labelForm">是否有效：</label> 
		<div class="col-sm-4">
	    	<c:choose>
		    	<c:when test="${'1' eq vo.isValid}">
					<input type="radio" value="1" checked class="formField required" name="isValid"/><span>有效</span>
					<input type="radio" value="0" class="formField required" name="isValid"/><span>无效</span>
		    	</c:when>
		    	<c:when test="${'0' eq vo.isValid}">
					<input type="radio" value="1" class="formField required" name="isValid"/><span>有效</span>
					<input type="radio" value="0" checked class="formField required" name="isValid"/><span>无效</span>
		    	</c:when>
		    	<c:otherwise>
					<input type="radio" value="1" checked class="formField required" name="isValid"/><span>有效</span>
					<input type="radio" value="0" class="formField required" name="isValid"/><span>无效</span>
		    	</c:otherwise>
	    	</c:choose>		
    	</div> --%>

<%-- <script src="<%=request.getContextPath()%>/pages/jsp/dpi/domainFuzzyRule/domainFuzzyRuleFormInsert.js"></script> --%>
<script src="<%=request.getContextPath()%>/pages/jsp/dpi/urlFuzzyRecognition/domainFuzzyRuleFormInsert.js"></script>

