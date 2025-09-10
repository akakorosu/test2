<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="cxt" uri="http://www.bonc.com.cn/common/tag/cxt"%>
<!-- 编辑表单div -->
<div id="piForm">
	<input type="hidden" class="formField" name="id" value="${vo.id}"> 
	<input type="hidden" class="formField" name="author" value="${vo.author}">
	<input type="hidden" class="formField" name="opTime" value="${vo.opTime}">
	<input type="hidden" class="formField" name="labelCode1" value="${vo.labelCode1}">
	<input type="hidden" class="formField" name="labelCode2" value="${vo.labelCode2}">
	<input type="hidden" class="formField" name="labelCode" value="${vo.labelCode}">
	<input type="hidden" class="formField" name="joinLabelName" value="${vo.joinLabelName}">
	<div class="clearfix formGroup">
	<!-- <p style="color: red">提示：标签不能重复</p> -->
	</div>
	<div class="clearfix formGroup">
		<label class="col-sm-2 labelForm">分类名称：</label> 
		<div class="col-sm-4">
			<%-- <input value="${vo.labelName }" type="text" placeholder="分类名称" class="formField required" id="labelName" name="labelName" list="datalist"/>  --%>
			<select style='width:179px;'  class="formField" id="label_name" name="labelName" >
				<option>休闲娱乐</option>
				<option>生活类</option>
				<option>行业类</option>
				<option>工具类</option>
				<option>个性偏好</option>
				<option>其他</option>   
            </select>
		</div>
		<label class="col-sm-2 labelForm">分类ID：</label> 
		<div class="col-sm-4">
			<input value="${vo.lableType }" type="text" placeholder="分类ID"    class="formField required" id="lableType" name="lableType"  readonly>
		</div>	
	</div>
	<div class="clearfix formGroup">
		<label class="col-sm-2 labelForm">一级标签名称：</label>
		<div class="col-sm-4">
			<input value="${vo.labelName1 }" type="text" placeholder="一级标签名称"  onlyUrl="/productLabel/check,id,labelName1Param,labelName2Param|联合校验重复!" class="formField required " id="labelName1" name="labelName1Param">
		</div>
		<label class="col-sm-2 labelForm">二级标签名称：</label> 
		<div class="col-sm-4">
			<input value="${vo.labelName2 }" type="text" placeholder="二级标签名称"  onlyUrl="/productLabel/check,id,labelName1Param,labelName2Param|联合校验重复!" class="formField " id="labelName2" name="labelName2Param" readonly>
		</div>	
	</div>
<script src="<%=request.getContextPath()%>/pages/jsp/dpi/productLabel/productLabelForm.js"></script>
<script src="<%=request.getContextPath()%>/pages/jsp/dpi/dpiCommon.js"></script>