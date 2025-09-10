<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="cxt" uri="http://www.bonc.com.cn/common/tag/cxt"%>
<!-- 编辑表单div -->
<div id="upForm">
	<input type="hidden" class="formField" name="id" value="${obj.id}">
	<input type="hidden" class="formField" name="author" value="${obj.author}">
	<input type="hidden" class="formField" name="opTime" value="${obj.opTime}">
	<input type="hidden" class="formField" id='con' name="contentLabelName" value="${obj.contentLabelName}">
	<input type="hidden"  id="le" value="${obj.contentLabelLevel}">
	<input type="hidden" class="formField" id="contentLabelCode" name="contentLabelCode"  value="${obj.contentLabelCode}">
	<input type="hidden" class="formField" id="contentLabelCode1" name="contentLabelCode1" value="${obj.contentLabelCode1}">
	<input type="hidden" class="formField" id="contentLabelCode2" name="contentLabelCode2" value="${obj.contentLabelCode2}">
	<input type="hidden" class="formField" id="contentLabelCode3" name="contentLabelCode3" value="${obj.contentLabelCode3}">
	<input type="hidden" class="formField" id="contentLabelCode4" name="contentLabelCode4" value="${obj.contentLabelCode4}">
	<input type="hidden" class="formField" id="contentLabelCode5" name="contentLabelCode5" value="${obj.contentLabelCode5}">
	<input type="hidden" class="formField" id="contentLabelCode6" name="contentLabelCode6" value="${obj.contentLabelCode6}">
	<div class="clearfix formGroup">
		<label class="col-sm-2 labelForm">标签级别：</label> 
		<div class="col-sm-4">
			<select style='width:181px' id="contentLabelLevel" name='contentLabelLevel' class='formField required' >
 				 <option value =""></option>
 				 <option value ="1">1</option>
 				 <option value ="2">2</option>
  				 <option value="3">3</option>
 				 <option value="4">4</option>
 				 <option value="5">5</option>
 				 <option value="6">6</option>
			</select>
		</div>
	</div>
	<div class="clearfix formGroup">
		<label class="col-sm-2 labelForm">一级标签：</label> 
		<div class="col-sm-4">
			<input value="${obj.contentLabelName1 }" type="text" placeholder="一级标签"   id="contentLabelName1" onlyUrl="/content/check,id,contentLabelName1Param,contentLabelName2,contentLabelName3,contentLabelName4,contentLabelName5,contentLabelName6,contentLabelLevel|联合校验重复!" name="contentLabelName1Param"  readonly="readonly"  />
		</div>
		<label class="col-sm-2 labelForm">二级标签：</label> 
		<div class="col-sm-4">
			<input value="${obj.contentLabelName2 }" type="text" placeholder="二级标签"   id="contentLabelName2" onlyUrl="/content/check,id,contentLabelName1Param,contentLabelName2,contentLabelName3,contentLabelName4,contentLabelName5,contentLabelName6,contentLabelLevel|联合校验重复!" name="contentLabelName2" readonly="readonly" />
		</div>
	</div>
    <div class="clearfix formGroup">		
		<label class="col-sm-2 labelForm">三级标签：</label>
		<div class="col-sm-4">
			<input value="${obj.contentLabelName3 }" type="text" placeholder="三级标签"   id="contentLabelName3" onlyUrl="/content/check,id,contentLabelName1Param,contentLabelName2,contentLabelName3,contentLabelName4,contentLabelName5,contentLabelName6,contentLabelLevel|联合校验重复!" name="contentLabelName3" readonly="readonly" />
		</div>
		<label class="col-sm-2 labelForm">四️级标签：</label> 
		<div class="col-sm-4">
			<input value="${obj.contentLabelName4 }" type="text" placeholder="四️级标签"   id="contentLabelName4" onlyUrl="/content/check,id,contentLabelName1Param,contentLabelName2,contentLabelName3,contentLabelName4,contentLabelName5,contentLabelName6,contentLabelLevel|联合校验重复!" name="contentLabelName4" readonly="readonly" />
		</div>
	</div> 
	<div class="clearfix formGroup">	
		<label class="col-sm-2 labelForm">五级标签：</label> 
		<div class="col-sm-4">
			<input value="${obj.contentLabelName5 }" type="text" placeholder="五级标签"   id="contentLabelName5" onlyUrl="/content/check,id,contentLabelName1Param,contentLabelName2,contentLabelName3,contentLabelName4,contentLabelName5,contentLabelName6,contentLabelLevel|联合校验重复!" name="contentLabelName5" readonly="readonly" />
		</div>
		<label class="col-sm-2 labelForm">六级标签：</label>
		<div class="col-sm-4">
			<input value="${obj.contentLabelName6 }" type="text" placeholder="六级标签"  id="contentLabelName6" onlyUrl="/content/check,id,contentLabelName1Param,contentLabelName2,contentLabelName3,contentLabelName4,contentLabelName5,contentLabelName6,contentLabelLevel|联合校验重复!" name="contentLabelName6" readonly="readonly"/>
		</div>
	</div>
<script src="<%=request.getContextPath()%>/pages/jsp/dpi/contentLabel/contentLabelFormChange.js"></script>
<script src="<%=request.getContextPath()%>/pages/jsp/dpi/dpiCommon.js"></script>