<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="cxt" uri="http://www.bonc.com.cn/common/tag/cxt"%>
<!-- 编辑表单div -->
<div id="upForm">
	<div class="clearfix formGroup">
		<label class="col-sm-2 labelForm">分类ID：</label> 
		<div class="col-sm-4">
			<input value="${vo.lableType }" type="text" readonly="readonly" />
		</div>
		<label class="col-sm-2 labelForm">分类名称：</label> 
		<div class="col-sm-4">
			<input value="${vo.labelName }" type="text" readonly="readonly" />
		</div>
	</div>
	<div class="clearfix formGroup">
		<label class="col-sm-2 labelForm">一级标签ID：</label> 
		<div class="col-sm-4">
			<input value="${vo.labelCode1 }" type="text" readonly="readonly" />
		</div>
		<label class="col-sm-2 labelForm">一级标签名称：</label>
		<div class="col-sm-4">
			<input value="${vo.labelName1 }" type="text" readonly="readonly" />
		</div>
	</div>
	<div class="clearfix formGroup">
		<label class="col-sm-2 labelForm">二级标签ID：</label> 
		<div class="col-sm-4">
			<input value="${vo.labelCode2 }" type="text" readonly="readonly" />
		</div>
		<label class="col-sm-2 labelForm">二级标签名称：</label> 
		<div class="col-sm-4">
			<input value="${vo.labelName2 }" type="text" readonly="readonly" />
		</div>		
	</div>
	<div class="clearfix formGroup">
		<label class="col-sm-2 labelForm">标签编码：</label> 
		<div class="col-sm-4">
			<input value="${vo.labelCode }" type="text" readonly="readonly" />
		</div>
		<label class="col-sm-2 labelForm">标签名称：</label> 
		<div class="col-sm-4">
			<input value="${vo.joinLabelName }" type="text" readonly="readonly" />
		</div>		
	</div>			
</div>

