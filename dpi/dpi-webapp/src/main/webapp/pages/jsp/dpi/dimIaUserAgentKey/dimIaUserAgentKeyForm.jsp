<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="cxt" uri="http://www.bonc.com.cn/common/tag/cxt"%>
<!-- 编辑表单div -->
<script type="text/javascript">
 var uaKey = "<%=request.getParameter("uaKey")%>";

</script>
<div id="showKeyForm">
	<div class="clearfix formGroup">
		<p style="color: red">提示：UA关键字，产品ID联合主键不能重复</p>
	</div>
	<!-- <input type="hidden"  class="formField" name="prodid" id="prodid" > -->
	<div class="clearfix formGroup">
		<div class="col-sm-4">
			<input value="${vo.id }" type="hidden" class="formField required" id="id" name="id" />
		</div>
	</div>

	<div class="clearfix formGroup">
		<label class="col-sm-2 labelForm">UA关键字：</label> 
		<div class="col-sm-4">
			<input value="${vo.uaKey }" type="text"  placeholder="UA关键字" onlyUrl="/uakey/check,id,prodIdParam,uaKeyParam|联合校验重复!" class="formField required" id="uaKey" name="uaKeyParam" />
		</div>
		
		<label class="col-sm-2 labelForm">举例：</label> 
		<div class="col-sm-4">
			<input value="${vo.uaExample }" type="text" placeholder="举例" class="formField" id="uaExample" name="uaExample" />
		</div>		
	</div>
	
	<div class="clearfix formGroup">	
		<label class="col-sm-2 labelForm">产品名称：</label> 
		<div class="col-sm-4">
			<input value="${vo.prodName }" type="text" placeholder="产品名称"  onlyUrl="/uakey/checkId,id,prodName|产品不存在!" class="formField " id="prodName" name="prodName" />
			<button class="btn btn-warning" id="checkParseRuleAndriod" onclick="getProdIdList('','','prodName')">查询</button>		
		</div>
		<label class="col-sm-2 labelForm">产品ID：</label>
		<div class="col-sm-4">
			<input value="${vo.prodId }" type="text" placeholder="产品ID" onlyUrl="/uakey/check,id,prodIdParam,uaKeyParam|联合校验重复!" class="formField required" id="prodIdParam" name="prodIdParam" readonly/>
		</div>	
	</div>
	
	<div class="clearfix formGroup">
		<label class="col-sm-2 labelForm">备注：</label>
		<div class="col-sm-4">
				<input value="${vo.comments }" type="text" placeholder="备注" class="formField" id="comments" name="comments" />
		</div>	

	</div>
	
</div>


<script src="<%=request.getContextPath()%>/pages/jsp/dpi/dimIaUserAgentKey/dimIaUserAgentKeyForm.js"></script>
<script src="<%=request.getContextPath()%>/pages/jsp/dpi/dpiCommon.js"></script>