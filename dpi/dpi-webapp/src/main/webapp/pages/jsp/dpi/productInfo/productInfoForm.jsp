<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="cxt" uri="http://www.bonc.com.cn/common/tag/cxt"%>
<!-- 编辑表单div -->
<style>
.input-md1{
    width: 180px;
    max-width: 100%;
   
   }
</style>
<div id="piForm">
	<input type="hidden" class="formField" id="id" name="id" value="${vo.id}">
	<input type="hidden" class="formField" name="prodIdOld" value="${vo.prodIdOld}">
	<input type="hidden" class="formField" name="author" value="${vo.author}">
	<input type="hidden" class="formField" name="opTime" value="${vo.opTime}">
	<div class="clearfix formGroup">
	<p style="color: red">提示：产品ID不能重复</p>
	</div>
	<div class="clearfix formGroup">
		<label class="col-sm-2 labelForm">产品ID：</label> 
		<div class="col-sm-4">
			<input value="${vo.prodId }" type="text" placeholder="产品ID" readonly="readonly" class="formField required" id="prodid" name="prodId" >
<%-- 			<input value="${vo.prodId }" type="text" placeholder="产品ID" <c:if test="${!empty vo.id}">readonly="readonly"</c:if> onlyUrl="/productInfo/check,id,prodId"  class="formField required" id="prodid" name="prodId" > --%>
<%-- 			<c:if test="${empty vo.id}"> --%>
<!-- 				<button class="btn btn-success" id="checkprodId" onclick="checkProdId()">校验</button> -->
<%-- 			</c:if>		 --%>
		</div>	
		<label class="col-sm-2 labelForm">安卓产品名称：</label>
		<div class="col-sm-4">
			<input value="${vo.prodName }" type="text" placeholder="产品名称" onlyUrl="/productInfo/checkRepeat,prodName,id" <c:if test="${!empty vo.id}"></c:if> class="formField required" id="prodName" name="prodName" />
		</div>
	</div>
	<div class="clearfix formGroup">
		<label class="col-sm-2 labelForm">标签名称：</label>
		<div class="col-sm-4">
			<input value="${vo.prodLabelCode}" type="hidden" placeholder="标签ID"   class="formField required " id="labelCodeHidden_form" name="prodLabelCode">
			<input value="${labelName}" type="text" placeholder="标签名称"  class="formField required " readonly id="labelNameOverView_form">
			<button class="btn btn-success" id="queryProdLabelCode" onclick="getLableCode_byForm()">查询</button>
		</div>
		<label class="col-sm-2 labelForm">附属标签列表：</label>
		<div class="col-sm-4">
			<input value="${vo.addLabelList }" type="text" placeholder="附属标签列表" class="formField " id="addLabelList" name="addLabelList">
		</div>
	</div>
	<div class="clearfix formGroup">
		<label class="col-sm-2 labelForm">父产品ID：</label>
		<div class="col-sm-4">
			<input value="${vo.fatherProdId }" type="text" placeholder="父产品ID" class="formField " id="fatherProdId" name="fatherProdId">
		</div>
		<label class="col-sm-2 labelForm">苹果产品名称：</label>
		<div class="col-sm-4">
			<input value="${vo.iosProdName}" type="text" placeholder="苹果产品名称" class="formField " id="iosProdName" name="iosProdName">
		</div>		
	</div>
	<div class="clearfix formGroup">
		<label class="col-sm-2 labelForm">产品描述：</label>
		<div class="col-sm-4">
			<input value="${vo.prodDesc }" type="text" placeholder="产品描述" class="formField " id="prodDesc" name="prodDesc">
		</div>
		<label class="col-sm-2 labelForm">是否拨测：</label>
		<div class="col-sm-4">
<%-- 			<input value="${vo. isApp}" type="text" placeholder="是否APP" class="formField required " id="isApp" name="isApp"> --%>
	    	<c:choose>
		    	<c:when test="${'1' eq vo.isBonc}">
					<input type="radio" value="1" checked class="formField required" name="isBonc"/><span>是</span>
					<input type="radio" value="0" class="formField required" name="isBonc"/><span>否</span>
		    	</c:when>
		    	<c:when test="${'0' eq vo.isBonc}">
					<input type="radio" value="1" class="formField required" name="isBonc"/><span>是</span>
					<input type="radio" value="0" checked class="formField required" name="isBonc"/><span>否</span>
		    	</c:when>
		    	<c:otherwise>
					<input type="radio" value="1" checked class="formField required" name="isBonc"/><span>是</span>
					<input type="radio" value="0" class="formField required" name="isBonc"/><span>否</span>
		    	</c:otherwise>
	    	</c:choose>
		</div>		
	</div>
	<div class="clearfix formGroup">
		<label class="col-sm-2 labelForm">拨测时间：</label>
		<div class="col-sm-4">
			<input value="${vo.boceDate }" type="text" placeholder="时间" class="formField searchField input-md1 datepicker-input" id="boceDate" name="boceDate" data-date-format="yyyymmdd" >
		</div>
		<div class="clearfix formGroup">
		<label class="col-sm-2 labelForm">是否稽核：</label>
		<div class="col-sm-4">
		<c:choose>
		    	<c:when test="${'1' eq vo.labelCheck}">
					<input type="radio" value="1" checked class="formField required" name="labelCheck"/><span>是</span>
					<input type="radio" value="0" class="formField required" name="labelCheck"/><span>否</span>
		    	</c:when>
		    	<c:when test="${'0' eq vo.labelCheck}">
					<input type="radio" value="1" class="formField required" name="labelCheck"/><span>是</span>
					<input type="radio" value="0" checked class="formField required" name="labelCheck"/><span>否</span>
		    	</c:when>
		    	<c:otherwise>
					<input type="radio" value="1" checked class="formField required" name="labelCheck"/><span>是</span>
					<input type="radio" value="0" class="formField required" name="labelCheck"/><span>否</span>
		    	</c:otherwise>
	    	</c:choose>
			<%-- <c:if test="${vo.labelCheck ==1}">
				<input value="是" type="text" readonly="readonly" />
			</c:if>	
			<c:if test="${vo.labelCheck !=1}">
				<input value="否" type="text" readonly="readonly" />
			</c:if> --%>
		</div>
	</div>
			
	</div>
	<div class="clearfix formGroup">
		<label class="col-sm-2 labelForm">产品归类ID：</label>
		<div class="col-sm-4">
			<input value="${vo.prodTypeId }" type="text" placeholder="产品归类ID" class="formField " id="prodTypeId" name="prodTypeId">
		</div>
		<label class="col-sm-2 labelForm">产品归类名称：</label>
		<div class="col-sm-4">
			<input value="${vo.prodTypeName }" type="text" placeholder="产品归类名称" class="formField " id="prodTypeName" name="prodTypeName">
		</div>		
	</div>	
	<div class="clearfix formGroup">
		<label class="col-sm-2 labelForm">产品类别：</label> 
		<div class="col-sm-4">
		    <input  type="hidden"    class="formField required " id="CodeHiddenCatagory" name="prodCatagory" >
			<input value="${labelNameType}" type="text" placeholder="产品类别" class="formField " id="prodCatagory" readonly >
			<button class="btn btn-success" id="queryProdCatagory" onclick="getLableCodeCatagory()">查询</button>
		</div>
		<label class="col-sm-2 labelForm">运营商业务：</label>
		<div class="col-sm-4">
<%-- 			<input value="${vo.isOperator }" type="text" placeholder="运营商自有业务" class="formField required " id="isOperator" name="isOperator"> --%>
			<cxt:CodeSelectTlb name="isOperator" codeType="isOperator" value="${vo.isOperator }" defaultyn="n" cssClass="formField"></cxt:CodeSelectTlb>
		</div>		
	</div>
	<div class="clearfix formGroup">
		<label class="col-sm-2 labelForm">是否主产品：</label>
		<div class="col-sm-4">
<%-- 			<input value="${vo.isMainProd }" type="text" placeholder="是否主产品" class="formField required " id="isMainProd" name="isMainProd"> --%>
	    	<c:choose>
		    	<c:when test="${'1' eq vo.isMainProd}">
					<input type="radio" value="1" checked class="formField required" name="isMainProd"/><span>是</span>
					<input type="radio" value="0" class="formField required" name="isMainProd"/><span>否</span>
		    	</c:when>
		    	<c:when test="${'0' eq vo.isMainProd}">
					<input type="radio" value="1" class="formField required" name="isMainProd"/><span>是</span>
					<input type="radio" value="0" checked class="formField required" name="isMainProd"/><span>否</span>
		    	</c:when>
		    	<c:otherwise>
					<input type="radio" value="1" checked class="formField required" name="isMainProd"/><span>是</span>
					<input type="radio" value="0" class="formField required" name="isMainProd"/><span>否</span>
		    	</c:otherwise>
	    	</c:choose>
		</div>
			
		<label class="col-sm-2 labelForm">是否APP：</label>
		<div class="col-sm-4">
<%-- 			<input value="${vo. isApp}" type="text" placeholder="是否APP" class="formField required " id="isApp" name="isApp"> --%>
	    	<c:choose>
		    	<c:when test="${'1' eq vo.isApp}">
					<input type="radio" value="1" checked class="formField required" name="isApp"/><span>是</span>
					<input type="radio" value="0" class="formField required" name="isApp"/><span>否</span>
		    	</c:when>
		    	<c:when test="${'0' eq vo.isApp}">
					<input type="radio" value="1" class="formField required" name="isApp"/><span>是</span>
					<input type="radio" value="0" checked class="formField required" name="isApp"/><span>否</span>
		    	</c:when>
		    	<c:otherwise>
					<input type="radio" value="1" checked class="formField required" name="isApp"/><span>是</span>
					<input type="radio" value="0" class="formField required" name="isApp"/><span>否</span>
		    	</c:otherwise>
	    	</c:choose>
		</div>
	</div>
	<div class="clearfix formGroup">
		<label class="col-sm-2 labelForm">spid：</label>
		<div class="col-sm-4">
			<input value="${vo.spId}" type="text" placeholder="spid" class="formField " id="spId" name="spId">
		</div>
		<label class="col-sm-2 labelForm">spName：</label>
		<div class="col-sm-4">
			<input value="${vo.spName }" type="text" placeholder="spName" class="formField " id="spName" name="spName">
		</div>		
	</div>
	
</div>

<script src="<%=request.getContextPath()%>/pages/jsp/dpi/productInfo/productInfoForm.js"></script>
<%-- <script type="text/javascript" src="<%=request.getContextPath()%>/pages/jsp/dpi/industryOverview/industryOverview.js"></script> --%>
<script src="<%=request.getContextPath()%>/pages/jsp/dpi/dpiCommon.js"></script>
