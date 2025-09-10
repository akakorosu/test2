<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="cxt" uri="http://www.bonc.com.cn/common/tag/cxt"%>
<!-- 编辑表单div -->
<div id="upForm">
	<div class="clearfix formGroup">
		<label class="col-sm-2 labelForm">标签级别：</label> 
		<div class="col-sm-4">
			<input value="${obj.contentLabelLevel }" type="text" readonly="readonly" />
		</div>
		<label class="col-sm-2 labelForm">映射标签：</label>
		<div class="col-sm-4">
			<input value="${obj.contentLabelNameRelevance }" type="text" readonly="readonly" />
		</div>
	</div>
	<div class="clearfix formGroup">
		<label class="col-sm-2 labelForm">一级标签：</label>
		<div class="col-sm-4">
			<input value="${obj.contentLabelName1 }" type="text" readonly="readonly" />
		</div>
		<label class="col-sm-2 labelForm">二级标签：</label> 
		<div class="col-sm-4">
			<input value="${obj.contentLabelName2 }" type="text" readonly="readonly" />
		</div>
	</div>
	<div class="clearfix formGroup">
		<label class="col-sm-2 labelForm">三级标签：</label> 
		<div class="col-sm-4">
			<input value="${obj.contentLabelName3 }" type="text" readonly="readonly" />
		</div>
		<label class="col-sm-2 labelForm">四️级标签：</label>
		<div class="col-sm-4">
			<input value="${obj.contentLabelName4 }" type="text" readonly="readonly" />
		</div>
	</div>
	<div class="clearfix formGroup">
		<label class="col-sm-2 labelForm">五级标签：</label> 
		<div class="col-sm-4">
			<input value="${obj.contentLabelName5 }" type="text" readonly="readonly" />
		</div>
		<label class="col-sm-2 labelForm">六级标签：</label> 
		<div class="col-sm-4">
			<input value="${obj.contentLabelName6 }" type="text" readonly="readonly" />
		</div>
	</div>

</div>

