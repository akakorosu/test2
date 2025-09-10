<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="cxt" uri="http://www.bonc.com.cn/common/tag/cxt"%>
<!-- 编辑表单div -->
<div id="upForm">
	<input type="hidden" class="formField" name="id" value="${vo.id}">
	<input type="hidden" class="formField" name="author" value="${vo.author}">
	<input type="hidden" class="formField" name="opTime" value="${vo.opTime}">
	<div class="clearfix formGroup">
		<p style="color: red">提示：产品ID、截取规则联合主键不能重复</p>
	</div>
	<div class="clearfix formGroup">
		<label class="col-sm-2 labelForm">截取规则：</label> 
		<div class="col-sm-4">
			<input value="${vo.parseRule }" type="text" placeholder="截取规则" onlyUrl="/domainFuzzyRule/check,id,prodid,parseRuleParam|联合校验重复!" class="formField required" id="parseRule" name="parseRuleParam" />
			<button class="btn btn-success" id="checkParseRuleAndriod" onclick="checkRule('1')">校验</button>
		</div>
		<label class="col-sm-2 labelForm">分组序号：</label> 
		<div class="col-sm-4">
			<input value="${vo.getIndex }" type="text" placeholder="获取结果分组序号"  class="formField isNum" id="getIndex" name="getIndex"  maxNum='9' minNum='0'/>
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
		<label class="col-sm-2 labelForm">产品名称：</label>
		<div class="col-sm-4">
			<input value="${vo.prodName }" type="text" placeholder="产品名称" onlyUrl="/ipport/checkId,id,prodName|产品不存在!" class="formField" id="prodName" name="prodName"  />
			<button class="btn btn-warning" id="checkParseRuleAndriod" onclick="getProdIdList('','','prodName')">查询</button>
		</div>	
		<label class="col-sm-2 labelForm">产品ID：</label> 
		<div class="col-sm-4">
			<input value="${vo.prodid }" type="text" placeholder="产品ID" onlyUrl="/domainFuzzyRule/check,id,prodid,parseRuleParam|联合校验重复!" class="formField required" id="prodid" name="prodid" readonly/>
			<!-- <button class="btn btn-warning" id="checkParseRuleAndriod" onclick="getProdIdList('','','prodId')">查询</button>		 -->
		</div>
		
	</div>
	<div class="clearfix formGroup">
		<label class="col-sm-2 labelForm">URL样例：</label> 
		<div class="col-sm-4">
			<input value="${vo.urlExample }" type="text" placeholder="URL样例"  class="formField" id="urlExample" name="urlExample" />
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

<script src="<%=request.getContextPath()%>/pages/jsp/dpi/domainFuzzyRule/domainFuzzyRuleFormInsert.js"></script>
<script src="<%=request.getContextPath()%>/pages/jsp/dpi/dpiCommon.js"></script>