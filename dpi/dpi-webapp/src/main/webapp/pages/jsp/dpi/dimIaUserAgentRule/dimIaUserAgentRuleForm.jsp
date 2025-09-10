<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="cxt" uri="http://www.bonc.com.cn/common/tag/cxt"%>
<!-- 编辑表单div -->
<div id="showRuleForm">
	<div class="clearfix formGroup">
		<p style="color: red">提示：产品ID、识别规则联合主键不能重复</p>
	</div>
	<div class="clearfix formGroup">
		<div class="col-sm-4">
			<input value="${vo.id }" type="hidden" type="text"  placeholder="ID" class="formField required" id="id" name="id" />
		</div>
	</div>
	
	
	<div class="clearfix formGroup">
		<label class="col-sm-2 labelForm">产品名称：</label> 
		<div class="col-sm-4">
			<input value="${vo.prodName }" type="text" placeholder="产品名称" onlyUrl="/uarule/checkId,id,prodName|产品不存在!" class="formField " id="prodName" name="prodName" />
			<button class="btn btn-warning" id="checkParseRuleAndriod" onclick="getProdidList('','','prodName')">查询</button>		
		</div>
		<label class="col-sm-2 labelForm">产品ID：</label> 
		<div class="col-sm-4">
			<input value="${vo.prodId }" type="text" placeholder="产品ID"  onlyUrl="/uarule/check,id,prodIdParam,uaKeyRuleParam|联合校验重复!" class="formField required"  id="prodIdParam" name="prodIdParam" readonly/>
		</div>
		
	</div>
	
	<div class="clearfix formGroup">
		<label class="col-sm-2 labelForm">识别规则：</label> 
		<div class="col-sm-4">
			<input value="${vo.uaKeyRule }" type="text" placeholder="识别规则"  onlyUrl="/uarule/check,id,prodIdParam,uaKeyRuleParam|联合校验重复!"  class="formField required" id="uaKeyRule" name="uaKeyRuleParam" />
		</div>	
		<label class="col-sm-2 labelForm">正则分组：</label>
		<div class="col-sm-4">
				<input value="${vo.getIndex }" type="text" placeholder="正则分组" class="formField isNum" id="getIndex" name="getIndex" maxNum='9' minNum='0'/>
		</div>
	</div>
	
	<div class="clearfix formGroup">
		<label class="col-sm-2 labelForm">举例：</label> 
		<div class="col-sm-4">
			<input value="${vo.uaExample }" type="text" placeholder="举例" class="formField" id="uaExample" name="uaExample" />
		</div>
		<label class="col-sm-2 labelForm">备注：</label>
		<div class="col-sm-4">
				<input value="${vo.comments }" type="text" placeholder="备注" class="formField" id="comments" name="comments" />
		</div>	
		<label class="col-sm-2 labelForm"></label> 
		<div class="col-sm-4">
			<input value="${vo.author }" type="hidden" placeholder="操作人" class="formField " id="author" name="author" />
		</div>	
	</div>
</div>
<script src="<%=request.getContextPath()%>/pages/jsp/dpi/dimIaUserAgentRule/dimIaUserAgentRuleForm.js"></script>
<script src="<%=request.getContextPath()%>/pages/jsp/dpi/dpiCommon.js"></script>