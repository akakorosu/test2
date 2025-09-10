<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="cxt" uri="http://www.bonc.com.cn/common/tag/cxt"%>
<!-- 编辑表单div -->
<div id="upForm">
	<input type="hidden" class="formField" name="id" value="${vo.id}">
	<input type="hidden" class="formField" name="author" value="${vo.author}">
	<input type="hidden" class="formField" name="opTime" value="${vo.opTime}">
	<div class="clearfix formGroup">
	<p style="color: red">提示：域名、匹配规则这两个值组合的联合主键不能重复</p>
	</div>
	<div class="clearfix formGroup">
		<label class="col-sm-2 labelForm">域名：</label>
		<div class="col-sm-4">
			<input value="${vo.host }" type="text" placeholder="域名" onlyUrl="/urlParameter/check,id,hostParam,regexpRuleParam|联合主键重复" class="formField required" id="host" name="hostParam" />
		</div>
		<label class="col-sm-2 labelForm">产品名称：</label>
		<div class="col-sm-4">
			<!--注意：此处的产品标签id是prodid -->
			<input value="${vo.prodId }" type="hidden" placeholder="产品ID"  class="formField required" id="prodid" name="prodId" >
			<input value="${vo.prodName }" type="text" placeholder="产品名称" onlyUrl="/productInfo/prodIdCheck,id,prodId|产品id必须是产品表中的"  class="formField required" id="prodNameNew" name="prodName">
			<button class="btn btn-warning"  onclick="getProdIdList('','','prodName')">查询</button>
		</div>
	</div>
	<div class="clearfix formGroup">
		<label class="col-sm-2 labelForm">参数名：</label> 
		<div class="col-sm-4">
			<input value="${vo.parameterCode }" type="text" placeholder="参数名"  class="formField required" id="parameterCode" name="parameterCode" >
		</div>
		<label class="col-sm-2 labelForm">参数值：</label> 
		<div class="col-sm-4">
			<input value="${vo.parameterValue }" type="text" placeholder="参数值"  class="formField required" id="parameterValue" name="parameterValue" >
		</div>
	</div>
	<div class="clearfix formGroup">
		<label class="col-sm-2 labelForm">匹配规则：</label> 
		<div class="col-sm-4">
			<input value="${vo.regexpRule }" type="text" placeholder="匹配规则" onlyUrl="/urlParameter/check,id,hostParam,regexpRuleParam|联合主键重复" class="formField required " id="regexpRuleParam" name="regexpRuleParam">
		</div>
		<label class="col-sm-2 labelForm">备注：</label>
		<div class="col-sm-4">
			<input value="${vo.comments }" type="text" placeholder="备注" class="formField " id="comments" name="comments">
		</div>
	</div>
	<div class="clearfix formGroup">
		<label class="col-sm-2 labelForm">URL样例：</label> 
		<div class="col-sm-4">
			<input value="${vo.urlExample }" type="text" placeholder="URL样例" class="formField " id="urlExample" name="urlExample">
		</div>
	</div>	
</div>

<script src="<%=request.getContextPath()%>/pages/jsp/dpi/productInfo/productInfoForm.js"></script>
<script src="<%=request.getContextPath()%>/pages/jsp/dpi/urlParameter/urlParameterForm.js"></script>
<script src="<%=request.getContextPath()%>/pages/jsp/dpi/dpiCommon.js"></script>