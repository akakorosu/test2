<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="cxt" uri="http://www.bonc.com.cn/common/tag/cxt"%>
<!-- 编辑表单div -->
<div id="showIpPortForm">
	<div class="clearfix formGroup">
		<p style="color: red">提示：IP端口重复校验</p>
	</div> 
	<div class="clearfix formGroup">
		<div class="col-sm-4">
			<input value="${vo.id }" type="hidden" class="formField required" id="id" name="id" />			
		</div>
	</div>
	<div class="clearfix formGroup">
	
	<div class="clearfix formGroup">
		<label class="col-sm-2 labelForm">IP端口：</label> 
		<div class="col-sm-4">
			<input value="${vo.ipPort }" type="text"  placeholder="IP、端口以 :分割" class="formField required" onlyUrl="/ipport/check,id,ipPortParam,prodid|IP端口校验重复或数据格式错误!"  id="ipPort" name="ipPortParam" />
		</div>
		
		
	</div>
	<div class="clearfix formGroup">
		<label class="col-sm-2 labelForm">产品名称：</label> 
		<div class="col-sm-4">
			<input value="${vo.prodName }" type="text" placeholder="产品名称" class="formField required" onlyUrl="/ipport/checkId,id,prodName|产品不存在!"  id="prodName" name="prodName" />
			<button class="btn btn-warning" id="checkParseRuleAndriod" onclick="getProdIdList('','','prodName')">查询</button>		
		</div>
		<label class="col-sm-2 labelForm">产品ID：</label>
		<div class="col-sm-4">
			<input value="${vo.prodid }" type="text" placeholder="产品ID"  class="formField required" onlyUrl="/ipport/check,id,ipPortParam,prodid|IP端口校验重复或数据格式错误!"  id="prodid" name="prodid" readonly/>
		</div>
	</div>
   
	<div class="clearfix formGroup">
		<label class="col-sm-2 labelForm"></label>
		<div class="col-sm-4">
				<input value="${vo.port }" type="hidden" placeholder="端口" class="formField required" id="port" name="port" />
		</div>
		<label class="col-sm-2 labelForm"></label> 
		<div class="col-sm-4">
			<input value="${vo.ip }" type="hidden" placeholder="IP" class="formField required" id="ip" name="ip" />
		</div>
	</div>	
	<div class="clearfix formGroup">
		<label class="col-sm-2 labelForm"></label>
		<div class="col-sm-4">
				<input value="${vo.author }" type="hidden" placeholder="操作人" class="formField required" id="author" name="author" />
		</div>

		<label class="col-sm-2 labelForm"></label> 
		<div class="col-sm-4">
			<input value="${vo.opTime }" type="hidden" placeholder="操作时间" class="formField required" id="opTime" name="opTime" />
		</div>
	</div>
    
</div>
<script src="<%=request.getContextPath()%>/pages/jsp/dpi/dimIaIpPortDynamic/dimIaIpPortDynamicForm.js"></script>
<script src="<%=request.getContextPath()%>/pages/jsp/dpi/dpiCommon.js"></script>