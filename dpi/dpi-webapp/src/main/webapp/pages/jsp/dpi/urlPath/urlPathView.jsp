<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="cxt" uri="http://www.bonc.com.cn/common/tag/cxt"%>
<!-- 编辑表单div -->
<div id="upForm">
	<div class="clearfix formGroup">
		<label class="col-sm-2 labelForm">域名：</label> 
		<div class="col-sm-4">
			<input value="${vo.host }" type="text" readonly="readonly" />
		</div>
		<label class="col-sm-2 labelForm">产品名称：</label> 
		<div class="col-sm-4">
			<input value="${vo.prodName }" type="text" readonly="readonly" />
		</div>
	</div>
	<div class="clearfix formGroup">
		<label class="col-sm-2 labelForm">匹配规则：</label> 
		<div class="col-sm-4">
			<input value="${vo.regexpRule }" type="text" readonly="readonly" />
		</div>
		<label class="col-sm-2 labelForm">备注：</label>
		<div class="col-sm-4">
			<input value="${vo.comments }" type="text" readonly="readonly" />
		</div>
	</div>
	<div class="clearfix formGroup">
		<label class="col-sm-2 labelForm">URL样例：</label>
		<div class="col-sm-4">
			<input value="${vo.urlExample }" type="text" readonly="readonly" />
		</div>
	</div>
	<div class="clearfix formGroup">
		<label class="col-sm-2 labelForm">操作人：</label> 
		<div class="col-sm-4">
			<input value="${vo.author }" type="text" readonly="readonly" />
		</div>
		<label class="col-sm-2 labelForm">操作时间：</label>
		<div class="col-sm-4">
			<input value="${vo.opTime }" type="text" readonly="readonly" />
		</div>
	</div>
<!-- 	<div class="clearfix formGroup"> -->
<!-- 		<label class="col-sm-2 labelForm">是否有效：</label>  -->
<!-- 		<div class="col-sm-4"> -->
<%-- 			<c:if test="${vo.isValid ==1}"> --%>
<!-- 				<input value="有效" type="text" readonly="readonly" /> -->
<%-- 			</c:if>	 --%>
<%-- 			<c:if test="${vo.isValid ==0}"> --%>
<!-- 				<input value="无效" type="text" readonly="readonly" /> -->
<%-- 			</c:if>				 --%>
<!-- 		</div> -->
<!-- 		<label class="col-sm-2 labelForm">累计访问次数：</label> -->
<!-- 		<div class="col-sm-4"> -->
<%-- 			<input value="${vo.totalCount }" type="text" readonly="readonly" /> --%>
<!-- 		</div> -->
<!-- 	</div>			 -->
</div>

